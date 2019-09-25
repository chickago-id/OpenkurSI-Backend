package backend;

import backend.model.NilaiSiswa;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface NilaiSiswaRepository {
    
    NilaiSiswa save(NilaiSiswa nilaiSiswa);
    NilaiSiswa update(Integer id, NilaiSiswa nilaiSiswa);
    List<NilaiSiswa> findAll();
    NilaiSiswa findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);

}