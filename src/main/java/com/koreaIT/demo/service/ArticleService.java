package com.koreaIT.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreaIT.demo.repository.ArticleRepository;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.Article;
import com.koreaIT.demo.vo.Member;
import com.koreaIT.demo.vo.ResultData;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public void writeArticle(String title, String body, int memberId) {
		articleRepository.writeArticle(title, body, memberId);
	}
	
	public int getLastInsertId() {
		return articleRepository.getLastInsertId();
	}
	
	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}
	
	public List<Article> getArticles(){
		return articleRepository.getArticles();
	}
	
	public ResultData<Article> modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
		return ResultData.from("S-1", Util.f("%d번 게시물 수정 했습니다.", getArticleById(id)));
	}
	
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public ResultData ArticleModifyIdCkr(int Articleid, int memberId) {
		
		if (Articleid != memberId) {
			return ResultData.from("F-3", Util.f("%d번 게시물에 권한이 없습니다.", Articleid));
		}
		
		return ResultData.from("S-1", Util.f("%d번 게시물 수정 가능.", Articleid));
	}
	
}
