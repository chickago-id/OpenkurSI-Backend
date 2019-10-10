package backend.controller;

import backend.model.Batch;
import backend.model.BatchResponse;
import backend.repository.BatchRepository;

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
@Controller("/batch")
@Secured("isAnonymous()")
public class BatchController {

    private BatchRepository batchRepository;

    public BatchController(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    @Get("/")
    public String index() {

        try {
            List<Batch> batch = batchRepository.findAll();
            BatchResponse response = new BatchResponse("ok", "Data Batch", batch);

            return new Gson().toJson(response);
        } catch (Exception e) {
            String message = e.getMessage();
            BatchResponse response = new BatchResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Post("/")
    @Secured("isAnonymous()")
    public String create(@Body Batch batch, @Nullable Authentication authentication) {

        try {

            if (authentication == null) {
                BatchResponse response = new BatchResponse("error", "Unauthorized user");
                return new Gson().toJson(response);
            } else {
                Object data = authentication.getAttributes().get("roles");
                String roles = data.toString();

                if (roles.equals("[\"Admin\"]")) {
                    Batch result = batchRepository.save(batch);
                    BatchResponse response = new BatchResponse("ok", "Berhasil menambahkan data batch", result);
                    return new Gson().toJson(response);
                } else {
                    BatchResponse response = new BatchResponse("error", "Anda tidak boleh mengakses halaman ini.");
                    return new Gson().toJson(response);
                }
            }

        } catch (Exception e) {
            String message = e.getMessage();

            BatchResponse response = new BatchResponse("error", message);
            return new Gson().toJson(response);
        }
    }

    @Get("/{id_batch}")
    @Secured("isAnonymous()")
    public String show(Long id_batch) {

        try {

            Batch batch = batchRepository.findById(id_batch);

            if (batch != null) {
                BatchResponse response = new BatchResponse("ok", "Data Batch", batch);
                return new Gson().toJson(response);
            } else {
                BatchResponse response = new BatchResponse("error", "Data Batch tidak ditemukan");
                return new Gson().toJson(response);
            }

        } catch (Exception e) {

            String message = e.getMessage();

            BatchResponse response = new BatchResponse("error", message);
            return new Gson().toJson(response);
        }

    }

    @Post("/{id_batch}")
    @Secured("isAnonymous()")
    public String update(@Body Batch batch, @Nullable Authentication authentication) {

        if (authentication == null) {
            BatchResponse response = new BatchResponse("error", "Unauthorized user");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if (roles.equals("[\"Admin\"]")) {
                Batch result = batchRepository.update(batch);

                if (result != null) {
                    BatchResponse response = new BatchResponse("ok", "Berhasil memperbaharui data materi", result);
                    return new Gson().toJson(response);
                } else {
                    BatchResponse response = new BatchResponse("error", "Data batch tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else {
                BatchResponse response = new BatchResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }

    @Delete("/{id_batch}")
    @Secured("isAnonymous()")
    public String delete(Long id_batch, @Nullable Authentication authentication) {
        if (authentication == null) {
            BatchResponse response = new BatchResponse("error", "Unauthorized user");
            return new Gson().toJson(response);
        } else {
            Object data = authentication.getAttributes().get("roles");
            String roles = data.toString();

            if (roles.equals("[\"Admin\"]")) {
                Batch getBatch = batchRepository.findById(id_batch);

                if (getBatch != null) {
                    batchRepository.deleteById(id_batch);
                    BatchResponse response = new BatchResponse("ok", "Berhasil menghapus data materi");
                    return new Gson().toJson(response);

                } else {
                    BatchResponse response = new BatchResponse("error", "Data batch tidak ditemukan");
                    return new Gson().toJson(response);
                }
            } else {
                BatchResponse response = new BatchResponse("error", "Anda tidak boleh mengakses halaman ini.");
                return new Gson().toJson(response);
            }
        }
    }

}
