package BSTreview;

import java.util.Scanner;
import java.util.Stack;

/**
 * 复习二叉查找树的插入，删除，查找，先序中序后序递归和非递归遍历
 */

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    TreeNode(int x) {
        value = x;
    }
}

public class Solution {

    private static TreeNode root = null;

    public static void main(String[] args) {
        while (true) {
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
            /**********************************/
            /*root = new TreeNode(4);
            root.left = new TreeNode(2);
            root.right = new TreeNode(6);
            root.left.left = new TreeNode(1);
            root.left.right = new TreeNode(3);
            root.right.left = new TreeNode(5);*/
            /**********************************/
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
                    boolean flag2 = find(node3, root) == null ? false : true;
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

    private static TreeNode find(TreeNode node3, TreeNode root) {
        if (root != null) {
            if (node3.value == root.value) {
                return root;
            } else if (node3.value > root.value) {
                return find(node3, root.right);
            } else {
                return find(node3, root.left);
            }
        } else
            return null;
    }

    private static void delete(TreeNode node2, TreeNode root) {
        /**
         * 节点的删除分为三种情况：
         * 1、节点没有左右子节点
         * 2、只有左节点或者右节点
         * 3、既有左，又有右节点
         */
        TreeNode delnode = find(node2, root);
        TreeNode child;
        if (delnode.left != null && delnode.right != null) {
            /**
             * 左右子树都不为空，则找到他的后继节点
             */
            TreeNode pnode = delnode.right;
            while (pnode.left != null) {
                pnode = pnode.left;
            }
            /**
             * 此时这个pnode就是原delnode的后继节点
             */
            delnode.value = pnode.value;
            delnode = pnode;
        }
        if (delnode.left != null) {
            child = delnode.left;
        } else {
            child = delnode.right;
        }
        if (child != null) {
            child.parent = delnode.parent;
        }
        if (delnode.parent == null) {
            root = child;
        } else if (delnode.parent.left == delnode) {
            delnode.parent.left = child;
        } else if (delnode.parent.right == delnode) {
            delnode.parent.right = child;
        }
    }

    private static TreeNode insert(TreeNode node1, TreeNode root) {
        /**
         * 利用迭代
         */
        TreeNode curnode = root;
        if (root == null) {
            root = node1;
            return root;
        } else {
            while (curnode != null) {
                if (curnode.value < node1.value) {
                    if (curnode.right == null) {
                        curnode.right = node1;
                        node1.parent = curnode;
                        break;
                    } else {
                        curnode = curnode.right;
                    }
                } else {
                    if (curnode.left == null) {
                        curnode.left = node1;
                        node1.parent = curnode;
                        break;
                    } else {
                        curnode = curnode.left;
                    }
                }
            }
        }
        return root;
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
