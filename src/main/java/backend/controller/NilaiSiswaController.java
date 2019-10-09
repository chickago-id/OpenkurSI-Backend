package backend.controller;

import backend.model.NilaiSiswa;
import backend.model.NilaiSiswaResponse;
import backend.repository.NilaiSiswaRepository;


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
@Controller("/nilai-siswa")
@Secured("isAnonymous()")
public class NilaiSiswaController {

    private NilaiSiswaRepository nilaiSiswaRepository;

    public NilaiSiswaController(NilaiSiswaRepository nilaiSiswaRepository) {
        this.nilaiSiswaRepository = nilaiSiswaRepository;
    }

    @Get("/")
    @Secured("isAnonymous()")
    public String index(@Nullable Authentication auth) {
        try {
            if (auth == null) {
                NilaiSiswaResponse response = new NilaiSiswaResponse(
                    "ERROR", 
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    List<NilaiSiswa> result = nilaiSiswaRepository.findAll();
                    if (result != null) {
                        NilaiSiswaResponse response = new NilaiSiswaResponse(
                        "OK", 
                        "GET DATA SUCCESS",
                        result
                    );
                    return new Gson().toJson(response);
                    } else {
                        NilaiSiswaResponse response = new NilaiSiswaResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    NilaiSiswaResponse response = new NilaiSiswaResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Admin OR Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
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
                NilaiSiswaResponse response = new NilaiSiswaResponse(
                    "ERROR", 
                    "POST FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    NilaiSiswa result = nilaiSiswaRepository.save(nilaiSiswa);
                    NilaiSiswaResponse response = new NilaiSiswaResponse(
                        "OK", 
                        "POST SUCCESS", 
                        result
                    );
                    return new Gson().toJson(response);
                } else {
                    NilaiSiswaResponse response = new NilaiSiswaResponse(
                        "ERROR", 
                        "POST FAILED NOT Admin OR Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            NilaiSiswaResponse response = new NilaiSiswaResponse("ERROR", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id_nilai_siswa}")
    @Secured("isAnonymous()")
    public String show(Long id_nilai_siswa, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                NilaiSiswaResponse response = new NilaiSiswaResponse(
                    "ERROR", 
                    "GET FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]") || roles.equals("[\"Peserta\"]")) {
                    NilaiSiswa result = nilaiSiswaRepository.findById(id_nilai_siswa);
                    if (result != null) {
                        NilaiSiswaResponse response = new NilaiSiswaResponse(
                            "OK", 
                            "GET SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        NilaiSiswaResponse response = new NilaiSiswaResponse(
                            "ERROR", 
                            "GET FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    NilaiSiswaResponse response = new NilaiSiswaResponse(
                        "ERROR", 
                        "GET FAILED DON'T HAVE ACCESS"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            NilaiSiswaResponse response = new NilaiSiswaResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Put("/{id_nilai_siswa}") 
    @Secured("isAnonymous()")
    public String update(Long id_nilai_siswa, @Body NilaiSiswa nilaiSiswa, @Nullable Authentication authentication) {
        if(authentication == null) {
            NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Bukan admin, anda tidak boleh update data.");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();
            if(roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
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
    public String delete(Long id, @Nullable Authentication authentication) {
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
            } else {
                NilaiSiswaResponse response = new NilaiSiswaResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }

    @Get("/kelas/{id_kelas}")
    @Secured("isAnonymous()")
    public String showByIdKelas(Long id_kelas, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                NilaiSiswaResponse response = new NilaiSiswaResponse(
                    "ERROR",
                    "GET FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    List<NilaiSiswa> result = nilaiSiswaRepository.findByIdKelas(id_kelas);
                    if (result != null) {
                        NilaiSiswaResponse response = new NilaiSiswaResponse(
                            "OK",
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        NilaiSiswaResponse response = new NilaiSiswaResponse(
                            "ERROR",
                            "GET FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    NilaiSiswaResponse response = new NilaiSiswaResponse(
                        "ERROR",
                        "GET FAILED DON'T HAVE ACCESS"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            NilaiSiswaResponse response = new NilaiSiswaResponse(
                "ERROR",
                msg
            );
            return new Gson().toJson(response);
        }
    }
}
