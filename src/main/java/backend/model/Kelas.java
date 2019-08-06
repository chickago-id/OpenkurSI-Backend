package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
public class Kelas {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Kode Materi harus diisi.")
    private String kode_kelas;
    @NotNull(message = "Nama Materi harus diisi.")
    private String jenis_kelas;

    public String getKode_materi() {
        return kode_kelas;
    }

    public void setKode_kelas(String kode_kelas) {
        this.kode_kelas = kode_kelas;
    }

    public String getJenis_kelas() {
        return jenis_kelas;
    }

    public void setJenis_kelas(String jenis_kelas) {
        this.jenis_kelas = jenis_kelas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}