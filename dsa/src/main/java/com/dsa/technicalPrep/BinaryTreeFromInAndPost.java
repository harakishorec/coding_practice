package com.dsa.techInterview;

import java.util.ArrayList;
import java.util.Arrays;

public class BinaryTreeFromInAndPost {

    int postLen = 0;

    public TreeNode buildTree(int[] inOrder, int[] postOrder){
        int inLen = inOrder.length;
        postLen = postOrder.length - 1;

        TreeNode root = buildTree(inOrder, 0, inLen - 1, postOrder);
        return root;
    }

    public TreeNode buildTree(int[] inOrder, int inStart, int inEnd, int[] postOrder){
        //Base Case
        if(inStart > inEnd ){
            return null;
        }

        int rootVal = postOrder[postLen--];
        TreeNode root = new TreeNode(rootVal);

        //index of Root Node in inOrder
        int rootIndex = inStart;
        for(int i = inStart; i<=inEnd; i++){
            if(inOrder[i] == rootVal){
                rootIndex = i;
                break;
            }
        }
        root.right=buildTree(inOrder,rootIndex+1,inEnd,postOrder);
        root.left=buildTree(inOrder,inStart,rootIndex-1,postOrder);

        return root;
    }

    public void recursivePreOrder(TreeNode root){

        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        recursivePreOrder(root.left);
        recursivePreOrder(root.right);
    }

    public void printArray(int[] arr1){
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] inOrder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        BinaryTreeFromInAndPost bt =new BinaryTreeFromInAndPost();
        TreeNode tn = bt.buildTree(inOrder,postorder);
        bt.recursivePreOrder(tn);
    }
}
