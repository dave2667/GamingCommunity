package com.dbrigade;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "MESSAGE")
public class Message {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private int messageID;
	
	@Column(name = "messageboard_id")
	private int messageBoardID;
	
	@Column(name = "main_message")
	private boolean mainMessage;
	
	@Column(name = "main_id")	
	private int mainID;
	
	@Column(name = "post_time")	
	private LocalDateTime postTime;
	
	@Column(name = "member_id")	
	private int memberID;
	
	@Column(name = "message_title")	
	private String messageTitle;
	
	@Column(name = "message_body")	
	private String messageBody;
	
	public Message() {	}

	public Message(int messageBoardID, boolean mainMessage, int mainID, LocalDateTime postTime, int memberID,
			String messageTitle, String messageBody) {
		super();
		this.messageBoardID = messageBoardID;
		this.mainMessage = mainMessage;
		this.mainID = mainID;
		this.postTime = postTime;
		this.memberID = memberID;
		this.messageTitle = messageTitle;
		this.messageBody = messageBody;
	}

	public int getMessageID() {
		return messageID;
	}

	public int getMessageBoardID() {
		return messageBoardID;
	}

	public void setMessageBoardID(int messageBoardID) {
		this.messageBoardID = messageBoardID;
	}

	public boolean isMainMessage() {
		return mainMessage;
	}

	public void setMainMessage(boolean mainMessage) {
		this.mainMessage = mainMessage;
	}

	public int getMainID() {
		return mainID;
	}

	public void setMainID(int mainID) {
		this.mainID = mainID;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
}
