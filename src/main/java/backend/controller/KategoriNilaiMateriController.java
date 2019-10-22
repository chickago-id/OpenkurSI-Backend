package backend.controller;

import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import backend.model.KategoriNilaiMateri;
import backend.repository.KategoriNilaiMateriRepository;
import backend.response.KategoriNilaiMateriResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.validation.Validated;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Validated
@Controller("/kategori-nilai-materi")
@Secured("isAnonymous()")
public class KategoriNilaiMateriController {
    private KategoriNilaiMateriRepository repoKNM;
    public KategoriNilaiMateriController(KategoriNilaiMateriRepository repoKNM) {
        this.repoKNM = repoKNM;
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
                    List<KategoriNilaiMateri> result = repoKNM.findAll();
                    if (result.isEmpty()) {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "ERROR", 
                            "Data tidak ditemukan"
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "OK", 
                            "Data berhasil diambil",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "Anda tidak diizinkan mengakses data ini"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "EXCEPTION ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body KategoriNilaiMateri kategoriNilaiMateri, @Nullable Authentication auth) {
        try {
            if(auth == null) {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                    "ERROR", 
                    "Anda belum sign in"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    Integer id_materi = kategoriNilaiMateri.getId_materi();
                    Integer id_kategori_nilai = kategoriNilaiMateri.getId_kategori_nilai();
                    if (repoKNM.existsByIdMateriAndIdKategoriNilai(id_materi, id_kategori_nilai)) {
                        KategoriNilaiMateri result = repoKNM.save(kategoriNilaiMateri);
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "OK", 
                            "Berhasil menambahkan data kategori nilai", 
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "ERROR",
                            "Gagal menambahkan data: data sudah ada"
                        );
                        return new Gson().toJson(response);
                    }   
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "Anda tidak diizinkan mengakses data ini."
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "EXCEPTION ERROR", 
                message
            );
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
                    "Anda belum sign in"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    List<KategoriNilaiMateri> result = repoKNM.findByIdMateri(id_materi);
                    if (result.isEmpty()) {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "ERROR", 
                            "Data tidak ditemukan"
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "OK", 
                            "Data berhasil diambil",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "Anda tidak diizinkan mengakses data ini"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "EXCEPTION ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Long id, @Nullable Authentication auth) {
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
                    KategoriNilaiMateri result = repoKNM.findById(id);
                    if(result != null) {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "OK", 
                            "Data berhasil diambil", 
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "ERROR", 
                            "Data tidak ditemukan"
                        );
                        return new Gson().toJson(response);
                    }       
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "Anda tidak diizinkan mengakses data ini"
                    );
                    return new Gson().toJson(response);
                }
            } 
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "EXCEPTION ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id_kategori_nilai}") 
    @Secured("isAnonymous()")
    public String update(Long id_kategori_nilai, @Body KategoriNilaiMateri KategoriNilaiMateri, @Nullable Authentication auth) {
        try {
            if(auth == null) {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                    "ERROR", 
                    "Anda belum sign in", 
                    KategoriNilaiMateri
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    KategoriNilaiMateri result = repoKNM.update(id_kategori_nilai, KategoriNilaiMateri);
                    if(result != null) {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "OK", 
                            "Data berhasil diperbaharui", 
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "ERROR", 
                            "Data tidak ditemukan"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "Anda tidak diizinkan mengakses data ini."
                    );
                    return new Gson().toJson(response);
                } 
            }    
        } catch (Exception e) {
            String msg = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Long id, @Nullable Authentication auth) {
        if(auth == null) {
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "ERROR", 
                "Anda belum sign in"
            );
            return new Gson().toJson(response);
        } else {
            Object data = auth.getAttributes().get("roles");
            String roles = data.toString();
            if(roles.equals("[\"Admin\"]")) {
                KategoriNilaiMateri result = repoKNM.findById(id);                 
                if(result != null) {
                    repoKNM.deleteById(id);
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "OK", 
                        "Data berhasil dihapus"
                    );
                    return new Gson().toJson(response);
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "Data tidak ditemukan"
                    );
                    return new Gson().toJson(response);
                }
            } else {
                KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                    "ERROR", 
                    "Anda tidak diizinkan mengakses data ini."
                );
                return new Gson().toJson(response);
            }
        }
    }

    @Get("/materi/{id_materi}/kategori-nilai/{id_kategori_nilai}")
    @Secured("isAnonymous()")
    public String showByIdMateriAndIdKategoriNilai(Integer id_materi, Integer id_kategori_nilai, @Nullable Authentication auth) {
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
                    KategoriNilaiMateri result = repoKNM.findByIdMateriAndIdKategoriNilai(id_materi, id_kategori_nilai);
                    if(result != null) {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "OK", 
                            "Data berhasil diambil", 
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                            "ERROR", 
                            "Data tidak ditemukan"
                        );
                        return new Gson().toJson(response);
                    }       
                } else {
                    KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                        "ERROR", 
                        "Anda tidak diizinkan mengakses data ini"
                    );
                    return new Gson().toJson(response);
                }
            } 
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiMateriResponse response = new KategoriNilaiMateriResponse(
                "EXCEPTION ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }
}
