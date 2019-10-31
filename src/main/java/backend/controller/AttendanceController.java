package backend.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import backend.model.Attendance;
import backend.model.AttendanceToken;
import backend.model.KelasPeserta;
import backend.model.KelasPesertaResponse;
import backend.repository.AttendanceRepository;
import backend.response.AttendanceResponse;
import backend.response.ObjectResponse;
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
                        "ERROR", 
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
                Object userRoles = auth.getAttributes().get("roles");
                String roles = userRoles.toString();
                if(roles.equals("[\"Peserta\"]")) {
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
                            Long id_user = Long.parseLong(id);
                            if(repoAttendance.existByIdUserAndToken(id_user, token)) {
                                Date clock = new Date();
                                Integer clockValue = clock.compareTo(objAT.getExpired_at());
                                if(clockValue <= 0) {
                                    Attendance objAttendance = new Attendance();
                                    objAttendance.setClock(clock);
                                    objAttendance.setToken(token);
                                    objAttendance.setId_user(id_user);
                                    objAttendance.setId_jadwal(objAT.getId_jadwal());
                                    objAttendance.setStatus(1);
                                    objAttendance.setIs_approved(0);
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
                } else {
                    AttendanceResponse response = new AttendanceResponse(
                        "ERROR", 
                        "ABSEN GAGAL: BUKAN PESERTA"
                    );
                    return new Gson().toJson(response);
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

    @Post(uri = "/absen/verify/{is_approved}")
    @Secured("isAnonymous()")
    public String notApprovedAttendance(Integer is_approved, Authentication auth, Integer id_jadwal, Long id_user) {
        try {
            if(auth == null) {
                AttendanceResponse response = new AttendanceResponse(
                    "ERROR", 
                    "ABSEN GAGAL: BELUM SIGN IN"
                );
                return new Gson().toJson(response);    
            } else {
                Object userRoles = auth.getAttributes().get("roles");
                String roles = userRoles.toString();
                if(roles.equals("[\"Pengajar\"]") || roles.equals("[\"Admin\"]")) {
                    try {
                        Attendance objA = repoAttendance.findByIdJadwalAndIdUserAndStatus(id_jadwal, id_user);
                        Date clock_out = new Date();
                        objA.setClock_out(clock_out);
                        switch (is_approved) {
                            case 1: // tepat waktu(sudah check in)
                                objA.setIs_approved(1);
                                objA.setStatus(1);
                                break;
                            case 2: // terlambat
                                objA.setIs_approved(2);
                                objA.setStatus(2);
                                break;
                            case 3: // sakit
                                objA.setIs_approved(3);
                                objA.setStatus(3);
                                break;
                            case 4: // izin
                                objA.setIs_approved(4);
                                objA.setStatus(4);
                                break;
                            case 5: // alpha
                                objA.setIs_approved(5);
                                objA.setStatus(5);
                                break;
                            default:
                                break;
                        }
                        Attendance result = repoAttendance.update(objA.getId(), objA);
                        if(result == null) {
                            AttendanceResponse response = new AttendanceResponse(
                                "ERROR",
                                "Gagal memperbarui data presensi"
                            );
                            return new Gson().toJson(response);
                        } else {
                            AttendanceResponse response = new AttendanceResponse(
                                "OK",
                                "Berhasil memperbarui data presensi"
                            );
                            return new Gson().toJson(response);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        Date clock = new Date();
                        Attendance objB = new Attendance();
                        objB.setId_jadwal(id_jadwal);
                        objB.setId_user(id_user);
                        objB.setClock(clock);
                        objB.setClock_out(clock);
                        switch (is_approved) {
                            case 1: // hadir tepat waktu
                                objB.setIs_approved(1);
                                objB.setStatus(1);
                                break;
                            case 2: // terlambat
                                objB.setIs_approved(2);
                                objB.setStatus(2);
                                break;
                            case 3: // sakit
                                objB.setIs_approved(3);
                                objB.setStatus(3);
                                break;
                            case 4: // izin
                                objB.setIs_approved(4);
                                objB.setStatus(4);
                                break;
                            case 5: // alpha
                                objB.setIs_approved(5);
                                objB.setStatus(5);
                                break;
                            default:
                                break;
                        }
                        Attendance result = repoAttendance.save(objB);
                        if(result == null) {
                            AttendanceResponse response = new AttendanceResponse(
                                "ERROR",
                                "Gagal menyimpan data presensi"
                            );
                            return new Gson().toJson(response);
                        } else {
                            AttendanceResponse response = new AttendanceResponse(
                                "OK", 
                                "Berhasil menyimpan data presensi",
                                result
                            );
                            return new Gson().toJson(response);
                        }
                    }
                } else {
                    AttendanceResponse response = new AttendanceResponse(
                        "ERROR", 
                        "Absen gagal: Bukan Pengajar atau Admin"
                    );
                    return new Gson().toJson(response);
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

    @Get(uri = "/find/peserta/{id_jadwal}")
    @Secured("isAnonymous()")
    public String findByIdPeserta(Integer id_jadwal, Authentication auth) {
        try {
            if (auth == null) {
                AttendanceResponse response = new AttendanceResponse(
                    "ERROR",
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                Object userId = auth.getAttributes().get("userId");
                Long id_user = Long.parseLong(userId.toString());
                String roles = data.toString();
                if (roles.equals("[\"Peserta\"]")) {
                    List<Attendance> result = repoAttendance.findByIdJadwalAndIdUser(id_jadwal, id_user);
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
                        "GET DATA FAILED NOT Peserta"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            AttendanceResponse response = new AttendanceResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }
    
    @Get(uri = "/find/kelaspeserta/{id_jadwal}")
    @Secured("isAnonymous()")
    public String findByIdKelasAndToken(Integer id_jadwal, Authentication auth) {
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
                if (roles.equals("[\"Pengajar\"]") || roles.equals("[\"Admin\"]")) {
                    List<Object> result = repoAttendance.findByIdJadwal(id_jadwal);
                    if (!result.isEmpty()) {
                        ObjectResponse response = new ObjectResponse(
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
                        "GET DATA FAILED NOT Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            AttendanceResponse response = new AttendanceResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Get(uri = "/find/kelaspeserta/verify/{id_kelas}")
    @Secured("isAnonymous()")
    public String showKelasPeserta(Long id_kelas, Authentication auth) {
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
                if (roles.equals("[\"Pengajar\"]") || roles.equals("[\"Admin\"]")) {
                    List<KelasPeserta> result = repoAttendance.findByIdKelas(id_kelas);
                    if (!result.isEmpty()) {
                        KelasPesertaResponse response = new KelasPesertaResponse(
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
                        "GET DATA FAILED NOT Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            AttendanceResponse response = new AttendanceResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Get(uri = "/find/presensi/peserta/{id_user}")
    @Secured("isAnonymous()")
    public String findByIdPeserta(Long id_user, Authentication auth) {
        try {
            if (auth == null) {
                AttendanceResponse response = new AttendanceResponse(
                    "ERROR",
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                //Object userId = auth.getAttributes().get("userId");
                //Long user_id = Long.parseLong(userId.toString());
                String roles = data.toString();
                if (roles.equals("[\"Peserta\"]")) { 
                    
                    List<Object> result = repoAttendance.findByIdUser(id_user);
                    if (result != null) {
                        ObjectResponse response = new ObjectResponse(
                            "SUCCESS",
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
                        "GET DATA FAILED NOT Peserta"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            AttendanceResponse response = new AttendanceResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        } 
    }
}
