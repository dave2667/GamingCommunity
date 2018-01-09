package com.dbrigade;
import javax.persistence.*;

@Entity
@Table(name = "MESSAGEBOARDACCESS")
public class MessageBoardAccess {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "boardaccess_id")
	private int boardAccessID;
	
	@Column(name = "messageboard_id")
	private int messageboardID;
	
	@Column(name = "access_id")
	private int accessID;

	public MessageBoardAccess(int boardAccessID) {
		super();
		this.boardAccessID = boardAccessID;
	}

	public MessageBoardAccess(int messageboardID, int accessID) {
		super();
		this.messageboardID = messageboardID;
		this.accessID = accessID;
	}

	public int getBoardAccessID() {
		return boardAccessID;
	}

	public int getMessageboardID() {
		return messageboardID;
	}

	public void setMessageboardID(int messageboardID) {
		this.messageboardID = messageboardID;
	}

	public int getAccessID() {
		return accessID;
	}

	public void setAccessID(int accessID) {
		this.accessID = accessID;
	}
	
	
}
