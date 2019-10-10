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

@Entity
@Table(name = "nilai_akhir")

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */
public class NilaiAkhir {

    @Id
    @GenericGenerator(name = "incrementId", strategy = "increment")
    @GeneratedValue(generator = "incrementId")
    @Column (name = "id")
    private Long id;

    @NotNull(message = "Id siswa harus diisi")
    private Long id_siswa;

    @NotNull(message = "Nilai Total Tidak Boleh Kosong")
    private Float nilai_total;

    // @NotNull(message = "ID Pembuat Tidak Boleh Kosong")
    // @Column(name = "created_by", nullable = false, updatable = false)
    // private Long created_by;

    @NotNull
    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name= "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
    private Long created_by;

    @NotNull
    @ManyToOne(targetEntity=NilaiHuruf.class, optional=false)
    @JoinColumn(name="id_nilai_huruf", referencedColumnName = "id", insertable = false, updatable = false)
    private NilaiHuruf nilaihuruf;
    private Long id_nilai_huruf;
    

    
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_date;

    @Column(name = "updated_date", nullable = true, updatable = true)
    @UpdateTimestamp
    private Date updated_date;

    @Column(name = "updated_by", nullable = true)
    private Long updated_by;


    public Long getId_siswa(){
        return id_siswa;
    }
    public void setId_siswa(Long id_siswa){
        this.id_siswa = id_siswa;
    }

    public Float getNilai_total(){
        return nilai_total;
    }
    public void setNilai_total(Float nilai_total){
        this.nilai_total=nilai_total;
    }

    public Long getCreated_by(){
        return created_by;
    }
    public void setCreated_by(Long created_by){
        this.created_by=created_by;
    }

    public Date getCreated_date(){
        return created_date;
    }
    public void setCreated_date(Date created_date){
        this.created_date=created_date;
    }

    public NilaiHuruf getNilaihuruf(){
        return nilaihuruf;
    }

    public void setNilaihuruf(NilaiHuruf nilaihuruf){
        this.nilaihuruf=nilaihuruf;
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
     * @param updated_date the updated_date to set
     */
    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }
    /**
     * @return the updated_date
     */
    public Date getUpdated_date() {
        return updated_date;
    }

    /**
     * @return the id_nilai_huruf
     */
    public Long getId_nilai_huruf() {
        return id_nilai_huruf;
    }
    /**
     * @param id_nilai_huruf the id_nilai_huruf to set
     */
    public void setId_nilai_huruf(Long id_nilai_huruf) {
        this.id_nilai_huruf = id_nilai_huruf;
    }
}