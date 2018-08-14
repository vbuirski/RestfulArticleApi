package au.com.vb.fairfax.service;

import au.com.vb.fairfax.model.Article;
import au.com.vb.fairfax.model.TagMetric;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private static Map<String, Article> articles = new HashMap<>();

  public Article createArticle(Article article) {

    articles.put(article.getId(), article);
    return article;
  }

  public Article retrieveArticle(String id) {

    Article article = articles.entrySet().stream().filter(e -> e.getKey().equals(id))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(null);
    return article;
  }

  public TagMetric retrieveTagsForNameAndDate(String name, String date) {

    TagMetric tagDetails = new TagMetric("health",
                                          17,
                                          new ArrayList<String>(Arrays.asList("1", "7")),
                                          new ArrayList<>(Arrays.asList("science", "fitness")));


    return tagDetails;
    //Map -> Stream -> Filter -> String
    //String result = articles.entrySet().stream()

            /*
            .entrySet().stream()
            .filter(map -> "aws.amazon.com".equals(map.getValue()))
            .map(map -> map.getValue())
            .collect(Collectors.joining());
*/
  }


}
