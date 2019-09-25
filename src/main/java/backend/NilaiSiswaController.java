package backend;

import backend.model.NilaiSiswa;
import backend.model.NilaiSiswaResponse;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.annotation.Secured;
import javax.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

@Validated
@Controller("/nilai-siswa")
@Secured("isAnonymous()")
public class NilaiSiswaController {

    private NilaiSiswaRepository nilaiSiswaRepository;

    public NilaiSiswaController(NilaiSiswaRepository nilaiSiswaRepository) {
        this.nilaiSiswaRepository = nilaiSiswaRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<NilaiSiswa> nilaiSiswa = nilaiSiswaRepository.findAll();
            NilaiSiswaResponse response = new NilaiSiswaResponse("ok", "Data peserta kelas", nilaiSiswa);

            return new Gson().toJson(response);
        } catch(Exception e) {
            String message = e.getMessage();
            NilaiSiswaResponse response = new NilaiSiswaResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body NilaiSiswa nilaiSiswa, @Nullable Authentication authentication) {
        try {

            if(authentication == null) {
                NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Belum Login, anda tidak boleh posting.");

                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if(roles.equals("[\"Admin\"]")) {
                    NilaiSiswa result = nilaiSiswaRepository.save(nilaiSiswa);
                    
                    NilaiSiswaResponse response = new NilaiSiswaResponse("ok", "Berhasil menambahkan data nilai siswa", result);

                    return new Gson().toJson(response);
                }
                else if(roles.equals("[\"Peserta\"]")) {
                    NilaiSiswa result = nilaiSiswaRepository.save(nilaiSiswa);
                    
                    NilaiSiswaResponse response = new NilaiSiswaResponse("ok", "Berhasil menambahkan data nilai siswa", result);

                    return new Gson().toJson(response);
                }
                
                else {
                    NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Anda tidak boleh mengakses halaman ini.");

                    return new Gson().toJson(response);
                }
            }

            

        } catch(Exception e) {
            String message = e.getMessage();

            NilaiSiswaResponse response = new NilaiSiswaResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id_nilai_siswa}")
    @Secured("isAnonymous()")
    public String show(Integer id_nilai_siswa) {

        try {

            NilaiSiswa nilaiSiswa = nilaiSiswaRepository.findById(id_nilai_siswa);

            if(nilaiSiswa != null) {
                NilaiSiswaResponse response = new NilaiSiswaResponse("ok", "Data nilai siswa", nilaiSiswa);

                return new Gson().toJson(response);
            } else {
                NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Data nilai siswa tidak ditemukan");

                return new Gson().toJson(response);
            } 

        } catch(Exception e) {

            String message = e.getMessage();

            NilaiSiswaResponse response = new NilaiSiswaResponse("error", message);

            return new Gson().toJson(response);
        }
        
    }

    @Post("/{id_nilai_siswa}") 
    @Secured("isAnonymous()")
    public String update(Integer id_nilai_siswa, @Body NilaiSiswa nilaiSiswa, @Nullable Authentication authentication) {
      
       
        if(authentication == null) {
            NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Bukan admin, anda tidak boleh update data.");

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                NilaiSiswa result = nilaiSiswaRepository.update(id_nilai_siswa, nilaiSiswa);

                if(result != null) {
                    NilaiSiswaResponse response = new NilaiSiswaResponse("ok", "Berhasil memperbarui data nilai siswa", result);

                    return new Gson().toJson(response);
                } else {
                    NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Data nilai siswa tidak ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            } 
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication) {
        if(authentication == null) {
            NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Bukan admin, anda tidak boleh hapus data.");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if(roles.equals("[\"Admin\"]")) {
                NilaiSiswa result = nilaiSiswaRepository.findById(id);
                         
                if(result != null) {
                    nilaiSiswaRepository.deleteById(id);

                    NilaiSiswaResponse response = new NilaiSiswaResponse("ok", "Berhasil menghapus data nilai siswa");

                    return new Gson().toJson(response);

                } else {
                    NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Data nilai siswa tidak ditemukan");

                    return new Gson().toJson(response);
                }
            }
            else if(roles.equals("[\"Peserta\"]")) {
                NilaiSiswa result = nilaiSiswaRepository.findById(id);
                         
                if(result != null) {
                    nilaiSiswaRepository.deleteById(id);

                    NilaiSiswaResponse response = new NilaiSiswaResponse("ok", "Berhasil menghapus data nilai siswa");

                    return new Gson().toJson(response);

                } else {
                    NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Data nilai siswa tidak ditemukan");

                    return new Gson().toJson(response);
                }
            }
            
            
            else {
                NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }
}
