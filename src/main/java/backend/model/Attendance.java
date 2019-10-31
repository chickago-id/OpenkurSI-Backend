package backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName="id_user", insertable = false, updatable = false, nullable = false)
    private UserDetail userDetail;
    private Long id_user;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jadwal", referencedColumnName="id", insertable = false, updatable = false, nullable = false)
    private Jadwal jadwal;
    private Integer id_jadwal;
    private Date clock;
    @Nullable
    private Date clock_out;
    @Nullable
    private Integer is_approved;
    private Integer status;
    private Integer type;
    private String token;
    private String latitude;
    private String longitude;
    private Long recognition_id;

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
    /**
     * @return the clock
     */
    public Date getClock() {
        return clock;
    }
    /**
     * @param clock the clock to set
     */
    public void setClock(Date clock) {
        this.clock = clock;
    }
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
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
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }
    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }
    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    /**
     * @return the recognition_id
     */
    public Long getRecognition_id() {
        return recognition_id;
    }
    /**
     * @param recognition_id the recognition_id to set
     */
    public void setRecognition_id(Long recognition_id) {
        this.recognition_id = recognition_id;
    }
    /**
     * @return the clock_out
     */
    public Date getClock_out() {
        return clock_out;
    }
    /**
     * @param clock_out the clock_out to set
     */
    public void setClock_out(Date clock_out) {
        this.clock_out = clock_out;
    }
    /**
     * @return the is_approved
     */
    public Integer getIs_approved() {
        return is_approved;
    }
    /**
     * @param is_approved the is_approved to set
     */
    public void setIs_approved(Integer is_approved) {
        this.is_approved = is_approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attendance)) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(userDetail.getNama_lengkap(), that.userDetail.getNama_lengkap()) &&
                Objects.equals(jadwal.getId(), that.jadwal.getId()) &&
                Objects.equals(clock, that.clock) &&
                Objects.equals(status, that.status) &&
                Objects.equals(type, that.type) &&
                Objects.equals(token, that.token) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(recognition_id, that.recognition_id) &&
                Objects.equals(clock_out, that.clock_out) &&
                Objects.equals(is_approved, that.is_approved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            userDetail.getNama_lengkap(), 
            jadwal.getId(), 
            clock, 
            status, 
            type, 
            token, 
            latitude, 
            longitude, 
            recognition_id,
            clock_out,
            is_approved
        );
    }
}