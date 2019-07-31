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

        // username sudah ada ato belum
        List<User> userByUsername = userRepository.findByUsername(username);

        if(userByUsername.get(0) != null) {
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
        List<User> userByEmail = userRepository.findByEmail(email);
        if(userByEmail.get(0) != null) {
            errorBag.add("Email sudah dipakai.");
            errorCond += 1; // nek kosong brati error
        }

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
            userData.setRole("Peserta");
            
            // simpan data ke db user
            userRepository.save(userData);

            // TODO simpan data ke db user_detail
            
            
            Map<String, String> returnData = new HashMap<String, String>();
            returnData.put("message", "Berhasil membuat akun");
            returnData.put("status", "ok");
            returnData.put("data", userData.toString());

            // kembalikan pesan sukses
            return new Gson().toJson(returnData);
        }
    }
}