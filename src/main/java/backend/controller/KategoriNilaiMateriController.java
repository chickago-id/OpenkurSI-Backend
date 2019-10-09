package backend.controller;

import backend.repository.KategoriNilaiMateriRepository;
import backend.model.KategoriNilaiMateri;
import backend.model.KategoriNilaiMateriResponse;
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
@Controller("/kategori-nilai-materi")
@Secured("isAnonymous()")
public class KategoriNilaiMateriController {
    private KategoriNilaiMateriRepository KategoriNilaiMateriRepository;
    public KategoriNilaiMateriController(KategoriNilaiMateriRepository KategoriNilaiMateriRepository) {
        this.KategoriNilaiMateriRepository = KategoriNilaiMateriRepository;
    }

    @Get("/")
    @Secured("isAnonymous()")
    public String index(@Nullable Authentication auth) {
        try {
            if (auth == null) {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                    "ERROR", 
                    "Anda belum sign in"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    List<KategoriNilaiMateri> result = KategoriNilaiMateriRepository.findAll();
                    if (result != null) {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "OK", 
                            "GET DATA findAll() SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "ERROR", 
                            "GET DATA findALL() FAILED: NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "GET DATA findAll() FAILED: NOT ADMIN"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body KategoriNilaiMateri KategoriNilaiMateri, @Nullable Authentication authentication) {
        try {
            if(authentication == null) {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse("error", "Belum Login, anda tidak boleh posting.");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    KategoriNilaiMateri result = KategoriNilaiMateriRepository.save(KategoriNilaiMateri);
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ok", 
                        "Berhasil menambahkan data kategori nilai", 
                        result
                        );
                    return new Gson().toJson(response);
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse("error", "Anda tidak boleh mengakses halaman ini.");
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/materi/{id_materi}")
    @Secured("isAnonymous()")
    public String showByIdMateri(Integer id_materi, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                    "ERROR", 
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    List<KategoriNilaiMateri> result = KategoriNilaiMateriRepository.findByIdMateri(id_materi);
                    if (result != null) {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "OK", 
                            "GET DATA findByIdMater() SUCCESS", 
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "ERROR", 
                            "GET DATA findByIdMateri() FAILED: NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "GET DATA FAILED: NOT ADMIN"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    /* @Get("/materi/sum/{id_materi}")
    @Secured("isAnonymous()")
    public String showSumOfBobotNilai(Long id_materi) {
        try {
            KategoriNilaiMateri kategoriNilaiMateri = KategoriNilaiMateriRepository.sumOfMateri(id_materi);
            if (kategoriNilaiMateri != null) {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse("ok", "Data Kategori nilai materi", kategoriNilaiMateri);
                return new Gson().toJson(response);
            } else {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse("error", "Data kategori nilai tidak ditemukan");
                return new Gson().toJson(response);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse("error", message);
            return new Gson().toJson(response);
        }
    } */

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Long id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                    "ERROR", 
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    KategoriNilaiMateri result = KategoriNilaiMateriRepository.findById(id);
                    if(result != null) {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "OK", 
                            "GET DATA findById() SUCCESS", 
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "ERROR", 
                            "GET DATA findById() FAILED: NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }       
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "GET DATA findById() FAILED: NOT ADMIN"
                    );
                    return new Gson().toJson(response);
                }
            } 
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id_kategori_nilai}") 
    @Secured("isAnonymous()")
    public String update(Long id_kategori_nilai, @Body KategoriNilaiMateri KategoriNilaiMateri, @Nullable Authentication authentication) {
        if(authentication == null) {
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "error", 
                "Bukan admin, anda tidak boleh update data.", 
                KategoriNilaiMateri
            );
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();
            if(roles.equals("[\"Admin\"]")) {
                KategoriNilaiMateri result = KategoriNilaiMateriRepository.update(id_kategori_nilai, KategoriNilaiMateri);
                if(result != null) {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ok", 
                        "Berhasil memperbarui data kategori nilai", 
                        result
                    );
                    return new Gson().toJson(response);
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "error", 
                        "Data kategori nilai tidak ditemukan"
                    );
                    return new Gson().toJson(response);
                }
            } else {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                    "error", 
                    "Anda tidak boleh mengakses halaman ini."
                );
                return new Gson().toJson(response);
            } 
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Long id, @Nullable Authentication authentication) {
        if(authentication == null) {
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "error", 
                "Bukan admin, anda tidak boleh hapus data."
            );
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();
            if(roles.equals("[\"Admin\"]")) {
                KategoriNilaiMateri result = KategoriNilaiMateriRepository.findById(id);                 
                if(result != null) {
                    KategoriNilaiMateriRepository.deleteById(id);
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ok", 
                        "Berhasil menghapus data kategori nilai"
                    );
                    return new Gson().toJson(response);
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "error", 
                        "Data kategori nilai tidak ditemukan"
                    );
                    return new Gson().toJson(response);
                }
            }
            else if(roles.equals("[\"Peserta\"]")) {
                KategoriNilaiMateri result = KategoriNilaiMateriRepository.findById(id);   
                if(result != null) {
                    KategoriNilaiMateriRepository.deleteById(id);
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ok", 
                        "Berhasil menghapus data kategori nilai"
                    );
                    return new Gson().toJson(response);
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "error", 
                        "Data kategori nilai tidak ditemukan"
                    );
                    return new Gson().toJson(response);
                }
            } else {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                    "error", 
                    "Anda tidak boleh mengakses halaman ini."
                );
                return new Gson().toJson(response);
            }
        }
    }
}
