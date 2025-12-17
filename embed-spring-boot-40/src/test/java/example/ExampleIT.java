package example;

import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.data.mongodb.test.autoconfigure.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureDataMongo
@SpringBootTest()
@EnableAutoConfiguration
@DirtiesContext
class ExampleIT {
    @Test
    void example(@Autowired final MongoTemplate mongoTemplate) {
        Assertions.assertNotNull(mongoTemplate.getDb());
        ArrayList<String> collectionNames = mongoTemplate.getDb()
          .listCollectionNames()
          .into(new ArrayList<>());
        assertThat(collectionNames).isEmpty();
    }
}
