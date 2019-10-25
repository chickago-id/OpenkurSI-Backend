package backend.controller;

import backend.repository.KategoriNilaiRepository;
import backend.model.KategoriNilai;
import backend.response.KategoriNilaiResponse;

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
    public String index(@Nullable Authentication auth) {
        try {
            if (auth == null) {
                KategoriNilaiResponse response = new KategoriNilaiResponse(
                    "ERROR", 
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    List<KategoriNilai> result = kategoriNilaiRepository.findAll();
                    if (result != null) {
                    KategoriNilaiResponse response = new KategoriNilaiResponse(
                        "OK", 
                        "GET DATA SUCCESS",
                        result
                    );
                    return new Gson().toJson(response);
                    } else {
                        KategoriNilaiResponse response = new KategoriNilaiResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    KategoriNilaiResponse response = new KategoriNilaiResponse(
                        "ERROR", 
                        "GET DATA FAILED NOT ADMIN OR PENGAJAR"
                    );
                    return new Gson().toJson(response);           
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiResponse response = new KategoriNilaiResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body KategoriNilai kategoriNilai, @Nullable Authentication auth) {
        try {
            if(auth == null) {
                KategoriNilaiResponse response = new KategoriNilaiResponse(
                    "ERROR", 
                    "POST DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    Object objectId = auth.getAttributes().get("userId");
                    Long id = Long.parseLong(objectId.toString());
                    kategoriNilai.setCreated_by(id);
                    KategoriNilai result = kategoriNilaiRepository.save(kategoriNilai);
                    KategoriNilaiResponse response = new KategoriNilaiResponse(
                        "OK", 
                        "POST DATA SUCCESS", 
                        result
                    );
                    return new Gson().toJson(response);
                } else {
                    KategoriNilaiResponse response = new KategoriNilaiResponse(
                        "ERROR", 
                        "POST DATA FAILED NOT ADMIN"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiResponse response = new KategoriNilaiResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                KategoriNilaiResponse response = new KategoriNilaiResponse(
                    "ERROR", 
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    KategoriNilai result = kategoriNilaiRepository.findById(id);
                    if (result != null) {
                        KategoriNilaiResponse response = new KategoriNilaiResponse(
                            "OK", 
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiResponse response = new KategoriNilaiResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    KategoriNilaiResponse response = new KategoriNilaiResponse(
                        "ERROR", 
                        "GET DATA FAILED NOT Admin OR Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            KategoriNilaiResponse response = new KategoriNilaiResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id_kategori_nilai}") 
    @Secured("isAnonymous()")
    public String update(Integer id_kategori_nilai, @Body KategoriNilai kategoriNilai, @Nullable Authentication authentication) {             
        try {
            if(authentication == null) {
                KategoriNilaiResponse response = new KategoriNilaiResponse(
                    "ERROR", 
                    "PUT DATA FAILED NOT SIGNED IN", 
                    kategoriNilai
                );
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]") || roles.equals("[\"Pengajar\"]")) {
                    Object dataId = authentication.getAttributes().get("userId");
                    Long user_id = Long.parseLong(dataId.toString());
                    kategoriNilai.setUpdated_by(user_id);
                    KategoriNilai result = kategoriNilaiRepository.update(id_kategori_nilai, kategoriNilai);
                    if(result != null) {
                        KategoriNilaiResponse response = new KategoriNilaiResponse(
                            "OK", 
                            "PUT DATA SUCCESS", 
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiResponse response = new KategoriNilaiResponse(
                            "ERROR", 
                            "PUT DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    KategoriNilaiResponse response = new KategoriNilaiResponse(
                        "ERROR", 
                        "PUT DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                } 
            }    
        } catch (Exception e) {
            String msg = e.getMessage();
            KategoriNilaiResponse response = new KategoriNilaiResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Integer id, @Nullable Authentication authentication) {
        try {
            if(authentication == null) {
                KategoriNilaiResponse response = new KategoriNilaiResponse(
                    "ERROR", 
                    "DELETE DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    KategoriNilai result = kategoriNilaiRepository.findById(id);
                    if(result != null) {
                        kategoriNilaiRepository.deleteById(id);
                        KategoriNilaiResponse response = new KategoriNilaiResponse(
                            "OK", 
                            "DELETE DATA SUCCESS"
                        );
                        return new Gson().toJson(response);
                    } else {
                        KategoriNilaiResponse response = new KategoriNilaiResponse(
                            "ERROR", 
                            "DELETE DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    KategoriNilaiResponse response = new KategoriNilaiResponse(
                        "ERROR", 
                        "DELETE DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }    
        } catch (Exception e) {
            String msg = e.getMessage();
            KategoriNilaiResponse response = new KategoriNilaiResponse(
                "EXCEPTION ERROR", 
                msg
            );
            return new Gson().toJson(response);
        }
        
    }
}
