# RestAssuredProject

Merhabalar,
Proje bir web servis test otomasyon projesidir.

Projede kullandığım bağımlılıklar;
rest assured, junit, okhttp, gauge...

files/spetcs gauge kütüphanesi sayesinde eklenebilen spec dosyasını ekleyip test senaryolarımızı yazabiliyoruz.

src/test/java/steps içerisinde BaseSteps class'ı içerisinde projede spec dosyalarında kullandığımız test senaryo adımları yer alıyor. 
Buradaki test adımlarını parametrik şekilde ve @Step anotasyonunu method başına yazarak oluşturuyoruz.
BaseSteps içerisindeki parametrik test adımları birçok test senaryosu yazarken kullanılabiliyor.

src/test/java/paths içerisinde Route class'ı içerisinde  web servise istek atarken kullandığımız token, tokenin türü ve base path bilgileri 

src/test/java/utils içerisinde Utilities class'ı içerisinde projede sürekli kullanılabilecek methodları bulundurmak istedim. Örnek olarak içerisinde rastgele bir email üreten method mevcuttur.

files/spects dosyasında bulunan exampleHttpRequest.spec dosyasına değinecek olursak. Burada örneğin create jObject adımı ile bir json nesnesi oluşturuluyor.
* jObject put "name" "value" adımında ise json nesnesi içerisine key value dediğimiz anahtar ve değerler giriliyor
* Send "http method" request To "url"   bu adımda ise isteğin gideceği adres ve hangi method türünde gideceği parametrik olarak ile yazılmıştır.
exampleHttpRequest.spec dosyasındaki senaryoları ve adımları inceleyerek daha fazla bilgi sahibi olabilirsiniz 
Proje içerisindeki diğer adımları da şu şekilde sıralayabiliriz;
1- Veritabanına bağlanma adımı
2- Veritabanına sorgu atma adımı
3- Veritabanında sorgu sonrası dönen değer ile response'daki değerin karşılaştırılması adımı
4- Response'daki değerin json path ile alınıp hashmap'e eklenmesi
5- Hashmapteki değeri response body'e eklemek
 

hi,
The project is a web service test automation project

Dependencies I use in the project: rest assured, junit, okhttp, gauge...

Thanks to the files/spetcs gauge library, we can add the spec file and write our test scenarios.

In the BaseSteps class in src/test/java/steps, there are test scenario steps that we use in the spec files of the project.
We create the test steps here parametrically and by writing the @Step annotation per method.
Parametric test steps in BaseSteps can be used when writing many test cases.

I wanted to have methods that can be used continuously in the project in the Utilities class in src/test/java/utils. For example, there is a method that generates a random email.

Let's talk about the example Http Request.spec file in the files/specs file.
Here, for example, a json object is created with the create jObject step.
* In the jObject put "name" "value" step, the key and values that we call key value are entered into the json object
* Send "http method" request To "url", in this step, the address to which the request will go and which method type it will go to are parametrically written.
You can learn more by reviewing the scenarios and steps in the exampleHttpRequest.spec file

We can list the other steps in the project as follows;
1- Step of connecting to database
2- Step of querying the database
3- The step of comparing the return value after the query in the database with the value in the response
4- Taking the value in the response with json path and adding it to the hashmap
5- Adding the value in the hashmap to the response body
