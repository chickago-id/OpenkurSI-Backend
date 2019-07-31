package backend;

import backend.model.Materi;
import backend.model.MateriResponse;

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
@Controller("/materi")
public class MateriController {

    private MateriRepository materiRepository;

    public MateriController(MateriRepository materiRepository) {
        this.materiRepository = materiRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<Materi> materi = materiRepository.findAll();

            MateriResponse response = new MateriResponse("ok", "Data materi", materi);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();

            MateriResponse response = new MateriResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Post("/")
    public String create(@Body Materi materi) {

        try {

            Materi result = materiRepository.save(materi);
                
            MateriResponse response = new MateriResponse("ok", "Berhasil menambahkan data materi", result);

            return new Gson().toJson(response);
            

        } catch(Exception e) {
            String message = e.getMessage();

            MateriResponse response = new MateriResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    public String show(Long id) {

        try {

            Materi materi = materiRepository.findById(id);

            if(materi != null) {
                MateriResponse response = new MateriResponse("ok", "Data materi", materi);

                return new Gson().toJson(response);
            } else {
                MateriResponse response = new MateriResponse("error", "Data materi tidak ditemukan");

                return new Gson().toJson(response);
            } 

        } catch(Exception e) {

            String message = e.getMessage();

            MateriResponse response = new MateriResponse("error", message);

            return new Gson().toJson(response);
        }
        
    }

    @Post("/{id}") 
    public String update(@Body Materi materi) {
        // Materi getMateri = materiRepository.findById(id);

        Materi result = materiRepository.update(materi);

        if(result != null) {
            MateriResponse response = new MateriResponse("ok", "Berhasil memperbarui data materi", result);

            return new Gson().toJson(response);
        } else {
            MateriResponse response = new MateriResponse("error", "Data materi tidak ditemukan");

            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    public String delete(Long id) {
        Materi getMateri = materiRepository.findById(id);
         
        if(getMateri != null) {
            materiRepository.deleteById(id);

            MateriResponse response = new MateriResponse("ok", "Berhasil menghapus data materi");

            return new Gson().toJson(response);

        } else {
            MateriResponse response = new MateriResponse("error", "Data materi tidak ditemukan");

            return new Gson().toJson(response);
        }
    }
}