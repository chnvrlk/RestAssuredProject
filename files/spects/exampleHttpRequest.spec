Go Rest
=================================

Put Request
---------------------------------
* Create jObject
* jObject put "name" "comolokkosssssdaasdsaaas"
* Generate random email and append to json with key "email"
* jObject put "status" "active"
* Send "PUT" Request To "https://gorest.co.in/public/v2/users/4020631"
* Check Status Code "200"
* Check Body Content "name"


Get Request
----------------------------------
* Send "GET" Request To "https://gorest.co.in/public/v2/users"
* Check Status Code "200"
* Check Body Content "email"


Post Request
----------------------------------
* Create jObject
* jObject put "name" "comolokkos121sadas"
* jObject put "gender" "male"
* Generate random email and append to json with key "email"
* jObject put "status" "active"
* Send "POST" Request To "https://gorest.co.in/public/v2/users"
* Check Status Code "201"
* Check Body Content "name"


Delete Request
-----------------------------------
* Send "DELETE" Request To "https://gorest.co.in/public/v2/users/3734"


Get Request (One User)
-----------------------------------
* Send "GET" Request To "https://gorest.co.in/public/v2/users/4020631"
* Check Status Code "200"
* Check Body Content "4020631"
