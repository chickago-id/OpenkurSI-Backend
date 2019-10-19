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
 * Author : supi.core@gmail.com | github.com/sup1core  
 */

@Entity
@Table(name="materi")
public class Materi {

    @Id
    @GenericGenerator(name="incrementId", strategy="increment")
    @GeneratedValue(generator = "incrementId")
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Kode Materi harus diisi.")
    @Column(unique=true)
    private String kode_materi;

    @NotNull(message = "Nama Materi harus diisi.")
    private String nama_materi;
   
    @NotNull(message = "Created by tidak boleh kosong")
    @Column(name = "created_by", nullable = false, updatable = false)
    private Long created_by;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_by", nullable =  true)
    private Long updated_by;

    @Column(name = "updated_date", nullable = false, updatable = true)
    @UpdateTimestamp
    private Date updated_date;
    

    public String getKode_materi() {
        return kode_materi;
    }

    public void setKode_materi(String kode_materi) {
        this.kode_materi = kode_materi;
    }

    public String getNama_materi() {
        return nama_materi;
    }

    public void setNama_materi(String nama_materi) {
        this.nama_materi = nama_materi;
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
     * @return the created_at
     */
    public Date getCreated_at() {
        return created_at;
    }
    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
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
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }/**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
}