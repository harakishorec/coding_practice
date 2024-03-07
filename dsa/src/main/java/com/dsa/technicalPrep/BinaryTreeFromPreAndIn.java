package com.dsa.techInterview;

public class BinaryTreeFromPreAndIn {

    int preorderIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        TreeNode root = buildTree(preorder, 0 , preorder.length - 1, inorder);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int left, int right, int[] inorder) {

        //Base Case
        if(preorderIndex >= preorder.length || left > right){
            return null;
        }

        int rootValue = preorder[preorderIndex];
        TreeNode root = new TreeNode(rootValue);

        for(int i = left; i<=right; i++){
            if(preorder[preorderIndex] == inorder[i]){
                preorderIndex++;

                root.left = buildTree(preorder,left,i-1,inorder);
                root.right = buildTree(preorder,i+1,right,inorder);
                break;
            }
        }
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
        int[] inOrder = {9,3,15,20,7};
        int[] preOrder = {3,9,20,15,7};

        BinaryTreeFromPreAndIn bt =new BinaryTreeFromPreAndIn();
        TreeNode tn = bt.buildTree(preOrder,inOrder);
        bt.recursivePreOrder(tn);
    }
}
