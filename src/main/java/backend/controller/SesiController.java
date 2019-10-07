package backend.controller;

import java.util.List;

import com.google.gson.Gson;

import backend.model.Sesi;
import backend.model.SesiResponse;
import backend.repository.SesiRepository;
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
 @Controller("/sesi")
 @Secured("isAnonymous()")
 
 public class SesiController {
    private SesiRepository sesiRepository;

    public SesiController(SesiRepository sesiRepository){
        this.sesiRepository = sesiRepository;
    }

    @Get("/")
    public String index(@Nullable Authentication authentication){
        try {
            if (authentication == null){
                SesiResponse response = new SesiResponse("error", "Unauthorized, sing in !");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<Sesi> result = sesiRepository.findAll();
                    if (result != null) {
                        SesiResponse response = new SesiResponse("ok", "success findAll() !", result);
                        return new Gson().toJson(response);
                    } else {
                        SesiResponse response = new SesiResponse("error", "failed findAll() !", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    SesiResponse response = new SesiResponse("error", "failed findAll(), you're unauthorized !");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            SesiResponse response = new SesiResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body Sesi sesi, @Nullable Authentication authentication){
        try {
            if (authentication == null){
                SesiResponse response = new SesiResponse ("error", "Unauthorized access, you must login !");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Sesi result = sesiRepository.save(sesi);
                    SesiResponse response = new SesiResponse("ok", "seccussfully create data", result);
                    return new Gson().toJson(response);
                } else {
                    SesiResponse response = new SesiResponse("error", "Unauthorized, you're not access this data !");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            SesiResponse response = new SesiResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id) {
        try {
            Sesi sesi = sesiRepository.findById(id);
            if (sesi != null) {
                SesiResponse response = new SesiResponse ("ok", "Data sesi", sesi);
                return new Gson().toJson(response);
            } else {
                SesiResponse response = new SesiResponse("error", "Data not found !");
                return new Gson().toJson(response);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            SesiResponse response = new SesiResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}")
    @Secured("isAnonymous()")
    public String update (Integer id, @Body Sesi sesi, @Nullable Authentication authentication) {
        if (authentication == null) {
            SesiResponse response = new SesiResponse("error", "You're not access !", sesi);
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();
            if (roles.equals("[\"Admin\"]")) {
                Sesi result = sesiRepository.update(id, sesi);
                if (result != null) {
                    SesiResponse response = new SesiResponse("ok", "Updating data is successfully !", result);
                    return new Gson().toJson(response);
                } else {
                    SesiResponse response = new SesiResponse("error", "Data not found !");
                    return new Gson().toJson(response);
                }
            } else {
                SesiResponse response = new SesiResponse("error", "You're not have access !");
                return new Gson().toJson(response);
            }
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication) {
        if (authentication == null) {
            SesiResponse response = new SesiResponse ("error", "You're not have access level");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();
            if (roles.equals("[\"Admin\"]")) {
                Sesi result = sesiRepository.findById(id);
                if (result != null) {
                    sesiRepository.deleteById(id);
                    SesiResponse response = new SesiResponse("ok", "Deleting is successfully !");
                    return new Gson().toJson(response);
                } else {
                    SesiResponse response = new SesiResponse("error", "Data not found !");
                    return new Gson().toJson(response);
                }
            } else {
                SesiResponse response = new SesiResponse("error", "You're not have access !");
                return new Gson().toJson(response);
            }
        }
    }
    
 }