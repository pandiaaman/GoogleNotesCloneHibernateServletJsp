package com.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="notes_table")
public class Note {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="note_id")
	private int id;
	@Column(name="note_title")
	private String title;
	@Column(name="note_content",length=1500)
	private String content;
	@Column(name="note_added_date")
	private Date addedDate;
	@Column(name="is_note_pinned")
	private boolean isNotePinned;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@Column(name="is_archived")
	private boolean isArchived;
	
	
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Note(String title, String content, Date addedDate,boolean isNotePinned, User user, boolean isArchived) {
		super();
		this.title = title;
		this.content = content;
		this.addedDate = addedDate;
		this.isNotePinned = isNotePinned;
		this.user = user;
		this.isArchived = isArchived;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	
	public boolean isNotePinned() {
		return isNotePinned;
	}

	public void setNotePinned(boolean isNotePinned) {
		this.isNotePinned = isNotePinned;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", content=" + content + ", addedDate=" + addedDate
				+ ", isNotePinned=" + isNotePinned + "]";
	}
	
	
	
	
}
