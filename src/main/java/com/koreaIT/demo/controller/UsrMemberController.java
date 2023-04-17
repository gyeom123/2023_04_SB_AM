package com.koreaIT.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.ArticleService;
import com.koreaIT.demo.vo.Article;

@Controller
public class UsrMemberController {

	private MemberService memberService;

	@Autowired
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 액션 메서드
	@RequestMapping("/usr/member/dojoin")
	@ResponseBody
	public Member dojoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		memberService.writeMember(loginId, loginPw, name, nickname, cellphoneNum, email);

		int id = memberService.getLastInsertId();

		return memberService.getArticleById(id);
	}

}
