package backend.repository;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author : supi.core@gmail.com | github.com/sup1core  
 */

public interface KelasRepository {
   
    Kelas save(Kelas kelas);
    Kelas update(Integer id, Kelas kelas);

    List<Kelas> findAll();
    List<Kelas> enRoll();

    KelasType cariId(Integer id);
    MasterKelas findById(Integer id);
    Batch cariBatchid(Long id_batch);

    Kelas findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}