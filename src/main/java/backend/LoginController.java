package backend;

import backend.model.User;
import backend.model.UserDetail;
import backend.helpers.MD5;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.http.MediaType;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.validator.routines.EmailValidator;

@Controller("/login")
public class LoginController {

    private UserRepository userRepository;
    private UserDetailRepository userDetailRepository;

    public LoginController(UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }

    @Post("/")
    public String login(@Body String userInput) {
        // get user input
        JsonObject data = new JsonParser().parse(userInput).getAsJsonObject();
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();

        Optional<User> userData = userRepository.findByEmail(email);

        if(userData != null) {

            // get user value
            User user = userData.get();

            // generate password hash
            String passwordHash = MD5.getMd5(password);
            // compare password
            if(passwordHash.equals(user.getPassword())) {
                // TODO set jwt token
                // TODO return it
                // return new Gson().toJson(user.toString());
                user.setPassword("");
                return new Gson().toJson(user);
            } else {
                // generate error message
                Map<String, String> errorMessage = new HashMap<String, String>();
                errorMessage.put("message", "Email/Password salah.");
                errorMessage.put("status", "error");
                return new Gson().toJson(errorMessage);
            }
            

        } else {
            // generate error message
            Map<String, String> errorMessage = new HashMap<String, String>();
            errorMessage.put("message", "Akun tidak ditemukan.");
            errorMessage.put("status", "error");
            return new Gson().toJson(errorMessage);
        }

    }
}