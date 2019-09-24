package backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "nilai_siswa")
public class NilaiSiswa {

    @Id
    @GenericGenerator(name="incrementId",strategy="increment")
    @GeneratedValue(generator = "incrementId")
    @Column(name = "id_nilai_siswa")
    private Integer id_nilai_siswa;


    @ManyToOne(targetEntity = UserDetail.class, optional = false)
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    private UserDetail user;
    @Column(nullable=false)
    private Long id_user;

    /**
     * @return the id_user
     */
    public Long getId_user() {
        return id_user;
    }
    /**
     * @param id_user the id_user to set
     */
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
    /*
        Relasi ke tabel materi serta getter dan setternya, sementara di non aktifkan
        @NotNull
        @ManyToOne(targetEntity = Materi.class)
        @JoinColumn(name = "id_materi")
        private Materi materi;
        public Materi getMateri() {return materi;}
        public void setMateri(Materi materi) {this.materi = materi;} 
    */
    

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = KategoriNilai.class, optional = false)
    @JoinColumn(name = "id_kategori_nilai", referencedColumnName = "id_kategori_nilai", updatable = false, insertable = false, nullable = true)
    private KategoriNilai kategoriNilai; 

    @Column(nullable=false)
    private Integer id_kategori_nilai;

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

    @NotNull
    @Column(name = "nilai_input")
    private Float nilai_input;

    @NotNull
    @Column(name = "nilai_hitung")
    private Float nilai_hitung;

    @NotNull
    @Column(name = "created_by")
    private Long created_by;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_date;
    
    @Column(name = "updated_by", nullable = false)
    private Long updated_by;

    @Column(name = "updated_date", nullable = false, updatable = true)
    @UpdateTimestamp
    private Date updated_date;

    /**
     * @return the id_nilai_siswa
     */
    public Integer getId_nilai_siswa() {
        return id_nilai_siswa;
    }
    /**
     * @param id_nilai_siswa the id_nilai_siswa to set
     */
    public void setId_nilai_siswa(Integer id_nilai_siswa) {
        this.id_nilai_siswa = id_nilai_siswa;
    }

    public UserDetail getUser() {
        return user;
    }
    public void setUser(UserDetail user) {
        this.user = user;
    }

    /* public KategoriNilai getKategoriNilai() {
        return kategoriNilai;
    }
    public void setKategoriNilai(KategoriNilai kategoriNilai) {
        this.kategoriNilai = kategoriNilai;
    } */

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