package backend.repository;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface KelasRepository {
   
    Kelas save(@NotNull Kelas kelas);
    Kelas update(@NotNull Kelas kelas);
    List<Kelas> findAll();
    Kelas findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}