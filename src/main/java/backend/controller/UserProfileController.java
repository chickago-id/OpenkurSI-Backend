package backend.controller;

import backend.model.UserProfile;
import backend.model.UserProfileResponse;
import backend.repository.UserProfileRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.http.MediaType;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.annotation.Secured;
import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Validated
@Controller("/userprofile")
@Secured("isAnonymous()")
public class UserProfileController {

    private UserProfileRepository userProfileRepository;
    // private KelasRepository kelasRepository;

    // public KelasController(KelasRepository kelasRepository) {
    // this.kelasRepository = kelasRepository;
    // }
    public UserProfileController(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<UserProfile> userProfile = userProfileRepository.findAll();
            // List<Kelas> kelas = kelasRepository.findAll();
            UserProfileResponse response = new UserProfileResponse("ok", "Data User Profile", userProfile);
            // KelasResponse response = new KelasResponse("ok", "Data kelas", kelas);

            return new Gson().toJson(response);
        } catch (Exception e) {
            String message = e.getMessage();
            UserProfileResponse response = new UserProfileResponse("error", message);
            // KelasResponse response = new KelasResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")

    public String create(@Body UserProfile userProfile, @Nullable Authentication authentication) {
        try {

            if (authentication == null) {
                UserProfileResponse response = new UserProfileResponse("error", "Selain admin dilarang create");
                // KelasResponse response = new KelasResponse("error", "Bukan admin, anda tidak
                // boleh posting.");

                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if (roles.equals("[\"Admin\"]")) {
                    UserProfile result = userProfileRepository.save(userProfile);
                    // Kelas result = kelasRepository.save(kelas);

                    UserProfileResponse response = new UserProfileResponse("ok", "Berhasil menambah data", result);
                    // KelasResponse response = new KelasResponse("ok", "Berhasil menambahkan data
                    // kelas", result);

                    return new Gson().toJson(response);
                }

                else {
                    UserProfileResponse response = new UserProfileResponse("error",
                            "Anda dilarang mengakses halaman ini");
                    // KelasResponse response = new KelasResponse("error", "Anda tidak boleh
                    // mengakses halaman ini.");

                    return new Gson().toJson(response);
                }
            }

        } catch (Exception e) {
            String message = e.getMessage();

            UserProfileResponse response = new UserProfileResponse("error", message);
            // KelasResponse response = new KelasResponse("error", message);

            return new Gson().toJson(response);
        }
    }

    @Get("/{id_user_profile}")
    @Secured("isAnonymous()")
    public String show(Long id_user_profile) {

        try {

            UserProfile userProfile = userProfileRepository.findById(id_user_profile);
            // Kelas kelas = kelasRepository.findById(id);

            if (userProfile != null) {
                UserProfileResponse response = new UserProfileResponse("ok", "Data Profil User", userProfile);
                // KelasResponse response = new KelasResponse("ok", "Data kelas", kelas);

                return new Gson().toJson(response);
            } else {
                UserProfileResponse response = new UserProfileResponse("error", "Data tidak ditemukan");
                // KelasResponse response = new KelasResponse("error", "Data kelas tidak
                // ditemukan");

                return new Gson().toJson(response);
            }

        } catch (Exception e) {

            String message = e.getMessage();

            UserProfileResponse response = new UserProfileResponse("error", message);
            // KelasResponse response = new KelasResponse("error", message);

            return new Gson().toJson(response);
        }

    }

    @Post("/{id_user_profile}")
    @Secured("isAnonymous()")
    public String update(@Body UserProfile userProfile, @Nullable Authentication authentication) {

        if (authentication == null) {
            UserProfileResponse response = new UserProfileResponse("error", "Selain admin dilarang update data");
            // KelasResponse response = new KelasResponse("error", "Bukan admin, anda tidak
            // boleh update data.");

            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if (roles.equals("[\"Admin\"]")) {
                UserProfile result = userProfileRepository.update(userProfile);
                // Kelas result = kelasRepository.update(kelas);

                if (result != null) {
                    UserProfileResponse response = new UserProfileResponse("ok", "Berhasil memperbaharui data", result);
                    // KelasResponse response = new KelasResponse("ok", "Berhasil memperbarui data
                    // kelas", result);

                    return new Gson().toJson(response);
                } else {
                    UserProfileResponse response = new UserProfileResponse("error", "Data tidak ditemukan");
                    // KelasResponse response = new KelasResponse("error", "Data kelas tidak
                    // ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                UserProfileResponse response = new UserProfileResponse("error", "Dilarang mengakses halaman ini");
                // KelasResponse response = new KelasResponse("error", "Anda tidak boleh
                // mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }

    @Delete("/{id_user_profile}")
    @Secured("isAnonymous()")
    public String delete(Long id_user_profile, @Nullable Authentication authentication) {
        if (authentication == null) {
            UserProfileResponse response = new UserProfileResponse("error", "Selain admin tidak bisa menghapus data");
            // KelasResponse response = new KelasResponse("error", "Bukan admin, anda tidak
            // boleh hapus data.");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if (roles.equals("[\"Admin\"]")) {
                UserProfile getKelasPeserta = userProfileRepository.findById(id_user_profile);
                // Kelas getMateri = kelasRepository.findById(id);

                if (getKelasPeserta != null) {
                    userProfileRepository.deleteById(id_user_profile);
                    // kelasRepository.deleteById(id);
                    UserProfileResponse response = new UserProfileResponse("ok", "Data berhasil dihapus");
                    // KelasResponse response = new KelasResponse("ok", "Berhasil menghapus data
                    // kelas");

                    return new Gson().toJson(response);

                } else {
                    UserProfileResponse response = new UserProfileResponse("error", "Data tidak ditemukan");
                    // KelasResponse response = new KelasResponse("error", "Data kelas tidak
                    // ditemukan");

                    return new Gson().toJson(response);
                }
            } else {
                UserProfileResponse response = new UserProfileResponse("error", "Dilarang mengakses halaman ini");
                // KelasResponse response = new KelasResponse("error", "Anda tidak boleh
                // mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }

}
