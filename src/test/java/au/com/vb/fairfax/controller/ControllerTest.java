package au.com.vb.fairfax.controller;

import au.com.vb.fairfax.model.Article;
import au.com.vb.fairfax.service.ArticleService;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = Controller.class, secure = false)
public class ControllerTest {


  Article mockArticle = new Article("1",
          "latest science shows that potato chips are better for you than sugar",
          "2016-09-22",
          "some text, potentially containing simple markup about how potato chips are great",
          new ArrayList<String>(Arrays.asList("health", "fitness", "science")));

  String exampleArticleJson = new StringBuilder("{")
          .append("\"id\":\"1\",")
          .append("\"title\":\"latest science shows that potato chips are better for you than sugar\",")
          .append("\"date\":\"2016-09-22\",")
          .append("\"body\":\"some text, potentially containing simple markup about how potato chips are great\",")
          .append("\"tags\":[\"health\",\"fitness\",\"science\"]")
          .append("}").toString();

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ArticleService articleService;

  @Test
  public void retrieveArticleForId() throws Exception {

    Mockito.when(
            articleService.retrieveArticle(Mockito.anyString())).thenReturn(mockArticle);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
            "/articles/1").accept(
            MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    JSONAssert.assertEquals(exampleArticleJson, result.getResponse()
            .getContentAsString(), false);
  }

  @Test
  public void createArticle() throws Exception {

    // articleService.addArticle to respond back with mockArticle
    Mockito.when(
            articleService.createArticle(
                    Mockito.any(Article.class))).thenReturn(mockArticle);

    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/articles")
            .accept(MediaType.APPLICATION_JSON).content(exampleArticleJson)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    assertEquals("http://localhost/articles",
            response.getHeader(HttpHeaders.LOCATION));

  }

}

