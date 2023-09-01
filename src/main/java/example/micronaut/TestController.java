package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class TestController {

    @Get("/test")
    public TestBean getTest() {
        return new TestBean();
    }
}
