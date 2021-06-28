package com.example.template.sort;

public class Sort {

  public static void main(String[] args) {
    int[] ints = HeapSortWithLimitHeap.initData();
    Long startTime = System.currentTimeMillis();
    int[] result = new int[10];
    for(int i=0;i<ints.length;i++){
      //前10个直接赋值
      if(i<10){
        result[i]=ints[i];
      }else{
        if(i==10){
          result = sort(result);
        }
        //如果最大的比当前的大
        if(result[9]>ints[i]){
          result[9] = ints[i];
          result = sort(result);
        }
      }
    }

    for (int i=0;i<10;i++){
      System.out.println(result[i]);
    }
    Long endTime = System.currentTimeMillis();
    System.out.println("------------------------");
    System.out.println(endTime-startTime);
  }

  public static  int[] sort(int[] arr){
    for(int i=0;i<arr.length-1;i++){
        for(int j=0;j<arr.length-1-i;j++){
            if(arr[j]>arr[j+1]){
              int temp=arr[j];
              arr[j]=arr[j+1];
              arr[j+1]=temp;
            }
        }
    }
    return arr;
  }

}
