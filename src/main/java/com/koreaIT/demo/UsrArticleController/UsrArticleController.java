package com.koreaIT.demo.UsrArticleController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.dto.Article;

@Controller
public class UsrArticleController {

	private int lastArticleId; // 1씩 증가하게 만들 고유번호
	private List<Article> articles;// 게시글들을 저장하는 리스트

	public UsrArticleController() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();

		makeTestData();
	}

	private void makeTestData() { // test 데이터 1~10
		for (int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;

			writeArticle(title, body);
		}
	}

	private Article writeArticle(String title, String body) { // articles에 article를 저장

		int id = this.lastArticleId + 1;
		this.lastArticleId = id;

		Article article = new Article(id, title, body);

		articles.add(article);

		return article;
	}

	private Article getArticleById(int id) { // 원하는 게시물을 찾는 함수
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	private void deleteArticle(int id) { // 원하는 게시물을 삭제 함수

		Article article = getArticleById(id);

		articles.remove(article);
	}

	private void modofyArticle(int id, String title, String body) { // 원하는 게시물을 수정 함수
		Article article = getArticleById(id);

		article.setTitle(title);
		article.setBody(body);
	}

	@RequestMapping("/usr/article/doAdd") // 게시물 추가
	@ResponseBody
	public Article doAdd(String title, String body) {

		Article article = writeArticle(title, body);

		return article;
	}

	@RequestMapping("/usr/article/doDelete") // 게시물 삭제
	@ResponseBody
	public String delete(int id) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}

		deleteArticle(id);

		return id + "번 게시물을 삭제했습니다";
	}

	@RequestMapping("/usr/article/doModify") // 게시물 수정
	@ResponseBody
	public String doModify(int id, String title, String body) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}

		modofyArticle(id, title, body);
		return id + "번 게시물을 수정했습니다";
	}

	@RequestMapping("/usr/article/getArticles") // 게시물들 보기
	@ResponseBody
	public List<Article> getArticles() {

		return this.articles;
	}

	@RequestMapping("/usr/article/getArticles") // 게시물 보기
	@ResponseBody
	public Object getArticle(int id) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 게시글이 없습니다.";
		}

		return article;
	}
}