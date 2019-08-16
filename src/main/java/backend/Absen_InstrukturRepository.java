package backend;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface Absen_InstrukturRepository {
    
    Absen_Instruktur save(@NotNull Absen_Instruktur absen_instruktur);
    List<Absen_Instruktur> findAll();
    Absen_Instruktur findById(@NotNull Long id);
    
}