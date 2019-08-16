package backend;

import backend.model.Absen_Instruktur;
import backend.model.Absen_InstrukturResponse;

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
@Controller("/absen_instruktur")
@Secured("isAnonymous()")
public class Absen_InstrukturController {

    private Absen_InstrukturRepository absen_instrukturRepository;

    public Absen_InstrukturController(Absen_InstrukturRepository absen_instrukturRepository) {
        this.absen_instrukturRepository = absen_instrukturRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<Absen_Instruktur> absen_instruktur = absen_instrukturRepository.findAll();
            Absen_InstrukturResponse response = new Absen_InstrukturResponse("ok", "Absensi", absen_instruktur);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();
            Absen_InstrukturResponse response = new Absen_InstrukturResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body Absen_Instruktur absen_instruktur, @Nullable Authentication authentication) 
    {


        try {

            if(authentication == null) {
                Absen_InstrukturResponse response = new Absen_InstrukturResponse("error", "Unauthorized user.");

                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    Absen_Instruktur result = absen_instrukturRepository.save(absen_instruktur);
                    
                    Absen_InstrukturResponse response = new Absen_InstrukturResponse("ok", "Berhasil melakukan absensi", result);

                    return new Gson().toJson(response);
                } else {
                    Absen_InstrukturResponse response = new Absen_InstrukturResponse("error", "Anda tidak boleh mengakses halaman ini.");

                    return new Gson().toJson(response);
                }
            }

            

        } catch(Exception e) {
            String message = e.getMessage();

            Absen_InstrukturResponse response = new Absen_InstrukturResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Long id) {

        try {

            Absen_Instruktur absen_instruktur = absen_instrukturRepository.findById(id);

            if(absen_instruktur != null) {
                Absen_InstrukturResponse response = new Absen_InstrukturResponse("ok", "Absensi", absen_instruktur);

                return new Gson().toJson(response);
            } else {
                Absen_InstrukturResponse response = new Absen_InstrukturResponse("error", "Absensi tidak ditemukan");

                return new Gson().toJson(response);
            } 

        } catch(Exception e) {

            String message = e.getMessage();

            Absen_InstrukturResponse response = new Absen_InstrukturResponse("error", message);

            return new Gson().toJson(response);
        }
        
    }


}
