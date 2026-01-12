package example;

import com.mongodb.assertions.Assertions;
import de.flapdoodle.embed.mongo.spring.autoconfigure.MongodWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest()
@ExtendWith(SpringExtension.class)
class ExampleIT {
    @Test
    void example(@Autowired final MongoTemplate mongoTemplate) {
        Assertions.assertNotNull(mongoTemplate.getDb());
        ArrayList<String> collectionNames = mongoTemplate.getDb()
          .listCollectionNames()
          .into(new ArrayList<>());
        assertThat(collectionNames).isEmpty();
    }

    @Test
    void checkBeans(@Autowired final ApplicationContext applicationContext) {
        assertThat(applicationContext.getBeanNamesForType(MongodWrapper.class)).isNotEmpty();
        assertThat(applicationContext.getBean(MongodWrapper.class)).isNotNull();
    }
}
