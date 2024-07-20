package org.hisham.ScribesByHisham.repositories;

import org.hisham.ScribesByHisham.models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
}
