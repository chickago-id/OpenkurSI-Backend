package backend.controller;

import backend.repository.KategoriNilaiRepository;
import backend.model.KategoriNilai;
import backend.model.KategoriNilaiResponse;

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
@Controller("/kategori-nilai")
@Secured("isAnonymous()")
public class KategoriNilaiController {

    private KategoriNilaiRepository kategoriNilaiRepository;

    public KategoriNilaiController(KategoriNilaiRepository kategoriNilaiRepository) {
        this.kategoriNilaiRepository = kategoriNilaiRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<KategoriNilai> kategoriNilai = kategoriNilaiRepository.findAll();
            KategoriNilaiResponse response = new KategoriNilaiResponse("ok", "Data peserta kelas", kategoriNilai);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiResponse response = new KategoriNilaiResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body KategoriNilai kategoriNilai, @Nullable Authentication authentication) {
        try {

            if(authentication == null) {
                KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Belum Login, anda tidak boleh posting.");

                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    KategoriNilai result = kategoriNilaiRepository.save(kategoriNilai);
                    
                    KategoriNilaiResponse response = new KategoriNilaiResponse("ok", "Berhasil menambahkan data kategori nilai", result);

                    return new Gson().toJson(response);
                }
                else if(roles.equals("[\"Peserta\"]")) {
                    KategoriNilai result = kategoriNilaiRepository.save(kategoriNilai);
                    
                    KategoriNilaiResponse response = new KategoriNilaiResponse("ok", "Berhasil menambahkan data kategori nilai", result);

                    return new Gson().toJson(response);
                }
                
                else {
                    KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Anda tidak boleh mengakses halaman ini.");

                    return new Gson().toJson(response);
                }
            }

            

        } catch(Exception e) {
            String message = e.getMessage();

            KategoriNilaiResponse response = new KategoriNilaiResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id) {

        try {

            KategoriNilai kategoriNilai = kategoriNilaiRepository.findById(id);

            if(kategoriNilai != null) {
                KategoriNilaiResponse response = new KategoriNilaiResponse("ok", "Data kategori nilai", kategoriNilai);

                return new Gson().toJson(response);
            } else {
                KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Data kategori nilai tidak ditemukan");

                return new Gson().toJson(response);
            } 

        } catch(Exception e) {

            String message = e.getMessage();

            KategoriNilaiResponse response = new KategoriNilaiResponse("error", message);

            return new Gson().toJson(response);
        }
        
    }

    @Put("/{id_kategori_nilai}") 
    @Secured("isAnonymous()")
    public String update(Integer id_kategori_nilai, @Body KategoriNilai kategoriNilai, @Nullable Authentication authentication) {
             
        if(authentication == null) {
            KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Bukan admin, anda tidak boleh update data.", kategoriNilai);

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();
            

            if(roles.equals("[\"Admin\"]")) {
                KategoriNilai result = kategoriNilaiRepository.update(id_kategori_nilai, kategoriNilai);

                if(result != null) {
                    KategoriNilaiResponse response = new KategoriNilaiResponse("ok", "Berhasil memperbarui data kategori nilai", result);

                    return new Gson().toJson(response);
                } else {
                    KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Data kategori nilai tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            } 
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication) {
        if(authentication == null) {
            KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Bukan admin, anda tidak boleh hapus data.");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                KategoriNilai result = kategoriNilaiRepository.findById(id);
                         
                if(result != null) {
                    kategoriNilaiRepository.deleteById(id);

                    KategoriNilaiResponse response = new KategoriNilaiResponse("ok", "Berhasil menghapus data kategori nilai");

                    return new Gson().toJson(response);

                } else {
                    KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Data kategori nilai tidak ditemukan");

                    return new Gson().toJson(response);
                }
            }
            else if(roles.equals("[\"Peserta\"]")) {
                KategoriNilai result = kategoriNilaiRepository.findById(id);
                         
                if(result != null) {
                    kategoriNilaiRepository.deleteById(id);

                    KategoriNilaiResponse response = new KategoriNilaiResponse("ok", "Berhasil menghapus data kategori nilai");

                    return new Gson().toJson(response);

                } else {
                    KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Data kategori nilai tidak ditemukan");

                    return new Gson().toJson(response);
                }
            }
            
            
            else {
                KategoriNilaiResponse response = new KategoriNilaiResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }
}
