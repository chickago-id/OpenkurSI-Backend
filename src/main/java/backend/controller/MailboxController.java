package backend.controller;

import java.util.List;

import com.google.gson.Gson;

import backend.model.Mailbox;
import backend.repository.MailboxRepository;
import backend.response.MailboxResponse;
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
 @Controller("/mailbox")
 @Secured("isAnonymous()")

 public class MailboxController {
 
     private MailboxRepository mailboxRepository;
     public MailboxController (MailboxRepository mailboxRepository) {
         this.mailboxRepository = mailboxRepository;
     }

     @Get("/")
     @Secured("isAnonymous()")
     public String index(@Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 MailboxResponse response = new MailboxResponse("error", "You does not have authentication.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     List<Mailbox> result = mailboxRepository.findAll();
                     if (result != null) {
                         MailboxResponse response = new MailboxResponse("ok", "getAll() is successfull", result);
                         return new Gson().toJson(response);
                     } else {
                         MailboxResponse response = new MailboxResponse("error", "Data not found", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     MailboxResponse response = new MailboxResponse("error", "Your level does not have access.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             MailboxResponse response = new MailboxResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Post("/")
     @Secured("isAnonymous()")
     public String create(@Body Mailbox mailbox, @Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 MailboxResponse response = new MailboxResponse("error", "Unauthorized.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     Mailbox result = mailboxRepository.save(mailbox);
                     MailboxResponse response = new MailboxResponse("ok", "Posting is successfull", result);
                     return new Gson().toJson(response);
                 } else {
                     MailboxResponse response = new MailboxResponse("error", "You does not have access.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             MailboxResponse response = new MailboxResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Get("/{id}")
     @Secured("isAnonymous()")
     public String show(Integer id, @Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 MailboxResponse response = new MailboxResponse("error", "Failed getData, you must sign in first");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     Mailbox result = mailboxRepository.findById(id);
                     if (result != null) {
                         MailboxResponse response = new MailboxResponse("ok", "Get data is successfull.", result);
                         return new Gson().toJson(response);
                     } else {
                         MailboxResponse response = new MailboxResponse("error", "Failed, data not found.", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     MailboxResponse response = new MailboxResponse("error", "Your level does not allowed get data.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             MailboxResponse response = new MailboxResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Put("/{id}")
     @Secured("isAnonymous()")
     public String update(Integer id, @Body Mailbox mailbox, @Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 MailboxResponse response = new MailboxResponse("error", "Failed, you must sign in first.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     Mailbox result = mailboxRepository.update(id, mailbox);
                     if (result != null) {
                         MailboxResponse response = new MailboxResponse("ok", "Update data is successfull", result);
                         return new Gson().toJson(response);
                     } else {
                         MailboxResponse response = new MailboxResponse("error", "Data not found.");
                         return new Gson().toJson(response);
                     }
                 } else {
                     MailboxResponse response = new MailboxResponse("error", "You does not have access this page.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             MailboxResponse response = new MailboxResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Delete("/{id}")
     @Secured("isAnonymous()")
     public String delete(Integer id, @Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 MailboxResponse response = new MailboxResponse("error", "Unauthentorized user !");
                 return new Gson().toJson(response);
             } else {
                 Object data  = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]")) {
                     Mailbox result = mailboxRepository.findById(id);
                     if (result != null) {
                         mailboxRepository.deleteById(id);
                         MailboxResponse response = new MailboxResponse("ok", "Delete data was successfull");
                         return new Gson().toJson(response);
                     } else {
                         MailboxResponse response = new MailboxResponse("error", "Data not found.");
                         return new Gson().toJson(response);
                     }
                 } else {
                     MailboxResponse response = new MailboxResponse("error", "You does not have access this ");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             MailboxResponse response = new MailboxResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Get("/running-text")
     @Secured("isAnonymous()")
     public String getRunningtext(@Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 MailboxResponse response = new MailboxResponse("error", "You must sign in first.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]") || roles.equals("[\"Frontdesk\"]") || roles.equals("[\"Siswa\"]")) {
                     List<Mailbox> result = mailboxRepository.getRunningtext();
                     if (result != null) {
                         MailboxResponse response = new MailboxResponse("ok", "Getting data is successfull", result);
                         return new Gson().toJson(response);
                     } else {
                         MailboxResponse response = new MailboxResponse("error", "Failed, data not found.", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     MailboxResponse response = new MailboxResponse("error", "Your access level does not allowed.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             MailboxResponse response = new MailboxResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Get("/carousel")
    // @Secured("isAnonymous()")
     public String getCarousel() {
      /*   try {
             if (authentication == null) {
                 MailboxResponse response = new MailboxResponse("error", "You must sign in first.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]") || roles.equals("[\"Frontdesk\"]") || roles.equals("[\"Siswa\"]")) {
                     List<Mailbox> result = mailboxRepository.getCarousel();
                     if (result != null) {
                         MailboxResponse response = new MailboxResponse("ok", "Getting data is successfull", result);
                         return new Gson().toJson(response);
                     } else {
                         MailboxResponse response = new MailboxResponse("error", "Failed, data not found.", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     MailboxResponse response = new MailboxResponse("error", "Your access level does not allowed.");
                     return new Gson().toJson(response);
                 }
             } */
        try {
            List<Mailbox> result = mailboxRepository.getCarousel();
            MailboxResponse response = new MailboxResponse("ok", "Getting data carousel is successfull", result);
            return new Gson().toJson(response);
         } catch (Exception e) {
             String message = e.getMessage();
             MailboxResponse response = new MailboxResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Get("/notification")
     @Secured("isAnonymous()")
     public String getNotification(@Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 MailboxResponse response = new MailboxResponse("error", "You must sign in first.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]") || roles.equals("[\"Frontdesk\"]") || roles.equals("[\"Siswa\"]")) {
                     List<Mailbox> result = mailboxRepository.getRunningtext();
                     if (result != null) {
                         MailboxResponse response = new MailboxResponse("ok", "Getting data is successfull", result);
                         return new Gson().toJson(response);
                     } else {
                         MailboxResponse response = new MailboxResponse("error", "Failed, data not found.", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     MailboxResponse response = new MailboxResponse("error", "Your access level does not allowed.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             MailboxResponse response = new MailboxResponse("error", message);
             return new Gson().toJson(response);
         }
     }

     @Get("/newsinfo")
     @Secured("isAnonymous()")
     public String getNewsinfo(@Nullable Authentication authentication) {
         try {
             if (authentication == null) {
                 MailboxResponse response = new MailboxResponse("error", "You must sign in first.");
                 return new Gson().toJson(response);
             } else {
                 Object data = authentication.getAttributes().get("roles");
                 String roles = data.toString();
                 if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]") || roles.equals("[\"Frontdesk\"]") || roles.equals("[\"Siswa\"]")) {
                     List<Mailbox> result = mailboxRepository.getRunningtext();
                     if (result != null) {
                         MailboxResponse response = new MailboxResponse("ok", "Getting data is successfull", result);
                         return new Gson().toJson(response);
                     } else {
                         MailboxResponse response = new MailboxResponse("error", "Failed, data not found.", result);
                         return new Gson().toJson(response);
                     }
                 } else {
                     MailboxResponse response = new MailboxResponse("error", "Your access level does not allowed.");
                     return new Gson().toJson(response);
                 }
             }
         } catch (Exception e) {
             String message = e.getMessage();
             MailboxResponse response = new MailboxResponse("error", message);
             return new Gson().toJson(response);
         }
     }

 }