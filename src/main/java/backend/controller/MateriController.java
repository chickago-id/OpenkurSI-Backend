package backend.controller;

import backend.model.Materi;
import backend.model.MateriResponse;
import backend.repository.MateriRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import javax.annotation.Nullable;

import com.google.gson.Gson;
import java.util.List;

/**
 * Author : supi.core@gmail.com | github.com/sup1core  
 */

@Validated
@Controller("/materi")
@Secured("isAnonymous()")
public class MateriController {

    private MateriRepository materiRepository;

    public MateriController(MateriRepository materiRepository) {
        this.materiRepository = materiRepository;
    }

    @Get("/")
    @Secured("isAnonymous()")
    public String index(@Nullable Authentication auth) {
        try {
            if (auth == null ){
                MateriResponse response = new MateriResponse("error", "Unathorized, sign in !");
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<Materi> result = materiRepository.findAll();
                    if (result != null){
                        MateriResponse response = new MateriResponse("ok", "success findAll() !", result);
                        return new Gson().toJson(response);
                    } else {
                        MateriResponse response = new MateriResponse ("error", "Failed findAll() !", result);
                        return new Gson().toJson(response);
                    }
                } else {
                    MateriResponse response = new MateriResponse("error", "Failed findAll(), Unauthorized !");
                    return new Gson().toJson(response);
                }
            }
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
            }
        } catch(Exception e) {
            String message = e.getMessage();
            MateriResponse response = new MateriResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id) {
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

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(Integer id, @Body Materi materi, @Nullable Authentication authentication) {
        if(authentication == null) {
            MateriResponse response = new MateriResponse("error", "Unauthorized user.");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                Materi result = materiRepository.update(id, materi);

                if(result != null) {
                    MateriResponse response = new MateriResponse("ok", "Berhasil memperbarui data materi", result);
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
    public String delete(Integer id, @Nullable Authentication authentication) {
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