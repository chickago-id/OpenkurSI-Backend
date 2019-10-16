package backend.controller;

import backend.repository.PresenceStatusRepository;
import backend.model.PresenceStatus;
import backend.model.PresenceStatusResponse;

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
public class PresenceStatusController {
    private PresenceStatusRepository repoPS;
    public PresenceStatusController(PresenceStatusRepository repoPS) {
        this.repoPS = repoPS;
    }

    @Get("/")
    public String index(Authentication auth) {
        try {
            if (auth == null) {
                PresenceStatusResponse response = new PresenceStatusResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<PresenceStatus> result = repoPS.findAll();
                    if (result != null) {
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceStatusResponse response = new PresenceStatusResponse(
                        "ERORR", 
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }   
        } catch (Exception e) {
            String msg = e.getMessage();
            PresenceStatusResponse response = new PresenceStatusResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body PresenceStatus PresenceStatus, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                PresenceStatusResponse response = new PresenceStatusResponse(
                    "ERROR", 
                    "POST DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    PresenceStatus result = repoPS.save(PresenceStatus);
                    if (result != null) {
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "OK",
                            "POST DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "ERROR",
                            "POST DATA FAILED NULL",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceStatusResponse response = new PresenceStatusResponse(
                        "ERROR",
                        "POST DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            PresenceStatusResponse response = new PresenceStatusResponse(
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
                PresenceStatusResponse response = new PresenceStatusResponse(
                    "ERROR",
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    PresenceStatus result = repoPS.findById(id);
                    if (result != null) {
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "ERROR",
                            "GET DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceStatusResponse response = new PresenceStatusResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            PresenceStatusResponse response = new PresenceStatusResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(Integer id, @Body PresenceStatus PresenceStatus, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                PresenceStatusResponse response = new PresenceStatusResponse(
                    "ERROR",
                    "PUT DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    PresenceStatus result = repoPS.update(id, PresenceStatus);
                    if (result != null) {
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "OK",
                            "PUT DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "ERROR",
                            "PUT DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceStatusResponse response = new PresenceStatusResponse(
                        "ERROR", 
                        "PUT DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            PresenceStatusResponse response = new PresenceStatusResponse(
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
                PresenceStatusResponse response = new PresenceStatusResponse(
                    "ERROR", 
                    "DELETE DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    PresenceStatus result = repoPS.findById(id);
                    if (result != null) {
                        repoPS.deleteById(id);
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "OK",
                            "DELETE DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceStatusResponse response = new PresenceStatusResponse(
                            "ERROR",
                            "DELETE DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceStatusResponse response = new PresenceStatusResponse(
                        "ERROR",
                        "DELETE DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            PresenceStatusResponse response = new PresenceStatusResponse(
                "EXCEPTION ERROR",
                msg
            );
            return new Gson().toJson(response);
        }
    }
}
