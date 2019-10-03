package backend.repository;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface MasterKelasRepository {
    // Materi save(@NotNull String kodeMateri, @NotNull String namaMateri);
    MasterKelas save(@NotNull MasterKelas masterKelas);

    MasterKelas update(@NotNull MasterKelas masterKelas);

    List<MasterKelas> findAll();

    MasterKelas findById(@NotNull String kode_kelas);

    void deleteById(@NotNull String kode_kelas);
}