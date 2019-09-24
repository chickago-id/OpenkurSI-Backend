package backend;

import backend.model.KategoriNilai;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Stream;

public interface KategoriNilaiRepository {
   
    KategoriNilai save(KategoriNilai kategoriNilai);
    KategoriNilai update(Integer id, KategoriNilai kategoriNilai);
    List<KategoriNilai> findAll();
    KategoriNilai findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
    //Stream<KategoriNilai> coba();
}