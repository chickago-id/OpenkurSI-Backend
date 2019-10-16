package backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_detail")
public class UserDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_user", referencedColumnName="id", insertable = false, updatable = false)
    private User user;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Long id_user;

    @NotNull(message = "Nama Lengkap harus diisi.")
    private String nama_lengkap;
    private String tempat_lahir;
    private String tanggal_lahir;
    private String jenis_kelamin;
    private String agama;
    private String alamat;
    private String kecamatan;
    private String kota_kabupaten;
    private String provinsi;
    private String kode_pos;
    private String telepon;
    private String email;
    private String status_saat_ini;
    private String asal_sekolah_kampus;
    private String pekerjaan;
    private String nama_orangtua;
    private String telepon_orangtua;
    private String foto;
    private String informasi_dari;
    private String akun_ig;

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota_kabupaten() {
        return kota_kabupaten;
    }

    public void setKota_kabupaten(String kota_kabupaten) {
        this.kota_kabupaten = kota_kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKode_pos() {
        return kode_pos;
    }

    public void setKode_pos(String kode_pos) {
        this.kode_pos = kode_pos;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus_saat_ini() {
        return status_saat_ini;
    }

    public void setStatus_saat_ini(String status_saat_ini) {
        this.status_saat_ini = status_saat_ini;
    }

    public String getAsal_sekolah_kampus() {
        return asal_sekolah_kampus;
    }

    public void setAsal_sekolah_kampus(String asal_sekolah_kampus) {
        this.asal_sekolah_kampus = asal_sekolah_kampus;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getNama_orangtua() {
        return nama_orangtua;
    }

    public void setNama_orangtua(String nama_orangtua) {
        this.nama_orangtua = nama_orangtua;
    }

    public String getTelepon_orangtua() {
        return telepon_orangtua;
    }

    public void setTelepon_orangtua(String telepon_orangtua) {
        this.telepon_orangtua = telepon_orangtua;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getInformasi_dari() {
        return informasi_dari;
    }

    public void setInformasi_dari(String informasi_dari) {
        this.informasi_dari = informasi_dari;
    }

    public String getAkun_ig() {
        return akun_ig;
    }

    public void setAkun_ig(String akun_ig) {
        this.akun_ig = akun_ig;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserPassword(String password) {
        this.user.setPassword(password);
    }
}