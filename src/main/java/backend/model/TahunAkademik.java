package backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tahun_akademik")
public class TahunAkademik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tahun_akademik")
    private Long id_tahun_akademik;

    @NotNull(message = "Tahun Mulai harus diisi.")
    @Column(name = "tahun_mulai", nullable = false, updatable = false)
    @CreationTimestamp
    private Date tahun_mulai;

    @NotNull(message = "Tahun Selesai harus diisi.")
    @Column(name = "tahun_selesai", nullable = false, updatable = true)
    private Date tahun_selesai;

    public Long getId_tahun_akademik() {
        return id_tahun_akademik;
    }

    public void setId_tahun_akademik(Long id_tahun_akademik) {
        this.id_tahun_akademik = id_tahun_akademik;
    }

    public Date getTahun_mulai() {
        return tahun_mulai;
    }

    public void setTahun_mulai(Date tahun_mulai) {
        this.tahun_mulai = tahun_mulai;
    }

    public Date getTahun_selesai() {
        return tahun_selesai;
    }

    public void setTahun_selesai(Date tahun_selesai) {
        this.tahun_selesai = tahun_selesai;
    }

}