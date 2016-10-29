package org.nhnnext.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	List<Answer> findByQuest(Question quest);
}
