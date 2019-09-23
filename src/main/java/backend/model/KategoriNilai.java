package backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "kategori_nilai")
public class KategoriNilai {

    @Id
    @GenericGenerator(name="incrementId",strategy="increment")
    @GeneratedValue(generator = "incrementId")
    @Column(name = "id_kategori_nilai")
    private Integer id_kategori_nilai;

    @NotNull
    @Column(name = "nama_kategori")
    private String nama_kategori;

    @ManyToOne(targetEntity = Materi.class, optional = false)
    @JoinColumn(name = "id_materi", referencedColumnName = "id", nullable = false)
    private Materi materi;

    @NotNull
    @Column(name = "bobot_nilai")
    private Float bobot_nilai;

    /*     
        @JsonIgnoreProperties(value = "kategoriNilai", allowSetters=true, allowGetters = true)
        @OneToMany(targetEntity = NilaiSiswa.class, mappedBy = "kategoriNilai")
        private List<NilaiSiswa> nilaiSiswa = new ArrayList<>();
    */

    @NotNull
    @Column(name = "created_by", nullable = false, updatable = false)
    private Long created_by;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_date;

    @Column(name = "updated_by", nullable =  true)
    private Long updated_by;

    @Column(name = "updated_date", nullable = false, updatable = true)
    @UpdateTimestamp
    private Date updated_date;

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
     * @return the nama_kategori
     */
    public String getNama_kategori() {
        return nama_kategori;
    }
    /**
     * @param nama_kategori the nama_kategori to set
     */
    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
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

    /* 
        public List<NilaiSiswa> getNilaiSiswa() {return nilaiSiswa;}
        public void setNilaiSiswa(List<NilaiSiswa> nilaiSiswa) {this.nilaiSiswa = nilaiSiswa;} 
    */

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
}