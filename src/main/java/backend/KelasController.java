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
@Controller("/kelas")
@Secured("isAnonymous()")
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
    @Secured("isAnonymous()")
    public String create(@Body Kelas kelas, @Nullable Authentication authentication) {
        try {

            if(authentication == null) {
                KelasResponse response = new KelasResponse("error", "Bukan admin, anda tidak boleh posting.");

                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    Kelas result = kelasRepository.save(kelas);
                    
                    KelasResponse response = new KelasResponse("ok", "Berhasil menambahkan data kelas", result);

                    return new Gson().toJson(response);
                } else {
                    KelasResponse response = new KelasResponse("error", "Anda tidak boleh mengakses halaman ini.");

                    return new Gson().toJson(response);
                }
            }

            

        } catch(Exception e) {
            String message = e.getMessage();

            KelasResponse response = new KelasResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
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
    @Secured("isAnonymous()")
    public String update(@Body Kelas kelas, @Nullable Authentication authentication) {
      
       
        if(authentication == null) {
            KelasResponse response = new KelasResponse("error", "Bukan admin, anda tidak boleh update data.");

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                Kelas result = kelasRepository.update(kelas);

                if(result != null) {
                    KelasResponse response = new KelasResponse("ok", "Berhasil memperbarui data kelas", result);

                    return new Gson().toJson(response);
                } else {
                    KelasResponse response = new KelasResponse("error", "Data kelas tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                KelasResponse response = new KelasResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            } 
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Long id, @Nullable Authentication authentication) {
        if(authentication == null) {
            KelasResponse response = new KelasResponse("error", "Bukan admin, anda tidak boleh hapus data.");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                Kelas getMateri = kelasRepository.findById(id);
                         
                if(getMateri != null) {
                    kelasRepository.deleteById(id);

                    KelasResponse response = new KelasResponse("ok", "Berhasil menghapus data kelas");

                    return new Gson().toJson(response);

                } else {
                    KelasResponse response = new KelasResponse("error", "Data kelas tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                KelasResponse response = new KelasResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }
}
