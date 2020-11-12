package projectoop2.classes;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="ev_type")
public class EvType implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="evtype_id")
	private int evtypeId;
	
	@Column(name="evtype_desc",length=45,nullable=false,unique=true)
	private String evtypeDesc;
	
	@OneToMany(mappedBy="evType",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<Events> eventses = new HashSet<Events>();

	public EvType() {
	}

	public EvType(int evtypeId, String evtypeDesc) {
		this.evtypeId = evtypeId;
		this.evtypeDesc = evtypeDesc;
	}

	public EvType(int evtypeId, String evtypeDesc, Set eventses) {
		this.evtypeId = evtypeId;
		this.evtypeDesc = evtypeDesc;
		this.eventses = eventses;
	}

	public int getEvtypeId() {
		return this.evtypeId;
	}

	public void setEvtypeId(int evtypeId) {
		this.evtypeId = evtypeId;
	}

	public String getEvtypeDesc() {
		return this.evtypeDesc;
	}

	public void setEvtypeDesc(String evtypeDesc) {
		this.evtypeDesc = evtypeDesc;
	}

	public Set getEventses() {
		return this.eventses;
	}

	public void setEventses(Set eventses) {
		this.eventses = eventses;
	}

}