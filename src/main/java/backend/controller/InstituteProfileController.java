package backend.controller;

import backend.repository.InstituteProfileRepository;
import backend.model.InstituteProfile;
import backend.model.InstituteProfileResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.annotation.Secured;
import javax.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Validated
@Controller("/institute-profile")
@Secured("isAnonymous()")
public class InstituteProfileController {
    private InstituteProfileRepository repoIP;
    public InstituteProfileController(InstituteProfileRepository repoIP) {
        this.repoIP = repoIP;
    }

    @Get("/")
    public String index(@Nullable Authentication auth) {
        try {
            if (auth == null) {
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<InstituteProfile> result = repoIP.findAll();
                    if (result != null) {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "OK", 
                        "GET DATA SUCCESS",
                        result
                    );
                    return new Gson().toJson(response);
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "GET DATA FAILED NOT ADMIN OR PENGAJAR"
                    );
                    return new Gson().toJson(response);           
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            InstituteProfileResponse response = new InstituteProfileResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/json")
    public String create(@Body InstituteProfile objIP, @Nullable Authentication auth) {
        try {
            if(auth == null) {
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    "POST DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    InstituteProfile result = repoIP.save(objIP);
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "OK", 
                        "POST DATA SUCCESS", 
                        result
                    );
                    return new Gson().toJson(response);
                } else {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "POST DATA FAILED NOT ADMIN"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            InstituteProfileResponse response = new InstituteProfileResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    InstituteProfile result = repoIP.findById(id);
                    if (result != null) {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "OK", 
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "GET DATA FAILED NOT Admin OR Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            InstituteProfileResponse response = new InstituteProfileResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(
        Integer id, 
        @Body InstituteProfile objIP, 
        @Nullable Authentication auth) {             
        try {
            if(auth == null) {
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    "PUT DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    InstituteProfile result = repoIP.update(id, objIP);
                    if(result != null) {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "OK", 
                            "PUT DATA SUCCESS", 
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "PUT DATA FAILED DATA NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "PUT DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            InstituteProfileResponse response = new InstituteProfileResponse(
                "ERROR",
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(
        Integer id, 
        @Nullable Authentication auth) {
        try {
            if(auth == null) {
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    "DELETE FAILED NOT SIGNED IN"
                    );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    InstituteProfile result = repoIP.findById(id);
                    if(result != null) {
                        repoIP.deleteById(id);
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "OK", 
                            "DELETE DATA SUCCESS"
                        );
                        return new Gson().toJson(response);
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "DELETE FAILED DATA NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "DELETE DATA FAILED NOT Admin");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            InstituteProfileResponse response = new InstituteProfileResponse(
                "ERROR",
                msg
            );
            return new Gson().toJson(response);
        }            
    }
}