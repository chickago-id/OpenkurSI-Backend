package backend.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import backend.model.AttendanceToken;
import backend.model.Jadwal;
import backend.model.Kelas;
import backend.model.KelasResponse;
import backend.repository.AttendanceTokenRepository;
import backend.repository.KelasRepository;
import backend.response.AttendanceTokenResponse;
import backend.response.JadwalResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.types.files.StreamedFile;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.validation.Validated;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Validated
@Controller("/attendance-token")
@Secured("isAnonymous()")
public class AttendanceTokenController {
    private AttendanceTokenRepository repoAT;
    private KelasRepository repoKelas;

    public AttendanceTokenController(AttendanceTokenRepository repoAT, KelasRepository repoKelas) {
        this.repoAT = repoAT;
        this.repoKelas = repoKelas;
    }

    @Get("/")
    public String index(Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    List<AttendanceToken> result = repoAT.findAll();
                    if (result != null) {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK", 
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "GET DATA FAILED NOT FOUND", 
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERORR",
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "EXCEPTION ERROR", 
                "EXCEPTION ERROR: " + msg
            );
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Nullable Authentication auth, Integer id_jadwal) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "POST DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Pengajar\"]") || roles.equals("[\"Admin\"]")){
                    AttendanceToken objAT = new AttendanceToken();
                    objAT.setToken(UUID.randomUUID().toString().substring(0, 6));
                    objAT.setExpiryDate(30);
                    objAT.setId_jadwal(id_jadwal);
                    AttendanceToken result = repoAT.save(objAT);
                    if (result != null) {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK", 
                            "POST DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR", 
                            "POST DATA FAILED NULL",
                            result
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "POST DATA FAILED NOT Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "EXCEPTION ERROR", 
                "EXCEPTION ERROR: " + message
            );
            return new Gson().toJson(response);
        }
    }

    @Get("/{id}")
    @Secured("isAnonymous()")
    public String show(Long id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR", 
                    "NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AttendanceToken result = repoAT.findById(id);
                    if (result != null) {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK", 
                            "GET DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "GET DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "ERROR", 
                "EXCEPTION ERROR: " + msg
            );
            return new Gson().toJson(response);
        }
    }

    @Put("/{id}")
    @Secured("isAnonymous()")
    public String update(Long id, @Body AttendanceToken AttendanceToken, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "PUT DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AttendanceToken result = repoAT.update(id, AttendanceToken);
                    if (result != null) {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK", 
                            "PUT DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "PUT DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "PUT DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "EXCEPTION ERROR", 
                "ERROR: " + msg
            );
            return new Gson().toJson(response);
        }
    }

    @Delete("/{id}")
    @Secured("isAnonymous()")
    public String delete(Long id, @Nullable Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "DELETE DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object data = auth.getAttributes().get("roles");
                String roles = data.toString();
                if (roles.equals("[\"Admin\"]")) {
                    AttendanceToken result = repoAT.findById(id);
                    if (result != null) {
                        repoAT.deleteById(id);
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "OK", 
                            "DELETE DATA SUCCESS",
                            result
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "DELETE DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "DELETE DATA FAILED NOT Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "EXCEPTION ERROR", 
                "ERROR: " + msg);
            return new Gson().toJson(response);
        }
    }

    @Get(value = "/find/{token}")
    @Secured("isAnonymous()")
    public String findByToken(String token) {
        AttendanceToken result = repoAT.findByToken(token);
        if (result != null) {
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "OK", 
                "GET SUCCESS", 
                result
            );
            return new Gson().toJson(response);
        } else {
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "ERROR", 
                "NOT FOUND"
            );
            return new Gson().toJson(response);
        }

    }

    @Get(value = "/find/kelas")
    @Secured("isAnonymous()")
    public String showKelas(Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object dataRoles = auth.getAttributes().get("roles");
                String roles = dataRoles.toString();
                if (roles.equals("[\"Pengajar\"]")) {
                    Object data = auth.getAttributes().get("userId");
                    Long id_pengajar = Long.parseLong(data.toString());
                    List<Jadwal> dataKelas = repoAT.findByIdPengajar(id_pengajar);
                    if (!dataKelas.isEmpty()) {
                        JadwalResponse response = new JadwalResponse(
                            "OK", 
                            "GET DATA SUCCESS", 
                            dataKelas
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "GET DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    }
                } else if(roles.equals("[\"Admin\"]")) {
                    List<Kelas> dataKelas = repoKelas.findAll();
                    if(dataKelas.isEmpty()) {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR", 
                            "GET DATA FAILED NOT FOUND"
                        );
                        return new Gson().toJson(response);
                    } else {
                        KelasResponse response = new KelasResponse(
                            "OK ADMIN", 
                            "GET DATA SUCCESS",
                            dataKelas
                        );
                        return new Gson().toJson(response);
                    }
                }  else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Pengajar OR Admin"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "ERROR", 
                "Exception: " + e.getMessage()
            );
            return new Gson().toJson(response);
        }
    }

    @Get(value = "/find/{id_kelas}/materi")
    @Secured("isAnonymous()")
    public String showMateri(Long id_kelas, Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object dataRoles = auth.getAttributes().get("roles");
                String roles = dataRoles.toString();
                if (roles.equals("[\"Pengajar\"]") || roles.equals("[\"Admin\"]")) {
                    List<Jadwal> dataMateri = repoAT.findByIdKelas(id_kelas);
                    if (!dataMateri.isEmpty()) {
                        JadwalResponse response = new JadwalResponse(
                            "OK", 
                            "GET DATA SUCCESS", 
                            dataMateri
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "Kelas ini belum mempunyai jadwal");
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "ERROR", 
                "Exception: " + e.getMessage()
            );
            return new Gson().toJson(response);
        }
    }

    @Get(value = "/find/jadwal/{id_kelas}/{id_materi}")
    @Secured("isAnonymous()")
    public String showJadwal(Long id_kelas, Integer id_materi, Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse(
                    "ERROR",
                    "GET DATA FAILED NOT SIGNED IN"
                );
                return new Gson().toJson(response);
            } else {
                Object dataRoles = auth.getAttributes().get("roles");
                String roles = dataRoles.toString();
                if (roles.equals("[\"Pengajar\"]") || roles.equals("[\"Admin\"]")) {
                    Jadwal dataJadwal = repoAT.findByIdKelasAndIdMateri(id_kelas, id_materi);
                    if (dataJadwal != null) {
                        JadwalResponse response = new JadwalResponse(
                            "OK", 
                            "GET DATA SUCCESS", 
                            dataJadwal
                        );
                        return new Gson().toJson(response);
                    } else {
                        AttendanceTokenResponse response = new AttendanceTokenResponse(
                            "ERROR",
                            "Kelas ini belum mempunyai jadwal"
                        );
                        return new Gson().toJson(response);
                    }
                } else {
                    AttendanceTokenResponse response = new AttendanceTokenResponse(
                        "ERROR",
                        "GET DATA FAILED NOT Pengajar"
                    );
                    return new Gson().toJson(response);
                }
            }
        } catch (Exception e) {
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "ERROR", 
                "Exception: " + e.getMessage()
            );
            return new Gson().toJson(response);
        }
    }

    /* @Get(value = "/find/{id}/qrcode")
    @Secured("isAnonymous()")
    @Produces({"image/jpeg,image/png"})
    public StreamedFile showQR(Long id, Authentication auth) {
        try {
            if (auth == null) {
                AttendanceTokenResponse response = new AttendanceTokenResponse("ERROR",
                        "GET DATA FAILED NOT SIGNED IN");
                return null;
            } else {
                Object dataRoles = auth.getAttributes().get("roles");
                String roles = dataRoles.toString();
                if (roles.equals("[\"Pengajar\"]")) {
                    AttendanceToken result = repoAT.findById(id);

                    generateQRCodeImage(result.getToken(), 350, 350, "images/qr/" + result.getId());
                    if(result != null) {
                        File file = new File("images/qr/" + result.getId());
                        InputStream inputStream = new FileInputStream(file);
                        return new StreamedFile(inputStream, file.getName());
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            AttendanceTokenResponse response = new AttendanceTokenResponse(
                "ERROR", 
                "Exception: " + e.getMessage()
            );
            return null;
        }
    } */

    /* @Get(value = "/find/{id}/qrcode")
    @Secured("isAnonymous()")
    @Produces({"image/jpeg,image/png"})
    public StreamedFile getQRCodeImage(Long id) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String token = repoAT.findById(id).getToken();
        BitMatrix bitMatrix = qrCodeWriter.encode(token, BarcodeFormat.QR_CODE, 350, 350);
        
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray(); 
        InputStream inputStream = new ByteArrayInputStream(pngData);
                
        return new StreamedFile(inputStream, "QR");
    } */
}
