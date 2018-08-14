package au.com.vb.fairfax.controller.mapper;

import au.com.vb.fairfax.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class ArticleToJsonMapperTest {

  @Test
  public void shouldTransformArticleToJson() throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    Article expectedArticle = objectMapper.readValue(new File("target/Article.json"), Article.class);
    String expectedArticleJson = objectMapper.writeValueAsString(expectedArticle);

    String articleJson = new StringBuilder("{")
            .append("\"id\":\"1\",")
            .append("\"title\":\"latest science shows that potato chips are better for you than sugar\"," )
            .append("\"date\":\"2016-09-22\",")
            .append("\"body\":\"some text, potentially containing simple markup about how potato chips are great\",")
            .append("\"tags\":[\"health\",\"fitness\",\"science\"]")
            .append("}").toString();

    Assert.assertEquals(expectedArticleJson, articleJson);
  }
}
