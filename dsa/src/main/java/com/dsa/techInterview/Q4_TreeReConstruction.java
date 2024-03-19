package com.dsa.techInterview;

import java.util.*;

/*

PostOrder: Left Right Root
InOrder: Left root right
PreOrder: root Left Right

Can solve using DFS or BFS, however, I would go with DFS. As I will be going node by node.

Approach:
I am assuming all nodes are unique in this case
Will traverse postOrder array in reverse from end to first, since last element would be the root of the tree.
Then, I will search for the index of the root in the inorder array. This will allow me to get the left and right subtrees of the root. Grandchild also

Recursively, I would solve for right sub trees first and then the left sub trees.
Why right sub trees first? --> I will be traversing the post order array in reverse. Hence, will get the right root node before the left root node.
so, by creating the right sub tree first, I will be traversing the postorder array only once. TC: O(n)

I will create map which will hold indexes of all nodes in inorder array. So that I can utilize it in my recursive method to split left and right subtrees.

TC: O(n), SC: O(n)

         1
   2            3
4     5             6
     7             8  9

Iterative Approach:
DFS using Stack.
1. Iterate(while) in reverse order of post order.
2. Keep maintaining previous node.
3. Will keep pushing to stack till inorder.val == root value. Then will keep popping till the entire stack is empty.
TC: O(n), SC:O(n)
 */

public class Q4_TreeReConstruction {

    private int[] preOrder;
    private int[] inOrder;
    private int[] posOrder;
    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    int index =0;
    public static void main(String[] args) {

        int[] preO = {1,2,4,5,7,3,6,8,9};
        int[] inO = {4,2,7,5,1,3,8,6,9};
        int[] poO = {4,7,5,2,8,9,6,3,1};
        Q4_TreeReConstruction reConstruction = new Q4_TreeReConstruction();
        System.out.println(reConstruction.buildTreeUsingPreAndIn(preO,inO));
        System.out.println();
        System.out.println();

        reConstruction.recursivePreOrder(reConstruction.buildTreeUsingPoAndIn(poO,inO));

    }

    public void recursivePreOrder(TreeNode root){

        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        recursivePreOrder(root.left);
        recursivePreOrder(root.right);
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

        TreeNode root = poInDFS(0,index);
        return root;
    }

    private TreeNode poInDFS(int start, int end) {

        //base
        if(start > end){
            return null;
        }
        //cur Node
        int rootVal = posOrder[index];
        index--;
        TreeNode root = new TreeNode(rootVal);
        //find mid
        int rootIndex = map.get(rootVal);

        //create right
        root.right = poInDFS(rootIndex+1,end);

        //create left sub
        root.left = poInDFS(start,rootIndex-1);

        //return node
        return root;
    }
}


class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {

    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}