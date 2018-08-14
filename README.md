# RestfulArticleApi
A RESTFUL api for article endpoints.

Create an article using POST
POST /articles 
{
  "id": "1",
  "title": "latest science shows that potato chips are better for you than sugar",
  "date" : "2016-09-22",
  "body" : "some text, potentially containing simple markup about how potato chips are great",
  "tags" : ["health", "fitness", "science"]
}

Retrieve the article by id
GET /articles/{id}

Get the related tags for a specific tag and date.
GET /tags/{tagName}/{date}

{
  "tag" : "health",
  "count" : 17,
    "articles" :
      [
        "1",
        "7"
      ],
    "related_tags" :
      [
        "science",
        "fitness"
      ]
}

