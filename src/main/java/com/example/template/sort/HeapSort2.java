package com.example.template.sort;

public class HeapSort2 {

  public static void main(String[] args) {
    int[] arr = HeapSortWithLimitHeap.initData();
    Long startTime = System.currentTimeMillis();
    heapSort(arr);
    for(int i =0;i<10;i++){
      System.out.println(arr[i]);
    }
    Long endTime = System.currentTimeMillis();
    System.out.println(endTime-startTime);
  }

  /**
   * 构建堆，初略的进行构建,最后一个父节点为(heap.length-2)/2
   * @param heap
   */
  public static void heapBuild(int[] heap) {
    for(int i = (heap.length-2)/2;i>=0;i--){
      heapFix(heap,i,heap.length-1);
    }
  }

  public static void heapSort(int[] heap){
    heapBuild(heap);
    //因为每次都将最大的放到了第一个位置，这里将最大的放到最后，重新进行单方面修复
    for(int last = heap.length-1;last>=0;last--){
      swap(heap,0,last);
      heapFix(heap,0,last-1);
    }
  }

  /**
   * 这里为了将来取数据方便，这里进行一个设置一个最后的标志
   * @param heap
   * @param index
   * @param last
   */
  public static void heapFix(int[] heap, int index, int last){
    if(index>=last){
      return;
    }
    //找到两个子节点
    int childleft = index*2+1;
    int childRight = index*2+2;
    //默认当前节点数据为最大
    int max = heap[index];
    int maxIndex = index;
    if(childleft<=last && heap[childleft]>max){
      max = heap[childleft];
      maxIndex = childleft;
    }
    if(childRight<=last && heap[childRight]>max){
      max = heap[childRight];
      maxIndex = childRight;
    }
    if(maxIndex!=index){
      swap(heap,index,maxIndex);
      //进行子节点的修复
      heapFix(heap,maxIndex,last);
    }
  }

  public static void swap(int[] heap, int i, int j){
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

}
