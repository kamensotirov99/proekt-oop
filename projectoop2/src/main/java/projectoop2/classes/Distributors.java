package projectoop2.classes;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="distributors")
public class Distributors implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dis_id")
	private int disId;
	
	@Column(name="dis_username",length=45,nullable=false,unique=true)
	private String disUsername;
	
	@Column(name="dis_password",length=45,nullable=false)
	private String disPassword;
	
	@Column(name="dis_fname",length=45,nullable=false)
	private String disFname;
	
	@Column(name="dis_lname",length=45,nullable=false)
	private String disLname;
	
	@Column(name="dis_email",length=45,nullable=false,unique=true)
	private String disEmail;
	
	@Column(name="dis_rating")
	private Float disRating;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "event_distributors", 
        joinColumns = { @JoinColumn(name = "dis_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "ev_id") }
    )
	private Set<Events> DisEvents = new HashSet<>();//eventite,svurzani s tozi distributor
	
	@OneToMany(mappedBy="distributors")
	private Set<Transactions> transactionses = new HashSet<Transactions>();

	public Distributors() {
	}

	public Distributors(int disId, String disUsername, String disPassword, String disFname, String disLname,
			String disEmail) {
		this.disId = disId;
		this.disUsername = disUsername;
		this.disPassword = disPassword;
		this.disFname = disFname;
		this.disLname = disLname;
		this.disEmail = disEmail;
		
	}

	public Distributors(int disId, String disUsername, String disPassword, String disFname, String disLname,
			String disEmail, Float disRating, Set<Events> eventses, Set transactionses) {
		this.disId = disId;
		this.disUsername = disUsername;
		this.disPassword = disPassword;
		this.disFname = disFname;
		this.disLname = disLname;
		this.disEmail = disEmail;
		this.disRating = disRating;
		this.DisEvents = eventses;
		this.transactionses = transactionses;
	}

	public int getDisId() {
		return this.disId;
	}

	public void setDisId(int disId) {
		this.disId = disId;
	}

	public String getDisUsername() {
		return this.disUsername;
	}

	public void setDisUsername(String disUsername) {
		this.disUsername = disUsername;
	}

	public String getDisPassword() {
		return this.disPassword;
	}

	public void setDisPassword(String disPassword) {
		this.disPassword = disPassword;
	}

	public String getDisFname() {
		return this.disFname;
	}

	public void setDisFname(String disFname) {
		this.disFname = disFname;
	}

	public String getDisLname() {
		return this.disLname;
	}

	public void setDisLname(String disLname) {
		this.disLname = disLname;
	}

	public String getDisEmail() {
		return this.disEmail;
	}

	public void setDisEmail(String disEmail) {
		this.disEmail = disEmail;
	}

	public Float getDisRating() {
		return this.disRating;
	}

	public void setDisRating(Float disRating) {
		this.disRating = disRating;
	}

	public Set<Events> getDisEvents() {
		return this.DisEvents;
	}

	public void setDisEvents(Set<Events> eventses) {
		this.DisEvents = eventses;
	}

	public Set getTransactionses() {
		return this.transactionses;
	}

	public void setTransactionses(Set transactionses) {
		this.transactionses = transactionses;
	}

}
