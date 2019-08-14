package backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="kelas_peserta")
public class KelasPeserta {

    @OneToOne(optional=false)
    @JoinColumn(name = "id_kelas", referencedColumnName="id", insertable = false, updatable = false)
    private Kelas kelas;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String id_user;
    private String id_kelas;
    private String nis;
    private String nomor_sertifikat;
    private String nilai;
    private String status;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_kelas() {
        return id_kelas;
    }

    public void setJenis_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }


    public String getNis() {
      return nis;
  }

  public void setNis(String nis) {
      this.nis = nis;
  }


  public String getNomor_sertifikat() {
    return nomor_sertifikat;
}

public void setNomor_sertifikat(String nomor_sertifikat) {
    this.nomor_sertifikat = nomor_sertifikat;
}

public String getNilai() {
  return nilai;
}

public void setNilai(String nilai) {
  this.nilai = nilai;
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