package backend.controller;

import backend.repository.AccessLevelRepository;
import backend.model.AccessLevel;
import backend.model.AccessLevelResponse;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
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
@Controller("/access-level")
@Secured("isAnonymous()")
public class AccessLevelController {
    private AccessLevelRepository repoAL;
    public AccessLevelController(AccessLevelRepository repoAL) {
        this.repoAL = repoAL;
    }

    @Get("/")
    public String index(Authentication auth) {
        try {
            if (auth == null) {
                AccessLevelResponse response = new AccessLevelResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<AccessLevel> result = repoAL.findAll();
                    if (result != null) {
                        AccessLevelResponse response = new AccessLevelResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AccessLevelResponse response = new AccessLevelResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AccessLevelResponse response = new AccessLevelResponse(
                        "ERORR", 
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }   
        } catch (Exception e) {
            String msg = e.getMessage();
            AccessLevelResponse response = new AccessLevelResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body AccessLevel accessLevel, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AccessLevelResponse response = new AccessLevelResponse(
                    "ERROR", 
                    "POST DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AccessLevel result = repoAL.save(accessLevel);
                    if (result != null) {
                        AccessLevelResponse response = new AccessLevelResponse(
                            "OK",
                            "POST DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AccessLevelResponse response = new AccessLevelResponse(
                            "ERROR",
                            "POST DATA FAILED NULL",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AccessLevelResponse response = new AccessLevelResponse(
                        "ERROR",
                        "POST DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            AccessLevelResponse response = new AccessLevelResponse(
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
                AccessLevelResponse response = new AccessLevelResponse(
                    "ERROR",
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AccessLevel result = repoAL.findById(id);
                    if (result != null) {
                        AccessLevelResponse response = new AccessLevelResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AccessLevelResponse response = new AccessLevelResponse(
                            "ERROR",
                            "GET DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AccessLevelResponse response = new AccessLevelResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            AccessLevelResponse response = new AccessLevelResponse(
                "ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(Integer id, @Body AccessLevel accessLevel, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AccessLevelResponse response = new AccessLevelResponse(
                    "ERROR",
                    "PUT DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AccessLevel result = repoAL.update(id, accessLevel);
                    if (result != null) {
                        AccessLevelResponse response = new AccessLevelResponse(
                            "OK",
                            "PUT DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AccessLevelResponse response = new AccessLevelResponse(
                            "ERROR",
                            "PUT DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AccessLevelResponse response = new AccessLevelResponse(
                        "ERROR", 
                        "PUT DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AccessLevelResponse response = new AccessLevelResponse(
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
                AccessLevelResponse response = new AccessLevelResponse(
                    "ERROR", 
                    "DELETE DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AccessLevel result = repoAL.findById(id);
                    if (result != null) {
                        repoAL.deleteById(id);
                        AccessLevelResponse response = new AccessLevelResponse(
                            "OK",
                            "DELETE DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AccessLevelResponse response = new AccessLevelResponse(
                            "ERROR",
                            "DELETE DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AccessLevelResponse response = new AccessLevelResponse(
                        "ERROR",
                        "DELETE DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AccessLevelResponse response = new AccessLevelResponse(
                "EXCEPTION ERROR",
                msg
            );
            return new Gson().toJson(response);
        }
    }
}
