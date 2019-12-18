package by.man.weather.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.mongodb.MongoClientException;
import com.mongodb.client.MongoCollection;
import by.man.weather.entity.example.Structure;


import com.mongodb.client.MongoClient;

@Repository
public class MongoRepository implements IRepository{
	
	 private static final Logger logger = LogManager.getLogger(MongoRepository.class);
	
	 @Value("${spring.data.mongodb.database}")
	 private String connectionDatabase;
	 
	 @Value("${spring.data.mongodb.collection_name}")
	 private String connectionCollection;
	
	@Autowired
    private MongoClient client;
	private MongoCollection<Structure> personCollection;
	
	@PostConstruct
    void init() {
		try {
	         
	      personCollection = client.getDatabase(connectionDatabase)
	    		  				   .getCollection(connectionCollection, Structure.class);
		} catch (MongoClientException e) {
			logger.debug("Unable to connect to database. StackTrace: " + e);
			new RepositoryExeption("Unable to connect to database", e);
		} 
    }
	
	@Override
    public Structure save(Structure info) {
    	TimeZone tz = TimeZone.getTimeZone("UTC");
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"); 
    	df.setTimeZone(tz);
    	String asISO = df.format(new Date());
    	info.setResponse_date(asISO);
    	info.setId((new ObjectId().toString()));
        personCollection.insertOne(info);
        
        logger.info("Structure file upload: " + info.toString());
        
        return info;
    }

}