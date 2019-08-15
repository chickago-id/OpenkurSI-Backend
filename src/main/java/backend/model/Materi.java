package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="materi")
public class Materi {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Kode Materi harus diisi.")
    private String kode_materi;
    @NotNull(message = "Nama Materi harus diisi.")
    private String nama_materi;
    @NotNull(message = "Jumlah Pertemuan harus diisi")
    private String jumlah_pertemuan;
    @NotNull(message = "Jam Pilihan harus diisi")
    private String jam_pilihan;
    @NotNull(message = "Biaya harus diisi")
    private String biaya;

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

    public String getJumlah_pertemuan(){
        return jumlah_pertemuan;
    }

    public void setJumlah_pertemuan(String jumlah_pertemuan){
        this.jumlah_pertemuan = jumlah_pertemuan;
    }

    public String getJam_pilihan(){
        return jam_pilihan;
    }

    public void setJam_pilihan(String jam_pilihan){
        this.jam_pilihan = jam_pilihan;
    } 

    public String getBiaya(){
        return biaya;
    }

    public void setBiaya(String biaya){
        this.biaya = biaya;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}