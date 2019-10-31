package backend.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="kelas_peserta", uniqueConstraints = @UniqueConstraint(
  columnNames = {
    "id_kelas",
    "id_user"
  }
))
public class KelasPeserta implements Serializable {

  @ManyToOne
  @JoinColumn(name = "id_kelas", referencedColumnName="id", insertable = false, updatable = false, nullable = false)
  private Kelas kelas;
  private Long id_kelas;
  
  @ManyToOne
  @JoinColumn(name = "id_user", referencedColumnName="id_user", insertable = false, updatable = false, nullable = false)
  private UserDetail user;
  private Long id_user;
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String nis;
  private String nomor_sertifikat;
  private String nilai;
  private String status;

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

/**
 * @param kelas the kelas to set
 */
public void setKelas(Kelas kelas) {
  this.kelas = kelas;
}

/**
 * @return the kelas
 */
public Kelas getKelas() {
  return kelas;
}

/**
 * @return the user
 */
public UserDetail getUser() {
  return user;
}

/**
 * @param user the user to set
 */
public void setUser(UserDetail user) {
  this.user = user;
}

/**
 * @return the id_kelas
 */
public Long getId_kelas() {
  return id_kelas;
}
/**
 * @param id_kelas the id_kelas to set
 */
public void setId_kelas(Long id_kelas) {
  this.id_kelas = id_kelas;
}

/**
 * @return the id_user
 */
public Long getId_user() {
  return id_user;
}
/**
 * @param id_user the id_user to set
 */
public void setId_user(Long id_user) {
  this.id_user = id_user;
}

@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KelasPeserta)) return false;
        KelasPeserta that = (KelasPeserta) o;
        return Objects.equals(kelas.getId(), that.kelas.getId()) &&
                Objects.equals(user.getId(), that.user.getId()) &&
                Objects.equals(nis, that.nis) &&
                Objects.equals(nomor_sertifikat, that.nomor_sertifikat) &&
                Objects.equals(nilai, that.nilai) &&
                Objects.equals(status, that.status); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(kelas.getId(), user.getId(), nis, nomor_sertifikat, nilai, status);
    }

}