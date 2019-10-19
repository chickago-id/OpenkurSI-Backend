package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Username harus diisi.")
    @Column(unique=true)
    private String username;

    @NotNull(message = "Email harus diisi.")
    @Column(unique=true)
    private String email;
    
    @NotNull(message = "Password harus diisi.")
    private String password;
    @NotNull(message = "Role harus diisi.")
    private String role;

    @ManyToOne
    @JoinColumn(name = "levelid", insertable = false, updatable = false, nullable = false)
    @ColumnDefault("4")
    private AccessLevel accessLevel;
    private Integer levelid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @JsonProperty(value = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param accessLevel the accessLevel to set
     */
    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
    /**
     * @return the accessLevel
     */
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }
    /**
     * @param levelid the levelid to set
     */
    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }
    /**
     * @return the levelid
     */
    public Integer getLevelid() {
        return levelid;
    }

    @Override
    public String toString() {
        return "{\n" + "id:" + id + ",\n" + "username:" + username + ",\n" + "email:" + email + ",\n" + "role:" + role
                + "\n" + "}";
    }

}