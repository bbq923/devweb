package org.nhnnext.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
	private Question quest;

	private String contents;

	public Answer() {
	}

	public Answer(User writer, Question quest, String contents) {
		super();
		this.writer = writer;
		this.quest = quest;
		this.contents = contents;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public void setQuest(Question quest) {
		this.quest = quest;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Question getQuestion() {
		return this.quest;
	}
	
	public User getWriter() {
		return this.writer;
	}
	
	@Override
	public String toString() {
		return "Answer [id=" + id + ", writer=" + writer + ", quest=" + quest + ", contents=" + contents + "]";
	}

	public boolean delete(User sessionedUser) {
		if (this.writer.getUserId() == sessionedUser.getUserId()) {
			return true; // 삭제 시도 유저가 해당 answer를 작성한 경우 삭제 허용
		}
		
		return false;
	}
	
	public boolean matchUser(User loginUser) {
		if (loginUser == null) return false;
		
		return loginUser.matchId(this.writer);
	}
	
}
