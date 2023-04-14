package com.koreaIT.demo.UsrArticleController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class ArticleController {


	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd() {

		return new Article();
	}

}

