package backend.controller;

import java.util.List;

import com.google.gson.Gson;

import backend.model.Day;
import backend.repository.DayRepository;
import backend.response.DayResponse;
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
 @Controller("/day")
 @Secured("isAnonymous()")

 public class DayController {
    private DayRepository dayRepository;
    public DayController(DayRepository dayRepository){
        this.dayRepository = dayRepository;
    }

    @Get("/")
    public String index(@Nullable Authentication authentication){
        try {
            if(authentication == null){
                DayResponse response = new DayResponse ("error", "you're not authentication");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<Day> result = dayRepository.findAll();
                    if (result != null) {
                        DayResponse response = new DayResponse("ok", "successfull getAll()", result);
                        return new Gson().toJson(response);
                    } else {
                        DayResponse response = new DayResponse("error", "Data not found", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    DayResponse response = new DayResponse("error", "You're not access level.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            DayResponse response = new DayResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body Day day, @Nullable Authentication authentication){
        try {
            if(authentication == null){
                DayResponse response = new DayResponse("error", "You're failed post because not sign in.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Day result = dayRepository.save(day);
                    DayResponse response = new DayResponse("ok", "Insert data is successfull.", result);
                    return new Gson().toJson(response);
                } else {
                    DayResponse response = new DayResponse("error", "Your level not allowed posting");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            DayResponse response = new DayResponse ("error", message);
            return new Gson().toJson(response);
        }
    }
    
    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id, @Nullable Authentication authentication){
        try {
            if (authentication == null){
            DayResponse response = new DayResponse("error", "Failed get data, please not sign in first.");
            return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Day result = dayRepository.findById(id);
                    if (result != null) {
                        DayResponse response = new DayResponse ("ok","Successfully getting data", result);
                        return new Gson().toJson(response);
                    } else {
                        DayResponse response = new DayResponse("error", "Failed, data not found.", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    DayResponse response = new DayResponse("error", "Your level not allowed get data");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            DayResponse response = new DayResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}")
    @Secured("isAnonymous()")
    public String update(Integer id, @Body Day day, @Nullable Authentication authentication){
        try {
            if(authentication == null){
                DayResponse response = new DayResponse("error", "Failed, you must sign in", day);
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Day result = dayRepository.update(id, day);
                    if (result != null) {
                        DayResponse response = new DayResponse("ok", "You're successfull update data", result);
                        return new Gson().toJson(response);
                    } else {
                        DayResponse response = new DayResponse("error", "Failed update, data not found.");
                        return new Gson().toJson(response);
                    }
                } else {
                    DayResponse response = new DayResponse("error", "Your level not allowed update data.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            DayResponse response = new DayResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication){
        try {
            if (authentication == null) {
                DayResponse response = new DayResponse ("error", "Failed, you must sign in");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Day result = dayRepository.findById(id);
                    if (result != null) {
                        dayRepository.deleteById(id);
                        DayResponse response = new DayResponse("ok", "Delete data is successful.");
                        return new Gson().toJson(response);
                    } else {
                        DayResponse response = new DayResponse("error", "Failed, data not found.");
                        return new Gson().toJson(response);
                    }
                } else {
                    DayResponse response = new DayResponse("error", "Failed delete, your level access not delete.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            DayResponse response = new DayResponse("error", message);
            return new Gson().toJson(response);
        }
    }
 }