package org.nhnnext.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.nhnnext.domain.Answer;
import org.nhnnext.domain.AnswerRepository;
import org.nhnnext.domain.Question;
import org.nhnnext.domain.QuestionRepository;
import org.nhnnext.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}
		return "/qna/form";
	}

	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		System.out.println("user : " + sessionUser);
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/show") //이 때의 id는 Question의 id
	public String show(@PathVariable Long id, Model model) {
		Question question = questionRepository.findOne(id);
		List<Answer> answers = answerRepository.findByQuest(question);
		
		model.addAttribute("question", question);
		model.addAttribute("answers", answers);
		return "/qna/show";
	}
}
