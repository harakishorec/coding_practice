package com.dsa.coding;

import com.dsa.util.TreeNode;

import java.util.*;

public class TreeReConstruction {

    private int[] preOrder;
    private int[] inOrder;
    private int[] posOrder;
    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    int index =0;
    public static void main(String[] args) {

        int[] preO = {3,9,20,15,7};
        int[] inO = {9,3,15,20,7};
        int[] poO = {9,15,7,20,3};
        TreeReConstruction reConstruction = new TreeReConstruction();
        System.out.println(reConstruction.buildTreeUsingPreAndIn(preO,inO));
        System.out.println();
        System.out.println(reConstruction.buildTreeUsingPoAndIn(poO,inO));

    }
    public TreeNode buildTreeUsingPreAndIn(int[] preorder, int[] inorder){
        preOrder =preorder;
        inOrder = inorder;
        int n = preOrder.length;
        for(int i =0; i<n;i++){
            map.put(inOrder[i],i);
        }

        TreeNode root = dfs(0,n-1);
        return root;
    }

    private TreeNode dfs(int start, int end) {
        //base case
        if(start > end) return null;

        //create TreeNode
        int curVal = preOrder[index++];
        TreeNode cur = new TreeNode(curVal);
        //Calculate mid

        int mid = map.get(curVal);

        //Build left subtree
        cur.left = dfs(start,mid-1);
        //Build right sub tree
        cur.right = dfs(mid+1,end);

        //Return treeNode
        return cur;
    }

    public TreeNode buildTreeUsingPoAndIn(int[] postorder, int[] inorder){

        posOrder = postorder;
        inOrder = inorder;

        int n = posOrder.length;
        map = new HashMap<Integer, Integer>();
        index =n-1;
        for(int i =0 ; i<n; i++){
            map.put(inOrder[i],i);
        }

        TreeNode root = poInDFS(0,n-1);
        return root;
    }

    private TreeNode poInDFS(int start, int end) {

        //base
        if(start > end){
            return null;
        }
        //cur Node
        int curVal = posOrder[index--];
        TreeNode cur = new TreeNode(curVal);
        //find mid
        int mid = map.get(curVal);

        //create right
        cur.right = poInDFS(mid+1,end);

        //create left sub
        cur.left = poInDFS(start,mid-1);


        //return node
        return cur;
    }





}
