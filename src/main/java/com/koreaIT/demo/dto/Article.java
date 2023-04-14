package com.koreaIT.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 			   	//특수한 명령어 없이 다른 클래스의 데이터를 넘겨줄 수 있다
@NoArgsConstructor  // 매개변수가 없는 생성자를 자동생성
@AllArgsConstructor // 매개변수가 있는 생성자를 자동생성
public class Article {
	private int id;
	private String title;
	private String body;
}