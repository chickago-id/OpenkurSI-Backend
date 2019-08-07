package backend;

import backend.model.User;
import backend.model.UserDetail;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.http.MediaType;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller("/update-profil")
public class ProfilController {

    private UserRepository userRepository;
    private UserDetailRepository userDetailRepository;

    public ProfilController(UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String updateProfile(@Body UserDetail userDetail, Authentication authentication) 
    {
        try {

            if(authentication == null) {
                return "Hello";
            } else {
                Map<String, String> errorMessage = new HashMap<String, String>();
                errorMessage.put("status", "error");
                errorMessage.put("message", "Unauthorized user");

                return new Gson().toJson(errorMessage);
            }

        } catch(Exception e) {
            Map<String, String> errorMessage = new HashMap<String, String>();
            errorMessage.put("status", "error");
            errorMessage.put("message", e.getMessage());

            return new Gson().toJson(errorMessage);
        }
    }
}