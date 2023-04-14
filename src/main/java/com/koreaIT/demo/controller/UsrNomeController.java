package com.koreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrNomeController {
	
	@RequestMapping("/usr/home/main1")
	@ResponseBody
	public int showMain() {
		return 1;
	}
	
	
}

