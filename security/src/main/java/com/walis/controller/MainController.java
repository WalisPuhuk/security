package com.walis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.walis.entity.Member;
import com.walis.service.MemberService;
import com.walis.service.SecurityService;
import com.walis.validator.MemberValidator;

@Controller
public class MainController {

	@Autowired
	private MemberService memSvc;
	
	@Autowired
	private SecurityService securitySvc;
	
	@Autowired
	private MemberValidator memValidator;
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("memForm", new Member());
		
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registration(@ModelAttribute("memForm") Member memForm, BindingResult bindingResult, Model model) {
		memValidator.validate(memForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		
		memSvc.save(memForm);
		
		securitySvc.autologin(memForm.getMemId(), memForm.getPassword());
		
		return "redirect:/welcome";
	}
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your id and password is invalid.");
		
		if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}
	
	
	
	/**
	 * 首頁預設進入 "welcome.html"
	 * @author Walis
	 * @param model
	 */
	@GetMapping({"/", "welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
	
}
