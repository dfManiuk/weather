package by.man.weather;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import org.bson.Document;
import org.junit.Test;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import by.man.weather.entity.example.Structure;
import junit.framework.TestCase;

public class ImportMongoDBTest extends TestCase {

	 
	@Test
    public void testStartAndStopMongoImportAndMongod() throws UnknownHostException, IOException {
		 int port = 27017;
		 String ip = "http://api.openweathermap.org/data/2.5/";
        String defaultHost = "mongodb+srv://di13man:engineer13@cluster0-5wkpr.azure.mongodb.net/";
        String database = "openweathermap";
        String collection = "testT7";
        ConnectionString connString = new ConnectionString(
        	    defaultHost+ "test?w=majority"
        	);
        try  {
        	MongoClientSettings settings = MongoClientSettings.builder()
        		    .applyConnectionString(connString)
        		    .retryWrites(true)
        		    .build();
        	MongoClient mongoClient = MongoClients.create(settings);
        	MongoDatabase databaseTest = mongoClient.getDatabase(database);

        	MongoCollection<Document> dCollection = databaseTest.getCollection(collection);
        	
        	FindIterable<Document> findIterable = dCollection.find(new Document());
        	
        	 FindIterable<Document> strFindIterable = dCollection.find();
        	 for (Document doc : strFindIterable) {
                 System.out.println(doc.getString("response_date"));
        	 }
      
        	 List< Structure> structures = ((Document) dCollection).getList("_id", Structure.class);
        	System.out.println(structures.toString() + "@@@@@@@@@@@@@@@@@@@@@@");
	
        } catch (Exception e) {
			// TODO: handle exception
		}
	}
}