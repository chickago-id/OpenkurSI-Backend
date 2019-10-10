package backend.controller;

import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import backend.model.User;
import backend.repository.UserRepository;
import backend.response.UserManagementResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.validation.Validated;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 @Validated
 @Controller("/userlist")
 @Secured("isAnonymouse()")

 public class UserManagementController {
     private UserRepository userRepository;
     public UserManagementController (UserRepository userRepository){
         this.userRepository = userRepository;
     }

     @Get("/")
     @Secured("isAnonymous()")
     public String index(@Nullable Authentication authentication){
         try {
             if (authentication == null){
                 UserManagementResponse response = new UserManagementResponse("error", "You're not access, please login first.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     List <User> result = userRepository.getAll();
                     if (result != null) {
                         UserManagementResponse response = new UserManagementResponse("ok", "Get data is successfull.", result);
                         return new Gson().toJson(response);
                     } else {
                         UserManagementResponse response = new UserManagementResponse("error", "Sorry, failed get data.", result);
                         return new Gson().toJson(response);
                     }
                     
                 } else {
                     UserManagementResponse response = new UserManagementResponse("error", "Sorry, you're not allow access.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             UserManagementResponse response = new UserManagementResponse("error", message);
             return new Gson().toJson(response);
         }
     }
}