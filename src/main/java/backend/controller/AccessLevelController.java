package backend.controller;

import backend.repository.AccessLevelRepository;
import backend.model.AccessLevel;
import backend.model.AccessLevelResponse;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.annotation.Secured;
import javax.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Validated
@Controller("/access-level")
@Secured("isAnonymous()")
public class AccessLevelController {

    private AccessLevelRepository AccessLevelRepository;

    public AccessLevelController(AccessLevelRepository AccessLevelRepository) {
        this.AccessLevelRepository = AccessLevelRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<AccessLevel> AccessLevel = AccessLevelRepository.findAll();
            AccessLevelResponse response = new AccessLevelResponse("ok", "Data peserta kelas", AccessLevel);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();
            AccessLevelResponse response = new AccessLevelResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body AccessLevel AccessLevel, @Nullable Authentication authentication) {
        try {

            if(authentication == null) {
                AccessLevelResponse response = new AccessLevelResponse("error", "Belum Login, anda tidak boleh posting.");

                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    AccessLevel result = AccessLevelRepository.save(AccessLevel);
                    
                    AccessLevelResponse response = new AccessLevelResponse("ok", "Berhasil menambahkan data kategori nilai", result);

                    return new Gson().toJson(response);
                }
                else if(roles.equals("[\"Peserta\"]")) {
                    AccessLevel result = AccessLevelRepository.save(AccessLevel);
                    
                    AccessLevelResponse response = new AccessLevelResponse("ok", "Berhasil menambahkan data kategori nilai", result);

                    return new Gson().toJson(response);
                }
                
                else {
                    AccessLevelResponse response = new AccessLevelResponse("error", "Anda tidak boleh mengakses halaman ini.");

                    return new Gson().toJson(response);
                }
            }

            

        } catch(Exception e) {
            String message = e.getMessage();

            AccessLevelResponse response = new AccessLevelResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id) {

        try {

            AccessLevel AccessLevel = AccessLevelRepository.findById(id);

            if(AccessLevel != null) {
                AccessLevelResponse response = new AccessLevelResponse("ok", "Data kategori nilai", AccessLevel);

                return new Gson().toJson(response);
            } else {
                AccessLevelResponse response = new AccessLevelResponse("error", "Data kategori nilai tidak ditemukan");

                return new Gson().toJson(response);
            } 

        } catch(Exception e) {

            String message = e.getMessage();

            AccessLevelResponse response = new AccessLevelResponse("error", message);

            return new Gson().toJson(response);
        }
        
    }

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(Integer id, @Body AccessLevel AccessLevel, @Nullable Authentication authentication) {
             
        if(authentication == null) {
            AccessLevelResponse response = new AccessLevelResponse("error", "Bukan admin, anda tidak boleh update data.", AccessLevel);

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();
            

            if(roles.equals("[\"Admin\"]")) {
                AccessLevel result = AccessLevelRepository.update(id, AccessLevel);

                if(result != null) {
                    AccessLevelResponse response = new AccessLevelResponse("ok", "Berhasil memperbarui data kategori nilai", result);

                    return new Gson().toJson(response);
                } else {
                    AccessLevelResponse response = new AccessLevelResponse("error", "Data kategori nilai tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                AccessLevelResponse response = new AccessLevelResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            } 
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication) {
        if(authentication == null) {
            AccessLevelResponse response = new AccessLevelResponse("error", "Bukan admin, anda tidak boleh hapus data.");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                AccessLevel result = AccessLevelRepository.findById(id);
                         
                if(result != null) {
                    AccessLevelRepository.deleteById(id);

                    AccessLevelResponse response = new AccessLevelResponse("ok", "Berhasil menghapus data kategori nilai");

                    return new Gson().toJson(response);

                } else {
                    AccessLevelResponse response = new AccessLevelResponse("error", "Data kategori nilai tidak ditemukan");

                    return new Gson().toJson(response);
                }
            }
            else if(roles.equals("[\"Peserta\"]")) {
                AccessLevel result = AccessLevelRepository.findById(id);
                         
                if(result != null) {
                    AccessLevelRepository.deleteById(id);

                    AccessLevelResponse response = new AccessLevelResponse("ok", "Berhasil menghapus data kategori nilai");

                    return new Gson().toJson(response);

                } else {
                    AccessLevelResponse response = new AccessLevelResponse("error", "Data kategori nilai tidak ditemukan");

                    return new Gson().toJson(response);
                }
            }
            
            
            else {
                AccessLevelResponse response = new AccessLevelResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }
}
