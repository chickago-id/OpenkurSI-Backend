package backend;

import backend.model.Absen_Peserta;
import backend.model.Absen_PesertaResponse;

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
@Controller("/absen_peserta")
@Secured("isAnonymous()")
public class Absen_PesertaController {

    private Absen_PesertaRepository absen_pesertaRepository;

    public Absen_PesertaController(Absen_PesertaRepository absen_pesertaRepository) {
        this.absen_pesertaRepository = absen_pesertaRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<Absen_Peserta> absen_peserta = absen_pesertaRepository.findAll();
            Absen_PesertaResponse response = new Absen_PesertaResponse("ok", "Absensi", absen_peserta);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();
            Absen_PesertaResponse response = new Absen_PesertaResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body Absen_Peserta absen_peserta, @Nullable Authentication authentication) 
    {


        try {

            if(authentication == null) {
                Absen_PesertaResponse response = new Absen_PesertaResponse("error", "Unauthorized user.");

                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    Absen_Peserta result = absen_pesertaRepository.save(absen_peserta);
                    
                    Absen_PesertaResponse response = new Absen_PesertaResponse("ok", "Berhasil melakukan absen", result);

                    return new Gson().toJson(response);
                } else {
                    Absen_PesertaResponse response = new Absen_PesertaResponse("error", "Anda tidak boleh mengakses halaman ini.");

                    return new Gson().toJson(response);
                }
            }

            

        } catch(Exception e) {
            String message = e.getMessage();

            Absen_PesertaResponse response = new Absen_PesertaResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Long id) {

        try {

            Absen_Peserta absen_peserta = absen_pesertaRepository.findById(id);

            if(absen_peserta != null) {
                Absen_PesertaResponse response = new Absen_PesertaResponse("ok", "Absensi", absen_peserta);

                return new Gson().toJson(response);
            } else {
                Absen_PesertaResponse response = new Absen_PesertaResponse("error", "Tidak dapat melakukan presensi");

                return new Gson().toJson(response);
            } 

        } catch(Exception e) {

            String message = e.getMessage();

            Absen_PesertaResponse response = new Absen_PesertaResponse("error", message);

            return new Gson().toJson(response);
        }
        
    }


   
}
