package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Entity
@Table(name = "presence_status")
public class PresenceStatus {
    @Id
    @GenericGenerator(name="incrementId",strategy="increment")
    @GeneratedValue(generator = "incrementId")
    private Integer id;
    private String name;

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
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}