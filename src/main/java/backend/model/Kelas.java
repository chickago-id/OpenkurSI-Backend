package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="kelas")
public class Kelas {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Kode Kelas harus di isi.")
    private String kode_kelas;
    
    private String jenis_kelas;
    private String jam_pilihan;
    private String tanggal_mulai;
    private String target_peserta;
    private String jumlah_pertemuan;
    private String biaya;
    private String status;

    public String getKode_kelas() {
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


    public String getJam_pilihan() {
      return jam_pilihan;
  }

  public void setjam_pilihan(String jam_pilihan) {
      this.jam_pilihan = jam_pilihan;
  }


  public String getTanggal_mulai() {
    return tanggal_mulai;
}

public void setTanggal_mulai(String tanggal_mulai) {
    this.tanggal_mulai = tanggal_mulai;
}

public String getTarget_peserta() {
  return target_peserta;
}

public void setTarget_peserta(String target_peserta) {
  this.target_peserta = target_peserta;
}




public String getJumlah_pertemuan() {
    return jumlah_pertemuan;
  }
  
  public void setJumlah_pertemuan(String jumlah_pertemuan) {
    this.jumlah_pertemuan = jumlah_pertemuan;
  }
  
public String getBiaya() {
    return biaya;
  }
  
  public void setBiaya(String biaya) {
    this.biaya = biaya;
  }


public String getStatus() {
  return status;
}

public void setStatus(String status) {
  this.status = status;
}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}