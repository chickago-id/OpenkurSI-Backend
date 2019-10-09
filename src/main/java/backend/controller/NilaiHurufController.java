package backend.controller;

import java.util.List;

import com.google.gson.Gson;
import backend.model.NilaiHuruf;
import backend.model.NilaiHurufResponse;
import backend.repository.NilaiHurufRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.validation.Validated;
import io.reactivex.annotations.Nullable;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

@Validated
@Controller("/nilaihuruf")
@Secured("isAnonymous()")

public class NilaiHurufController {
    private NilaiHurufRepository nilaihurufRepository;

    public NilaiHurufController (NilaiHurufRepository nilaihurufRepository){
        this.nilaihurufRepository = nilaihurufRepository;
    }

    @Get("/")
    public String index(){
        try {
            List<NilaiHuruf> nilaiHuruf = nilaihurufRepository.findAll();
            NilaiHurufResponse response = new NilaiHurufResponse ("ok", "data nilai huruf", nilaiHuruf );

            return new Gson().toJson(response);
        } catch (Exception e) {
            String message = e.getMessage();
            NilaiHurufResponse response = new NilaiHurufResponse ("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Long id){
        try {
            NilaiHuruf nilaiHuruf = nilaihurufRepository.findById(id);
            if (nilaiHuruf !=null) {
                NilaiHurufResponse response = new NilaiHurufResponse("Ok", "Data nilai huruf", nilaiHuruf);
                return new Gson().toJson(response);
            } else {
                NilaiHurufResponse response = new NilaiHurufResponse("Error", "Data tidak ditemukan");
                return new Gson().toJson(response);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            NilaiHurufResponse response = new NilaiHurufResponse("Error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create (@Body NilaiHuruf nilaiHuruf, @Nullable Authentication authentication){
        try {
            if(authentication == null){
                NilaiHurufResponse response = new NilaiHurufResponse("Error", "Unauthorized user.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    NilaiHuruf result = nilaihurufRepository.save(nilaiHuruf);
                    NilaiHurufResponse response = new NilaiHurufResponse ("ok","Berhasil menambahkan data nilai huruf", result);
                    return new Gson().toJson(response);
                } else {
                    NilaiHurufResponse response = new NilaiHurufResponse ("error", "Anda tidak boleh mengakses halaman ini.");
                    return new Gson().toJson(response);
                 }
              }

        } catch (Exception e) {
            String message = e.getMessage();
            NilaiHurufResponse response = new NilaiHurufResponse ("error", message);
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}")
    @Secured("isAnonymous()")
    public String update(Long id, @Body NilaiHuruf nilaiHuruf, @Nullable Authentication authentication){
        if (authentication == null){
            NilaiHurufResponse response = new NilaiHurufResponse ("error", "Unauthorized user.", nilaiHuruf);
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();
            if(roles.equals("[\"Admin\"]")){
                NilaiHuruf result = nilaihurufRepository.update(id, nilaiHuruf);
                if(result !=null){
                    NilaiHurufResponse response = new NilaiHurufResponse ("ok","Berhasil memperbaharui data nilai huruf", result);
                    return new Gson().toJson(response);
                } else {
                    NilaiHurufResponse response = new NilaiHurufResponse("error", "Data nilai huruf tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else {
                NilaiHurufResponse response = new NilaiHurufResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }

    @Delete ("/{id}")
    @Secured ("isAnonymous()")
    public String delete (Long id, @Nullable Authentication authentication){
        if (authentication == null){
            NilaiHurufResponse response = new NilaiHurufResponse("error", "Unauthorized user.");

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                NilaiHuruf getNilaiHuruf = nilaihurufRepository.findById(id);

                if (getNilaiHuruf !=null){
                    nilaihurufRepository.deleteById(id);

                    NilaiHurufResponse response = new NilaiHurufResponse("ok","Berhasil menghapus data nilai huruf");
                    return new Gson().toJson(response);
                } else {
                    NilaiHurufResponse response = new NilaiHurufResponse("error", "Data nilai huruf tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                NilaiHurufResponse response = new NilaiHurufResponse("error", "Anda tidak boleh mengakses halaman ini.");

                return new Gson().toJson(response);
            }
        }
    }
}