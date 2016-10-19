package org.nhnnext.web;

import javax.servlet.http.HttpSession;

import org.nhnnext.domain.User;
import org.nhnnext.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println("user :" + user);
		userRepository.save(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user==null) {
			System.out.println("Login Failure!");
			return "redirect:/user/loginForm";
		}
		
		if (!password.equals(user.getPassword())) {
			System.out.println("Login Failure!");
			return "redirect:/user/loginForm";
		}
		
		System.out.println("Login Success!");
		session.setAttribute("sessionedUser", user);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
	@GetMapping("/{id}/updateForm")
	public String update(@PathVariable Long id, Model model, HttpSession session) {
		Object tempUser = session.getAttribute("sessionedUser");
		if (tempUser == null) {
			return "redirect:/user/loginForm";
		}
		
		User sessionedUser = (User)tempUser;
		if (!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("You can't change other user's information.");
		}
		
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "updateForm";
	}
	
	@PostMapping("/{id}/update")
	public String updateUser(@PathVariable Long id, User updatedUser, HttpSession session) {
		Object tempUser = session.getAttribute("sessionedUser");
		if (tempUser == null) {
			return "redirect:/user/loginForm";
		}
		
		User sessionedUser = (User)tempUser;
		if (!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("You can't change other user's information.");
		}
		
		System.out.println("user : " + updatedUser);
		User dbUser = userRepository.findOne(id);
		dbUser.update(updatedUser);
		userRepository.save(dbUser);
		return "redirect:/user/list";
	}
}
