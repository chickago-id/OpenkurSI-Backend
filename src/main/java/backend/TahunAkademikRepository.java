package backend;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface TahunAkademikRepository {
    // Materi save(@NotNull String kodeMateri, @NotNull String namaMateri);

    TahunAkademik save(@NotNull TahunAkademik tahunAkademik);

    TahunAkademik update(@NotNull TahunAkademik tahunAkademik);

    List<TahunAkademik> findAll();

    TahunAkademik findById(@NotNull Long id_tahun_akademik);

    void deleteById(@NotNull Long id_tahun_akademik);
}