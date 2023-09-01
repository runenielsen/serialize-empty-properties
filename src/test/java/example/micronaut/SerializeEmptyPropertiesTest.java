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
        var testModel = new TestBean("", new String[0]);

        var testModelStr = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(testModel);

        Assertions.assertEquals("{\"str\":\"\",\"strArr\":[]}", testModelStr);
    }

    @Test
    void testJacksonSerializationAndDeserializationWithEmptyProperties() throws IOException {
        var jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        var testModel = new TestBean("", new String[0]);

        var testModelStr = jacksonObjectMapper.writeValueAsString(testModel);
        var testModelCopy = jacksonObjectMapper.readValue(testModelStr, TestBean.class);

        Assertions.assertEquals(testModel, testModelCopy);
    }

    @Test
    void testMicronautSerializationWithEmptyProperties() throws IOException {
        var testModel = new TestBean("", new String[0]);

        var testModelStr = objectMapper.writeValueAsString(testModel);

        Assertions.assertEquals("{\"str\":\"\",\"strArr\":[]}", testModelStr);
    }

    @Test
    void testMicronautSerializationAndDeserializationWithEmptyProperties() throws IOException {
        var testModel = new TestBean("", new String[0]);

        var testModelStr = objectMapper.writeValueAsString(testModel);
        var testModelCopy = objectMapper.readValue(testModelStr, TestBean.class);

        Assertions.assertEquals(testModel, testModelCopy);
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
