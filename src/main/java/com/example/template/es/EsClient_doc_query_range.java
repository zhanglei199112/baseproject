package com.example.template.es;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

public class EsClient_doc_query_range {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //条件查询 :termQuery
    SearchRequest request = new SearchRequest();
    request.indices("user");
    SearchSourceBuilder builder = new SearchSourceBuilder();
    //仅仅范围查询
//    RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
//    rangeQuery.gte(30);
//    rangeQuery.lte(50);
//    builder.query(rangeQuery);
    //范围带条件查询
//    BoolQueryBuilder must = QueryBuilders.boolQuery()
//        .must(QueryBuilders.matchQuery("name_", "张"))
//        .must(QueryBuilders.rangeQuery("age").gte(30).lte(80));

//    //模糊查询
//    //模糊查询  相隔一个字节
//    FuzzyQueryBuilder fuzziness = QueryBuilders.fuzzyQuery("name_", "张三").fuzziness(Fuzziness.ONE);
//    builder.query(fuzziness);
    //高亮查询
//    HighlightBuilder highlightBuilder = new HighlightBuilder();
//    highlightBuilder.field("name_");
//    highlightBuilder.preTags("<font color='red'>").postTags("</font>");
//    builder.highlighter(highlightBuilder);
//    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name_", "张");

    //聚合查询  分组统计
    TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
    builder.aggregation(aggregationBuilder);
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