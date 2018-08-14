package au.com.vb.fairfax.controller;

import au.com.vb.fairfax.model.Article;
import au.com.vb.fairfax.model.TagMetric;
import au.com.vb.fairfax.service.ArticleService;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class Controller {

  private static final Logger log = LoggerFactory.getLogger(Controller.class);

  @Autowired
  private ArticleService articleService;

  @PostMapping("/articles")
  public ResponseEntity<Void> createArticle(@RequestBody Article newArticle) {
    log.info("Request to create Article for id: {}", newArticle.getId());
    Article article = articleService.createArticle(newArticle);

    if (article == null)
      return ResponseEntity.noContent().build();

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(article.getId()).toUri();

    return ResponseEntity.created(location).build();

  }

  @GetMapping("/articles/{id}")
  public Article retrieveArticleForId(@PathVariable String id) {
    log.info("Request to retrieve Article for id: {}", id);

    Article article = articleService.retrieveArticle(id);
    if (article == null) {
      log.info("No article exists for id: {}", id);
      return null;
    }

    log.info("Article found for id: {}", id);
    return article;
  }

  @GetMapping("/articles/{tagName}/{date}")
  public TagMetric retrieveTagsForNameAndDate(@PathVariable String tagName,
                                              @PathVariable String date) {
    log.info("Request to retrieve Article for name: {} and date: {}", tagName, date);
    return articleService.retrieveTagsForNameAndDate(tagName, date);
  }
}
