package bootcamp.java.blackjack.spring.models.player;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="players", uniqueConstraints=@UniqueConstraint(name="UIDX_Code", columnNames= {"username"}))
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=50, nullable=false)
	private String firstName = "";
	@Column(length=50, nullable=false)
	private String lastName = "";
	@Column(length=100, nullable=false)
	private String username = "";
	@Column(length=255, nullable=false)
	private String password = "";
	@Column(columnDefinition="decimal(11,2) not null")
	private double wallet = 0;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateCreated = LocalDateTime.now();
	@Column(columnDefinition="decimal(11,2) not null")
	private double winnings = 0;
	
	// Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public double getWinnings() {
		return winnings;
	}
	public void setWinnings(double winnings) {
		this.winnings = winnings;
	}
	
	
	
}
