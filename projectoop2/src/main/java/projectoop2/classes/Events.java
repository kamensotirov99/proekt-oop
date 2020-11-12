package projectoop2.classes;

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
	
	@ManyToOne(optional=false)
	@JoinColumn(name="evtype_id",referencedColumnName="evtype_id")
	private EvType evType;
	
	@Column(name="ev_location",length=45,nullable=false)
	private String evLocation;
	
	@Column(name="ev_date",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date evDate;
	
	@Column(name="ev_name",length=45,nullable=false)
	private String evName;
	
	@Column(name="ev_status",nullable=false)
	private byte evStatus;
	
	@Column(name="ev_seatcount",nullable=false)
	private int evSeatcount;
	
	@Column(name="ev_curr_seats",nullable=false)
	private int evCurrSeats;
	
	@Column(name="ev_ticketprice",nullable=false)
	private float evTicketprice;
	
	@Column(name="ev_max_restriction")
	private int evMaxRestriction;//max broi bileti,koito mogat da budat zakupeni ot kupuvach
	
	@ManyToMany(mappedBy = "DisEvents")
	private Set<Distributors >distributorses = new HashSet<>();
	
	@OneToMany(mappedBy="events")
	private Set<Transactions> transactionses = new HashSet<>();
	
	@ManyToMany(mappedBy = "OrgEvents")
	private Set<Organizers> organizerses = new HashSet<>();

	public Events() {
	}

	public Events(EvType evType, String evLocation, Date evDate, String evName, byte evStatus, int evSeatcount,
			 float evTicketprice, int evMaxRestriction) {
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

	public Events(EvType evType, String evLocation, Date evDate, String evName, byte evStatus, int evSeatcount,
			int evCurrSeats, float evTicketprice, int evMaxRestriction, Set<Distributors> distributorses, Set<Transactions> transactionses,
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

	public EvType getEvType() {
		return this.evType;
	}

	public void setEvType(EvType evType) {
		this.evType = evType;
	}

	public String getEvLocation() {
		return this.evLocation;
	}

	public void setEvLocation(String evLocation) {
		this.evLocation = evLocation;
	}

	public Date getEvDate() {
		return this.evDate;
	}

	public void setEvDate(Date evDate) {
		this.evDate = evDate;
	}

	public String getEvName() {
		return this.evName;
	}

	public void setEvName(String evName) {
		this.evName = evName;
	}

	public byte getEvStatus() {
		return this.evStatus;
	}

	public void setEvStatus(byte evStatus) {
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

	public float getEvTicketprice() {
		return this.evTicketprice;
	}

	public void setEvTicketprice(float evTicketprice) {
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
