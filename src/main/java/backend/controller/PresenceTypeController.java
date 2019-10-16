package backend.controller;

import backend.repository.PresenceTypeRepository;
import backend.model.PresenceType;
import backend.model.PresenceTypeResponse;

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
@Controller("/presence-type")
@Secured("isAnonymous()")
public class PresenceTypeController {
    private PresenceTypeRepository repoPT;
    public PresenceTypeController(PresenceTypeRepository repoPT) {
        this.repoPT = repoPT;
    }

    @Get("/")
    public String index(Authentication auth) {
        try {
            if (auth == null) {
                PresenceTypeResponse response = new PresenceTypeResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<PresenceType> result = repoPT.findAll();
                    if (result != null) {
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceTypeResponse response = new PresenceTypeResponse(
                        "ERORR", 
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }   
        } catch (Exception e) {
            String msg = e.getMessage();
            PresenceTypeResponse response = new PresenceTypeResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body PresenceType PresenceType, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                PresenceTypeResponse response = new PresenceTypeResponse(
                    "ERROR", 
                    "POST DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    PresenceType result = repoPT.save(PresenceType);
                    if (result != null) {
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "OK",
                            "POST DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "ERROR",
                            "POST DATA FAILED NULL",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceTypeResponse response = new PresenceTypeResponse(
                        "ERROR",
                        "POST DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            PresenceTypeResponse response = new PresenceTypeResponse(
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
                PresenceTypeResponse response = new PresenceTypeResponse(
                    "ERROR",
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    PresenceType result = repoPT.findById(id);
                    if (result != null) {
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "ERROR",
                            "GET DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceTypeResponse response = new PresenceTypeResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            PresenceTypeResponse response = new PresenceTypeResponse(
                "ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(Integer id, @Body PresenceType PresenceType, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                PresenceTypeResponse response = new PresenceTypeResponse(
                    "ERROR",
                    "PUT DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    PresenceType result = repoPT.update(id, PresenceType);
                    if (result != null) {
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "OK",
                            "PUT DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "ERROR",
                            "PUT DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceTypeResponse response = new PresenceTypeResponse(
                        "ERROR", 
                        "PUT DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            PresenceTypeResponse response = new PresenceTypeResponse(
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
                PresenceTypeResponse response = new PresenceTypeResponse(
                    "ERROR", 
                    "DELETE DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    PresenceType result = repoPT.findById(id);
                    if (result != null) {
                        repoPT.deleteById(id);
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "OK",
                            "DELETE DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        PresenceTypeResponse response = new PresenceTypeResponse(
                            "ERROR",
                            "DELETE DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    PresenceTypeResponse response = new PresenceTypeResponse(
                        "ERROR",
                        "DELETE DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            PresenceTypeResponse response = new PresenceTypeResponse(
                "EXCEPTION ERROR",
                msg
            );
            return new Gson().toJson(response);
        }
    }
}
