package com.dbrigade;
import javax.persistence.*;

@Entity
@Table(name = "MESSAGEBOARD")
public class MessageBoard {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "messageboard_id")
	private int messageboardID;
	
	@Column(name = "messageboard_name")	
	private String messageboardName;
	
	@Column(name = "messageboard_active")	
	private boolean active;
	
	@Column(name = "rank_access")	
	private boolean rankAccess;
	
	public MessageBoard() {	}
	
	public MessageBoard(String messageboardName, boolean active, boolean rankAccess) {
		super();
		this.messageboardName = messageboardName;
		this.active = active;
		this.rankAccess = rankAccess;
	}

	public int getMessageboardID() {
		return messageboardID;
	}

	public String getMessageboardName() {
		return messageboardName;
	}

	public void setMessageboardName(String messageboardName) {
		this.messageboardName = messageboardName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isRankAccess() {
		return rankAccess;
	}

	public void setRankAccess(boolean rankAccess) {
		this.rankAccess = rankAccess;
	}
	
}