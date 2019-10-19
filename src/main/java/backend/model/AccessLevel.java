package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Entity
@Table(name = "access_level")
public class AccessLevel {

    @Id
    @GenericGenerator(name="incrementId",strategy="increment")
    @GeneratedValue(generator = "incrementId")
    private Integer id;

    @NotNull(message = "Akses level tidak boleh kosong")
    @Column(name = "access_level", unique = true)
    private String access_level;

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
     * @return the access_level
     */
    public String getAccess_level() {
        return access_level;
    }
    /**
     * @param access_level the access_level to set
     */
    public void setAccess_level(String access_level) {
        this.access_level = access_level;
    }
}