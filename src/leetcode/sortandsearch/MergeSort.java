package leetcode.sortandsearch;

import org.junit.Test;

/**
 * @author leolu
 * @create 2020-02-05-17:27
 **/
public class MergeSort {
    //两路归并算法，两个排好序的子序列合并为一个子序列
    public void merge(int []a,int left,int mid,int right){
        int []tmp=new int[a.length];//辅助数组
        int p1=left,p2=mid+1,k=left;//p1、p2是检测指针，k是存放指针

        while(p1<=mid && p2<=right){
            if(a[p1]<=a[p2])
                tmp[k++]=a[p1++];
            else {
                tmp[k++]=a[p2++];
            }
        }

        while(p1<=mid) tmp[k++]=a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while(p2<=right) tmp[k++]=a[p2++];//同上

//        System.arraycopy(tmp,0,a,0,tmp.length);

        //复制回原素组
        for (int i = left; i <=right; i++)
            a[i]=tmp[i];

    }

    public void mergeSort(int[] a,int start,int end){
        if (start == end)
            return;//当子序列中只有一个元素时结束递归
        int mid=(start+end)/2;//划分子序列
        mergeSort(a, start, mid);//对左侧子序列进行递归排序
        mergeSort(a, mid+1, end);//对右侧子序列进行递归排序
        merge(a, start, mid, end);//合并

    }

    @Test
    public void test(){
        int[] a = {7,5,6,4};
        mergeSort(a, 0, a.length-1);
        System.out.println("排好序的数组：");
        for (int e : a)
            System.out.print(e+" ");
    }

}

class Solution {
    private int res = 0;
    public int InversePairs(int [] array) {
        mergeSort(array, 0, array.length - 1);
        return res;
    }
    public void merge(int []a,int left,int mid,int right){
        int []tmp=new int[a.length];//辅助数组
        int p1=left,p2=mid+1,k=left;//p1、p2是检测指针，k是存放指针

        while(p1<=mid && p2<=right){
            if(a[p1]<=a[p2])
                tmp[k++]=a[p1++];
            else {
                res += mid - p1 + 1;
                tmp[k++]=a[p2++];
            }


        }

        while(p1<=mid) tmp[k++]=a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while(p2<=right) tmp[k++]=a[p2++];//同上

//        System.arraycopy(tmp,0,a,0,tmp.length);

        //复制回原素组
        for (int i = left; i <=right; i++)
            a[i]=tmp[i];

    }

    public void mergeSort(int[] a,int start,int end){
        if (start == end)
            return;//当子序列中只有一个元素时结束递归
        int mid=start + ((end - start) >> 1);//划分子序列
        mergeSort(a, start, mid);//对左侧子序列进行递归排序
        mergeSort(a, mid+1, end);//对右侧子序列进行递归排序
        merge(a, start, mid, end);//合并

    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        System.out.println(new Solution().InversePairs(a));
    }
}