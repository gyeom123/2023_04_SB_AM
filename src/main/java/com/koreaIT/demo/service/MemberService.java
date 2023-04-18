package com.koreaIT.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreaIT.demo.repository.MemberRepository;
import com.koreaIT.demo.vo.Member;

@Service
public class MemberService {

	private MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public int doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {

		Member existsMember = getMemberByLoginId(loginId);

		if (existsMember != null) {
			return -1;
		}
		
		existsMember = getMemberBynNickname(nickname);
		
		if (existsMember != null) {
			return -2;
		}

		existsMember = getMemberBynNameEmail(name, email);

		if (existsMember != null) {
			return -3;
		}

		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		return memberRepository.getLastInsertId();
	}

	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	private Member getMemberBynNickname(String nickname) {
		return memberRepository.getMemberByNickname(nickname);
	}

	private Member getMemberBynNameEmail(String name, String email) {
		return memberRepository.getMemberBynNameEmail(name,email);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

}
