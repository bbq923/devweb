package org.nhnnext.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
public class QnaController {
	
	@GetMapping("/show")
	public String show() {
		return "qna/show";
	}
	
	@GetMapping("/form")
	public String form() {
		return "qna/form";
	}
}
