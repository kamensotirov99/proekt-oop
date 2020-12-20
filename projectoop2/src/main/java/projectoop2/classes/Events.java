package projectoop2.classes;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="events")
public class Events implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ev_id")
	private Integer evId;
	
	@Column(name="ev_type",length=45,nullable=false)
	private String evType;
	
	@Column(name="ev_location",length=45,nullable=false)
	private String evLocation;
	
	@Column(name="ev_date",nullable=false)
	
	private LocalDate evDate;
	
	@Column(name="ev_name",length=45,nullable=false)
	private String evName;
	
	@Column(name="ev_status",length=45,nullable=false)
	private String evStatus;
	
	@Column(name="ev_seatcount",nullable=false)
	private int evSeatcount;
	
	@Column(name="ev_curr_seats",nullable=false)
	private int evCurrSeats;
	
	@Column(name="ev_ticketprice",nullable=false)
	private double evTicketprice;
	
	@Column(name="ev_max_restriction")
	private int evMaxRestriction;//max broi bileti,koito mogat da budat zakupeni ot kupuvach
	
	@ManyToMany(mappedBy = "DisEvents")
	private Set<Distributors >distributorses = new HashSet<Distributors>();
	
	@OneToMany(mappedBy="events",fetch=FetchType.LAZY)
	private Set<Transactions> transactionses = new HashSet<Transactions>();
	
	@ManyToMany(mappedBy = "OrgEvents",fetch=FetchType.LAZY)
	private Set<Organizers> organizerses = new HashSet<Organizers>();

	public Events() {
	}

	public Events(String evType, String evLocation, LocalDate evDate, String evName, String evStatus, int evSeatcount,
			 double evTicketprice, int evMaxRestriction) {
		this.evType = evType;
		this.evLocation = evLocation;
		this.evDate = evDate;
		this.evName = evName;
		this.evStatus = evStatus;
		this.evSeatcount = evSeatcount;
		this.evCurrSeats = evSeatcount;
		this.evTicketprice = evTicketprice;
		this.evMaxRestriction = evMaxRestriction;
	}

	public Events(String evType, String evLocation, LocalDate evDate, String evName, String evStatus, int evSeatcount,
			int evCurrSeats, double evTicketprice, int evMaxRestriction, Set<Distributors> distributorses, Set<Transactions> transactionses,
			Set<Organizers> organizerses) {
		this.evType = evType;
		this.evLocation = evLocation;
		this.evDate = evDate;
		this.evName = evName;
		this.evStatus = evStatus;
		this.evSeatcount = evSeatcount;
		this.evCurrSeats = evCurrSeats;
		this.evTicketprice = evTicketprice;
		this.evMaxRestriction = evMaxRestriction;
		this.distributorses = distributorses;
		this.transactionses = transactionses;
		this.organizerses = organizerses;
	}

	public Integer getEvId() {
		return this.evId;
	}

	public void setEvId(Integer evId) {
		this.evId = evId;
	}

	public String getEvType() {
		return this.evType;
	}

	public void setEvType(String evType) {
		this.evType = evType;
	}

	public String getEvLocation() {
		return this.evLocation;
	}

	public void setEvLocation(String evLocation) {
		this.evLocation = evLocation;
	}

	public LocalDate getEvDate() {
		return this.evDate;
	}

	public void setEvDate(LocalDate evDate) {
		this.evDate = evDate;
	}

	public String getEvName() {
		return this.evName;
	}

	public void setEvName(String evName) {
		this.evName = evName;
	}

	public String getEvStatus() {
		return this.evStatus;
	}

	public void setEvStatus(String evStatus) {
		this.evStatus = evStatus;
	}

	public int getEvSeatcount() {
		return this.evSeatcount;
	}

	public void setEvSeatcount(int evSeatcount) {
		this.evSeatcount = evSeatcount;
	}

	public int getEvCurrSeats() {
		return this.evCurrSeats;
	}

	public void setEvCurrSeats(int evCurrSeats) {
		this.evCurrSeats = evCurrSeats;
	}

	public double getEvTicketprice() {
		return this.evTicketprice;
	}

	public void setEvTicketprice(double evTicketprice) {
		this.evTicketprice = evTicketprice;
	}

	public int getEvMaxRestriction() {
		return this.evMaxRestriction;
	}

	public void setEvMaxRestriction(int evMaxRestriction) {
		this.evMaxRestriction = evMaxRestriction;
	}

	public Set<Distributors> getDistributorses() {
		return this.distributorses;
	}

	public void setDistributorses(Set<Distributors> distributorses) {
		this.distributorses = distributorses;
	}

	public Set<Transactions> getTransactionses() {
		return this.transactionses;
	}

	public void setTransactionses(Set<Transactions> transactionses) {
		this.transactionses = transactionses;
	}

	public Set<Organizers> getOrganizerses() {
		return this.organizerses;
	}

	public void setOrganizerses(Set<Organizers> organizerses) {
		this.organizerses = organizerses;
	}

}
