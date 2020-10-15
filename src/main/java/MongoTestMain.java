import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoTestMain {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("students");
        FindIterable<Document> documents = collection.find().sort(new Document("apellido", 1));
        MongoCursor<Document> cursor = documents.iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            System.out.println(document.toJson());
        }
        collection.insertOne(new Document("nombre", "Patricia").append("apellido", "Duarte").append("_id", "123"));
        // db.students.insert({"nombre":"Patricia","apellido":"Duarte"})
        collection.updateOne(new Document("_id", "123"), new Document("$set", new Document("apellido", "Lopez")));
        //db.students.update({"_id": "123"}, {$set: {"apellido":"Lopez"}})
    }
}
