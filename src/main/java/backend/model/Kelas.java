package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.ManyToOne;

import javax.persistence.JoinColumn;

import java.util.Date;

@Entity
@Table(name = "kelas")
public class Kelas {

  @ManyToOne(optional = false)
  @JoinColumn(name = "kode_kelas", referencedColumnName = "kode_kelas", insertable = false, updatable = false)
  private MasterKelas masterKelas;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Kode Kelas harus di isi.")
  @Column(name = "kode_kelas", length = 100)
  private String kode_kelas;

  @Column(name = "jenis_kelas")
  private String jenis_kelas;

  @Column(name = "tanggal_mulai", nullable = false, updatable = true)
  @CreationTimestamp
  private Date tanggal_mulai;

  @Column(name = "target_peserta")
  private Integer target_peserta;

  @Column(name = "jumlah_pertemuan")
  private String jumlah_pertemuan;

  @Column(name = "biaya")
  private String biaya;

  @Column(name = "status")
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

  public Date getTanggal_mulai() {
    return tanggal_mulai;
  }

  public void setTanggal_mulai(Date tanggal_mulai) {
    this.tanggal_mulai = tanggal_mulai;
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

  public MasterKelas getMasterKelas() {
    return masterKelas;
  }

  public Integer getTarget_peserta() {
    return target_peserta;
  }

  public void setTarget_peserta(Integer target_peserta) {
    this.target_peserta = target_peserta;
  }

  /**
   * @param masterKelas the masterKelas to set
   */
  public void setMasterKelas(MasterKelas masterKelas) {
    this.masterKelas = masterKelas;
  }

}