package by.man.weather.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import by.man.weather.annotation.MyEnableScheduling;
import by.man.weather.connection.WeatherConnection;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
@ComponentScan(basePackages = {"by.man.weather"}) 
@MyEnableScheduling
public class MongoAndWhetherConfiguration {
	
	 @Value("${spring.data.mongodb.uri}")
	 private String connectionString;
	 
	 @Value("${schedule.cron}")
	 private static String time;
	 
	 @Bean
	 @Lazy
	    public MongoClient mongoClient() { 
	        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
	        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
	        return MongoClients.create(MongoClientSettings.builder()
	                                                      .applyConnectionString(new ConnectionString(connectionString))
	                                                      .codecRegistry(codecRegistry)
	                                                      .build());
	    }

	 @Bean 
	 public WeatherConnection setWeatherConnection() {
		 WeatherConnection connection = new WeatherConnection();
		return connection;
	 }	
	 
	 @Bean
	    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	        return new PropertySourcesPlaceholderConfigurer();
	    }
}
