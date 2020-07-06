package leetcode.tree;

import java.util.LinkedList;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 */
public class 二叉搜索树的后序遍历序列 {
    //时间复杂度为O(n^2),因为递归次数为O(n),若是为最坏情况，每一次都是O(n),所以为O(n^2),空间复杂度为O(n)
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) return false;
        return verify(postorder, 0, postorder.length - 1);
    }

    private boolean verify(int[] postorder, int left, int right) {
        if (right - left <= 1)
            return true;
        int rootVal = postorder[right];
        int cutIndex = left;
        while (cutIndex < right && postorder[cutIndex] <= rootVal)
            cutIndex++;
        for (int i = cutIndex; i < right; i++)
            if (postorder[i] < rootVal)
                return false;
        return verify(postorder, left, cutIndex - 1) && verify(postorder, cutIndex, right - 1);
    }

    //下面是自己写的版本，存在三个问题
    private boolean verifyPostorder(int[] postorder, int left, int right) {
        //首先，这个边界条件是有问题的，走到最后一步，cutIndex与right的距离若是超过1应该返回false
        if (left >= right)
            return true;
        int root = postorder[right];
        int cutIndex = left;
        //第二个错误，这里少了一个判断条件
        while (postorder[cutIndex] <= root ) {
            cutIndex ++;
        }
        //最大的粗心出现在这里，这里写for循环的时候竟然出现了问题
        for (int i = cutIndex; cutIndex < right; cutIndex ++) {
            if (postorder[i] < root) return false;
        }
        return verifyPostorder(postorder, left, cutIndex - 1) && verifyPostorder(postorder, cutIndex, right - 1);
    }

    /**
     * 以[5,2,6,1,3]为例，后序遍历的倒序为[5,6,2,3,1]
     * 由于后序遍历的逆序为"根，右，左"，所以我们依次从左到右把节点推入栈，由于是"根，右"，所以第一部分应该为顺序
     * 递增，也就是[5,6],出现的第一个降序节点即到了"左"的部分，这个节点对应的根节点应为此时栈中最接近他的节点，
     * 也就是5，若是之后出现比5大的数，则return false，否则，继续循环，若是所以节点都满足以上关系，则return true
     */
    //    单调栈思路
    public boolean verifyPostorderSecond(int[] postorder) {
        if (postorder == null || postorder.length == 0) return true;
        LinkedList<Integer> stack = new LinkedList<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i --) {
            if (postorder[i] > root) return false;
            while (!stack.isEmpty() && stack.peekLast() > postorder[i]) {
                root = stack.pollLast();
            }
            stack.addLast(postorder[i]);
        }
        return true;
    }
}


































