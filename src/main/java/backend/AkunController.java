package backend;

import backend.model.User;
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

import org.apache.commons.validator.routines.EmailValidator;

@Controller("/buat-akun")
public class AkunController {

    private UserRepository userRepository;

    public AkunController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Post("/")
    public String register(@Body String userInput) {

        // get user input
        JsonObject data = new JsonParser().parse(userInput).getAsJsonObject();
        String username = data.get("username").getAsString();
        String email = data.get("email").getAsString();
        String nama_lengkap = data.get("nama_lengkap").getAsString();
        String password = data.get("password").getAsString();

        // ora error critane neng awal
        Integer errorCond = 0;

        // error message bag
        ArrayList<String> errorBag = new ArrayList<String>();
        String errorText = "";

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

        // TODO validasi email boleh dipakai

        if(nama_lengkap.isEmpty()) {
            errorBag.add("Nama lengkap harus diisi.");
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
            userData.setRole("Admin");
            
            // simpan data ke db user
            userRepository.save(userData);
            
            // TODO simpan data ke db user_detail
            
            
            ArrayList<Map> returnData = new ArrayList<Map>();

            Map<String, Long> userIdProp = new HashMap<String, Long>();
            userIdProp.put("id", userData.getId());
            returnData.add(userIdProp);

            Map<String, String> userUsernameProp = new HashMap<String, String>();
            userUsernameProp.put("username", userData.getUsername());
            returnData.add(userUsernameProp);

            Map<String, String> userEmailProp = new HashMap<String, String>();
            userEmailProp.put("email", userData.getEmail());
            returnData.add(userEmailProp);

            Map<String, String> userRoleProp = new HashMap<String, String>();
            userRoleProp.put("role", userData.getRole());
            returnData.add(userRoleProp);

            // kembalikan pesan sukses
            return new Gson().toJson(returnData);
        }
    }
}