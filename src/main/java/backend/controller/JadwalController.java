package backend.controller;

import backend.model.Jadwal;
import backend.repository.JadwalRepository;
import backend.response.DayResponse;
import backend.response.JadwalResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import javax.annotation.Nullable;

import com.google.gson.Gson;
import java.util.List;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

@Validated
@Controller("/jadwal")
@Secured("isAnonymous()")
public class JadwalController {
    private JadwalRepository jadwalRepository;
    public JadwalController(JadwalRepository jadwalRepository) {
        this.jadwalRepository = jadwalRepository;
    }

    @Get("/")
    public String index(@Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                JadwalResponse response = new JadwalResponse ("error", "you are not authentication.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles =data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    List<Jadwal> result = jadwalRepository.findAll();
                    if (result != null) {
                        JadwalResponse response = new JadwalResponse ("ok", "Successfull getAll()", result);
                        return new Gson().toJson(response);
                    } else {
                        JadwalResponse response = new JadwalResponse("error", "Data not found.", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    JadwalResponse response = new JadwalResponse("error", "Your level does not have this access.");
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            JadwalResponse response = new JadwalResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body Jadwal jadwal, @Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                JadwalResponse response = new JadwalResponse("error", "Unauthorized user.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    Jadwal result = jadwalRepository.save(jadwal);
                    JadwalResponse response = new JadwalResponse("ok", "Berhasil menambahkan data Jadwal", result);
                    return new Gson().toJson(response);
                } else {
                    JadwalResponse response = new JadwalResponse("error", "Anda tidak boleh mengakses halaman ini.");
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            JadwalResponse response = new JadwalResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id, @Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                JadwalResponse response = new JadwalResponse("error", "failed get data, you must sign in first");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    Jadwal result = jadwalRepository.findById(id);
                    if (result != null) {
                        JadwalResponse response = new JadwalResponse("ok", "Get data successfull", result);
                        return new Gson().toJson(response);
                    } else {
                        JadwalResponse response = new JadwalResponse("error", "Failed, data not found.", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    JadwalResponse response = new JadwalResponse ("error", "Your level not allowed get data.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            JadwalResponse response = new JadwalResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(Integer id, @Body Jadwal jadwal, @Nullable Authentication authentication) {
        try {
            if (authentication == null) {
                JadwalResponse response = new JadwalResponse("error", "Failed, you must sign in first.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    Jadwal result = jadwalRepository.update(id, jadwal);
                    if (result != null) {
                        JadwalResponse response = new JadwalResponse("ok", "Berhasil memperbarui jadwal", result);
                        return new Gson().toJson(response);
                    } else {
                        JadwalResponse response = new JadwalResponse("error", "jadwal tidak ditemukan");
                        return new Gson().toJson(response);
                    }
                } else {
                    JadwalResponse response = new JadwalResponse("error", "Anda tidak boleh mengakses halaman ini.");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e){
            String message = e.getMessage();
            DayResponse response = new DayResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication) {
        try{
            if (authentication == null) {
                JadwalResponse response = new JadwalResponse("error", "Unauthorized user.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    Jadwal getJadwal = jadwalRepository.findById(id);
                    if(getJadwal != null) {
                        jadwalRepository.deleteById(id);
                        JadwalResponse response = new JadwalResponse("ok", "Berhasil menghapus jadwal");
                        return new Gson().toJson(response);
                    } else {
                        JadwalResponse response = new JadwalResponse("error", "Jadwal tidak ditemukan");
                        return new Gson().toJson(response);
                    }
                } else {
                    JadwalResponse response = new JadwalResponse("error", "Anda tidak boleh mengakses halaman ini.");
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