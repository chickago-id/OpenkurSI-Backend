package backend;

import backend.model.KelasPeserta;
import backend.model.KelasPesertaResponse;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.http.MediaType;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.annotation.Secured;
import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Validated
@Controller("/kelaspeserta")
@Secured("isAnonymous()")
public class KelasPesertaController {

    private KelasPesertaRepository kelasPesertaRepository;

    public KelasPesertaController(KelasPesertaRepository kelasPesertaRepository) {
        this.kelasPesertaRepository = kelasPesertaRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<KelasPeserta> kelaspeserta = kelasPesertaRepository.findAll();
            KelasPesertaResponse response = new KelasPesertaResponse("ok", "Data peserta kelas", kelaspeserta);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();
            KelasPesertaResponse response = new KelasPesertaResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body KelasPeserta kelaspeserta, @Nullable Authentication authentication) {
        try {

            if(authentication == null) {
                KelasPesertaResponse response = new KelasPesertaResponse("error", "Bukan admin, anda tidak boleh posting.");

                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    KelasPeserta result = kelasPesertaRepository.save(kelaspeserta);
                    
                    KelasPesertaResponse response = new KelasPesertaResponse("ok", "Berhasil menambahkan data peserta kelas", result);

                    return new Gson().toJson(response);
                } else {
                    KelasPesertaResponse response = new KelasPesertaResponse("error", "Anda tidak boleh mengakses halaman ini.");

                    return new Gson().toJson(response);
                }
            }

            

        } catch(Exception e) {
            String message = e.getMessage();

            KelasPesertaResponse response = new KelasPesertaResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Long id) {

        try {

            KelasPeserta kelaspeserta = kelasPesertaRepository.findById(id);

            if(kelaspeserta != null) {
                KelasPesertaResponse response = new KelasPesertaResponse("ok", "Data peserta kelas", kelaspeserta);

                return new Gson().toJson(response);
            } else {
                KelasPesertaResponse response = new KelasPesertaResponse("error", "Data peserta kelas tidak ditemukan");

                return new Gson().toJson(response);
            } 

        } catch(Exception e) {

            String message = e.getMessage();

            KelasPesertaResponse response = new KelasPesertaResponse("error", message);

            return new Gson().toJson(response);
        }
        
    }

    @Post("/{id}") 
    @Secured("isAnonymous()")
    public String update(@Body KelasPeserta kelaspeserta, @Nullable Authentication authentication) {
      
       
        if(authentication == null) {
            KelasPesertaResponse response = new KelasPesertaResponse("error", "Bukan admin, anda tidak boleh update data.");

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                KelasPeserta result = kelasPesertaRepository.update(kelaspeserta);

                if(result != null) {
                    KelasPesertaResponse response = new KelasPesertaResponse("ok", "Berhasil memperbarui data peserta kelas", result);

                    return new Gson().toJson(response);
                } else {
                    KelasPesertaResponse response = new KelasPesertaResponse("error", "Data peserta kelas tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                KelasPesertaResponse response = new KelasPesertaResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            } 
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Long id, @Nullable Authentication authentication) {
        if(authentication == null) {
            KelasPesertaResponse response = new KelasPesertaResponse("error", "Bukan admin, anda tidak boleh hapus data.");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                KelasPeserta getMateri = kelasPesertaRepository.findById(id);
                         
                if(getMateri != null) {
                    kelasPesertaRepository.deleteById(id);

                    KelasPesertaResponse response = new KelasPesertaResponse("ok", "Berhasil menghapus data peserta kelas");

                    return new Gson().toJson(response);

                } else {
                    KelasPesertaResponse response = new KelasPesertaResponse("error", "Data peserta kelas tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                KelasPesertaResponse response = new KelasPesertaResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }
}
