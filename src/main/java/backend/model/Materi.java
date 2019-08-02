package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
@table(name="materi")
public class Materi {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Kode Materi harus diisi.")
    private String kode_materi;
    @NotNull(message = "Nama Materi harus diisi.")
    private String nama_materi;

    public String getKode_materi() {
        return kode_materi;
    }

    public void setKode_materi(String kode_materi) {
        this.kode_materi = kode_materi;
    }

    public String getNama_materi() {
        return nama_materi;
    }

    public void setNama_materi(String nama_materi) {
        this.nama_materi = nama_materi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}