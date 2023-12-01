package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {
    private ClassLoader cl = JsonTest.class.getClassLoader();

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("orderjs.json")
        )) {

            ObjectMapper objectMapper = new ObjectMapper();
            Order order = objectMapper.readValue(reader, Order.class);
            assertThat(order.getName()).isEqualTo("Anna");
            assertThat(order.getAddress()).isEqualTo("Lenina street");
            assertThat(order.getFlat()).isEqualTo("55");
            assertThat(order.getGrocery().getBread()).isEqualTo("Stolichny");
            assertThat(order.getGrocery().getSnaks()).isEqualTo("Chips");
            assertThat(order.getGrocery().getDrink()).isEqualTo("Byratino");
        }
    }

}

