package pl.com.bernas.ioz.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * User: iru
 * Date: Feb 10, 2010
 * Time: 3:26:27 PM
 */
@MappedSuperclass
public abstract class AbstractIozEntity implements Serializable, IozEntity {

	private static final long serialVersionUID = 1L;
	private Long id;
    private Timestamp creationDate;
    @Version
    @Access(AccessType.FIELD)
    private long version;
    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="creation_date", nullable=false)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
