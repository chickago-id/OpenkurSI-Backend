package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_batch;

    @NotNull(message = "Nomor Batch harus diisi.")
    private Integer no_batch;

    public Long getId_batch() {
        return id_batch;
    }

    public void setId_batch(Long id_batch) {
        this.id_batch = id_batch;
    }

    public Integer getNo_batch() {
        return no_batch;
    }

    public void setNo_batch(Integer no_batch) {
        this.no_batch = no_batch;
    }
}