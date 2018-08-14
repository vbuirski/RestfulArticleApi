package au.com.vb.fairfax.service;

import au.com.vb.fairfax.model.Article;
import au.com.vb.fairfax.model.TagMetric;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Test;

public class ArticleServiceTest {

  private ArticleService articleService = new ArticleService();

  @Test
  public void shouldCreateArticle() {
    Article article = articleBuilder();
    Article savedArticle = articleService.createArticle(article);
    Assert.assertEquals(article, savedArticle);
  }

  @Test
  public void shouldRetrieveArticle() {
    Article article = articleBuilder();

    Article savedArticle = articleService.createArticle(article);

    Article retrievedArticle = articleService.retrieveArticle(article.getId());
    Assert.assertEquals(savedArticle, retrievedArticle);
  }

  @Test
  public void shouldGetTags() throws IOException {

    Article article1 = articleBuilder();
    Article article2 = articleBuilder();

    articleService.createArticle(article1);
    articleService.createArticle(article2);

    String tagName = article1.getTags().get(0);

    ObjectMapper objectMapper = new ObjectMapper();
    TagMetric tagMetric = articleService.retrieveTagForNameAndDate(tagName, article1.getDate());
    objectMapper.writeValue(new File("target/metrics.json"), tagMetric);

  }

  private Article articleBuilder() {

    return new Article(RandomString.make(3),
            RandomString.make(10),
            "2016-09-22",
            RandomString.make(20),
            new ArrayList<String>(Arrays.asList("health", "fitness", "science")));
  }
}
