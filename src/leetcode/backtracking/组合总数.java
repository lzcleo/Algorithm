package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 */
public class 组合总数 {
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0 || candidates == null) return result;
        Arrays.sort(candidates);
        helpCombinationSum(candidates, 0, new LinkedList<Integer>(), target, target);
        return result;
    }

    private void helpCombinationSum(int[] candidates, int start, LinkedList<Integer> res, int target, int remain) {
        if (remain < 0) return;
        if (remain == 0) {
            result.add(new LinkedList<Integer>(res));
            return;
        }
        for (int i = start; i < candidates.length; i ++) {
            res.addLast(candidates[i]);
            helpCombinationSum(candidates, i, res, target, remain - candidates[i]);
            res.removeLast();
        }
    }

}
