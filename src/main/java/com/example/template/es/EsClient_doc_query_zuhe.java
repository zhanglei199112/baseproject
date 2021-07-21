package com.example.template.es;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class EsClient_doc_query_zuhe {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //条件查询 :termQuery
    SearchRequest request = new SearchRequest();
    request.indices("user");
    SearchSourceBuilder builder = new SearchSourceBuilder();
    //构建查询条件
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//    boolQueryBuilder.must(QueryBuilders.matchQuery("age",30));
//    boolQueryBuilder.mustNot(QueryBuilders.termQuery("sex","男"));//这里应该映射的关系，sex字段会被映射成分词之后的结果，如果用"男的"会匹配不出来，或者用"sex.keyWord"进行匹配，最好是提前将映射进行修改
//    boolQueryBuilder.mustNot(QueryBuilders.existsQuery("sex"));//一定不包含某些属性
    boolQueryBuilder.should(QueryBuilders.matchQuery("age",30));
    boolQueryBuilder.should(QueryBuilders.matchQuery("age",40));
    builder.query(boolQueryBuilder);
    request.source(builder);//带条件查询
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