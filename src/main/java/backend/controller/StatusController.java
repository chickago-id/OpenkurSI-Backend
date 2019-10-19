package backend.controller;

import java.util.List;

import com.google.gson.Gson;

import backend.model.Status;
import backend.repository.StatusRepository;
import backend.response.StatusResponse;
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
 @Controller("/status")
 @Secured("isAnonymous()")

 public class StatusController {
 
    private StatusRepository statusRepository;
    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Get("/")
    public String index(@Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                StatusResponse response = new StatusResponse("error", "You're not authentication. Sign in first.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<Status> result = statusRepository.findAll();
                    if (result != null) {
                        StatusResponse response = new StatusResponse("ok", "List Data", result);
                        return new Gson().toJson(response);
                    } else {
                        StatusResponse response = new StatusResponse("error", "Sorry, data not found.", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    StatusResponse response = new StatusResponse("error", "You does not have access this page.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            StatusResponse  response = new StatusResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id, @Nullable Authentication authentication){
        try {
            if (authentication == null) {
                StatusResponse response = new StatusResponse("error", "Failed, sign in first");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")){
                    Status result = statusRepository.findById(id);
                    if (result != null) {
                        StatusResponse response = new StatusResponse("ok", "List data.", result);
                        return new Gson().toJson(response);
                    } else {
                        StatusResponse response = new StatusResponse("error", "Failed, data not found.", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    StatusResponse response = new StatusResponse("error", "Your level not allowed access this page.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            StatusResponse response = new StatusResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post
    @Secured("isAnonymous()")
    public String create (@Body Status status, @Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                StatusResponse response = new StatusResponse("error", "Failed, login first.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Status result = statusRepository.save(status);
                    StatusResponse response = new StatusResponse("ok", "Insert data is successfull.", result);
                    return new Gson().toJson(response);
                } else {
                    StatusResponse response = new StatusResponse("error", "Sorry, you does not have access this page.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            StatusResponse response = new StatusResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}")
    @Secured("isAnonymous()")
    public String update (Integer id, @Body Status status, @Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                StatusResponse response = new StatusResponse("error", "Failed, you must sign in.", status);
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Status result = statusRepository.update(id, status);
                    if (result != null) {
                        StatusResponse response = new StatusResponse("ok", "Update data is successfull", result);
                        return new Gson().toJson(response);
                    } else {
                        StatusResponse response = new StatusResponse("error", "Data not found.", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    StatusResponse response = new StatusResponse("error", "You does not have access this page.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            StatusResponse response = new StatusResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication){
        try {
            if (authentication == null) {
                StatusResponse response = new StatusResponse("error", "Failed, you must sing in first.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Status result = statusRepository.findById(id);
                    if (result != null) {
                        statusRepository.deleteById(id);
                        StatusResponse response = new StatusResponse("ok", "Delete data is successfull.");
                        return new Gson().toJson(response);
                    } else {
                        StatusResponse response = new StatusResponse("error", "Failed, data not found.");
                        return new Gson().toJson(response);
                    }
                } else {
                    StatusResponse response = new StatusResponse("error", "Failed, your level does not allow access this page.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            StatusResponse response = new StatusResponse("error", message);
            return new Gson().toJson(response);
        }
    }

 }