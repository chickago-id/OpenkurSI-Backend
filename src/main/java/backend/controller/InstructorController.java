package backend.controller;

import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import backend.model.UserDetail;
import backend.model.UserDetailResponse;
import backend.repository.UserDetailRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.validation.Validated;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 @Validated
 @Controller("/list")
 @Secured("isAnonymous()")

 public class InstructorController {
 
     private UserDetailRepository userDetailRepository;
     public InstructorController (UserDetailRepository userDetailRepository) {
         this.userDetailRepository = userDetailRepository;
     }

     @Get("/instructor")
     @Secured("isAnonymous()")
     public String getInstructor(@Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 UserDetailResponse response = new UserDetailResponse("error", "You must sign in first.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     List <UserDetail> result = userDetailRepository.getInstructor();
                     if (result != null) {
                         UserDetailResponse response = new UserDetailResponse("ok", "List Data Instruktur :", result);
                         return new Gson().toJson(response); 
                     } else {
                         UserDetailResponse response = new UserDetailResponse("error", "Failed get data.", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     UserDetailResponse response = new UserDetailResponse("error", "You does not allow access this page.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             UserDetailResponse response = new UserDetailResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Get("/siswa")
     @Secured("isAnonymous()")
     public String getSiswa(@Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 UserDetailResponse response = new UserDetailResponse("error", "You must sign in first.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     List <UserDetail> result = userDetailRepository.getInstructor();
                     if (result != null) {
                         UserDetailResponse response = new UserDetailResponse("ok", "List Data Peserta :", result);
                         return new Gson().toJson(response); 
                     } else {
                         UserDetailResponse response = new UserDetailResponse("error", "Failed get data.", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     UserDetailResponse response = new UserDetailResponse("error", "You does not allow access this page.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             UserDetailResponse response = new UserDetailResponse("error", message);
             return new Gson().toJson(response);
         }
     }
 }