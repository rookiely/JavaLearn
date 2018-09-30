package RBTreview;

/**
 * Created by john on 2018/5/22.
 */

class TreeNode {
    boolean color;
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    TreeNode(int val,boolean color) {
        value = val;
        this.color = color;
        left = null;
        right = null;
        parent = null;
    }
}

public class Solution {
    private static Boolean RED = false;
    private static Boolean BLACK = true;

    private static TreeNode root = null;

    public static void main(String[] args) {

    }

    /**
     * 首先实现左旋和右旋操作
     */

    private static void leftRotation(TreeNode node) {
        /**
         * 左旋的思想主要就是针对三个节点对的链接，包括next和parent的链接
         */
        TreeNode noderight = node.right;
        /**
         * 这里是第一个节点对的链接
        */
        node.right = noderight.left;
        noderight.left.parent = node;
        /**
         *这里是第二个节点对的链接
         */
        noderight.parent = node.parent;
        if (node.parent == null) {
            root = noderight;
        }else{
            if (node.parent.left == node) {
                node.parent.left = noderight;
            }else{
                node.parent.right = noderight;
            }
        }
        /**
         * 这里是第三个节点对的链接
         */
        noderight.left = node;
        node.parent = noderight;
    }

    private static void rightRotation(TreeNode node) {
        /**
         * 右旋就和左旋相反即可
         */
        TreeNode nodeleft = node.left;
        node.left = nodeleft.right;
        nodeleft.right.parent = node;
        nodeleft.parent = node.parent;
        if (node.parent == null) {
            root = nodeleft;
        } else {
            if (node.parent.left == node) {
                node.parent.left = nodeleft;
            } else {
                node.parent.right = nodeleft;
            }
        }
        nodeleft.right = node;
        node.parent = nodeleft;
    }

    private static boolean isRed(TreeNode node) {
        return node.color == RED;
    }

    /**
     * 将节点插入树中
     * @param node
     */
    private static void insert(TreeNode node) {
        TreeNode pnode = null;
        TreeNode curnode = root;
        while (curnode != null) {
            pnode = curnode;
            if (curnode.value > node.value) {
                curnode = curnode.left;
            }else{
                curnode = curnode.right;
            }
        }
        /**
         * 这时候pnode保存的就是当前需要插入节点的父节点
         */
        node.parent = pnode;
        if (pnode != null) {
            if (node.value < pnode.value) {
                pnode.left = node;
            } else {
                pnode.right = node;
            }
        } else {
            root = node;
        }
        node.color = RED;
        insertFixup(node);
    }

    private static void insertFixup(TreeNode node) {
        /**
         * 针对插入的调整总共分为三种情况：
         * 1、根节点为空，那么直接将插入节点的颜色改成黑色即可
         * 2、插入节点的父节点为黑色，不需要任何操作
         * 3、插入节点的父节点为红色，这时候又分三种情况：（同时这里隐含的条件是：插入节点的祖父节点是黑色）
         *   1）叔叔节点是红色
         *   2）叔叔节点是黑色，同时插入节点的位置和其父节点的位置在一个方向上
         *   3）叔叔节点是黑色，同时插入节点的位置和其父节点的位置在不同的方向上
         * 这里主要针对第3中情况进行调整操作
          */
        TreeNode parent = node.parent,gparent;
        while (parent != null && isRed(node.parent)) {
            /**
             * 这里又分为两种情况：父节点是祖父节点的左孩子或者右孩子
             */
            gparent = parent.parent;
            if (parent == gparent.left) {
                /**
                 * 这里就分上述三种情况
                 */
                TreeNode uncle = gparent.right;
                /**
                 * 叔叔是红色
                 */
                if (uncle != null && isRed(uncle)) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }
                /**
                 * 叔叔是黑色，同时位置相反，通过反转转化为另一种情况，统一处理
                 */
                if (parent.right == node) {
                    leftRotation(parent);
                    TreeNode tnode = node;
                    node = parent;
                    parent = tnode;
                }
                /**
                 * 统一处理
                 */
                parent.color = BLACK;
                gparent.color = RED;
                rightRotation(gparent);
            } else {
                /**
                 * 这里也分上述三种情况
                 */
                TreeNode uncle = gparent.left;
                /**
                 * 叔叔是红色
                 */
                if (uncle != null && isRed(uncle)) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }
                /**
                 * 叔叔是黑色，同时位置相反，通过反转转化为另一种情况，统一处理
                 */
                if (parent.left == node) {
                    rightRotation(parent);
                    TreeNode tnode = node;
                    node = parent;
                    parent = tnode;
                }
                /**
                 * 统一处理
                 */
                parent.color = BLACK;
                gparent.color = RED;
                leftRotation(gparent);
            }
        }
        root.color = BLACK;
    }

    private static void delete(TreeNode node) {

    }

    private static void deleteFixUp(TreeNode node) {

    }


}
