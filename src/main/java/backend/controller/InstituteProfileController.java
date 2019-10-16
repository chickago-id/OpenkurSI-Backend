package backend.controller;

import backend.repository.InstituteProfileRepository;
import backend.model.InstituteProfile;
import backend.model.InstituteProfileResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.server.types.files.StreamedFile;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.annotation.Secured;
import javax.annotation.Nullable;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Validated
@Controller("/institute-profile")
@Secured("isAnonymous()")
public class InstituteProfileController {
    public static String DIR_PATH = "./";
    private InstituteProfileRepository repoIP;
    public InstituteProfileController(InstituteProfileRepository repoIP) {
        this.repoIP = repoIP;
    }

    @Post("/")
    @Secured("isAnonymous()")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String create(
        CompletedFileUpload file, 
        String nama_institusi,
        String alamat,
        String kode_pos,
        String no_telepon,
        String website,
        String email,
        String npwp,
        String kode_institusi,
        String negara,
        String provinsi,
        String kota,
        //Integer is_group,
        Authentication auth
        ) {
            try {
                if (auth == null) {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "POST DATA FAILED NOT SIGNED IN"
                    );
                    return new Gson().toJson(response);
                } else {
                    Object data = auth.getAttributes().get("roles");
                    String roles = data.toString();
                    if (roles.equals("[\"Admin\"]")) {
                        try {
                            File f = new File("images/"+kode_institusi+ file.getFilename());
                            if (!f.exists()) {
                                f.createNewFile();
                            }
                            OutputStream output = new FileOutputStream(f);
                            output.write(file.getBytes());
                            output.flush();
                            output.close();
                            InstituteProfile objIP = new InstituteProfile();
                            objIP.setNama_institusi(nama_institusi); 
                            objIP.setAlamat(alamat); 
                            objIP.setKode_pos(kode_pos);
                            objIP.setNo_telepon(no_telepon); 
                            objIP.setWebsite(website); 
                            objIP.setEmail(email);
                            objIP.setNpwp(npwp); 
                            objIP.setKode_institusi(kode_institusi); 
                            objIP.setNegara(negara);
                            objIP.setProvinsi(provinsi); 
                            objIP.setKota(kota); 
                            objIP.setLogo(f.getAbsolutePath());
                            //objIP.setIs_group(is_group);
                            InstituteProfile result = repoIP.save(objIP);
                            InstituteProfile result2 = repoIP.findById(result.getId());
                            result2.setDownload_path("http://localhost:8081/institute-profile/"+result2.getId().toString()+"/image");
                            InstituteProfile result3 = repoIP.update(result2.getId(), result2);
                            InstituteProfileResponse response = new InstituteProfileResponse(
                                "OK", 
                                "POST DATA SUCCESS",
                                result3
                            );
                            return new Gson().toJson(response);    
                        } catch (IOException e) {
                            String message = e.getMessage();
                            InstituteProfileResponse response = new InstituteProfileResponse(
                                "ERROR", 
                                message
                            );
                            return new Gson().toJson(response);
                        }
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "POST DATA FAILED NOT Admin"
                        );
                        return new Gson().toJson(response);
                    }
                }
            } catch (Exception e) {
                String msg = e.getMessage();
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    msg
                );
                return new Gson().toJson(response);
            }
        }
    
    @Get("/")
    @Secured("isAnonymous()")
    public String index(Authentication auth) {
        try {
            if (auth == null) {
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<InstituteProfile> result = repoIP.findAll();
                    if (result != null) {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "OK", 
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            InstituteProfileResponse response = new InstituteProfileResponse(
                "ERROR",
                msg
            );
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Integer id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    InstituteProfile result = repoIP.findById(id);
                    if (result != null) {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "OK", 
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "GET DATA FAILED NOT Admin OR Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch(Exception e) {
            String message = e.getMessage();
            InstituteProfileResponse response = new InstituteProfileResponse(
                "ERROR", 
                message
            );
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}/image")
    @Secured("isAnonymous()")
    @Produces({"image/jpeg,image/png"})
    public StreamedFile showImage(Integer id) throws IOException {
        try {
            InstituteProfile result = repoIP.findById(id);
            if(result != null) {
                File file = new File(result.getLogo());
                InputStream inputStream = new FileInputStream(file);
                return new StreamedFile(inputStream, file.getName());
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Put("/{id}")
    @Secured("isAnonymous()")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String update(
        CompletedFileUpload file,
        Integer id, 
        String nama_institusi,
        String alamat,
        String kode_pos,
        String no_telepon,
        String website,
        String email,
        String npwp,
        String kode_institusi,
        String negara,
        String provinsi,
        String kota,
        //Integer is_group,
        Authentication auth
    ) 
        {
            try {
                if (auth == null) {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "PUT DATA FAILED NOT SIGNED IN"
                    );
                    return new Gson().toJson(response);
                } else {
                    Object data = auth.getAttributes().get("roles");
                    String roles = data.toString();
                    if (roles.equals("[\"Admin\"]")) {
                        try {
                            InstituteProfile result = repoIP.findById(id);
                            if(result != null) {
                                File f = new File(result.getLogo());
                                if(!f.exists()) {
                                    f.createNewFile();
                                }
                                OutputStream output = new FileOutputStream(f);
                                output.write(file.getBytes());
                                output.flush();
                                output.close();
                                result.setId(id);
                                result.setNama_institusi(nama_institusi);
                                result.setAlamat(alamat);
                                result.setKode_pos(kode_pos);
                                result.setNo_telepon(no_telepon);
                                result.setWebsite(website); 
                                result.setEmail(email);
                                result.setNpwp(npwp); 
                                result.setKode_institusi(kode_institusi); 
                                result.setNegara(negara);
                                result.setProvinsi(provinsi); 
                                result.setKota(kota); 
                                result.setLogo(f.getAbsolutePath());
                                result.setDownload_path("http://localhost:8081/institute-profile/"+id.toString()+"/image");
                                InstituteProfile result2 = repoIP.update(id, result);
                                InstituteProfileResponse response = new InstituteProfileResponse(
                                    "OK", 
                                    "PUT DATA SUCCESS",
                                    result2
                                );
                                return new Gson().toJson(response);
                            } else {
                                InstituteProfileResponse response = new InstituteProfileResponse(
                                    "ERROR", 
                                    "PUT DATA FAILED NOT FOUND"
                                );
                                return new Gson().toJson(response);    
                            }   
                        } catch (IOException e) {
                            String message = e.getMessage();
                            InstituteProfileResponse response = new InstituteProfileResponse(
                                "ERROR", 
                                message
                            );
                            return new Gson().toJson(response);
                        }
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "PUT DATA FAILED NOT Admin"
                        );
                        return new Gson().toJson(response);
                    }
                }
            } catch (Exception e) {
                String msg = e.getMessage();
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    msg
                );
                return new Gson().toJson(response);
            }
        }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(@PathVariable(name = "id")
        Integer id, 
        @Nullable Authentication auth) {
        try {
            if(auth == null) {
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    "DELETE FAILED NOT SIGNED IN"
                    );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    InstituteProfile result = repoIP.findById(id);
                    if(result != null) {
                        repoIP.deleteById(id);
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "OK", 
                            "DELETE DATA SUCCESS"
                        );
                        return new Gson().toJson(response);
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "DELETE FAILED DATA NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    InstituteProfileResponse response = new InstituteProfileResponse(
                        "ERROR", 
                        "DELETE DATA FAILED NOT Admin");
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            InstituteProfileResponse response = new InstituteProfileResponse(
                "ERROR",
                msg
            );
            return new Gson().toJson(response);
        }            
    }
}