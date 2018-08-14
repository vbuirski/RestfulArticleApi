package au.com.vb.fairfax.service;

import au.com.vb.fairfax.model.Article;
import au.com.vb.fairfax.model.TagMetric;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private static Map<String, Article> articles = new HashMap<>();

  public Article createArticle(Article article) {

    articles.put(article.getId(), article);
    return article;
  }

  public Article retrieveArticle(String id) {

    Article article = articles.entrySet()
                      .stream()
                      .filter(e -> e.getKey().equals(id))
                      .map(Map.Entry::getValue)
                      .findFirst()
                      .orElse(null);
    return article;
  }

  public TagMetric retrieveTagForNameAndDate(String name, String date) {

    return buildMetrics(name, formatDate(date));
  }

  private synchronized TagMetric buildMetrics(String tag, String date) {
    Integer count = 0;
    List<String> articleIds = new ArrayList<String>();
    List<String> allRelatedTags = new ArrayList<>();

    Iterator it = articles.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      Article article = (Article) pair.getValue();
      List<String> tags = new ArrayList<>();
      if (article.getTags().contains(tag) && date.equals(article.getDate())) {
        count++;
        articleIds.add(article.getId());
        tags = article.getTags().stream()
                .filter(p -> !p.equalsIgnoreCase(tag)).collect(Collectors.toList());
      }
      Set<String> relatedTagSet = new LinkedHashSet<>(allRelatedTags);
      relatedTagSet.addAll(tags);
      allRelatedTags = new ArrayList<>(relatedTagSet);
    }
    return new TagMetric(tag, count, articleIds, allRelatedTags);
  }

  private String formatDate(String date) {
    return new StringBuilder(date.substring(0,4))
            .append("-")
            .append(date.substring(4,6))
            .append("-")
            .append(date.substring(6,8))
            .toString();
  }
}
