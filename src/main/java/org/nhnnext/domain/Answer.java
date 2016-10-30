package org.nhnnext.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer extends AbstractEntity {
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	@JsonProperty
	private User writer;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	@JsonProperty
	private Question question;

	@Lob
	@JsonProperty
	private String contents;
	
	public Answer() {
	}

	public Answer(User writer, Question question, String contents) {
		this.writer = writer;
		this.question = question;
		this.contents = contents;
	}

	public boolean isSameWriter(User loginUser) {
		return loginUser.equals(this.writer);
	}
	
	public void setWriter(User writer) {
		this.writer = writer;
	}

	public void setQuest(Question question) {
		this.question = question;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Question getQuestion() {
		return this.question;
	}
	
	public User getWriter() {
		return this.writer;
	}
	
	@Override
	public String toString() {
		return "Answer [" + super.toString() + ", writer=" + writer + ", question=" + question + ", contents=" + contents + "]";
	}

	public boolean delete(User sessionedUser) {
		if (this.writer.getUserId() == sessionedUser.getUserId()) {
			return true; // 삭제 시도 유저가 해당 answer를 작성한 경우 삭제 허용
		}
		
		return false;
	}
	
}
