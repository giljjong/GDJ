package com.gdu.contact.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.contact.domain.ContactDTO;
import com.gdu.contact.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("ctt/list")
	public String list(Model model) {
		model.addAttribute("contacts", contactService.findAllContact());
		model.addAttribute("count", contactService.countAllContact());
		return "contact/list";
	}
	
	@GetMapping("contact/write")
	public String write() {
		return "contact/write";
	}
	
	@PostMapping("ctt/add")
	public String add(ContactDTO contact, Model model) {
		int result = contactService.addContact(contact);
		try {
			if(result == 1) {
				model.addAttribute("msg", "추가");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect";
	}
	
	@PostMapping("ctt/modify")
	public String edit(ContactDTO contact, Model model) {
		int result = contactService.modifyContact(contact);
		
		try {
			if(result == 1) {
				model.addAttribute("msg", "수정");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect";
	}
	
	@PostMapping("ctt/remove")
	public String remove(@RequestParam(value="no") int no, Model model) {
		int result = contactService.removeContact(no);
		
		try {
			if(result == 1) {
				model.addAttribute("msg", "삭제");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect";
	}
	
	@GetMapping("ctt/detail")
	public String detail(@RequestParam(value="no") int no, Model model) {
		model.addAttribute("contact", contactService.findContactByNo(no));
		return "contact/detail";
	}
	
}
