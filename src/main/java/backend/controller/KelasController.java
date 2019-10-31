package backend.controller;

import backend.model.Batch;
import backend.model.Kelas;
import backend.model.KelasResponse;
import backend.model.KelasType;
import backend.model.MasterKelas;
import backend.repository.KelasRepository;
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
 * Author : aries and revised by supi.core@gmail.com | github.com/sup1core  
 */

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
    
    @Get("/enroll")
    public String enRoll() {
        try {
            List<Kelas> kelas = kelasRepository.enRoll();
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
                    MasterKelas masterKelas = kelasRepository.findById(kelas.getId_kelas());
                    KelasType kelasType = kelasRepository.cariId(kelas.getId_kelastype());
                    Batch batch = kelasRepository.cariBatchid(kelas.getId_batch());

                    kelas.setKode_kelas (kelasType.getCode()+'.'+masterKelas.getKode_masterkelas()+'.'+'#'+batch.getId_batch()+'.'+(kelas.getTanggal_mulai().getMonth()+1)+'.'+(kelas.getTanggal_mulai().getYear()+1900));
                    Kelas result = kelasRepository.save(kelas);

                    KelasResponse response = new KelasResponse("ok", "Berhasil menambahkan data kelas", result);
                    return new Gson().toJson(response);
                } 
                else {
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
            if (kelas != null) {
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

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(Integer id, @Body Kelas kelas, @Nullable Authentication authentication) { 
        if(authentication == null) {
            KelasResponse response = new KelasResponse("error", "Sorry,you does not update data, login first !");

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if (roles.equals("[\"Admin\"]")) {
                MasterKelas masterKelas = kelasRepository.findById(kelas.getId_kelas());
                KelasType kelasType = kelasRepository.cariId(kelas.getId_kelastype());
                kelas.setKode_kelas (kelasType.getCode()+'.'+masterKelas.getKode_masterkelas()+'.'+(kelas.getTanggal_mulai().getMonth()+1)+'.'+(kelas.getTanggal_mulai().getYear()+1900));
                Kelas result = kelasRepository.update(id, kelas);

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
