package backend.repository;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BatchRepository {
    // Batch save(@NotNull String kodeMateri, @NotNull String namaMateri);
    Batch save(@NotNull Batch batch);

    Batch update(@NotNull Batch batch);

    List<Batch> findAll();

    Batch findById(@NotNull Long id_batch);

    void deleteById(@NotNull Long id_batch);
}