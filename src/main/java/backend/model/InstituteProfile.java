package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Entity
@Table(name = "institute_profile")
public class InstituteProfile {

    @Id
    @GenericGenerator(name="incrementId",strategy="increment")
    @GeneratedValue(generator = "incrementId")
    private Integer id;
    @NotNull
    private String nama_institusi;
    @NotNull
    private String alamat;
    @NotNull
    private String kode_pos;
    @NotNull
    private String no_telepon;
    @NotNull
    private String website;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(unique = true)
    private String npwp;
    @NotNull
    @Column(unique = true)
    private String kode_institusi;
    @NotNull
    private String negara;
    @NotNull
    private String provinsi;
    @NotNull
    private String kota;
    private String logo;
    private String download_path;
    //@NotNull
    //private Integer is_group;

    //public InstituteProfile (Integer id,)

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
     * @return the nama_institusi
     */
    public String getNama_institusi() {
        return nama_institusi;
    }
    /**
     * @param nama_institusi the nama_institusi to set
     */
    public void setNama_institusi(String nama_institusi) {
        this.nama_institusi = nama_institusi;
    }
    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }
    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    /**
     * @return the kode_pos
     */
    public String getKode_pos() {
        return kode_pos;
    }
    /**
     * @param kode_pos the kode_pos to set
     */
    public void setKode_pos(String kode_pos) {
        this.kode_pos = kode_pos;
    }
    /**
     * @return the no_telepon
     */
    public String getNo_telepon() {
        return no_telepon;
    }
    /**
     * @param no_telepon the no_telepon to set
     */
    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }
    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }
    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the kode_institusi
     */
    public String getKode_institusi() {
        return kode_institusi;
    }
    /**
     * @param kode_institusi the kode_institusi to set
     */
    public void setKode_institusi(String kode_institusi) {
        this.kode_institusi = kode_institusi;
    }
    /**
     * @return the kota
     */
    public String getKota() {
        return kota;
    }
    /**
     * @param kota the kota to set
     */
    public void setKota(String kota) {
        this.kota = kota;
    }
    /**
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }
    /**
     * @param logo the logo to set
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }
    /**
     * @return the negara
     */
    public String getNegara() {
        return negara;
    }
    /**
     * @param negara the negara to set
     */
    public void setNegara(String negara) {
        this.negara = negara;
    }
    /**
     * @return the npwp
     */
    public String getNpwp() {
        return npwp;
    }
    /**
     * @param npwp the npwp to set
     */
    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }
    /**
     * @return the provinsi
     */
    public String getProvinsi() {
        return provinsi;
    }
    /**
     * @param provinsi the provinsi to set
     */
    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }
    /**
     * @return the download_path
     */
    public String getDownload_path() {
        return download_path;
    }
    /**
     * @param download_path the download_path to set
     */
    public void setDownload_path(String download_path) {
        this.download_path = download_path;
    }
    /*
    public Integer getIs_group() {
        return is_group;
    }
    public void setIs_group(Integer is_group) {
        this.is_group = is_group;
    }
    */
}