package com.koreaIT.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.ArticleService;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.Article;
import com.koreaIT.demo.vo.ResultData;

@Controller
public class UsrArticleController {

	private ArticleService articleService;

	@Autowired
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	// 액션 메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpSession httpsession, String title, String body) {

		if (httpsession.getAttribute("loginId") != null) {
			return ResultData.from("F-1", Util.f("로그인 후 이용해주세요"));
		}
		
		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}

		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}

		articleService.writeArticle(title, body,(int)httpsession.getAttribute("loginId"));
		
		int id = articleService.getLastInsertId();

		return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다", id), articleService.getArticleById(id));
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		return ResultData.from("S-1", Util.f("%d번 게시물 입니다", id), article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {

		return ResultData.from("S-1", "게시물 리스트", articleService.getArticles());
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpSession httpsession, int id, String title, String body) {
		// Data1 의미가 없는 특수한 경우에는 굳이 리스트 타입을 넣어 줄 필요는 없다.

		if (httpsession.getAttribute("loginId") != null) {
			return ResultData.from("F-1", Util.f("로그인 후 이용해주세요"));
		}

		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-2", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		int memberId = (int)httpsession.getAttribute("loginId");
		
		ResultData<Article> ArticleModifyIdCkr = articleService.ArticleModifyIdCkr(article.getMemberid(),memberId);

		//실패했으면 해당 코드를 실행 성공했으면 해당 코드 실행 X
		if (ArticleModifyIdCkr.isFail()) {
			return ResultData.from(ArticleModifyIdCkr.getResultCode(),ArticleModifyIdCkr.getMsg());
		}
		
		return articleService.modifyArticle(id, title, body);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Article> doDelete(HttpSession httpsession, int id) {

		if (httpsession.getAttribute("loginId") != null) {
			return ResultData.from("F-1", Util.f("로그인 후 이용해주세요"));
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		int memberId = (int)httpsession.getAttribute("loginId");
		
		if (article.getMemberid() != memberId) {
			return ResultData.from("F-2", Util.f("%d번 게시물에 권한이 없습니다.", id));
		}

		articleService.deleteArticle(id);

		return ResultData.from("S-1", Util.f("%d번 게시물을 삭제했습니다.", id));
	}
}
