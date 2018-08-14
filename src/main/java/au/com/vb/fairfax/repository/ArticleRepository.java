package au.com.vb.fairfax.repository;

import au.com.vb.fairfax.model.Article;
import java.util.HashMap;
import java.util.Map;

public class ArticleRepository {

  private static final ArticleRepository instance = new ArticleRepository();
  private static Map<String, Article> articles = new HashMap<>();

  private ArticleRepository() {
  }

  public static ArticleRepository getInstance() {
    return instance;
  }

  public Map<String, Article> getArticles() {
    return articles;
  }
}
