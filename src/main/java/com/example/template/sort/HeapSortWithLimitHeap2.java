package com.example.template.sort;

public class HeapSortWithLimitHeap2 {

  public static void main(String[] args) {
    v2();
  }

  private static void v2() {
    Long startTime = System.currentTimeMillis();
    int[] origin = initData();
    int[] heap = new int[10];
    for (int i=0;i<origin.length;i++){
      //前10个数直接放入堆数组中，并直接构建堆
      if(i<10){
        heap[i] = origin[i];
        if(i==9){
          //构建大堆顶
          heapBuild(heap);
          print(heap);
        }
      }else{
        //获取堆顶，如果比堆顶小，则替换，并重新排序
        if(origin[i]<heap[0]){
          heap[0]=origin[i];
          heapfix(heap,0,heap.length);
        }
      }
    }
    //构建完成之后，替换最大的到末尾，然后重新进行排序
    System.out.println("-----------------------");
//    heapSort(heap,heap.length-1);
    print(heap);
    Long endTime = System.currentTimeMillis();
    System.out.println(endTime-startTime);
  }


  private static void heapSort(int[] origin,int lastIndex) {
    for(int i=lastIndex;i>=0;i--){
      int temp = origin[i];
      origin[i] = origin[0];
      origin[0] = temp;
      heapfix(origin,0,i);
    }
  }

  private static void  heapBuild(int[] heap){
    int i = heap.length;
    //这里有个概念问题，第一，奇数节点一定是左边，偶数节点一定是右边
    //                0
    //             /     \
    //            1       2
    //          /   \   /   \
    //         3    4  5     6
    //上层节点一定是（n-1）/2,子节点一定是2n+1 与2n+2
    int lastParent = (i-1)/2;
    for (int j=lastParent;j>=0;j--){
      heapfix(heap,j,heap.length);
    }
  }

  /**
   * 修复是从上往下,这里只写大碓顶
   * @param heap
   * @param index
   */
  static void heapfix(int[] heap ,int index,int lastIndex){
    //获取两个子节点
    int c1 = index*2+1;
    int c2 = index*2+2;
    //假设是当前节点是最大值
    int max = index;
    if(c1<lastIndex && heap[c1]>heap[max]){
      max =c1;
    }
    if(c2<lastIndex && heap[c2]>heap[max]){
      max =c2;
    }
    if(max!=index){
      swap(heap,max,index);
      heapfix(heap,max,lastIndex);
    }
  }

  static void swap(int[] heap,int i,int j){
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

  //如果是奇数节点，父节点就是（n-1）/2，如果是偶数
  private int getParent(int index) {
    if(index%2==0){
      return (index-2)/2;
    }else{
      return (index-1)/2;
    }
  }

  static void print(int[] origin){
    for (int i=0;i<origin.length;i++){
      System.out.println(origin[i]);
    }
  }

  static int[] initData(){
    int[] originInt = new int[10000000];
    //奇数数组
    int[] jishu = new int[5000000];
    //偶数数组
    int[] oushu = new int[5000000];
    int jishuindex = 0;
    int oushuindex = 0;
    for(int i=0;i<10000000;i++){
      if(i%2==0){
        oushu[oushuindex++] = i;
      }
      if(i%2==1){
        jishu[jishuindex++] = i;
      }
    }
    //合并奇数与偶数数组
    int originindex = 0;
    for (int i=0;i<jishu.length;i++){
      originInt[originindex++] = jishu[i];
    }
    for (int i=0;i<oushu.length;i++){
      originInt[originindex++] = oushu[i];
    }
    return originInt;
  }

}
