package projectoop2.classes;

import javax.persistence.*;

@Entity
@Table(name="transactions")
public class Transactions implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tr_id")
	private Integer trId;
	
	@ManyToOne
    @JoinColumn(name="dis_id", nullable=false)
	private Distributors distributors;
	
	
	@ManyToOne
    @JoinColumn(name="ev_id", nullable=false)
	private Events events;
	
	@Column(name="tr_buyer_fname",length=45,nullable=false)
	private String trBuyerFname;
	
	@Column(name="tr_buyer_lname",length=45,nullable=false)
	private String trBuyerLname;
	
	@Column(name="ticket_tr_count",nullable=false)
	private int ticketTrCount;//broi zakupeni bileti ot kupuvacha

	public Transactions() {
	}

	public Transactions(Distributors distributors, Events events, String trBuyerFname, String trBuyerLname,
			int ticketTrCount) {
		this.distributors = distributors;
		this.events = events;
		this.trBuyerFname = trBuyerFname;
		this.trBuyerLname = trBuyerLname;
		this.ticketTrCount = ticketTrCount;
	}

	public Integer getTrId() {
		return this.trId;
	}

	public void setTrId(Integer trId) {
		this.trId = trId;
	}

	public Distributors getDistributors() {
		return this.distributors;
	}

	public void setDistributors(Distributors distributors) {
		this.distributors = distributors;
	}

	public Events getEvents() {
		return this.events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}

	public String getTrBuyerFname() {
		return this.trBuyerFname;
	}

	public void setTrBuyerFname(String trBuyerFname) {
		this.trBuyerFname = trBuyerFname;
	}

	public String getTrBuyerLname() {
		return this.trBuyerLname;
	}

	public void setTrBuyerLname(String trBuyerLname) {
		this.trBuyerLname = trBuyerLname;
	}

	public int getTicketTrCount() {
		return this.ticketTrCount;
	}

	public void setTicketTrCount(int ticketTrCount) {
		this.ticketTrCount = ticketTrCount;
	}

}
