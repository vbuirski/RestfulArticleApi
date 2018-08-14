package au.com.vb.fairfax.service;

import au.com.vb.fairfax.model.Article;
import au.com.vb.fairfax.model.TagMetric;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ArticleServiceTest {

  private ArticleService articleService = new ArticleService();

  @Test
  public void shouldCreateArticle() {
    Article article = new Article("1",
            "latest science shows that potato chips are better for you than sugar",
            "2016-09-22",
            "some text, potentially containing simple markup about how potato chips are great",
            new ArrayList<String>(Arrays.asList("health", "fitness", "science")));

    Article savedArticle = articleService.createArticle(article);
    Assert.assertEquals(article, savedArticle);
  }

  @Test
  public void shouldRetrieveArticle() {
    Article article = new Article("1",
            "latest science shows that potato chips are better for you than sugar",
            "2016-09-22",
            "some text, potentially containing simple markup about how potato chips are great",
            new ArrayList<String>(Arrays.asList("health", "fitness", "science")));

    Article savedArticle = articleService.createArticle(article);

    Article retrievedArticle = articleService.retrieveArticle(article.getId());
    Assert.assertEquals(savedArticle, retrievedArticle);
  }

  @Test
  public void shouldGetTags() throws IOException {
   // retrieveTagsForNameAndDate(name, date);
    ObjectMapper objectMapper = new ObjectMapper();
    TagMetric tagMetric = articleService.retrieveTagsForNameAndDate("name", "date");
    objectMapper.writeValue(new File("target/metrics.json"), tagMetric);

    System.out.println("Tag = " + tagMetric.toString());

  }
}
