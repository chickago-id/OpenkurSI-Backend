package backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

@Entity
@Table(name="jadwal")
public class Jadwal {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_kelas", insertable = false, updatable = false, nullable = false)
    private Kelas kelas;
    private Long id_kelas;

    @ManyToOne
    @JoinColumn(name = "id_pengajar", insertable = false, updatable = false, nullable = false)
    private UserDetail userDetail;
    private Long id_pengajar;

    @ManyToOne
    @JoinColumn(name = "id_ruang", insertable = false, updatable = false, nullable = false)
    private Ruang ruang;
    private Integer id_ruang;

    @ManyToOne
    @JoinColumn(name = "id_hari", insertable = false, updatable = false, nullable = false)
    private Day day;
    private Integer id_hari;

    @ManyToOne
    @JoinColumn(name = "id_sesi", insertable = false, updatable = false, nullable = false)
    private Sesi sesi;
    private Integer id_sesi;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_materi", insertable = false, updatable = false)
    private Materi materi;
    private Integer id_materi;

    @NotNull(message = "Created by not allowed null")
    @Column(name = "created_by", nullable = false, updatable = false)
    private Long created_by;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_by", nullable =  true)
    private Long updated_by;

    @Column(name = "updated_at", nullable = true, updatable = true)
    @UpdateTimestamp
    private Date updated_at;

    @Column(name = "deleted_by", nullable =  true)
    private Long deleted_by;

    @Column(name = "deleted_at", nullable = true, updatable = true)
    @UpdateTimestamp
    private Date deleted_at;



    public Long getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(Long id_kelas) {
        this.id_kelas = id_kelas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param id_hari the id_hari to set
     */
    public void setId_hari(Integer id_hari) {
        this.id_hari = id_hari;
    }
    /**
     * @return the id_hari
     */
    public Integer getId_hari() {
        return id_hari;
    }
    /**
     * @param id_pengajar the id_pengajar to set
     */
    public void setId_pengajar(Long id_pengajar) {
        this.id_pengajar = id_pengajar;
    }
    /**
     * @return the id_pengajar
     */
    public Long getId_pengajar() {
        return id_pengajar;
    }
    /**
     * @param id_ruang the id_ruang to set
     */
    public void setId_ruang(Integer id_ruang) {
        this.id_ruang = id_ruang;
    }
    /**
     * @return the id_ruang
     */
    public Integer getId_ruang() {
        return id_ruang;
    }
    /**
     * @param id_sesi the id_sesi to set
     */
    public void setId_sesi(Integer id_sesi) {
        this.id_sesi = id_sesi;
    }
    /**
     * @return the id_sesi
     */
    public Integer getId_sesi() {
        return id_sesi;
    }
    /**
     * @param kelas the kelas to set
     */
    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }
    /**
     * @return the kelas
     */
    public Kelas getKelas() {
        return kelas;
    }
    /**
     * @param user the user to set
     */
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
     * @param sesi the sesi to set
     */
    public void setSesi(Sesi sesi) {
        this.sesi = sesi;
    }
    /**
     * @return the sesi
     */
    public Sesi getSesi() {
        return sesi;
    }
    /**
     * @param ruang the ruang to set
     */
    public void setRuang(Ruang ruang) {
        this.ruang = ruang;
    }
    /**
     * @return the ruang
     */
    public Ruang getRuang() {
        return ruang;
    }
    /**
     * @param day the day to set
     */
    public void setDay(Day day) {
        this.day = day;
    }
    /**
     * @return the day
     */
    public Day getDay() {
        return day;
    }
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
    }
    /**
     * @return the deleted_at
     */
    public Date getDeleted_at() {
        return deleted_at;
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
     * @param id_materi the id_materi to set
     */
    public void setId_materi(Integer id_materi) {
        this.id_materi = id_materi;
    }
    /**
     * @return the id_materi
     */
    public Integer getId_materi() {
        return id_materi;
    }
    /**
     * @param materi the materi to set
     */
    public void setMateri(Materi materi) {
        this.materi = materi;
    }
    /**
     * @return the materi
     */
    public Materi getMateri() {
        return materi;
    }
}