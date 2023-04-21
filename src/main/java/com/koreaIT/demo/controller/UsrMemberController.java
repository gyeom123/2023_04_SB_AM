package com.koreaIT.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.MemberService;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.Member;
import com.koreaIT.demo.vo.ResultData;

@Controller
public class UsrMemberController {

	private MemberService memberService;

	@Autowired
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 액션 메서드
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(HttpSession httpsession, String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		if (memberService.MemberLoginChk(httpsession) == false) {
			return ResultData.from("F-1", Util.f("로그아웃 후 이용해주에요"));
		}
		
		if (Util.empty(loginId)) {
			return ResultData.from("F-2", "아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return ResultData.from("F-3", "비밀번호를 입력해주세요");
		}
		if (Util.empty(name)) {
			return ResultData.from("F-4", "이름을 입력해주세요");
		}
		if (Util.empty(nickname)) {
			return ResultData.from("F-5", "닉네임을 입력해주세요");
		}
		if (Util.empty(cellphoneNum)) {
			return ResultData.from("F-6", "전화번호를 입력해주세요");
		}
		if (Util.empty(email)) {
			return ResultData.from("F-7", "이메일을 입력해주세요");
		}

		ResultData<Integer> doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		//실패했으면 해당 코드를 실행 성공했으면 해당 코드 실행 X
		if (doJoinRd.isFail()) {
			return ResultData.from(doJoinRd.getResultCode(), doJoinRd.getMsg());
		}

		return ResultData.from(doJoinRd.getResultCode(), doJoinRd.getMsg(),
				memberService.getMemberById((int) doJoinRd.getData1()));
	}

	@RequestMapping("/usr/member/dologin")
	@ResponseBody
	public ResultData doLogin(HttpSession httpsession, String loginId, String loginPw) {

		if (memberService.MemberLoginChk(httpsession)) {
			return ResultData.from("F-1", Util.f("로그아웃 후 이용해주세요"));
		}

		if (Util.empty(loginId)) {
			return ResultData.from("F-2", "아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return ResultData.from("F-3", "비밀번호를 입력해주세요");
		}

		Member LoginMember = memberService.getMemberByLoginId(loginId);

		if (LoginMember == null) {
			return ResultData.from("F-4", Util.f("%s는 존재하지 않는 아이디입니다", loginId));
		}
		if (loginPw.equals(LoginMember.getLoginPw()) == false) {
			return ResultData.from("F-5", "비밀번호가 일치하지 않습니다.");
		}

		httpsession.setAttribute("loginId", LoginMember.getId());

		return ResultData.from("S-1", Util.f("%s회원님 환영합니다", LoginMember.getNickname()));
	}

	@RequestMapping("/usr/member/dologout")
	@ResponseBody
	public ResultData logOut(HttpSession httpsession) {
 
		if (memberService.MemberLoginChk(httpsession) == false) {
			return ResultData.from("F-1", Util.f("로그인 후 이용해주세요"));
		}

		httpsession.removeAttribute("loginId"); //removeAttribute 삭제

		return ResultData.from("S-1", Util.f("정상적으로 로그아웃 되었습니다."));
	}

}
