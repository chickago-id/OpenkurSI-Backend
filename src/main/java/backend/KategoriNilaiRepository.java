package backend;

import backend.model.KategoriNilai;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface KategoriNilaiRepository {
   
    KategoriNilai save(KategoriNilai kategoriNilai);
    KategoriNilai update(Integer id, KategoriNilai kategoriNilai);
    List<KategoriNilai> findAll();
    KategoriNilai findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
    
}