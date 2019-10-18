package backend;

import backend.model.User;
import backend.model.UserDetail;
import backend.repository.UserRepository;
import backend.helpers.MD5;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.validator.routines.EmailValidator;

@Controller("/buat-akun")
public class AkunController {

    private UserRepository userRepository;
    private UserDetailRepository userDetailRepository;

    public AkunController(UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String register(@Body String userInput) {

        // get user input
        JsonObject data = new JsonParser().parse(userInput).getAsJsonObject();
        String username = data.get("username").getAsString();
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();

        // ora error critane neng awal
        Integer errorCond = 0;

        // error message bag
        ArrayList<String> errorBag = new ArrayList<String>();
        String errorText = "";

        // username sudah ada ato belum
        Optional<User> userByUsername = userRepository.findByUsername(username);

        if(userByUsername.isPresent()) {
            errorBag.add("Username sudah dipakai.");
            errorCond += 1; // nek kosong brati error
        }

        if(username.isEmpty()) {
            errorBag.add("Username harus diisi.");
            errorCond += 1; // nek kosong brati error
        }
        if(email.isEmpty()) {
            errorBag.add("Email harus diisi.");
            errorCond += 1; // nek kosong brati error
        }
        boolean validEmail = EmailValidator.getInstance().isValid(email);
        if(!validEmail) {
            errorBag.add("Email tidak valid.");
            errorCond += 1; // nek kosong brati error
        }

        // validasi email boleh dipakai
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if(userByEmail.isPresent()) {
            errorBag.add("Email sudah dipakai.");
            errorCond += 1; // nek kosong brati error
        }

        if(password.isEmpty()) {
            errorBag.add("Password harus diisi.");
            errorCond += 1; // nek kosong brati error
        }
        if(password.length() < 6) {
            errorBag.add("Password minimal 6 karakter.");
            errorCond += 1; // nek kosong brati error
        }

        // jika error
        if(errorCond > 0) {

            for(String err:errorBag) {
                errorText += err + "<br>";
            }

            // return pesan error
            Map<String, String> errorMessage = new HashMap<String, String>();
            errorMessage.put("message", errorText);
            errorMessage.put("status", "error");
            return new Gson().toJson(errorMessage);

        } else { // nek ora error

            // generate password hash
            String passwordHash = MD5.getMd5(password);

            // populate user data
            User userData = new User();
            userData.setUsername(username);
            userData.setEmail(email);
            userData.setPassword(passwordHash);
            userData.setRole("Peserta");
            
            // simpan data ke db user
            userRepository.save(userData);

            // generate data user_detail
            UserDetail userDetail = new UserDetail();
            userDetail.setId_user(userData.getId());
            userDetail.setEmail(email);

            // simpan data ke db user_detail
            userDetailRepository.save(userDetail);

            JsonObject userProfile = new JsonObject();
            userProfile.addProperty("id", userData.getId());
            userProfile.addProperty("username", userData.getUsername());
            userProfile.addProperty("email", userData.getEmail());
            userProfile.addProperty("role", userData.getRole());
            
            Map<String, String> returnData = new HashMap<String, String>();
            returnData.put("message", "Berhasil membuat akun");
            returnData.put("status", "ok");
            returnData.put("data", new Gson().toJson(userProfile));

            // kembalikan pesan sukses
            return new Gson().toJson(returnData);
        }
    }
}