package backend;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.*;

public interface NilaiHurufRepository{
    NilaiHuruf save(@NotNull NilaiHuruf nilaihuruf);
    NilaiHuruf update(@NotNull NilaiHuruf nilaihuruf);

    List<NilaiHuruf> findAll();
    NilaiHuruf findById(@NotNull Long id_nilai_huruf);
    void deleteById(@NotNull Long id_nilai_huruf);
}
