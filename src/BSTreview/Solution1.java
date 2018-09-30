package BSTreview;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by john on 2018/5/21.
 */

public class Solution1 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.right = new TreeNode(6);
        List<Integer> res = preorderTraversal(root);
        for (int x :
                res) {
            System.out.print(x + " ");
        }
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        /**
         * 在LeetCode上提交的时候需要考虑道root为空的情况，否则会导致空指针异常。
         */
        List<Integer> res = new ArrayList<>();
        if(root != null){
            Stack<TreeNode> snode = new Stack<>();
            snode.push(root);
            while (!snode.isEmpty()) {
                TreeNode q = snode.pop();
                res.add(q.value);
                if (q.right != null) {
                    snode.push(q.right);
                }
                if (q.left != null) {
                    snode.push(q.left);
                }
            }
        }
        return res;
    }
}