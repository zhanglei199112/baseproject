package com.example.template.es;

import com.example.template.es.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class EsClient_doc_batch_insert {

  public static void main(String[] args) throws Exception {
    batchInsert();
//    batchDelete();
  }

  private static void batchInsert() throws IOException {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //插入数据
    BulkRequest request = new BulkRequest();
    request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON,"name_","张三","age",30,"sex","男的")) ;
    request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON,"name_","李四","age",30,"sex","男的")) ;
    request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON,"name_","王五","age",30,"sex","男的")) ;
    request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON,"name_","张三","age",30,"sex","女的")) ;
    request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON,"name_","李四","age",30,"sex","女的")) ;
    request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON,"name_","王五","age",30,"sex","女的")) ;
    BulkResponse bulk = esclient.bulk(request, RequestOptions.DEFAULT);
    //获取响应状态
    System.out.println(bulk.getTook());
    System.out.println(bulk.getItems());
    //关闭es客户端
    esclient.close();
  }

  private static void batchDelete() throws IOException {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //插入数据
    BulkRequest request = new BulkRequest();
    request.add(new DeleteRequest().index("user").id("1001")) ;
    request.add(new DeleteRequest().index("user").id("1002")) ;
    request.add(new DeleteRequest().index("user").id("1003")) ;
    BulkResponse bulk = esclient.bulk(request, RequestOptions.DEFAULT);
    //获取响应状态
    System.out.println(bulk.getTook());
    System.out.println(bulk.getItems());
    //关闭es客户端
    esclient.close();
  }
}
