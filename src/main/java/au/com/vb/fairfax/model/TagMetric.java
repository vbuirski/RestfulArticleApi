package au.com.vb.fairfax.model;

import java.util.List;
import lombok.Getter;

@Getter
public class TagMetric {

  String tag;
  Integer count;
  List<String> articles;
  List<String> related_tags;

  public TagMetric(String tag, Integer count, List<String> articles, List<String> related_tags) {
    this.tag = tag;
    this.count = count;
    this.articles = articles;
    this.related_tags = related_tags;
  }

}
