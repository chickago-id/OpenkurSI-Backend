package backend;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;

import java.util.List;

@Validated
@Controller("/materi")
public class MateriController {

    @Get("/")
    public String hello() {
        return "hello";
    }
}
