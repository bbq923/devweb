package org.nhnnext.web;

import javax.servlet.http.HttpSession;

import org.nhnnext.domain.Question;
import org.nhnnext.domain.QuestionRepository;
import org.nhnnext.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}
		return "/qna/form";
	}

	@PostMapping("/make")
	public String create(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		System.out.println("user : " + sessionUser);
		Question newQuestion = new Question(sessionUser.getUserId(), title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
}
