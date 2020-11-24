package projectoop2.classes;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="organizers")
public class Organizers implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="org_id")
	private Integer orgId;
	
	@Column(name="org_username",length=45,nullable=false,unique=true)
	private String orgUsername;
	
	@Column(name="org_password",length=45,nullable=false)
	private String orgPassword;
	
	@Column(name="org_fname",length=45,nullable=false)
	private String orgFname;
	
	@Column(name="org_lname",length=45,nullable=false)
	private String orgLname;
	
	@Column(name="org_email",length=45,nullable=false,unique=true)
	private String orgEmail;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "event_organizers", 
        joinColumns = { @JoinColumn(name = "org_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "ev_id") }
    )
	private Set<Events> OrgEvents = new HashSet<>();

	public Organizers() {
	}

	public Organizers(String orgUsername, String orgPassword, String orgFname, String orgLname, String orgEmail) {
		this.orgUsername = orgUsername;
		this.orgPassword = orgPassword;
		this.orgFname = orgFname;
		this.orgLname = orgLname;
		this.orgEmail = orgEmail;
	}

	public Organizers(String orgUsername, String orgPassword, String orgFname, String orgLname, String orgEmail,
			Set<Events> eventses) {
		this.orgUsername = orgUsername;
		this.orgPassword = orgPassword;
		this.orgFname = orgFname;
		this.orgLname = orgLname;
		this.orgEmail = orgEmail;
		this.OrgEvents = eventses;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgUsername() {
		return this.orgUsername;
	}

	public void setOrgUsername(String orgUsername) {
		this.orgUsername = orgUsername;
	}

	public String getOrgPassword() {
		return this.orgPassword;
	}

	public void setOrgPassword(String orgPassword) {
		this.orgPassword = orgPassword;
	}

	public String getOrgFname() {
		return this.orgFname;
	}

	public void setOrgFname(String orgFname) {
		this.orgFname = orgFname;
	}

	public String getOrgLname() {
		return this.orgLname;
	}

	public void setOrgLname(String orgLname) {
		this.orgLname = orgLname;
	}

	public String getOrgEmail() {
		return this.orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public Set<Events> getOrgEvents() {
		return this.OrgEvents;
	}

	public void setOrgEvents(Set<Events> eventses) {
		this.OrgEvents = eventses;
	}

	
}