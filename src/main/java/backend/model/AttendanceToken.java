package backend.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "attendance_token")
public class AttendanceToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;
    @Column(nullable = false)
    private Date expired_at;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kelas", referencedColumnName="id", insertable = false, updatable = false, nullable = false)
    private Kelas kelas;
    private Long id_kelas;
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
     * @return the token
     */
    public String getToken() {
        return token;
    }
    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
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
     * @return the expired_at
     */
    public Date getExpired_at() {
        return expired_at;
    }
    /**
     * @param expired_at the expired_at to set
     */
    public void setExpired_at(Date expired_at) {
        this.expired_at = expired_at;
    }
    /**
     * @return the kelas
     */
    public Kelas getKelas() {
        return kelas;
    }
    /**
     * @param kelas the kelas to set
     */
    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }
    /**
     * @return the id_kelas
     */
    public Long getId_kelas() {
        return id_kelas;
    }
    /**
     * @param id_kelas the id_kelas to set
     */
    public void setId_kelas(Long id_kelas) {
        this.id_kelas = id_kelas;
    }
    public void setExpiryDate(int minutes){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expired_at = now.getTime();
    }
    public boolean isExpired() {
        return new Date().after(this.expired_at);
    }
}