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
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Validated
@Controller("/materi")
@Secured("isAnonymous()")
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
    @Secured("isAnonymous()")
    public String create(@Body Materi materi, @Nullable Authentication authentication) {


        try {

            if(authentication == null) {
                MateriResponse response = new MateriResponse("error", "Unauthorized user.");

                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    Materi result = materiRepository.save(materi);
                    
                    MateriResponse response = new MateriResponse("ok", "Berhasil menambahkan data materi", result);

                    return new Gson().toJson(response);
                } else {
                    MateriResponse response = new MateriResponse("error", "Anda tidak boleh mengakses halaman ini.");

                    return new Gson().toJson(response);
                }

            

        } catch(Exception e) {
            String message = e.getMessage();

            MateriResponse response = new MateriResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
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
    @Secured("isAnonymous()")
    public String update(@Body Materi materi, @Nullable Authentication authentication) 
    {
        
        if(authentication == null) {
            MateriResponse response = new MateriResponse("error", "Unauthorized user.");

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                Materi result = materiRepository.update(materi);

                if(result != null) {
                    MateriResponse response = new MateriResponse("ok", "Berhasil memperbarui data materi", result);
                    Materi result = materiRepository.update(materi);

                    return new Gson().toJson(response);
                } else {
                    MateriResponse response = new MateriResponse("error", "Data materi tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                MateriResponse response = new MateriResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            } 
        }
    }


    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Long id, @Nullable Authentication authentication) 
    {

        if(authentication == null) {
            MateriResponse response = new MateriResponse("error", "Unauthorized user.");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                Materi getMateri = materiRepository.findById(id);
                         
                if(getMateri != null) {
                    materiRepository.deleteById(id);

                    MateriResponse response = new MateriResponse("ok", "Berhasil menghapus data materi");

                    return new Gson().toJson(response);

                } else {
                    MateriResponse response = new MateriResponse("error", "Data materi tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                MateriResponse response = new MateriResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }
}
