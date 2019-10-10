package backend.controller;

import backend.model.Jadwal;
import backend.model.JadwalResponse;
import backend.repository.JadwalRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import javax.annotation.Nullable;

import com.google.gson.Gson;
import java.util.List;

@Validated
@Controller("/jadwal")
@Secured("isAnonymous()")
public class JadwalController {

    private JadwalRepository jadwalRepository;

    public JadwalController(JadwalRepository jadwalRepository) {
        this.jadwalRepository = jadwalRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<Jadwal> jadwal = jadwalRepository.findAll();
            JadwalResponse response = new JadwalResponse("ok", "Data Jadwal", jadwal);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();
            JadwalResponse response = new JadwalResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body Jadwal jadwal, @Nullable Authentication authentication) 
    {


        try {

            if(authentication == null) {
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
    public String show(Long id) {

        try {

            Jadwal jadwal = jadwalRepository.findById(id);

            if(jadwal != null) {
                JadwalResponse response = new JadwalResponse("ok", "Data Jadwal", jadwal);

                return new Gson().toJson(response);
            } else {
                JadwalResponse response = new JadwalResponse("error", "Jadwal tidak ditemukan");

                return new Gson().toJson(response);
            } 

        } catch(Exception e) {

            String message = e.getMessage();

            JadwalResponse response = new JadwalResponse("error", message);

            return new Gson().toJson(response);
        }
        
    }

    @Post("/{id}") 
    @Secured("isAnonymous()")
    public String update(@Body Jadwal jadwal, @Nullable Authentication authentication) 
    {
        
        if(authentication == null) {
            JadwalResponse response = new JadwalResponse("error", "Unauthorized user.");

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                Jadwal result = jadwalRepository.update(jadwal);

                if(result != null) {
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
    }


    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Long id, @Nullable Authentication authentication) 
    {
        if(authentication == null) {
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
    }
}
