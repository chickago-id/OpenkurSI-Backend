package backend.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

@Entity
@Table(name = "sesi")

public class Sesi {

    @Id
    @GenericGenerator(name = "incrementId", strategy = "increment")
    @GeneratedValue(generator = "incrementId")
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Jam mulai tidak boleh kosong !")
    private Time jam_mulai;

    @NotNull(message = "Jam selesai tidak boleh kosong !")
    private Time jam_selesai;

   // @NotNull(message = "Status setting tidak boleh kosong !")
    @ColumnDefault("1")
    private Integer setting;
    // @Enumerated(EnumType.STRING)
    // @Column(columnDefinition = "post_setting")
    // @Type (type ="mysql_enum")
    // private Enum setting;

    @ManyToOne
    @JoinColumn(name = "created_by", insertable = false, updatable = false, nullable = false)
    private UserDetail userDetail;
    private Long created_by;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_by", nullable =  true, updatable = true)
    private Long updated_by;

    @Column(name = "updated_at", nullable = true, updatable = true)
    @UpdateTimestamp
    private Date updated_at;

    /**
     * @return the jam_mulai
     */
    public Time getJam_mulai() {
        return jam_mulai;
    }
    /**
     * @param jam_mulai the jam_mulai to set
     */
    public void setJam_mulai(Time jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    /**
     * @return the jam_selesai
     */
    public Time getJam_selesai() {
        return jam_selesai;
    }
    /**
     * @param jam_selesai the jam_selesai to set
     */
    public void setJam_selesai(Time jam_selesai) {
        this.jam_selesai = jam_selesai;
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
     * @return the userDetail
     */
    public UserDetail getUserDetail() {
        return userDetail;
    }
    /**
     * @param userDetail the userDetail to set
     */
    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
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
     * @return the setting
     */
    public Integer getSetting() {
        return setting;
    }
    /**
     * @param setting the setting to set
     */
    public void setSetting(Integer setting) {
        this.setting = setting;
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
}
