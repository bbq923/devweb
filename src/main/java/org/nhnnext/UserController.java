package org.nhnnext;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>();
	
	@GetMapping("/helloworld")
	public String helloworld(String name, int age, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "helloworld";
	}
	
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println("User :" + user);
		users.add(user);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", users);
		return "list";
	}
}
