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
@Table(name = "user_detail")
public class UserDetail {

    @OneToOne(optional=false)
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
}