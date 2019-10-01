package backend.controller;

import java.util.List;

import com.google.gson.Gson;

import backend.model.NilaiAkhir;
import backend.model.NilaiAkhirResponse;
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

@Validated
@Controller("/nilaiakhir")
@Secured("isAnonymous()")

/**
 * Author : supi.core@gmail.com
 */
public class NilaiAkhirController {

    private NilaiAkhirRepository nilaiAkhirRepository;
    
    public NilaiAkhirController(NilaiAkhirRepository nilaiAkhirRepository){
        this.nilaiAkhirRepository = nilaiAkhirRepository;
    }

    @Get("/")
    public String index(){
        try {
            List<NilaiAkhir> nilaiAkhir = nilaiAkhirRepository.findAll();
            NilaiAkhirResponse response = new NilaiAkhirResponse ("ok", "Data Nilai Akhir", nilaiAkhir);
            return new Gson().toJson(response);

        } catch (Exception e) {
            String message = e.getMessage();
            NilaiAkhirResponse response = new NilaiAkhirResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body NilaiAkhir nilaiAkhir, @Nullable Authentication authentication){
        try {
            if(authentication == null){
                NilaiAkhirResponse response = new NilaiAkhirResponse("ok","Belum log in, dilarang posting.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    NilaiAkhir result = nilaiAkhirRepository.save(nilaiAkhir);
                    NilaiAkhirResponse response = new NilaiAkhirResponse("ok", "Berhasil menambahkan data nilai akhir", result);
                    return new Gson().toJson(response);
                }
                else if(roles.equals("[\"Peserta\"]")) {
                    NilaiAkhir result = nilaiAkhirRepository.save(nilaiAkhir);
                    NilaiAkhirResponse response = new NilaiAkhirResponse("ok", "Berhasil menambahkan data nilai akhir", result);
                    return new Gson().toJson(response);
                } else {
                    NilaiAkhirResponse response = new NilaiAkhirResponse("error", "Anda tidak boleh mengakses halaman ini");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            NilaiAkhirResponse response = new NilaiAkhirResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Long id){
        try {
            NilaiAkhir nilaiAkhir = nilaiAkhirRepository.findById(id);
            if (nilaiAkhir !=null) {
                NilaiAkhirResponse response = new NilaiAkhirResponse("ok", "Data nilai akhir", nilaiAkhir);
                return new Gson().toJson(response);
            } else {
                NilaiAkhirResponse response = new NilaiAkhirResponse("error", "Data tidak ditemukan.");
                return new Gson().toJson(response);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            NilaiAkhirResponse response = new NilaiAkhirResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Put ("/{id}")
    @Secured("isAnonymous()")
    public String update(Long id, @Body NilaiAkhir nilaiAkhir, @Nullable Authentication authentication){
        if(authentication == null){
            NilaiAkhirResponse response = new NilaiAkhirResponse("error", "Bukan admin, anda tidak boleh update data.", nilaiAkhir);
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")){
                NilaiAkhir result = nilaiAkhirRepository.update(id, nilaiAkhir);

                if (result != null) {
                    NilaiAkhirResponse response = new NilaiAkhirResponse("error", "Data berhasil diupdate", result);
                    return new Gson().toJson(response);
                } else {
                    NilaiAkhirResponse response = new NilaiAkhirResponse("errir", "Data tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else {
                NilaiAkhirResponse response = new NilaiAkhirResponse("error", "Tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Long id, @Nullable Authentication authentication){
        if(authentication == null){
            NilaiAkhirResponse response = new NilaiAkhirResponse("error","Tidak ada akses !");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")){
                NilaiAkhir result = nilaiAkhirRepository.findById(id);

                if(result != null){
                    nilaiAkhirRepository.deleteById(id);
                    NilaiAkhirResponse response = new NilaiAkhirResponse("ok", "Berhasil hapus data");
                    return new Gson().toJson(response);
                } else {
                    NilaiAkhirResponse response = new NilaiAkhirResponse("error", "Data tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else if (roles.equals("[\"Peserta\"]")){
                NilaiAkhir result = nilaiAkhirRepository.findById(id);
                if (result != null){
                    nilaiAkhirRepository.deleteById(id);
                    NilaiAkhirResponse response = new NilaiAkhirResponse("ok", "Berhasil menghapus data");
                    return new Gson().toJson(response);
                } else {
                    NilaiAkhirResponse response = new NilaiAkhirResponse("error", "Data tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else {
                NilaiAkhirResponse response = new NilaiAkhirResponse("error", "Tidak ada hak akses !");
                return new Gson().toJson(response);
            }
        }
    }

}