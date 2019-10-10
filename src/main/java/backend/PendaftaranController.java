package backend;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import com.google.gson.Gson;

@Controller("/pendaftaran")
public class PendaftaranController {

    @Post("/")
    public String register(@Body String userInput) {

        return new Gson().toJson(userInput);

    }
}
