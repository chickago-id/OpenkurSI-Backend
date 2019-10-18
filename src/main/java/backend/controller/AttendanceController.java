package backend.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import backend.model.Attendance;
import backend.model.AttendanceToken;
import backend.repository.AttendanceRepository;
import backend.response.AttendanceResponse;
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
@Controller("/attendance")
@Secured("isAnonymous()")
public class AttendanceController {
    private AttendanceRepository repoAttendance;
    public AttendanceController(AttendanceRepository repoAttendance) {
        this.repoAttendance = repoAttendance;
    }

    @Get("/")
    public String index(Authentication auth) {
        try {
            if (auth == null) {
                AttendanceResponse response = new AttendanceResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<Attendance> result = repoAttendance.findAll();
                    if (result != null) {
                        AttendanceResponse response = new AttendanceResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceResponse response = new AttendanceResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceResponse response = new AttendanceResponse(
                        "ERORR", 
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }   
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceResponse response = new AttendanceResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body Attendance Attendance, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceResponse response = new AttendanceResponse(
                    "ERROR", 
                    "POST DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Attendance result = repoAttendance.save(Attendance);
                    if (result != null) {
                        AttendanceResponse response = new AttendanceResponse(
                            "OK",
                            "POST DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceResponse response = new AttendanceResponse(
                            "ERROR",
                            "POST DATA FAILED NULL",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceResponse response = new AttendanceResponse(
                        "ERROR",
                        "POST DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            AttendanceResponse response = new AttendanceResponse(
                "EXCEPTION ERROR", 
                message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Long id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceResponse response = new AttendanceResponse(
                    "ERROR",
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Attendance result = repoAttendance.findById(id);
                    if (result != null) {
                        AttendanceResponse response = new AttendanceResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceResponse response = new AttendanceResponse(
                            "ERROR",
                            "GET DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceResponse response = new AttendanceResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            AttendanceResponse response = new AttendanceResponse(
                "ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(Long id, @Body Attendance Attendance, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceResponse response = new AttendanceResponse(
                    "ERROR",
                    "PUT DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Attendance result = repoAttendance.update(id, Attendance);
                    if (result != null) {
                        AttendanceResponse response = new AttendanceResponse(
                            "OK",
                            "PUT DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceResponse response = new AttendanceResponse(
                            "ERROR",
                            "PUT DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceResponse response = new AttendanceResponse(
                        "ERROR", 
                        "PUT DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceResponse response = new AttendanceResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Long id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceResponse response = new AttendanceResponse(
                    "ERROR", 
                    "DELETE DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Attendance result = repoAttendance.findById(id);
                    if (result != null) {
                        repoAttendance.deleteById(id);
                        AttendanceResponse response = new AttendanceResponse(
                            "OK",
                            "DELETE DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceResponse response = new AttendanceResponse(
                            "ERROR",
                            "DELETE DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceResponse response = new AttendanceResponse(
                        "ERROR",
                        "DELETE DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceResponse response = new AttendanceResponse(
                "EXCEPTION ERROR",
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Post(uri = "/absen")
    @Secured("isAnonymous()")
    public String absen(Authentication auth, String token) {
        try {
            if(auth == null) {
                AttendanceResponse response = new AttendanceResponse(
                    "ERROR", 
                    "ABSEN GAGAL: BELUM SIGN IN"
                );
                return new Gson().toJson(response);    
            } else {
                AttendanceToken objAT = repoAttendance.findByToken(token);
                if(objAT == null) {
                    AttendanceResponse response = new AttendanceResponse(
                        "ERROR", 
                        "ABSEN GAGAL: TOKEN TIDAK VALID"
                    );
                    return new Gson().toJson(response);
                } else {
                    if(token.equals(objAT.getToken())) {
                        String id = auth.getAttributes().get("userId").toString();
                        Long user_id = Long.parseLong(id);
                        if(repoAttendance.existByUserIdAndToken(user_id, token)) {
                            Date clock = new Date();
                            Integer clockValue = clock.compareTo(objAT.getExpired_at());
                            if(clockValue <= 0) {
                                Attendance objAttendance = new Attendance();
                                objAttendance.setClock(clock);
                                objAttendance.setToken(token);
                                objAttendance.setUser_id(user_id);
                                objAttendance.setStatus(1);
                                Attendance result = repoAttendance.save(objAttendance);
                                AttendanceResponse response = new AttendanceResponse(
                                    "OK", 
                                    "ABSEN BERHASIL",
                                    result
                                );
                                return new Gson().toJson(response);
                            } else if(clockValue > 0) {
                                AttendanceResponse response = new AttendanceResponse(
                                    "ERROR", 
                                    "ABSEN GAGAL: TOKEN KADALUARSA"
                                );
                                return new Gson().toJson(response);
                            } else {
                                AttendanceResponse response = new AttendanceResponse(
                                    "ERROR", 
                                    "ABSEN GAGAL: ???"
                                );
                                return new Gson().toJson(response);
                            }
                        } else {
                            AttendanceResponse response = new AttendanceResponse(
                                "ERROR", 
                                "ABSEN GAGAL: ANDA SUDAH ABSEN"
                            );
                            return new Gson().toJson(response);
                        }     
                    } else {
                        AttendanceResponse response = new AttendanceResponse(
                            "ERROR", 
                            "ABSEN GAGAL: TOKEN TIDAK VALID"
                        );
                        return new Gson().toJson(response);
                    }
                } 
            }
        } catch (Exception e) {
            AttendanceResponse response = new AttendanceResponse(
                "ERROR", 
                "ABSEN GAGAL: " + e.getMessage()
            );
            return new Gson().toJson(response);
        }
        
    }
}
