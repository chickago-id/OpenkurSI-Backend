package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="jadwal")
public class Jadwal {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Kode kelas harus diisi.")
    private String id_kelas;
    @NotNull(message = "jam harus dipilih.")
    private String jam_pilihan;
    @NotNull(message = "Tanggal diisi.")
    private String tanggal;

    public String getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getJam_pilihan() {
        return jam_pilihan;
    }

    public void setJam_pilihan(String jam_pilihan) {
        this.jam_pilihan = jam_pilihan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTangal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}