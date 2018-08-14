package au.com.vb.fairfax.model;

import java.util.List;
import lombok.Getter;

@Getter
public class Article {

  private String id;
  private String title;
  private String date;
  private String body;
  private List<String> tags;

 public Article() {
 }

 public Article(String id, String title, String date, String body, List<String> tags) {
   this.id = id;
   this.title = title;
   this.date = date;
   this.body = body;
   this.tags = tags;
 }
}
