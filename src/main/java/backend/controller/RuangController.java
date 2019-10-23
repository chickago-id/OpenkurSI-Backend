package backend.controller;

import java.util.List;

import com.google.gson.Gson;

import backend.model.Ruang;
import backend.repository.RuangRepository;
import backend.response.RuangResponse;
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
 * Author : supi.core@gmail.com | githun.com/sup1core
 */

 @Validated
 @Controller("/ruang")
 @Secured ("isAnonymous()")

 public class RuangController {
    private RuangRepository ruangRepository;

    public RuangController (RuangRepository ruangRepository){
        this.ruangRepository = ruangRepository;
    }

    @Get("/")
    public String index(){
        try {
            List<Ruang> ruang = ruangRepository.findAll();
            RuangResponse response = new RuangResponse ("ok", "data ruang", ruang);
            return new Gson().toJson(response);
        } catch (Exception e) {
            String message = e.getMessage();
            RuangResponse response = new RuangResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id){
        try {
            Ruang ruang = ruangRepository.findById(id);
            if (ruang !=null){
                RuangResponse response = new RuangResponse("ok", "Data ruang", ruang);
                return new Gson().toJson(response);
            } else {
                RuangResponse response = new RuangResponse("error", "Data tidak ditemukan !");
                return new Gson().toJson(response);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            RuangResponse response = new RuangResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body Ruang ruang, @Nullable Authentication authentication){
        try {
            if(authentication==null){
                RuangResponse response = new RuangResponse ("error", "Unauthorized user !");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")){
                    Ruang result = ruangRepository.save(ruang);
                    RuangResponse response = new RuangResponse("ok", "Berhasil menambahkan data ruang", result);
                    return new Gson().toJson(response);
                } else {
                    RuangResponse response = new RuangResponse("error", "Tidak ada hak akses !");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            RuangResponse response = new RuangResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}")
    @Secured("isAnonymous()")
    public String update(Integer id, @Body Ruang ruang, @Nullable Authentication authentication){
        if (authentication==null){
            RuangResponse response = new RuangResponse("error", "Unauthorized !", ruang);
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();
            if (roles.equals("[\"Admin\"]")){
                Ruang result = ruangRepository.update(id, ruang);
                if (result !=null) {
                    RuangResponse response = new RuangResponse("ok", "Update berhasil", result);
                    return new Gson().toJson(response);
                } else {
                    RuangResponse response = new RuangResponse("error", "Data tidak ditemukan !");
                    return new Gson().toJson(response);
                }
            } else {
                RuangResponse response = new RuangResponse("error", "Tidak ada hak akses !");
                return new Gson().toJson(response);
            }
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication){
        if (authentication == null){
            RuangResponse response = new RuangResponse("error", "Unathorized user !");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")){
                Ruang getRuang = ruangRepository.findById(id);

                if (getRuang != null) {
                    ruangRepository.deleteById(id);
                    RuangResponse response = new RuangResponse("ok", "Data berhasil dihapus");
                    return new Gson().toJson(response);
                } else {
                    RuangResponse response = new RuangResponse("error", "Data tidak ditemukan !");
                    return new Gson().toJson(response);
                }
            } else {
                RuangResponse response = new RuangResponse("error", "Tidak ada hak akses !");
                return new Gson().toJson(response);
            }
        }
    }
    
    
}