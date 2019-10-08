package backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

import javax.persistence.JoinColumn;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "kelas")
public class Kelas {

  @ManyToOne(optional = false)
  @JoinColumn(name = "id_materi", referencedColumnName = "id", insertable = false, updatable = false)
  private Materi materi;

  @ManyToOne(optional = false)
  @JoinColumn(name = "kode_kelas", referencedColumnName = "kode_kelas", insertable = false, updatable = false)
  private MasterKelas masterKelas;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Kode Kelas harus di isi.")
  @Column(name = "kode_kelas")
  private String kode_kelas;

  @Column(name = "jenis_kelas")
  private String jenis_kelas;

  @Column(name = "jam_pilihan")
  private String jam_pilihan;

  @Column(name = "jam_mulai")
  private String jam_mulai;

  @Column(name = "jam_selesai")
  private String jam_selesai;

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

  @Column(name = "id_materi")
  private Long id_materi;

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

  public Materi getMateri() {
    return materi;
  }

  public MasterKelas getMasterKelas() {
    return masterKelas;
  }

  public String getJam_mulai() {
    return jam_mulai;
  }

  public void setJam_mulai(String jam_mulai) {
    this.jam_mulai = jam_mulai;
  }

  public String getJam_selesai() {
    return jam_selesai;
  }

  public void setJam_selesai(String jam_selesai) {
    this.jam_selesai = jam_selesai;
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

  public Long getId_materi() {
    return id_materi;
  }

  public void setId_materi(Long id_materi) {
    this.id_materi = id_materi;
  }
}