package backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Entity
@Table(name = "kategori_nilai")
public class KategoriNilai {

    @Id
    @GenericGenerator(name="incrementId",strategy="increment")
    @GeneratedValue(generator = "incrementId")
    @Column(name = "id_kategori_nilai")
    private Integer id_kategori_nilai;

    @NotNull(message = "Nama kategori tidak boleh kosong")
    @Column(name = "nama_kategori")
    private String nama_kategori;

    @NotNull(message = "Created by tidak boleh kosong")
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