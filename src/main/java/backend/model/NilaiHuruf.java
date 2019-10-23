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
@Table(name="nilai_huruf")

public class NilaiHuruf {

    @Id
    @GenericGenerator(name="incrementId",strategy="increment")
    @GeneratedValue(generator = "incrementId")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Nilai Huruf Harus diisi")
    private String nilai_huruf;

    @NotNull(message = "Batas Bawah Harus Diisi")
    private Integer batas_bawah;

    @NotNull(message = "Batas Atas Harus Diisi")
    private Integer batas_atas;

    @ManyToOne
    @JoinColumn(name = "created_by", insertable = false, updatable = false, nullable = false)
    private UserDetail userDetail;
    private Long created_by;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_date;
    
    @Column(name = "updated_by", nullable =  true)
    private Long updated_by;

    @Column(name = "updated_date", nullable = false, updatable = true)
    @UpdateTimestamp
    private Date updated_date;

    public String getNilai_huruf(){
        return nilai_huruf;
    }
    public void setNilai_huruf(String nilai_huruf){
        this.nilai_huruf = nilai_huruf;
    }

    public Integer getBatas_bawah(){
        return batas_bawah;
    }
    public void setBatas_bawah(Integer batas_bawah){
        this.batas_bawah = batas_bawah;
    }

    public Integer getBatas_atas(){
        return batas_atas;
    } 
    public void setBatas_atas(Integer batas_atas){
        this.batas_atas=batas_atas;
    }

    public Long getCreated_by(){
        return created_by;
    }
    public void setCreated_by(Long created_by){
        this.created_by=created_by;
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

    public Date getCreated_date(){
        return created_date;
    }
    public void setCreated_date(Date created_date){
        this.created_date=created_date;
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
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
}