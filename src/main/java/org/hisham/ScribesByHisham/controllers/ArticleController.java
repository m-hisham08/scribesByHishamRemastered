package org.hisham.ScribesByHisham.controllers;

import jakarta.validation.Valid;
import org.hisham.ScribesByHisham.constants.AppConstants;
import org.hisham.ScribesByHisham.models.Article;
import org.hisham.ScribesByHisham.payloads.ApiResponse;
import org.hisham.ScribesByHisham.payloads.request.ArticleRequestDTO;
import org.hisham.ScribesByHisham.payloads.response.ArticleResponseDTO;
import org.hisham.ScribesByHisham.payloads.response.PagedResponse;
import org.hisham.ScribesByHisham.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<PagedResponse<ArticleResponseDTO>> getAllArticles(
            @RequestParam(name = "page", defaultValue = AppConstants.Defaults.PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.Defaults.PAGE_SIZE) int size,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.Defaults.SORT_BY) String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = AppConstants.Defaults.SORT) String sortDirection
    ){
        Page<Article> pagedArticles = articleService.getAllArticles(page, size, sortDirection, sortBy);
        List<Article> articles = pagedArticles.getContent();

        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());

        List<ArticleResponseDTO> articleResponses = articles.stream()
                .map(article -> new ArticleResponseDTO(
                        article.getId(),
                        article.getTitle(),
                        article.getContent(),
                        formatter.format(article.getCreatedAt()), // Convert Instant to formatted String
                        article.getMinuteRead()
                ))
                .collect(Collectors.toList());

        PagedResponse<ArticleResponseDTO> response = new PagedResponse<ArticleResponseDTO>(articleResponses, pagedArticles.getNumber(), pagedArticles.getSize(), pagedArticles.getTotalElements(), pagedArticles.getTotalPages(), pagedArticles.isLast());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDTO> getSingleArticle(
            @PathVariable(name = "articleId") String articleId
    ){
        Article article = articleService.getSingleArticle(articleId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
        ArticleResponseDTO response = new ArticleResponseDTO(article.getId(), article.getTitle(), article.getContent(), formatter.format(article.getCreatedAt()), article.getMinuteRead());

        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<ArticleResponseDTO> createArticle(@RequestBody @Valid ArticleRequestDTO articleRequest){
        Article article = articleService.addArticle(articleRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{articleId}")
                .buildAndExpand(article.getId())
                .toUri();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
        ArticleResponseDTO response = new ArticleResponseDTO(article.getId(), article.getTitle(), article.getContent(), formatter.format(article.getCreatedAt()), article.getMinuteRead());

        return ResponseEntity.created(location).body(response);
    }
}
