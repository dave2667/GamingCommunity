package com.dbrigade;
import javax.persistence.*;

@Entity
@Table(name = "RANK")
public class Rank {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rank_id")
	private int rankID;
	
	@Column(name = "rank_name")	
	private String rankName;
	
	@Column(name = "rank_active")	
	private boolean active;
	
	public Rank() {	}
	
	public Rank(String rankName, boolean active) {
		super();
		this.rankName = rankName;
		this.active = active;
	}

	public int getRankID() {
		return rankID;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
}
