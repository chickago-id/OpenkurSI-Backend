package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import javax.persistence.JoinColumn;

import java.util.Date;

@Entity
@Table(name = "kelas")
public class Kelas {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "id_kelas", referencedColumnName = "id", insertable = false, updatable = false)
  private MasterKelas masterKelas;
  private Integer id_kelas;

  @ManyToOne(optional = false)
  @JoinColumn(name = "id_kelastype", referencedColumnName = "id", insertable = false, updatable = false)
  private KelasType kelasType;
  private Integer id_kelastype;

  @ManyToOne(optional = false)
  @JoinColumn(name = "id_batch", referencedColumnName = "id_batch", insertable = false, updatable = false)
  private Batch batch;
  private Long id_batch;

  @Column(name = "kode_kelas", length = 100, unique = true)
  private String kode_kelas;

  @Column(name = "tanggal_mulai", nullable = false, updatable = true)
  private Date tanggal_mulai;

  @Column(name = "target_peserta")
  private Integer target_peserta;

  @Column(name = "jumlah_pertemuan")
  private String jumlah_pertemuan;

  @Column(name = "biaya")
  private String biaya;

  @ManyToOne(optional = false)
  @JoinColumn(name = "id_status", referencedColumnName = "id", insertable = false, updatable = false)
  private Status status;
  private Integer id_status;

  public String getKode_kelas() {
    return kode_kelas;
  }

  public void setKode_kelas(String kode_kelas) {
    this.kode_kelas = kode_kelas;
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
  /**
   * @param id_kelas the id_kelas to set
   */
  public void setId_kelas(Integer id_kelas) {
    this.id_kelas = id_kelas;
  }
  /**
   * @return the id_kelas
   */
  public Integer getId_kelas() {
    return id_kelas;
  }
  /**
   * @param kelasType the kelasType to set
   */
  public void setKelasType(KelasType kelasType) {
    this.kelasType = kelasType;
  }
  /**
   * @return the kelasType
   */
  public KelasType getKelasType() {
    return kelasType;
  }
  /**
   * @param id_kelastype the id_kelastype to set
   */
  public void setId_kelastype(Integer id_kelastype) {
    this.id_kelastype = id_kelastype;
  }
  /**
   * @return the id_kelastype
   */
  public Integer getId_kelastype() {
    return id_kelastype;
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
   * @param batch the batch to set
   */
  public void setBatch(Batch batch) {
    this.batch = batch;
  }
  /**
   * @return the batch
   */
  public Batch getBatch() {
    return batch;
  }
  /**
   * @param id_batch the id_batch to set
   */
  public void setId_batch(Long id_batch) {
    this.id_batch = id_batch;
  }
  /**
   * @return the id_batch
   */
  public Long getId_batch() {
    return id_batch;
  }

}