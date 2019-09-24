package backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="nilai_huruf")

/**
 * NilaiHuruf
 */
public class NilaiHuruf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_nilai_huruf;

    @NotNull(message = "Nilai Huruf Harus diisi")
    private String nilai_huruf;

    @NotNull(message = "Batas Bawah Harus Diisi")
    private Integer batas_bawah;

    @NotNull(message = "Batas Atas Harus Diisi")
    private Integer batas_atas;

    @NotNull(message = "ID Pembuat Tidak Boleh Kosong !")
    private Long created_by;

    @NotNull(message = "Tanggal Data Dibuat Tidak Boleh Kosong")
    private Date created_date;


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

    public Date getCreated_date(){
        return created_date;
    }
    public void setCreated_date(Date created_date){
        this.created_date=created_date;
    }
}