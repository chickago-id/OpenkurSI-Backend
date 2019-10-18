package backend.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import backend.model.AttendanceToken;
import backend.repository.AttendanceTokenRepository;
import backend.response.AttendanceTokenResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.validation.Validated;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Validated
@Controller("/attendance-token")
@Secured("isAnonymous()")
public class AttendanceTokenController {
    private AttendanceTokenRepository repoAT;
    public AttendanceTokenController(AttendanceTokenRepository repoAT) {
        this.repoAT = repoAT;
    }

    @Get("/")
    public String index(Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<AttendanceToken> result = repoAT.findAll();
                    if (result != null) {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERORR", 
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }   
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR", 
                    "POST DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AttendanceToken objAT = new AttendanceToken();
                    objAT.setToken(UUID.randomUUID().toString().substring(0, 6));
                    objAT.setExpiryDate(30);
                    AttendanceToken result = repoAT.save(objAT);
                    if (result != null) {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK",
                            "POST DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "POST DATA FAILED NULL",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "POST DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "EXCEPTION ERROR", 
                message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AttendanceToken result = repoAT.findById(id);
                    if (result != null) {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "GET DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(Integer id, @Body AttendanceToken AttendanceToken, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "PUT DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AttendanceToken result = repoAT.update(id, AttendanceToken);
                    if (result != null) {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK",
                            "PUT DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "PUT DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR", 
                        "PUT DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR", 
                    "DELETE DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AttendanceToken result = repoAT.findById(id);
                    if (result != null) {
                        repoAT.deleteById(id);
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK",
                            "DELETE DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "DELETE DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "DELETE DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "EXCEPTION ERROR",
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Get(value = "/find/{token}")
    @Secured("isAnonymous()")
    public String findByToken(String token) {
        AttendanceToken result = repoAT.findByToken(token);
        if(result!=null) {
            AttendanceTokenResponse response = new AttendanceTokenResponse(
            "OK", 
            "GET SUCCESS",
            result
        );
        return new Gson().toJson(response);
        } else {
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "ERROR", 
                "NOT FOUND"
            );
            return new Gson().toJson(response);
        }
        
    }
}
