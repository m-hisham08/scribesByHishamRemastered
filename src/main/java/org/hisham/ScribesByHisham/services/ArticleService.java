package org.hisham.ScribesByHisham.services;

import org.hisham.ScribesByHisham.models.Article;
import org.hisham.ScribesByHisham.payloads.request.ArticleRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

public interface ArticleService {
    Page<Article> getAllArticles(int page, int size, String sortDirection, String sortBy);
    Article getSingleArticle(String articleId);
    Article addArticle(ArticleRequestDTO articleRequest);
}
