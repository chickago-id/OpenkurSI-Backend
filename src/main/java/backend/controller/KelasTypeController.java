package backend.controller;

import java.util.List;

import com.google.gson.Gson;

import backend.model.KelasType;
import backend.repository.KelasTypeRepository;
import backend.response.KelasTypeResponse;
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
 @Controller("/class-type")
 @Secured("isAnonymous()")

 public class KelasTypeController {
    private KelasTypeRepository kelasTypeRepository;
    public KelasTypeController (KelasTypeRepository kelasTypeRepository){
        this.kelasTypeRepository = kelasTypeRepository;
    }

    @Get("/")
    public String index(@Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                KelasTypeResponse response = new KelasTypeResponse("error", "you must login first.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<KelasType> result = kelasTypeRepository.findAll();
                    if (result != null) {
                        KelasTypeResponse response = new KelasTypeResponse("ok", "List data.", result);
                        return new Gson().toJson(response);
                    } else {
                        KelasTypeResponse response = new KelasTypeResponse("error", "Data not found.");
                        return new Gson().toJson(response);
                    }
                } else {
                    KelasTypeResponse response = new KelasTypeResponse("error", "You does not access this page");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            KelasTypeResponse response = new KelasTypeResponse ("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show (Integer id, @Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                KelasTypeResponse response = new KelasTypeResponse("error", "Failed, login first.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    KelasType result = kelasTypeRepository.findById(id);
                    if (result != null) {
                        KelasTypeResponse response = new KelasTypeResponse("ok", "List data :", result);
                        return new Gson().toJson(response);
                    } else {
                        KelasTypeResponse response = new KelasTypeResponse("error", "failed", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    KelasTypeResponse response = new KelasTypeResponse("error", "Your level does not get data");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            KelasTypeResponse response = new KelasTypeResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body KelasType kelasType, @Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                KelasTypeResponse response = new KelasTypeResponse("error", "Failed post, sign in first");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    KelasType result = kelasTypeRepository.save(kelasType);
                    KelasTypeResponse response = new KelasTypeResponse("ok", "Insert data is successfull:", result);
                    return new Gson().toJson(response);
                } else {
                    KelasTypeResponse response = new KelasTypeResponse("error", "Your level does not access this page.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            KelasTypeResponse response = new KelasTypeResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}")
    @Secured("isAnonymous()")
    public String update(Integer id, @Body KelasType kelasType, @Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                KelasTypeResponse response = new KelasTypeResponse("error", "Failed, you must log in first");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    KelasType result = kelasTypeRepository.update(id, kelasType);
                    if (result != null) {
                        KelasTypeResponse response = new KelasTypeResponse("ok", "You're successfull update data.", result);
                        return new Gson().toJson(response);
                    } else {
                        KelasTypeResponse response = new KelasTypeResponse("error", "Failed, data not found.");
                        return new Gson().toJson(response);
                    }
                } else {
                    KelasTypeResponse response = new KelasTypeResponse("error", "Your leve does not allowed update data");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            KelasTypeResponse response = new KelasTypeResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                KelasTypeResponse response = new KelasTypeResponse("error", "Failed, login first");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    KelasType result = kelasTypeRepository.findById(id);
                    if (result != null) {
                        kelasTypeRepository.deleteById(id);
                        KelasTypeResponse response = new KelasTypeResponse("ok", "Delete data is successfull");
                        return new Gson().toJson(response);
                    } else {
                        KelasTypeResponse response = new KelasTypeResponse("error", "Failed, data not found");
                        return new Gson().toJson(response);
                    }
                } else {
                    KelasTypeResponse response = new KelasTypeResponse("error", "Failed delete. your level access not allowed");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            KelasTypeResponse response = new KelasTypeResponse("error", message);
            return new Gson().toJson(response);
        }
    }
 }