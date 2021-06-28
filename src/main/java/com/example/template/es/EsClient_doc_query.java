package com.example.template.es;

import javax.naming.directory.SearchResult;
import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class EsClient_doc_query {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //查询数据
//    SearchRequest request = new SearchRequest();
//    request.indices("user");
//    request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
//    SearchResponse search = esclient.search(request, RequestOptions.DEFAULT);
//    //获取响应状态
//    TotalHits totalHits = search.getHits().getTotalHits();
//    System.out.println(totalHits);
//    System.out.println(search.getTook());
//    for (SearchHit searchHit :search.getHits()){
//      System.out.println(searchHit.getSourceAsString());
//    }
    //条件查询 :termQuery
    SearchRequest request = new SearchRequest();
    request.indices("user");
//    String s = new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 30)).toString();
    request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("name_","张")));//带条件查询
    SearchResponse search = esclient.search(request, RequestOptions.DEFAULT);
    //获取响应状态
    TotalHits totalHits = search.getHits().getTotalHits();
    System.out.println(totalHits);
    System.out.println(search.getTook());
    for (SearchHit searchHit :search.getHits()){
      System.out.println(searchHit.getSourceAsString());
    }
    //关闭es客户端
    esclient.close();
  }
}
