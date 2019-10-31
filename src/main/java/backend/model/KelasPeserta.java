package backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Author : aried modify supi.core@gmail.com | github.com/sup1core
 */

@Entity
@Table(name="kelas_peserta", uniqueConstraints = @UniqueConstraint(
  columnNames = {
    "id_kelas",
    "id_peserta"
  }
))
public class KelasPeserta implements Serializable {

  @ManyToOne
  @JoinColumn(name = "id_kelas", referencedColumnName="id", insertable = false, updatable = false, nullable = false)
  private Kelas kelas;
  private Long id_kelas;
  
  @ManyToOne
  @JoinColumn(name = "id_peserta", referencedColumnName="id_user", insertable = false, updatable = false, nullable = false)
  private UserDetail userDetail;
  private Long id_peserta;
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String nis;

  @ManyToOne
  @JoinColumn(name = "id_status", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
  private Status status;
  private Integer id_status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_by", nullable =  true, updatable = true)
    private Long updated_by;

    @Column(name = "updated_at", nullable = true, updatable = true)
    @UpdateTimestamp
    private Date updated_at;

    @Column(name = "nomor_sertifikat", unique = true)
    private String nomor_sertifikat;
    
    private Integer sertifikat_status;

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
 * @param id_peserta the id_peserta to set
 */
public void setId_peserta(Long id_peserta) {
  this.id_peserta = id_peserta;
}
/**
 * @return the id_peserta
 */
public Long getId_peserta() {
  return id_peserta;
}

/**
 * @param status the status to set
 */
public void setStatus(Status status) {
  this.status = status;
}
/**
 * @return the status
 */
public Status getStatus() {
  return status;
}
/**
 * @param id_status the id_status to set
 */
public void setId_status(Integer id_status) {
  this.id_status = id_status;
}
/**
 * @return the id_status
 */
public Integer getId_status() {
  return id_status;
}
/**
 * @param created_at the created_at to set
 */
public void setCreated_at(Date created_at) {
  this.created_at = created_at;
}
/**
 * @return the created_at
 */
public Date getCreated_at() {
  return created_at;
}
/**
 * @param updated_at the updated_at to set
 */
public void setUpdated_at(Date updated_at) {
  this.updated_at = updated_at;
}
/**
 * @return the updated_at
 */
public Date getUpdated_at() {
  return updated_at;
}
/**
 * @param updated_by the updated_by to set
 */
public void setUpdated_by(Long updated_by) {
  this.updated_by = updated_by;
}
/**
 * @return the updated_by
 */
public Long getUpdated_by() {
  return updated_by;
}
/**
 * @param userDetail the userDetail to set
 */
public void setUserDetail(UserDetail userDetail) {
  this.userDetail = userDetail;
}
/**
 * @return the userDetail
 */
public UserDetail getUserDetail() {
  return userDetail;
}
/**
 * @param sertifikat_status the sertifikat_status to set
 */
public void setSertifikat_status(Integer sertifikat_status) {
  this.sertifikat_status = sertifikat_status;
}
/**
 * @return the sertifikat_status
 */
public Integer getSertifikat_status() {
  return sertifikat_status;
}


@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KelasPeserta)) return false;
        KelasPeserta that = (KelasPeserta) o;
        return Objects.equals(kelas.getId(), that.kelas.getId()) &&
                Objects.equals(userDetail.getId(), that.userDetail.getId()) &&
                Objects.equals(nis, that.nis) &&
                Objects.equals(nomor_sertifikat, that.nomor_sertifikat) &&
                Objects.equals(status, that.status); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(kelas.getId(), userDetail.getId(), nis, nomor_sertifikat, status);
    }

}