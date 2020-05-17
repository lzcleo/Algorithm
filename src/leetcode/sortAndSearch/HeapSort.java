package leetcode.sortAndSearch;

/**
 * @author leolu
 * @create 2020-02-12-23:18
 **/
public class HeapSort {
    public static void heapSort(int[] tree,int n) {
        buildHeap(tree, n);//第一步是将得到的数组构建成大顶堆
        for(int i = n-1;i>=0;i--) {
            swap(tree, i, 0);//第一次构建完大顶堆之后，要进行第一个数和最后一个数的交换
            //交换完之后，最上面的数就不是最大数了，因此只需要对最上面的数，进行一个树的调整即可
            //所以，我们使用的时adjustTree而不是buildHeap
            adjustTree(tree, i, 0);//这里解释一下，这参数的含义：之所以将i当做数组的长度，
            //是因为我们将第一个数和最后一个数交换之后，就已经把最大的数放在了数组最后，进行
            //树调整的时候，就不需要管最后一个数字了。而0就是因为交换之后需要进行节点调节的那个节点
            //换到了第一个位置
        }
    }



    /*
     * 这个函数写完之后，就可以将任意一个数组，构建成大顶堆了，构建完大顶堆之后，就要进行堆排序了
     */
    public static void buildHeap(int[]tree,int n) {
        for(int i = (n-1)/2;i>=0;i--) {//i从最后一个子节点的父节点开始，所以i = (n-1)/2
            adjustTree(tree, n, i);
        }
    }
    //利用adjustTree和swap两个函数，可以针对某一个父节点，进行调节。接下来，解决当整个树是
    //乱序的，将一个树构建成一个大顶堆。思路是这样的：从最后一个子节点的父节点开始调节，往上走。
    //不断重复，每往上一个父节点，父节点的下标就减一，可以将adjustTree和swap函数放进一个for循环
    //就是上面的for循环
    /*
     * 表示从某一个节点开始，调整一次树，使之成为堆，其中i表示某一个节点的下标
     */
    public static void adjustTree(int[]tree,int n,int i) {
        if(i>=n) {//这是递归头。
            return;
        }
        //首先确定i节点的左右两个孩子的下标
        int c1 = 2*i+1;
        int c2 = 2*i+2;
        //接下来，在这三个值中，找出最大值
        int max = i;//先假设最大值为这个父节点
        if(c1<n && tree[c1]>tree[max]) {//要保证c1不会出界
            max = c1;
        }
        if(c2<n && tree[c2]>tree[max]) {//保证c2不会出界  c2<n
            max = c2;
        }
        //经过上面的条件判断，就可以将最大值的下标保存到max中了，如果最大值max就是i，也就是
        //父节点最大，就不用调整，但是如果父节点不是最大，就要进行交换了
        if(max!=i) {
            swap(tree,max,i);
            adjustTree(tree,n,max);//交换之后，将父节点下放一级，就有可能会破坏下一层结构，
            //所以，递归调用adjustTree.使用递归之后，就要添加递归头了
        }


    }
    private static void swap(int[] tree, int i, int j) {
        int temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;
    }

}
class Heap<T extends Comparable<T>> {

    private T[] heap;
    private int N = 0;

    public Heap(int maxN) {
        this.heap = (T[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private void swap(int i, int j) {
        T t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }
}
