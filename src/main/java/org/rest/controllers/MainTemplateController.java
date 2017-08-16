package org.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class MainTemplateController {
	@RequestMapping(value="/Accounts")
	public String getAccounts(){
		return "jsp/accountList";
	}

	@RequestMapping(value="/add/Accounts")
	public String addAccounts(){
		return "/template/addAccount";
	}
}
