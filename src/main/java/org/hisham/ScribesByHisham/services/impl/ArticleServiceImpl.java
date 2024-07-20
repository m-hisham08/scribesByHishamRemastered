package org.hisham.ScribesByHisham.services.impl;

import org.hisham.ScribesByHisham.constants.AppConstants;
import org.hisham.ScribesByHisham.exceptions.CustomExceptions.BadRequestException;
import org.hisham.ScribesByHisham.exceptions.CustomExceptions.ResourceNotFoundException;
import org.hisham.ScribesByHisham.models.Article;
import org.hisham.ScribesByHisham.payloads.request.ArticleRequestDTO;
import org.hisham.ScribesByHisham.repositories.ArticleRepository;
import org.hisham.ScribesByHisham.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Page<Article> getAllArticles(int page, int size, String sortDirection, String sortBy) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }
        if (size > AppConstants.Defaults.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page number cannot exceed " + AppConstants.Defaults.MAX_PAGE_SIZE);
        }
        if (!sortDirection.equals("ASC") && !sortDirection.equals("DESC")) {
            throw new BadRequestException("Invalid sort direction!");
        }

        Pageable pageable;
        if(sortDirection.equals("ASC")){
            pageable = PageRequest.of(page, size, Sort.Direction.ASC, sortBy);
        }
        else{
            pageable = PageRequest.of(page, size, Sort.Direction.DESC, sortBy);
        }

        Page<Article> articles = articleRepository.findAll(pageable);
        return articles;
    }

    @Override
    public Article getSingleArticle(String articleId) {
        Article article = articleRepository.findById(articleId).
                orElseThrow(() -> new ResourceNotFoundException("Article", "ID", articleId));

        return article;
    }

    @Override
    public Article addArticle(ArticleRequestDTO articleRequest) {
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setMinuteRead(articleRequest.getMinuteRead());

        Article savedArticle = articleRepository.save(article);

        return savedArticle;
    }
}
