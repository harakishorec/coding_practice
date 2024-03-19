package com.dsa.techInterview;

import java.util.Stack;

public class Q4_Tree_Iterative {

    public TreeNode buildTreeIteratively(int[] po, int[] in){
        int postIndex, inIndex ;
        postIndex = inIndex = po.length-1;
        int flag =0; // To keep track of left/right sub tree
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode prev = new TreeNode(po[postIndex]);
        TreeNode root = prev;
        nodeStack.push(root);
        --postIndex;

        while(postIndex>=0){
            if(!nodeStack.isEmpty() && in[inIndex] == nodeStack.peek().val){
                prev = nodeStack.pop();
                --inIndex;
                flag =1;
            }else{
                TreeNode node = new TreeNode(po[postIndex]);
                if(flag==0){
                    prev.right = node;
                    prev = prev.right;
                }else{
                    prev.left = node;
                    prev=prev.left;
                    flag = 0;
                }
                nodeStack.push(node);
                --postIndex;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] preO = {3,9,20,15,7};
        int[] inO = {9,3,15,20,7};
        int[] poO = {9,15,7,20,3};

        Q4_Tree_Iterative bt = new Q4_Tree_Iterative();
        TreeNode root = bt.buildTreeIteratively(poO,inO);
        bt.preorderTraversal(root);

    }

    public void preorderTraversal(TreeNode root) {
        if (root == null)
            return;

        // Print the value of the current node
        System.out.print(root.val + " ");

        // Traverse left subtree
        preorderTraversal(root.left);

        // Traverse right subtree
        preorderTraversal(root.right);
    }
}
