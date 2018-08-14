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

 /*   TagMetric tagDetails = new TagMetric("health",
                                          17,
                                          new ArrayList<String>(Arrays.asList("1", "7")),
                                          new ArrayList<>(Arrays.asList("science", "fitness")));
*/
    TagMetric tagMetrics;
    synchronized (this) {
       Integer count = countTags(name);
       List<String> articles = getArticleIdsForTag(name);
       List<String> relatedTags = getRelatedTags(name);

       tagMetrics = new TagMetric(name, count, articles, relatedTags);
     }
    return tagMetrics;

  }

  private Integer countTags(String tag) {
    Integer count = 0;
    Iterator it = articles.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      Article article = (Article) pair.getValue();
      if (article.getTags().contains(tag)) {
        count++;
      }
    }
    return count++;
  }

  private List<String> getArticleIdsForTag(String tag) {
    List<String> ids = new ArrayList<>();

    Iterator it = articles.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      Article article = (Article) pair.getValue();
      if (article.getTags().contains(tag)) {
        ids.add(article.getId());
      }
    }
    return ids;
  }

  private List<String> getRelatedTags(String tag) {
    List<String> relatedTags = new ArrayList<>();
    List<String> allRelatedTags = new ArrayList<>();

    Iterator it = articles.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      Article article = (Article) pair.getValue();
      List<String> tags = new ArrayList<>();
      if (article.getTags().contains(tag)) {
        tags = article.getTags().stream()
                .filter(p -> !p.equalsIgnoreCase(tag)).collect(Collectors.toList());
      }
      Set<String> relatedTagSet = new LinkedHashSet<>(relatedTags);
      relatedTagSet.addAll(tags);
      allRelatedTags = new ArrayList<>(relatedTagSet);
    }
    return allRelatedTags;
  }
}
