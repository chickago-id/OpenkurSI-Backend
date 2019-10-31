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
    @JoinColumn(name = "id_jadwal", referencedColumnName="id", insertable = false, updatable = false, nullable = false)
    private Jadwal jadwal;
    private Integer id_jadwal;
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
     * @return the jadwal
     */
    public Jadwal getJadwal() {
        return jadwal;
    }
    /**
     * @param jadwal the jadwal to set
     */
    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }
    /**
     * @return the id_jadwal
     */
    public Integer getId_jadwal() {
        return id_jadwal;
    }
    /**
     * @param id_jadwal the id_jadwal to set
     */
    public void setId_jadwal(Integer id_jadwal) {
        this.id_jadwal = id_jadwal;
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