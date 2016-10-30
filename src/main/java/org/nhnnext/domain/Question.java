package org.nhnnext.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonProperty
	private User writer;
	
	@JsonProperty
	private String title;
	
	@Lob
	@JsonProperty
	private String contents;
	
	@JsonProperty
	private Integer countOfAnswer = 0;
	
	@OneToMany(mappedBy="question") // mappedBy의 이름은 ManyToOne 매핑 되는 쪽에서의 이름을 사용하면 된다. (Answer 클래스에서 question이라는 이름으로 ManyToOne 매핑 중)
	@OrderBy("id DESC")
	private List<Answer> answers;
	
	public Question() {}

	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameWriter(User loginUser) {
		System.out.println("writer : " + writer);
		return this.writer.equals(loginUser);
	}

	public void addAnswer() {
		this.countOfAnswer += 1;
	}
	
	public void deleteAnswer() {
		this.countOfAnswer -= 1;
	}

	
}
