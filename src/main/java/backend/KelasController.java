package backend;

import backend.model.Kelas;
import backend.model.KelasResponse;

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
@Controller("/kelas")
public class KelasController {

    private KelasRepository kelasRepository;

    public KelasController(KelasRepository kelasRepository) {
        this.kelasRepository = kelasRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<Kelas> kelas = kelasRepository.findAll();
            KelasResponse response = new KelasResponse("ok", "Data kelas", kelas);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();
            KelasResponse response = new KelasResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    public String create(@Body Kelas kelas) {

        try {

            Kelas result = kelasRepository.save(kelas);
                
            KelasResponse response = new KelasResponse("ok", "Berhasil menambahkan data kelas", result);

            return new Gson().toJson(response);
            

        } catch(Exception e) {
            String message = e.getMessage();

            KelasResponse response = new KelasResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    public String show(Long id) {

        try {

            Kelas kelas = kelasRepository.findById(id);

            if(kelas != null) {
                KelasResponse response = new KelasResponse("ok", "Data kelas", kelas);

                return new Gson().toJson(response);
            } else {
                KelasResponse response = new KelasResponse("error", "Data kelas tidak ditemukan");

                return new Gson().toJson(response);
            } 

        } catch(Exception e) {

            String message = e.getMessage();

            KelasResponse response = new KelasResponse("error", message);

            return new Gson().toJson(response);
        }
        
    }

    @Post("/{id}") 
    public String update(@Body Kelas kelas) {
        // Materi getMateri = materiRepository.findById(id);

        Kelas result = kelasRepository.update(kelas);

        if(result != null) {
            KelasResponse response = new KelasResponse("ok", "Berhasil memperbarui data kelas", result);

            return new Gson().toJson(response);
        } else {
            KelasResponse response = new KelasResponse("error", "Data kelas tidak ditemukan");

            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    public String delete(Long id) {
        Kelas getKelas = kelasRepository.findById(id);
         
        if(getKelas != null) {
            kelasRepository.deleteById(id);

            KelasResponse response = new KelasResponse("ok", "Berhasil menghapus data kelas");

            return new Gson().toJson(response);

        } else {
            KelasResponse response = new KelasResponse("error", "Data kelas tidak ditemukan");

            return new Gson().toJson(response);
        }
    }
}
