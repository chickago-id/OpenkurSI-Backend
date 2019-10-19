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
 *  Author : supi.core@gmail.com    |   github.com/sup1core
 */

 @Entity
 @Table (name = "ruang")

 public class Ruang {
    @Id
    @GenericGenerator(name = "incrementId", strategy = "increment")
    @GeneratedValue(generator = "incrementId")
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Nama ruang tidak boleh kosong !")
    @Column(unique=true)
    private String name;
     
    @NotNull(message = "Kapasitas tidak boleh kosong !")
    private Integer kapasitas;

    @Column (name = "keterangan", nullable = true, updatable = true)
    private String keterangan;

    @NotNull (message = "ID user tidak boleh kosong !")
    @Column(name = "created_by", nullable = false, updatable = false)
    private Long created_by;

    //@NotNull (message = "Tanggal buat tidak boleh kosong !")
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_by", nullable =  true, updatable = true)
    private Long updated_by;

    @Column(name = "updated_at", nullable = true, updatable = true)
    @UpdateTimestamp
    private Date updated_at;


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the kapasitas
     */
    public Integer getKapasitas() {
        return kapasitas;
    }
    /**
     * @param kapasitas the kapasitas to set
     */
    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }
    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the updated_at
     */
    public Date getUpdated_at() {
        return updated_at;
    }
    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
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
 }