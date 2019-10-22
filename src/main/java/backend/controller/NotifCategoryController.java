package backend.controller;

import java.util.List;

import com.google.gson.Gson;

import backend.model.NotifCategory;
import backend.repository.NotifCategoryRepository;
import backend.response.NotifCategoryResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.validation.Validated;
import io.reactivex.annotations.Nullable;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 @Validated
 @Controller("/notifcategory")
 @Secured("isAnonymous()")

 public class NotifCategoryController {
 
     private NotifCategoryRepository notifCategoryRepository;
     public NotifCategoryController (NotifCategoryRepository notifCategoryRepository) {
         this.notifCategoryRepository = notifCategoryRepository;
     }

     @Get("/")
     public String index(@Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 NotifCategoryResponse response = new NotifCategoryResponse("error", "You are not authentication.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     List<NotifCategory> result = notifCategoryRepository.findAll();
                     if (result != null) {
                         NotifCategoryResponse response = new NotifCategoryResponse("ok", "getAll() is successfull", result);
                         return new Gson().toJson(response);
                     } else {
                         NotifCategoryResponse response = new NotifCategoryResponse("error", "Data not found", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     NotifCategoryResponse response = new NotifCategoryResponse("error", "Your level does not have this access.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             NotifCategoryResponse response = new NotifCategoryResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Post("/")
     @Secured("isAnonymous()")
     public String create(@Body NotifCategory notifCategory, @Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 NotifCategoryResponse response = new NotifCategoryResponse("error", "Unauthorized user.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     NotifCategory result = notifCategoryRepository.save(notifCategory);
                     NotifCategoryResponse response = new NotifCategoryResponse("ok", "Berhasil menambahkan data", result);
                     return new Gson().toJson(response);
                 } else {
                     NotifCategoryResponse response = new NotifCategoryResponse("error", "Anda tidak boleh mengakses halaman ini.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
            String  message = e.getMessage();
            NotifCategoryResponse response = new NotifCategoryResponse("error", message);
            return new Gson().toJson(response);
         }
     }

     @Get("/{id}")
     @Secured("isAnonymous()")
     public String show(Integer id, @Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 NotifCategoryResponse response = new NotifCategoryResponse("error", "Failed getData, you must sign in first");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     NotifCategory result = notifCategoryRepository.findById(id);
                     if (result != null) {
                         NotifCategoryResponse response = new NotifCategoryResponse("ok", "Get data is successfull", result);
                         return new Gson().toJson(response);
                     } else {
                         NotifCategoryResponse response = new NotifCategoryResponse("error", "Failed, data not found", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     NotifCategoryResponse response = new NotifCategoryResponse("error", "Your level not allowed get data.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
            String message = e.getMessage();
            NotifCategoryResponse response = new NotifCategoryResponse("error", message);
            return new Gson().toJson(response);
         }
     }

     @Put("/{id}")
     @Secured("isAnonymous()")
     public String update(Integer id, @Body NotifCategory notifCategory, @Nullable Authentication authentication) {
        try {
             if (authentication == null) {
                 NotifCategoryResponse response = new NotifCategoryResponse("error", "Failed, you must sign in first.", notifCategory);
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     NotifCategory result = notifCategoryRepository.update(id, notifCategory);
                     if (result != null) {
                        NotifCategoryResponse response = new NotifCategoryResponse("ok", "Data update successfull.", result);
                        return new Gson().toJson(response);
                     } else {
                         NotifCategoryResponse response = new NotifCategoryResponse("error", "Data not found.");
                         return new Gson().toJson(response);
                     }
                 } else {
                     NotifCategoryResponse response = new NotifCategoryResponse("error", "You does not have access this page.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             NotifCategoryResponse response = new NotifCategoryResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Delete("/{id}")
     @Secured("isAnonymous()")
     public String delete(Integer id, @Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 NotifCategoryResponse response = new NotifCategoryResponse("error", "Unauthorized user !");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     NotifCategory notifCategory = notifCategoryRepository.findById(id);
                     if (notifCategory != null) {
                         notifCategoryRepository.deleteById(id);
                         NotifCategoryResponse response = new NotifCategoryResponse("ok", "Delete data was successfull");
                         return new Gson().toJson(response);
                     } else {
                         NotifCategoryResponse response = new NotifCategoryResponse("error", "Data not found.");
                         return new Gson().toJson(response);
                     }
                 } else {
                     NotifCategoryResponse response = new NotifCategoryResponse("error", "You does not have access this page.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             NotifCategoryResponse response = new NotifCategoryResponse("error", message);
             return new Gson().toJson(response);
         }
     }
 }