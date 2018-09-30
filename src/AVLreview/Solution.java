package AVLreview;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by john on 2018/5/22.
 */

class TreeNode {
    int value;
    int height;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        value = val;
        left = null;
        right = null;
    }
}

public class Solution {


    private static TreeNode root = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("*******************");
            System.out.println("请输入序号：");
            System.out.println("1、插入元素");
            System.out.println("2、删除元素");
            System.out.println("3、查找元素");
            System.out.println("4、先序遍历");
            System.out.println("5、中序遍历");
            System.out.println("6、后序遍历");
            System.out.println("*******************");
            Scanner sc = new Scanner(System.in);
            int num1 = sc.nextInt();
            switch (num1) {
                case 1:
                    System.out.println("请输入需要插入的节点的值：");
                    int val1 = sc.nextInt();
                    TreeNode node1 = new TreeNode(val1);
                    root = insert(node1, root);
                    recurPerOrder(root);
                    break;
                case 2:
                    System.out.println("请输入需要删除的节点的值：");
                    int val2 = sc.nextInt();
                    TreeNode node2 = new TreeNode(val2);
                    delete(node2, root);
                    System.out.print("删除成功！");
                    break;
                case 3:
                    System.out.println("请输入需要查找的节点的值：");
                    int val3 = sc.nextInt();
                    TreeNode node3 = new TreeNode(val3);
                    boolean flag2 = find(node3, root);
                    if (flag2) System.out.println("找到该节点！");
                    else System.out.println("找不到该节点！");
                    break;
                case 4:
                    System.out.println("1、递归遍历");
                    System.out.println("2、非递归遍历");
                    int num2 = sc.nextInt();
                    switch (num2) {
                        case 1:
                            recurPerOrder(root);
                            break;
                        case 2:
                            unrecurPreOrder(root);
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    System.out.println("1、递归遍历");
                    System.out.println("2、非递归遍历");
                    int num3 = sc.nextInt();
                    switch (num3) {
                        case 1:
                            recurInOrder(root);
                            break;
                        case 2:
                            unrecurInOrder(root);
                            break;
                        default:
                            break;
                    }
                    break;
                case 6:
                    System.out.println("1、递归遍历");
                    System.out.println("2、非递归遍历");
                    int num4 = sc.nextInt();
                    switch (num4) {
                        case 1:
                            recurPostOrder(root);
                            break;
                        case 2:
                            unrecurPostOrder(root);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private static int height(TreeNode root) {
        if (root != null) {
            return root.height;
        } else {
            return 0;
        }
    }

    private static int max(int x, int y) {
        return x > y ? x : y;
    }

    private static TreeNode leftleftRotation(TreeNode node) {
        TreeNode pnode = node.left;
        node.left = pnode.right;
        pnode.right = node;
        node.height = max(height(node.left), height(node.right)) + 1;
        pnode.height = max(node.height, height(pnode.left)) + 1;
        return pnode;
    }

    private static TreeNode rightrightRotation(TreeNode node) {
        TreeNode pnode = node.right;
        node.right = pnode.left;
        pnode.left = node;
        node.height = max(height(node.left), height(node.right)) + 1;
        pnode.height = max(node.height, height(pnode.right)) + 1;
        return pnode;
    }

    private static TreeNode leftrightRotation(TreeNode node) {
        node.left = rightrightRotation(node.left);
        return leftleftRotation(node);
    }

    private static TreeNode rightleftRotation(TreeNode node) {
        node.right = leftleftRotation(node.left);
        return rightrightRotation(node);
    }

    private static TreeNode insert(TreeNode node1, TreeNode root) {
        if (root == null) {
            root = node1;
            if (root == null) {
                System.out.println("创建节点错误！");
                return null;
            }
        } else {
            int cmp = node1.value - root.value;
            if (cmp < 0) {
                root.left = insert(node1, root.left);
                if (height(root.left) - height(root.right) == 2) {
                    if (node1.value - root.left.value < 0) {
                        root = leftleftRotation(root);
                    } else {
                        root = leftrightRotation(root);
                    }
                }
            } else if (cmp > 0) {
                root.right = insert(node1, root.right);
                if (height(root.right) - height(root.left) == 2) {
                    if (node1.value - root.right.value > 0) {
                        root = rightrightRotation(root);
                    } else {
                        root = rightleftRotation(root);
                    }
                }
            } else {
                System.out.print("无法插入值相同的节点！");
            }
        }
        root.height = max(height(root.left), height(root.right)) + 1;
        return root;
    }

    private static TreeNode delete(TreeNode node2, TreeNode root) {
        if (root == null || node2 == null) {
            return null;
        }
        int cmp = node2.value - root.value;
        if (cmp < 0) {
            root.left = delete(node2, root.left);
            if (height(root.right) - height(root.left) == 2) {
                TreeNode pnode = root.right;
                if (height(pnode.left) < height(pnode.right)) {
                    root = rightrightRotation(root);
                } else {
                    root = rightleftRotation(root);
                }
            }
        } else if (cmp > 0) {
            root.right = delete(node2, root.right);
            if (height(root.left) - height(root.right) == 2) {
                TreeNode pnode = root.left;
                if (height(pnode.left) < height(pnode.right)) {
                    root = leftrightRotation(root);
                } else {
                    root = leftleftRotation(root);
                }
            }
        } else {
            /**
             * 这里是找到了需要删除的节点，首先判断是否左右子树都不为空，如果是，则判断左右子树的高度，然后删除高的
             * 子树中的最大（最小）节点，同时替代需要删除的节点，若左右子树有一个不为空或者都为空，则直接赋值即可
             */
            if (root.left != null && root.right != null) {
                if (height(root.left) > height(root.right)) {
                    /**
                     * 找到左子树的中的最大节点
                     */
                    TreeNode pnode = root.left;
                    while (pnode.right != null) {
                        pnode = pnode.right;
                    }
                    root.value = pnode.value;
                    root.left = delete(pnode,root.left);
                }else{
                    /**
                     * 找到右子树中的最小节点
                     */
                    TreeNode pnode = root.right;
                    while (pnode.left != null) {
                        pnode = pnode.left;
                    }
                    root.value = pnode.value;
                    root.right = delete(pnode,root.right);
                }
            }else{
                root = (root.left==null)?root.right:root.left;
            }
        }
        return root;
    }

    private static boolean find(TreeNode node3, TreeNode root) {
        if (root == null) {
            System.out.print("树为空！");
            return false;
        }
        TreeNode curnode = root;
        while (curnode != null) {
            if (curnode.value < node3.value) {
                curnode = curnode.right;
            }else if(curnode.value > node3.value){
                curnode = curnode.left;
            }else{
                System.out.print("查找成功！");
                return true;
            }
        }
        System.out.print("找不到该节点！");
        return false;
    }

    private static void unrecurPostOrder(TreeNode root) {
        /**
         * 后序迭代遍历和先序，中序不一样，需要判断什么时候读取根节点，同时需要考虑将根节点重新放入栈中的情况
         */
        Stack<TreeNode> snode = new Stack<>();
        TreeNode lastnode = null;
        TreeNode curnode = root;
        while (curnode != null) {
            snode.push(curnode);
            curnode = curnode.left;
        }
        while (!snode.isEmpty()) {
            /**
             * 只有当右节点不存在或者右节点是上一个被访问的节点的时候才会访问根节点
             */
            curnode = snode.pop();
            if (curnode.right == null || curnode.right == lastnode) {
                System.out.print(curnode.value + " ");
                lastnode = curnode;
            } else {
                /**
                 * 当右子树存在，并且不是上一个访问的元素的时候，将根节点重新入栈，同时进入右子树，将左子树入栈
                 */
                snode.push(curnode);
                curnode = curnode.right;
                while (curnode != null) {
                    snode.push(curnode);
                    curnode = curnode.left;
                }
            }
        }
    }

    private static void recurPostOrder(TreeNode root) {
        if (root != null) {
            recurPostOrder(root.left);
            recurPostOrder(root.right);
            System.out.print(root.value + " ");
        }
    }

    private static void unrecurInOrder(TreeNode root) {
        /**
         * 类似于先序遍历，将每个节点看做一个根节点处理
         */
        Stack<TreeNode> snode = new Stack<>();
        TreeNode p = root;
        while (!snode.isEmpty() || p != null) {
            if (p != null) {
                snode.push(p);
                p = p.left;
            } else {
                TreeNode q = snode.pop();
                System.out.print(q.value + " ");
                p = q.right;
            }
        }
    }

    private static void recurInOrder(TreeNode root) {
        if (root != null) {
            recurInOrder(root.left);
            System.out.print(root.value + " ");
            recurInOrder(root.right);
        }
    }

    private static void unrecurPreOrder(TreeNode root) {
        /**
         * 直接利用栈将每个节点都当做一个根节点
         */
        /*Stack<TreeNode> snode = new Stack<>();
        TreeNode p = root;
        while (!snode.isEmpty() || p != null) {
            if (p!=null) {
                System.out.print(p.value+" ");
                snode.push(p);
                p = p.left;
            }else{
                TreeNode q = snode.pop();
                p = q.right;
            }
        }*/
        /**
         * 利用栈的后进先出的特性，将左右节点反过来放入栈中
         */
        Stack<TreeNode> snode = new Stack<>();
        snode.push(root);
        while (!snode.isEmpty()) {
            TreeNode q = snode.pop();
            System.out.print(q.value + " ");
            if (q.right != null) {
                snode.push(q.right);
            }
            if (q.left != null) {
                snode.push(q.left);
            }
        }
    }

    private static void recurPerOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + " ");
            recurPerOrder(root.left);
            recurPerOrder(root.right);
        }
    }

}
