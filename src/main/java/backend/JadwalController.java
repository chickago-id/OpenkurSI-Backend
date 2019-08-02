package backend;

import backend.model.Jadwal;
import backend.model.JadwalResponse;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.http.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Optional;

@Validated
@Controller("/jadwal")
public class JadwalController {

    private JadwalRepository jadwalRepository;

    public JadwalController(JadwalRepository jadwalRepository) {
        this.jadwalRepository = jadwalRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<Jadwal> jadwal = jadwalRepository.findAll();

            JadwalResponse response = new JadwalResponse("ok", "Data jadwal", jadwal);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();

            JadwalResponse response = new JadwalResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Post("/")
    public String create(@Body Jadwal jadwal) {

        try {

            Jadwal result = jadwalRepository.save(jadwal);
                
            JadwalResponse response = new JadwalResponse("ok", "Berhasil menambahkan jadwal", result);

            return new Gson().toJson(response);
            

        } catch(Exception e) {
            String message = e.getMessage();

            JadwalResponse response = new JadwalResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    public String show(Long id) {

        try {

            Jadwal jadwal = jadwalRepository.findById(id);

            if(jadwal != null) {
                JadwalResponse response = new JadwalResponse("ok", "Data jadwal", jadwal);

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
    public String update(@Body Jadwal jadwal) {
        // Materi getMateri = materiRepository.findById(id);

        Jadwal result = jadwalRepository.update(jadwal);

        if(result != null) {
            JadwalResponse response = new JadwalResponse("ok", "Berhasil memperbarui jadwal", result);

            return new Gson().toJson(response);
        } else {
            JadwalResponse response = new JadwalResponse("error", "Jadwal tidak ditemukan");

            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    public String delete(Long id) {
        Jadwal getJadwal = jadwalRepository.findById(id);
         
        if(getJadwal != null) {
            jadwalRepository.deleteById(id);

            JadwalResponse response = new JadwalResponse("ok", "Berhasil menghapus jadwal");

            return new Gson().toJson(response);

        } else {
            JadwalResponse response = new JadwalResponse("error", "Jadwal tidak ditemukan");

            return new Gson().toJson(response);
        }
    }
}
