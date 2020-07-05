package leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/ 可以看看大佬题解
 * @author leolu
 * @create 2020-03-16-13:32
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 这道题收获颇丰，是一道较为经典的回溯问题，下面的解法是相当规范的一种解法，利用index记录排列的长度并保存在list中，依次回溯
 * 但是中间有一个地方卡了很久，debug了一段时间也没有解决，最后倒是依靠直接在递归中打印list解决，有以下几点需要注意：
 * 1 这种规范的回溯题，无论一维二维在回溯的主方法中都需要记录此事所处的位置，一维使用index，二维使用坐标
 * 2 在递归头中，若是直接res.add（list），尽管递归过程没有任何问题，但是最后返回的res里面是六个空的list，此处对其中的原理尚不清楚，
 *   但是应该是因为java的参数是值传递，若是最后返回时不新建一个对象指向主方法new ArrayList<>()，则会一直对最初在主方法permute中创建
 *   的对象new ArrayList<>()进行操作，同时最后返回的时候，由于list会依次弹出数字，最终此对象为空，res会存六个空list
 **/
public class 全排列 {
    private boolean[] used;
    private List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        if (nums == null)
            return res;
        used = new boolean[nums.length];
        Arrays.fill(used, false);
        generatePermutation(nums, 0, new ArrayList<>());
        return res;
    }
    private void generatePermutation(int[] nums, int index, List<Integer> list) {
        if(index == nums.length) {

            res.add(new ArrayList<>(list));
//            res.add(list);
            return;
        }
        for (int i = 0; i < nums.length; i ++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            /**
             * 上面三行代码是用来剪枝的，应该在主函数中先对数组进行排序，这样才能确保剪枝成功
             */
            if (!used[i]) {
                list.add(nums[i]);
                used[i] = true;
//                System.out.println(list);
                generatePermutation(nums, index + 1, list);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
        return;
    }

    @Test
    public void test(){
        int[] ints = {1,2,3};
        System.out.println(permute(ints));
    }


}
