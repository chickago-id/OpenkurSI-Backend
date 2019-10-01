package backend.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Entity
@Table(name="nilai_siswa", uniqueConstraints = @UniqueConstraint(
        columnNames = {
        "id_kelas_peserta",
        "id_kategori_nilai_materi"
    }
))
public class NilaiSiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_kelas_peserta", insertable = false, updatable = false, nullable = false)
    private KelasPeserta kelasPeserta;
    private Long id_kelas_peserta;
 
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_kategori_nilai_materi", insertable = false, updatable = false, nullable = false)
    private KategoriNilaiMateri kategoriNilaiMateri;
    private Long id_kategori_nilai_materi;

    private Float nilai_input;
    private Float nilai_hitung;
    @NotNull(message = "created_by tidak boleh kosong")
    private Long created_by;
    @CreationTimestamp
    private Date created_date;
    @NotNull(message = "updated_by tidak boleh kosong")
    private Long updated_by;
    @UpdateTimestamp
    private Date updated_date;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the id_kategori_nilai_materi
     */
    public Long getId_kategori_nilai_materi() {
        return id_kategori_nilai_materi;
    }
    /**
     * @param id_kategori_nilai_materi the id_kategori_nilai_materi to set
     */
    public void setId_kategori_nilai_materi(Long id_kategori_nilai_materi) {
        this.id_kategori_nilai_materi = id_kategori_nilai_materi;
    }
    /**
     * @return the id_kelas_peserta
     */
    public Long getId_kelas_peserta() {
        return id_kelas_peserta;
    }
    /**
     * @param id_kelas_peserta the id_kelas_peserta to set
     */
    public void setId_kelas_peserta(Long id_kelas_peserta) {
        this.id_kelas_peserta = id_kelas_peserta;
    }
    /**
     * @return the kategoriNilaiMateri
     */
    public KategoriNilaiMateri getKategoriNilaiMateri() {
        return kategoriNilaiMateri;
    }
    /**
     * @param kategoriNilaiMateri the kategoriNilaiMateri to set
     */
    public void setKategoriNilaiMateri(KategoriNilaiMateri kategoriNilaiMateri) {
        this.kategoriNilaiMateri = kategoriNilaiMateri;
    }
    /**
     * @return the kelasPeserta
     */
    public KelasPeserta getKelasPeserta() {
        return kelasPeserta;
    }
    /**
     * @param kelasPeserta the kelasPeserta to set
     */
    public void setKelasPeserta(KelasPeserta kelasPeserta) {
        this.kelasPeserta = kelasPeserta;
    }
    /**
     * @return the nilai_hitung
     */
    public Float getNilai_hitung() {
        return nilai_hitung;
    }
    /**
     * @param nilai_hitung the nilai_hitung to set
     */
    public void setNilai_hitung(Float nilai_hitung) {
        this.nilai_hitung = nilai_hitung;
    }
    /**
     * @return the nilai_input
     */
    public Float getNilai_input() {
        return nilai_input;
    }
    /**
     * @param nilai_input the nilai_input to set
     */
    public void setNilai_input(Float nilai_input) {
        this.nilai_input = nilai_input;
    }
    
    /**
     * @return the created_by
     */
    public Long getCreated_by() {
        return created_by;
    }
    /**
     * @param created_by the created_by to set
     */
    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    /**
     * @return the created_date
     */
    public Date getCreated_date() {
        return created_date;
    }
    /**
     * @param created_date the created_date to set
     */
    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    /**
     * @return the updated_by
     */
    public Long getUpdated_by() {
        return updated_by;
    }
    /**
     * @param updated_by the updated_by to set
     */
    public void setUpdated_by(Long updated_by) {
        this.updated_by = updated_by;
    }

    /**
     * @return the updated_date
     */
    public Date getUpdated_date() {
        return updated_date;
    }
    /**
     * @param updated_date the updated_date to set
     */
    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NilaiSiswa)) return false;
        NilaiSiswa that = (NilaiSiswa) o;
        return Objects.equals(
            kelasPeserta.getId(), that.kelasPeserta.getId()) &&
            Objects.equals(kategoriNilaiMateri.getId(), that.kategoriNilaiMateri.getId()) &&
            Objects.equals(nilai_input, that.nilai_input) &&
            Objects.equals(nilai_hitung, that.nilai_hitung) &&
            Objects.equals(created_by, that.created_by) &&
            Objects.equals(created_date, that.created_date) &&
            Objects.equals(updated_by, that.updated_by) &&
            Objects.equals(updated_date, that.updated_date
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            kelasPeserta.getId(), 
            kategoriNilaiMateri.getId(), 
            nilai_input,
            nilai_hitung,
            created_by,
            created_date,
            updated_by,
            updated_date
        );
    }

}