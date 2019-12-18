# weather
This application periodically makes requests to the OpenWeatherMap API and
saves responses to a MongoDB database.
-----------------------------------

![Image alt](https://github.com/dfManiuk/weather/blob/master/src/main/resources/Schema.png)

To configure the application, you must create the application.properties file with the following scheme:

#mongodb
* spring.data.mongodb.collection_name=collection name
* spring.data.mongodb.host=host adress
* spring.data.mongodb.port=27017
* spring.data.mongodb.database=base name
* spring.data.mongodb.uri=mongodb+srv://<mongoDB_nickname>:<mongoDB_password>@cluster0-5wkpr.azure.mongodb.net/

#time
* schedule.cron=cron expression        
* #this is example=0 0/1 * 1/1 * ? * - frequency of receiving and downloading information 1 minute
 
#openWeather
* openweathermap.api_key=openWeather api key
* openweathermap.api.city_id=openWeather sity id
* openweathermap.api.units=metric 
* #For temperature in Fahrenheit use units=imperial, for temperature in Celsius use units=metric
* openweathermap.api.base_url=http://api.openweathermap.org/data/2.5/

Place the configuration file in the resources directory

For further reference, please consider the following sections:
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/maven-plugin/)
* [Apache TomCat ](http://tomcat.apache.org/tomcat-9.0-doc/index.html)

After creating the configuration file, you need to compile using Maven.
The package will be packed into a war file.
To load the application into the container of sevlets Apache Tomcat, you need to specify the url, path, username and password in the pom file.

To load into the container on the local machine, you need to uncomment the corresponding setting in the pom file and comment out the setting for remote loading.
For configuring the Apache Tomcat, see the link above.

After loading the servlet into the container, the application starts automatically.
Stopping the application is provided by Apache Tomcat. Restarting can also be done by  Apache Tomcat.

You can change the settings by reloading the application or in the corresponding directory of the tomcat in the folder / <your_path> / WEB-INF / classes / application

MongoDB document example:

![Image alt](https://github.com/dfManiuk/weather/blob/master/src/main/resources/T6.png)
