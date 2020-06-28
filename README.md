The Project invokes APIs in https://bpdts-test-app-v3.herokuapp.com/ to get list of users in a city and within given miles of given lat and lon.

The projects uses:
* Springboot
* Lombok
* RestTemplate

How to run:

Start the server by running mvn spring-boot:run or Run Starter class in any IDE.
Once the server is up, goto localhost:8080/rest/users to see results.
By default, the API use London city with London lat and lon with 60mile distance.

sample url: http://localhost:8080/rest/users (By Default uses city as London, miles as 60, lat and lon on London)
Full url with supported params: http://localhost:8080/rest/users?city=Kax&lat=51.5074&lon=-0.1278&distance=60
