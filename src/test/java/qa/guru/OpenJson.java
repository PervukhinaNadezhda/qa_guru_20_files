package qa.guru;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qa.guru.model.Model;

import java.io.File;

public class OpenJson {
    File file = new File("src/test/resources/test.json");
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void OpenJsonTest() throws Exception {
        Model test = mapper.readValue(file, Model.class);

        Assertions.assertEquals("fruits basket", test.getName());
        Assertions.assertEquals(7, test.getFruitQuantity());
        Assertions.assertEquals("apple", test.getFruits().get(0));
        Assertions.assertEquals("orange", test.getFruits().get(1));
        Assertions.assertEquals("pinapple", test.getFruits().get(2));
    }
}