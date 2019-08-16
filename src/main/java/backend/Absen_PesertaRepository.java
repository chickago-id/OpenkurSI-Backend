package backend;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface Absen_PesertaRepository {
    
    Absen_Peserta save(@NotNull Absen_Peserta absen_peserta);
    List<Absen_Peserta> findAll();
    Absen_Peserta findById(@NotNull Long id);
    
}