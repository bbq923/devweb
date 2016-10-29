package org.nhnnext.web;

import javax.servlet.http.HttpSession;

import org.nhnnext.domain.Answer;
import org.nhnnext.domain.AnswerRepository;
import org.nhnnext.domain.Question;
import org.nhnnext.domain.QuestionRepository;
import org.nhnnext.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("{id}/post") // 이 때의 id는 해당 question의 id
	public String post(@PathVariable Long id, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}
		
		User writer = HttpSessionUtils.getUserFromSession(session);
		Question quest = questionRepository.findOne(id);
		
		Answer answer = new Answer(writer, quest, contents);
		answerRepository.save(answer);
		return String.format("redirect:/questions/%d/show", id);
	}
	
	@DeleteMapping("{qid}/{id}/delete") // 이 때의 id는 해당 answer의 id, 고려할 점은 삭제 버튼을 클릭한 사용자가 answer를 등록한 사용자와 동일 인물인지 검사
	public String delete(@PathVariable Long qid, @PathVariable Long id, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			System.out.println("로그인 하시고 삭제 시도 하세요...");
			return "redirect:/user/loginForm";
		}
		
		Answer answer = answerRepository.findOne(id);
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		
		if (!answer.matchUser(sessionedUser)) {
			System.out.println("다른 사람 답변 지우지 마세요...");
			return String.format("redirect:/questions/%d/show", qid);
		}
		
		answerRepository.delete(answer);
		
		return String.format("redirect:/questions/%d/show", qid);
	}
}
