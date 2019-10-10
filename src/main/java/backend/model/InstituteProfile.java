package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Entity
@Table(name = "institute_profile")
public class InstituteProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String kode_perusahaan;
    @NotNull
    private String negara;
    @NotNull
    private String provinsi;
    @NotNull
    private String kota;
    @Lob
    @Column(length = 100000)
    private byte[] logo;

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
     * @return the kode_perusahaan
     */
    public String getKode_perusahaan() {
        return kode_perusahaan;
    }
    /**
     * @param kode_perusahaan the kode_perusahaan to set
     */
    public void setKode_perusahaan(String kode_perusahaan) {
        this.kode_perusahaan = kode_perusahaan;
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
    public byte[] getLogo() {
        return logo;
    }
    /**
     * @param logo the logo to set
     */
    public void setLogo(byte[] logo) {
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
    
}