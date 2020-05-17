package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author leolu
 * @create 2020-03-18-20:21
 **/
public class 二叉树的序列化和反序列化 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null)
            return "[]";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder res = new StringBuilder("[");
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(!(node == null)){
                res.append(node.val + ",");
                queue.offer(node.left);
                queue.offer(node.right);
            }else{
                res.append("null,");
            }
        }
        String result = res.toString().substring(0, res.length() - 1);
        return result += "]";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || "[]".equals(data))
            return null;
        String res = data.substring(1, data.length()-1);
        String[] values = res.split(",");
        int index = 0;
        TreeNode head = generateTreeNode(values[index++]);
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode node = head;
        queue.offer(head);
        while(!queue.isEmpty()){
            node = queue.poll();
            node.left = generateTreeNode(values[index++]);
            node.right = generateTreeNode(values[index++]);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    public TreeNode generateTreeNode(String value){
        if("null".equals(value))return null;
        return new TreeNode(Integer.valueOf(value));
    }

}
