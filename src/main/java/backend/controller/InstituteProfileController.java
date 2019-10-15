package backend.controller;

import backend.repository.InstituteProfileRepository;
import backend.model.InstituteProfile;
import backend.model.InstituteProfileResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.annotation.Secured;
import javax.annotation.Nullable;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Validated
@Controller("/institute-profile")
@Secured("isAnonymous()")
public class InstituteProfileController {
    public static String DIR_PATH = "/home/akbarlaz/Code/Project/OpenKurSi/" + 
        "colab/openkursi/openCoursev2-FrontEnd/public/images";
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
        String kota
        ) {
        try {
            Date date = new Date();
            String fileName = file.getFilename() + date + ".png";
            Path filePath = Files.createTempFile(Paths.get(DIR_PATH), file.getFilename(), date + ".png" );
            InputStream input = file.getInputStream(); 
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
            InstituteProfile objIP = new InstituteProfile();
            objIP.setNama_institusi(nama_institusi); objIP.setAlamat(alamat); objIP.setKode_pos(kode_pos);
            objIP.setNo_telepon(no_telepon); objIP.setWebsite(website); objIP.setEmail(email);
            objIP.setNpwp(npwp); objIP.setKode_institusi(kode_institusi); objIP.setNegara(negara);
            objIP.setProvinsi(provinsi); objIP.setKota(kota); objIP.setLogo(fileName);
            InstituteProfile result = repoIP.save(objIP);
            InstituteProfileResponse response = new InstituteProfileResponse(
                "OK", 
                "POST DATA SUCCESS",
                result
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
    }
    
    @Post("/{id}/logo")
    @Secured("isAnonymous()")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/json")
    public HttpResponse<String> uploadCompleted(Integer id, CompletedFileUpload file) { 
        InstituteProfile result = repoIP.findById(id);
        if (result != null) {
            try {
                File tempFile = File.createTempFile(file.getFilename(), "temp"); 
                Path path = Paths.get(tempFile.getAbsolutePath());
                Files.write(path, file.getBytes()); 
                return HttpResponse.ok("Uploaded");
            } catch (IOException exception) {
                return HttpResponse.badRequest("Upload Failed");
            }
        } else {
            return HttpResponse.notFound("Resource not found");
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

    @Put("/{id}") 
    @Secured("isAnonymous()")
    public String update(
        Integer id, 
        @Body InstituteProfile objIP, 
        @Nullable Authentication auth) {             
        try {
            if(auth == null) {
                InstituteProfileResponse response = new InstituteProfileResponse(
                    "ERROR", 
                    "PUT DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if(roles.equals("[\"Admin\"]")) {
                    InstituteProfile result = repoIP.update(id, objIP);
                    if(result != null) {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "OK", 
                            "PUT DATA SUCCESS", 
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        InstituteProfileResponse response = new InstituteProfileResponse(
                            "ERROR", 
                            "PUT DATA FAILED DATA NOT FOUND"
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
    public String delete(
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