package bootcamp.java.blackjack.spring.models.hand;

import java.time.LocalDateTime;

import bootcamp.java.blackjack.spring.models.player.Player;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="hands")
public class Hand {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition="decimal(11,2) not null")
	private double initialBet = 0;
	private int playerHandTotal = 0;
	private int dealerHandTotal = 0;
	@Column(length=4, nullable=false)
	private String winLoss = "LOSS";
	@Column(columnDefinition="decimal(11,2) not null")
	private double amountWon = 0; // Doesn't include initial bet
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateTime = LocalDateTime.now();
	@ManyToOne(optional=false)
	@JoinColumn(name="playerId")
	private Player player;
	
	
	// Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getInitialBet() {
		return initialBet;
	}
	public void setInitialBet(double initialBet) {
		this.initialBet = initialBet;
	}
	public int getPlayerHandTotal() {
		return playerHandTotal;
	}
	public void setPlayerHandTotal(int playerHandTotal) {
		this.playerHandTotal = playerHandTotal;
	}
	public int getDealerHandTotal() {
		return dealerHandTotal;
	}
	public void setDealerHandTotal(int dealerHandTotal) {
		this.dealerHandTotal = dealerHandTotal;
	}
	public String getWinLoss() {
		return winLoss;
	}
	public void setWinLoss(String winLoss) {
		this.winLoss = winLoss;
	}
	public double getAmountWon() {
		return amountWon;
	}
	public void setAmountWon(double amountWon) {
		this.amountWon = amountWon;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
}
