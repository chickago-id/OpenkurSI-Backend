package backend.model;

import java.util.Date;

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

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 @Entity
 @Table(name = "master_status")

 public class Status {
 
    @Id
    @GenericGenerator(name = "incrementId", strategy = "increment")
    @GeneratedValue(generator = "incrementId")
    @Column(name = "id")
    private int id;

    @NotNull(message = "Status name not allowed null value.")
    @Column(name = "name", unique = true)
    private String name;

    @Column (name = "description")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "created_by", insertable = false, updatable = false, nullable = false)
    private UserDetail userDetail;
    private Long created_by;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_by", nullable =  true)
    //@ColumnDefault("1")
    private Long updated_by;

    @Column(name = "updated_at", nullable = false, updatable = true)
    @UpdateTimestamp
    private Date updated_at;

    @Column(name = "deleted_by", nullable =  true)
    private Long deleted_by;

    @Column(name = "deleted_at", nullable = true, updatable = true)
    @UpdateTimestamp
    private Date deleted_at;

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    /**
     * @return the created_at
     */
    public Date getCreated_at() {
        return created_at;
    }
    /**
     * @param created_by the created_by to set
     */
    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }
    /**
     * @return the created_by
     */
    public Long getCreated_by() {
        return created_by;
    }
    /**
     * @param deleted_at the deleted_at to set
     */
    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }/**
     * @return the deleted_at
     */
    public Date getDeleted_at() {
        return deleted_at;
    }
    /**
     * @param deleted_by the deleted_by to set
     */
    public void setDeleted_by(Long deleted_by) {
        this.deleted_by = deleted_by;
    }
    /**
     * @return the deleted_by
     */
    public Long getDeleted_by() {
        return deleted_by;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    /**
     * @return the updated_at
     */
    public Date getUpdated_at() {
        return updated_at;
    }
    /**
     * @param updated_by the updated_by to set
     */
    public void setUpdated_by(Long updated_by) {
        this.updated_by = updated_by;
    }
    /**
     * @return the updated_by
     */
    public Long getUpdated_by() {
        return updated_by;
    }
    /**
     * @param userDetail the userDetail to set
     */
    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }
    /**
     * @return the userDetail
     */
    public UserDetail getUserDetail() {
        return userDetail;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
 }