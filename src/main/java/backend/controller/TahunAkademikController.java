package backend.controller;

import backend.model.TahunAkademik;
import backend.model.TahunAkademikResponse;
import backend.repository.TahunAkademikRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import javax.annotation.Nullable;

import com.google.gson.Gson;
import java.util.List;

@Validated
@Controller("/tahunakademik")
@Secured("isAnonymous()")
public class TahunAkademikController {

    private TahunAkademikRepository tahunAkademikRepository;

    public TahunAkademikController(TahunAkademikRepository tahunAkademikRepository) {
        this.tahunAkademikRepository = tahunAkademikRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<TahunAkademik> tahunAkademik = tahunAkademikRepository.findAll();
            TahunAkademikResponse response = new TahunAkademikResponse("ok", "Data Tahaun Akademik", tahunAkademik);
            return new Gson().toJson(response);
        } catch (Exception e) {
            String message = e.getMessage();
            TahunAkademikResponse response = new TahunAkademikResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body TahunAkademik tahunAkademik, @Nullable Authentication authentication) {
        try {

            if (authentication == null) {
                TahunAkademikResponse response = new TahunAkademikResponse("error", "Unauthorized user");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if (roles.equals("[\"Admin\"]")) {
                    TahunAkademik result = tahunAkademikRepository.save(tahunAkademik);
                    TahunAkademikResponse response = new TahunAkademikResponse("ok", "Berhasil menambahkan data",
                            result);
                    return new Gson().toJson(response);
                } else {
                    TahunAkademikResponse response = new TahunAkademikResponse("error",
                            "Anda tidak boleh mengakses halaman ini");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            TahunAkademikResponse response = new TahunAkademikResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id_tahun_akademik}")
    @Secured("isAnonymous()")
    public String show(Long id_tahun_akademik) {

        try {
            TahunAkademik tahunAkademik = tahunAkademikRepository.findById(id_tahun_akademik);

            if (tahunAkademik != null) {
                TahunAkademikResponse response = new TahunAkademikResponse("ok", "Data Tahun Akademik", tahunAkademik);
                return new Gson().toJson(response);
            } else {
                TahunAkademikResponse response = new TahunAkademikResponse("error",
                        "Data Tahun Akademik tidak ditemukan");
                return new Gson().toJson(response);
            }

        } catch (Exception e) {

            String message = e.getMessage();
            TahunAkademikResponse response = new TahunAkademikResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/{id_tahun_akademik}")
    @Secured("isAnonymous()")
    public String update(@Body TahunAkademik tahunAkademik, @Nullable Authentication authentication) {

        if (authentication == null) {
            TahunAkademikResponse response = new TahunAkademikResponse("error", "Unauthorized User");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if (roles.equals("[\"Admin\"]")) {
                TahunAkademik result = tahunAkademikRepository.update(tahunAkademik);
                if (result != null) {
                    TahunAkademikResponse response = new TahunAkademikResponse("ok",
                            "Berhasil memperbaharui data Tahun Akademik", result);
                    return new Gson().toJson(response);
                } else {
                    TahunAkademikResponse response = new TahunAkademikResponse("error",
                            "Data Tahun Akademik tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else {
                TahunAkademikResponse response = new TahunAkademikResponse("error",
                        "Anda tidak boleh mengakses halaman ini");
                return new Gson().toJson(response);
            }
        }
    }

    @Delete("/{id_tahun_akademik}")
    @Secured("isAnonymous()")
    public String delete(Long id_tahun_akademik, @Nullable Authentication authentication) {
        if (authentication == null) {
            TahunAkademikResponse response = new TahunAkademikResponse("error", "Unauthorized user");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if (roles.equals("[\"Admin\"]")) {
                TahunAkademik getTahunAkademik = tahunAkademikRepository.findById(id_tahun_akademik);

                if (getTahunAkademik != null) {
                    tahunAkademikRepository.deleteById(id_tahun_akademik);
                    TahunAkademikResponse response = new TahunAkademikResponse("ok",
                            "Berhasil menghapus data Tahun Akademik");
                    return new Gson().toJson(response);

                } else {
                    TahunAkademikResponse response = new TahunAkademikResponse("error",
                            "Data Tahun Akademik tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else {
                TahunAkademikResponse response = new TahunAkademikResponse("error",
                        "Anda tidak boleh mengakses halaman ini");
                return new Gson().toJson(response);
            }
        }
    }
}
