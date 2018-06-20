package com.rqhua.demo.algorithm;

/**
 * Created by Administrator on 2018/6/20.
 */

public class AVLTree<V> {
    private BinaryTreeNode root;
    private int size;

    //增加节点
    public void add(BinaryTreeNode<V> node) {

        if (node == null)
            return;
        //1、插入
        if (root == null) {
            root = node;
            return;
        }
        int compare = 0;
        BinaryTreeNode<V> n = root;
        BinaryTreeNode<V> parent = n;
        while (n != null) {
            compare = compare(n, node);
            if (compare == 1) {
                n = n.left;
            } else if (compare == -1) {
                n = n.right;
            } else if (compare == 0) {
                return;
            }
            parent = n;
        }
        if (compare == 1) { //插入到left
            parent.left = node;
        } else if (compare == -1) { //插入到right
            parent.right = node;
        }

        //2、平衡
        while (parent != null) {
            if (compare == 1) { //插入到了left，平衡因子+1
                parent.balance++;
            } else if (compare == -1) {//插入到了right，平衡因子-1
                parent.balance--;
            } else {
                return;
            }
            if (parent.balance == 0) { //平衡未改变，不在寻找需要平衡的子树
                break;
            }
            if (Math.abs(parent.balance) == 2) {//找到需要平衡的子树根节点
                // TODO: 2018/6/20 平衡二叉树
                fixAfterAdd(parent);
                break;
            }
            parent = parent.parent;
            compare = compare(parent, node);
        }
        size++;
        System.out.println("Size = " + size);
    }

    private void fixAfterAdd(BinaryTreeNode<V> parent) {
        if (parent == null)
            return;
        if (parent.balance == 2) {
            //平衡左子树
        } else if (parent.balance == -2) {
            //平衡右子树
        }
    }

    private void fixLeft(BinaryTreeNode<V> parent) {
        //子树根节点
        BinaryTreeNode<V> childTreeRoot = parent.left;
        BinaryTreeNode<V> childL = childTreeRoot.left;
        BinaryTreeNode<V> childR = childTreeRoot.right;
        if (childTreeRoot.balance == 1) {
            //情况1 2 - 1
            parent.left = childR;
            childR.parent = parent;
            parent.parent = childTreeRoot;
            childTreeRoot.right = parent;
        } else if (childTreeRoot.balance == -1) {
            //将childTreeRoot的右节点的左节点，设为childTreeRoot的右节点
            childTreeRoot.right = childR.left;
            if (childR.left != null) {
                childL.left.parent = childTreeRoot;
            }

            parent.left = childTreeRoot.right;


        }


    }

    private void fixRight() {

    }

    /**
     * @param node1
     * @param node2
     * @return -1 node1.value < node2.value
     */
    private int compare(BinaryTreeNode<V> node1, BinaryTreeNode<V> node2) {
        if (node1.value != null && node2.value != null) {
            if (node1.value instanceof Integer && node2.value instanceof Integer) {
                int v1 = ((Integer) node1.value);
                int v2 = ((Integer) node2.value);
                int i = v1 - v2;
                if (i > 0) {
                    return 1;
                } else if (i < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

}