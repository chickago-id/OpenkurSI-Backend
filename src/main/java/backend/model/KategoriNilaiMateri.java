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

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Entity
@Table(name="kategori_nilai_materi", uniqueConstraints = @UniqueConstraint(
    columnNames = {
        "id_kategori_nilai",
        "id_materi"
    }
))
public class KategoriNilaiMateri implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_kategori_nilai", insertable = false, updatable = false, nullable = false)
    private KategoriNilai kategori_nilai;
    private Integer id_kategori_nilai;

    @ManyToOne
    @JoinColumn(name="id_materi", insertable = false, updatable=false, nullable=false)
    private Materi materi;
    private Integer id_materi;

    private Float bobot_nilai;

    /**
     * @return the bobot_nilai
     */
    public Float getBobot_nilai() {
        return bobot_nilai;
    }
    /**
     * @param bobot_nilai the bobot_nilai to set
     */
    public void setBobot_nilai(Float bobot_nilai) {
        this.bobot_nilai = bobot_nilai;
    }
    /**
     * @return the kategori_nilai
     */
    public KategoriNilai getKategori_nilai() {
        return kategori_nilai;
    }
    /**
     * @param kategori_nilai the kategori_nilai to set
     */
    public void setKategori_nilai(KategoriNilai kategori_nilai) {
        this.kategori_nilai = kategori_nilai;
    }
    /**
     * @return the materi
     */
    public Materi getMateri() {
        return materi;
    }
    /**
     * @param materi the materi to set
     */
    public void setMateri(Materi materi) {
        this.materi = materi;
    }
    
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
     * @return the id_kategori_nilai
     */
    public Integer getId_kategori_nilai() {
        return id_kategori_nilai;
    }

    /**
     * @param id_kategori_nilai the id_kategori_nilai to set
     */
    public void setId_kategori_nilai(Integer id_kategori_nilai) {
        this.id_kategori_nilai = id_kategori_nilai;
    }

    /**
     * @return the id_materi
     */
    public Integer getId_materi() {
        return id_materi;
    }

    /**
     * @param id_materi the id_materi to set
     */
    public void setId_materi(Integer id_materi) {
        this.id_materi = id_materi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KategoriNilaiMateri)) return false;
        KategoriNilaiMateri that = (KategoriNilaiMateri) o;
        return Objects.equals(kategori_nilai.getNama_kategori(), that.kategori_nilai.getNama_kategori()) &&
                Objects.equals(materi.getNama_materi(), that.materi.getNama_materi()) &&
                Objects.equals(bobot_nilai, that.bobot_nilai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kategori_nilai.getNama_kategori(), materi.getNama_materi(), bobot_nilai);
    }

}