package backend.repository;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface MasterKelasRepository {
    // Materi save(@NotNull String kodeMateri, @NotNull String namaMateri);
    MasterKelas save(@NotNull MasterKelas masterKelas);

    MasterKelas update(@NotNull MasterKelas masterKelas);

    List<MasterKelas> findAll();

    MasterKelas findById(@NotNull Integer id);

    void deleteById(@NotNull Integer id);
}