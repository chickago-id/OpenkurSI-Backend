package backend.controller;

import backend.model.MasterKelas;
import backend.model.MasterKelasResponse;
import backend.repository.MasterKelasRepository;
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
@Controller("/masterkelas")
@Secured("isAnonymous()")
public class MasterKelasController {

    private MasterKelasRepository masterKelasRepository;

    public MasterKelasController(MasterKelasRepository masterKelasRepository) {
        this.masterKelasRepository = masterKelasRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<MasterKelas> masterKelas = masterKelasRepository.findAll();
            MasterKelasResponse response = new MasterKelasResponse("ok", "Data Master kelas", masterKelas);

            return new Gson().toJson(response);
        } catch (Exception e) {
            String message = e.getMessage();
            MasterKelasResponse response = new MasterKelasResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body MasterKelas masterKelas, @Nullable Authentication authentication) {
        try {

            if (authentication == null) {
                MasterKelasResponse response = new MasterKelasResponse("error", "Unauthorized User");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if (roles.equals("[\"Admin\"]")) {
                    MasterKelas result = masterKelasRepository.save(masterKelas);
                    MasterKelasResponse response = new MasterKelasResponse("ok", "Berhasil menambahkan data materi",
                            result);

                    return new Gson().toJson(response);
                } else {
                    MasterKelasResponse response = new MasterKelasResponse("error",
                            "Anda tidak boleh mengakses halaman ini.");

                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            MasterKelasResponse response = new MasterKelasResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{kode_kelas}")
    @Secured("isAnonymous()")
    public String show(String kode_kelas) {

        try {
            MasterKelas masterKelas = masterKelasRepository.findById(kode_kelas);

            if (masterKelas != null) {
                MasterKelasResponse response = new MasterKelasResponse("ok", "Data Master Kelas", masterKelas);
                return new Gson().toJson(response);
            } else {
                MasterKelasResponse response = new MasterKelasResponse("error", "Data materi tidak ditemukan");
                return new Gson().toJson(response);
            }

        } catch (Exception e) {

            String message = e.getMessage();
            MasterKelasResponse response = new MasterKelasResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/{kode_kelas}")
    @Secured("isAnonymous()")
    public String update(@Body MasterKelas masterKelas, @Nullable Authentication authentication) {

        if (authentication == null) {
            MasterKelasResponse response = new MasterKelasResponse("error", "Unauthorized user");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if (roles.equals("[\"Admin\"]")) {
                MasterKelas result = masterKelasRepository.update(masterKelas);

                if (result != null) {
                    MasterKelasResponse response = new MasterKelasResponse("ok", "Berhasil memperbaharui data materi",
                            result);
                    return new Gson().toJson(response);
                } else {
                    MasterKelasResponse response = new MasterKelasResponse("error",
                            "Data Master Kelas tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else {
                MasterKelasResponse response = new MasterKelasResponse("error",
                        "Anda tidak boleh mengakses halaman ini");
                return new Gson().toJson(response);
            }
        }
    }

    @Delete("/{kode_kelas}")
    @Secured("isAnonymous()")
    public String delete(String kode_kelas, @Nullable Authentication authentication) {
        if (authentication == null) {
            MasterKelasResponse response = new MasterKelasResponse("error", "Unauthorized user");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if (roles.equals("[\"Admin\"]")) {
                MasterKelas getMasterKelas = masterKelasRepository.findById(kode_kelas);

                // MasterKelas getKelas = masterKelasRepository.findById(kode_kelas);

                if (getMasterKelas != null) {
                    masterKelasRepository.deleteById(kode_kelas);
                    MasterKelasResponse response = new MasterKelasResponse("ok",
                            "Berhasil menghapus data master kelas");
                    return new Gson().toJson(response);

                } else {
                    MasterKelasResponse response = new MasterKelasResponse("error",
                            "Data master kelas tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else {
                MasterKelasResponse response = new MasterKelasResponse("error",
                        "Anda tidak boleh mengakses halaman ini");
                return new Gson().toJson(response);
            }
        }
    }
}
