package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@MicronautTest
class SerializeEmptyPropertiesTest {

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public SerializeEmptyPropertiesTest(ObjectMapper objectMapper, @Client("/") HttpClient httpClient) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
    }

    @Test
    void testJacksonSerializationWithEmptyProperties() throws IOException {
        var testModel = new TestBean();

        var testModelStr = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(testModel);

        Assertions.assertEquals("{\"str\":\"\",\"strArr\":[]}", testModelStr);
    }

    @Test
    void testMicronautSerializationWithEmptyProperties() throws IOException {
        var testModel = new TestBean();

        var testModelStr = objectMapper.writeValueAsString(testModel);

        Assertions.assertEquals("{\"str\":\"\",\"strArr\":[]}", testModelStr);
    }

    @Test
    void testControllerResponseWithEmptyProperties() {
        var request = HttpRequest.GET("/test");
        var response = httpClient.toBlocking().exchange(request, String.class);
        var body = response.getBody(String.class).orElseThrow();

        Assertions.assertEquals(200, response.getStatus().getCode());
        Assertions.assertEquals("{\"str\":\"\",\"strArr\":[]}", body);
    }
}
