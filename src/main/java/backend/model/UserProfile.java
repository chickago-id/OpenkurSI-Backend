package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile {

  // @ManyToOne(optional = false)
  // @JoinColumn(name = "id", referencedColumnName = "id_user", insertable =
  // false, updatable = false)
  // private User user;

  // @OneToOne(cascade = CascadeType.ALL)
  // @OneToOne(optional = false)
  // @JoinColumn(name = "id", referencedColumnName = "id_user_profile", insertable
  // = false, updatable = false)
  // @JoinTable(name = "kelas_peserta", joinColumns = @JoinColumn(name = "id"),
  // inverseJoinColumns = @JoinColumn(name = "id_user_profile"))
  // private KelasPeserta kelasPeserta;

  // @OneToMany(cascade=CascadeType.ALL)
  // @JoinTable(name="propertysub",joinColumns=@JoinColumn(name="propertyid"),
  // inverseJoinColumns=@JoinColumn(name="propertysubpk"))

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_user_profile;

  private Long id_kelas;
  private Long id_user;

  // public KelasPeserta getKelasPeserta() {
  // return kelasPeserta;
  // }

  public Long getId_user_profile() {
    return id_user_profile;
  }

  public void setId_user_profile(Long id_user_profile) {
    this.id_user_profile = id_user_profile;
  }

  public Long getId_kelas() {
    return id_kelas;
  }

  public void setId_kelas(Long id_kelas) {
    this.id_kelas = id_kelas;
  }

  public Long getId_user() {
    return id_user;
  }

  public void setId_user(Long id_user) {
    this.id_user = id_user;
  }

}