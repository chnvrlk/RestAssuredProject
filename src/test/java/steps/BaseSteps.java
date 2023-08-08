package steps;

import com.thoughtworks.gauge.Step;
import okhttp3.*;
import org.jooq.impl.DSL;
import org.json.JSONObject;
import org.junit.Assert;
import utils.Utilities;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static paths.Route.*;


public class BaseSteps {

    JSONObject obj = null;
    okhttp3.Response response = null;
    Request request = null;
    OkHttpClient client = null;
    Connection connection = null;

    HashMap<String, String> responseHashMap = new HashMap<String, String>();

    public Request requestBuildWithoutBody(String path,String method, RequestBody body) {
        request = new Request.Builder()
                .url(path)
                .method(method, body)
                .addHeader(AUTH, TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        return request;
    }

    public Request createRequestBody(String method,String url) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody content = RequestBody.create(mediaType, String.valueOf(obj));
        request = new Request.Builder()
                .url(url)
                .method(method, content)
                .addHeader(AUTH, TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        return request;
    }

    public OkHttpClient clientBuild(){
        client = new OkHttpClient().newBuilder().build();
        return client;
    }

    @Step("Create jObject")
    public void jObjectBuild() {
        obj = new JSONObject();
        System.out.println("jObject olusturuldu.");
    }

    @Step("jObject put <key> <value>")
    public void jObjectPut(String key,String value) {
        obj.put(key, value);
    }


    @Step("Send <method> Request To <url>")
    public void sendPostRequest(String method,String url) throws IOException {

        client = clientBuild();

        if(method.contains("PUT") || method.contains("POST")) {
            request = createRequestBody(method, url);
        }else if(method.contains("GET") || method.contains("DELETE")){
            request = requestBuildWithoutBody(url,method,null);
        }else{
            System.out.println("HATAAAAA-----------");
        }

        response = client.newCall(request).execute();

    }

    @Step("Check Status Code <code>")
    public void statusCodeCheck(int code){
        Assert.assertEquals(response.code(),code);
    }

    @Step("Check Body Content <content>")
    public void bodyContentCheck(String content) throws IOException {
        String responseBody = response.body().string();
        System.out.println("Response :"+responseBody);
        Assert.assertTrue(responseBody.contains(content));
    }

    @Step("Generate random email and append to json with key <key>")
    public void randomEmailCreateAndAddJson(String key) {
        obj.put(key, Utilities.generateEmailWithTimeStamp());
    }

    @Step("Add value In the response to hashmap responseKey<key> hashMapKey<hKey>")
    public void addValueInTheResponseToTheHashmap(String key, String hKey) {
        Map jsonMap = (Map) response.body();
        String keyString = (String) jsonMap.get(key);
        responseHashMap.put(hKey,key);
        System.out.println(keyString);
    }

    @Step("Add value In the hashMap to requestBody getHashmap<hKey> requestKey<key>")
    public void addValueInTheHashMapToTheRequest(String hKey, String key) {

        String hashMapValue= responseHashMap.get(hKey);
        jObjectPut(key,hashMapValue);

    }

    @Step("Database connection with parameters url<url> dbname<dbName> userName<userName> password<password>")
    public void connectionDatabase(String url, String dbName, String userName, String password){


        try {

            //Create an object for Driver Class
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).getDeclaredConstructor().newInstance();

            //Connect to example Database
            connection = DriverManager.getConnection(url+dbName, userName, password);

                //Verify the connection and execute SQL Statements
                if (!connection.isClosed()) {
                    System.out.println("Successfully connected to Database");
                }

            } catch(Exception e) {
                e.printStackTrace();
            }

    }

    @Step("Query <query> from database")
    public void queryFromDatabase(String query) throws SQLException {

        if (!connection.isClosed()) {
            //Fire SQL Selection Statements
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            JSONObject result = new JSONObject(DSL.using(connection)
                    .fetch(resultSet)
                    .formatJSON());

        }else{
            throw new SQLException();
        }

    }

    @Step("Query <query> from database and check jsonPath data <jsonPath> <value>")
    public void queryFromDatabase(String query, String jsonPath, String value) throws SQLException {

        if (!connection.isClosed()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            JSONObject result = new JSONObject(DSL.using(connection)
                    .fetch(resultSet)
                    .formatJSON());

            String jsonPathString = result.get(jsonPath).toString();
            Assert.assertEquals(value,jsonPathString);

        }else{
            throw new SQLException();
        }

    }



}