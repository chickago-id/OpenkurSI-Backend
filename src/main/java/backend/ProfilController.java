package backend;

import backend.model.UserDetail;
import backend.model.UserDetailResponse;
import backend.repository.UserRepository;

import backend.repository.UserDetailRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller("/profil")
public class ProfilController {

    private UserDetailRepository userDetailRepository;

    public ProfilController(UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }
    @Get("/")
    @Secured("isAnonymous()")
    public String getProfile(Authentication authentication)
    {
        try {

            if(authentication == null) {
                Map<String, String> errorMessage = new HashMap<String, String>();
                errorMessage.put("status", "error");
                errorMessage.put("message", "Unauthorized user");

                return new Gson().toJson(errorMessage);
            } else {

                Object userId = authentication.getAttributes().get("userId");

                Optional<UserDetail> userDetail = userDetailRepository.findByUserId((Long) userId);

                if(userDetail.isPresent()) {
                    UserDetail detail = userDetail.get();
                    detail.setUserPassword("");

                    UserDetailResponse response = new UserDetailResponse(
                        "OK",
                        "GET DATA PROFILE SUCCESS",
                        detail
                    );
                    Map<String, String> returnData = new HashMap<String, String>();
                    returnData.put("status", "ok");
                    returnData.put("message", "Profil");
                    returnData.put("data", new Gson().toJson(detail));
                    
                    return new Gson().toJson(response);

                } else {
                    ArrayList detail = new ArrayList();

                    Map<String, String> returnData = new HashMap<String, String>();
                    returnData.put("status", "ok");
                    returnData.put("message", "Profil");
                    returnData.put("data", new Gson().toJson(detail));

                    return new Gson().toJson(detail);

                }

                
            }

        } catch(Exception e) {
            Map<String, String> errorMessage = new HashMap<String, String>();
            errorMessage.put("status", "error");
            errorMessage.put("message", e.getMessage());

            return new Gson().toJson(errorMessage);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String updateProfile(@Body UserDetail userDetail, Authentication authentication) 
    {
        try {

            if(authentication == null) {
                Map<String, String> errorMessage = new HashMap<String, String>();
                errorMessage.put("status", "error");
                errorMessage.put("message", "Unauthorized user");

                return new Gson().toJson(errorMessage);
            } else {
                UserDetail result = userDetailRepository.update(userDetail);

                if(result != null) {
                    
                    Map<String, String> returnData = new HashMap<String, String>();
                    returnData.put("status", "ok");
                    returnData.put("message", "Berhasil memperbarui profil");
                    returnData.put("data", new Gson().toJson(userDetail));

                    return new Gson().toJson(returnData);
                } else {
                    Map<String, String> errorMessage = new HashMap<String, String>();
                    errorMessage.put("status", "error");
                    errorMessage.put("message", "Data tidak ditemukan.");

                    return new Gson().toJson(errorMessage);
                }
            }

        } catch(Exception e) {
            Map<String, String> errorMessage = new HashMap<String, String>();
            errorMessage.put("status", "error");
            errorMessage.put("message", e.getMessage());

            return new Gson().toJson(errorMessage);
        }
    }
}