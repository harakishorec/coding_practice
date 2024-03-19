package com.dsa.techInterview;

import java.util.*;

//Time complexity: o(n)
//Space complexity: o(1)

//Questions:
//Given a tree,
/*
        1
   2            3
4    5      6    be a perfect case for BFS, give   7

ans: 1,2,3,4,5,6,7 or

1
2 3
4 5 6 7

//Is this going to be a complete binary tree?
//how should we handle null scenario? Do you want me to throw an exception or just return as null?

If I am going to tree, this looks like level order traversal. To do this in a BT, I would use BFS or DFS.
However, in here this looks level order, this would n that in BFS we go level by level.

Approach:
I would be doing BFS traversal using a queue to keep track of the different child nodes of the root.
Once all the children for a given level are complete will move to the next level.

Edge cases:
1. Null tree
2. single node tree

BFS:
TC: O(n), SC: O(n)

DFS:
TC: O(n) , SC: O(h) (DFS, goes in depth so till height of the tree)

I would recursively traverse first the left node and then right.
In each traversal, I would do following
1. connect left node of root with right node(2->3), that is root.left.next = root.right
2. if root.right exists,
    if root.next = null -> no more nodes to add in that level. hence root.right.next = null
    if root,next != null --> root.right.next = root.next.left(5-->6), root 2

 */


public class Q2_LevelOrderBT {

    private Node root;

    public Node createNode(){
        Node t1 = new Node(1);
        Node t2 = new Node(2);
        Node t3 = new Node(3);
        Node t4 = new Node(4);
        Node t5 = new Node(5);
        Node t6 = new Node(6);
        Node t7 = new Node(7);

        root = t1;
        t1.left = t2;
        t2.left = t4;
        t2.right = t5;
        t1.right = t3;
        t3.left = t6;
        t3.right = t7;

        return root;
    }

    public void recursivePreOrder(Node root){

        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        recursivePreOrder(root.left);
        recursivePreOrder(root.right);
    }

    public Node levelOrderTree(Node root){
        //Check for null
        if(root == null){
            return null;
        }

        //Check for single Node

        //BFS Traversal using Queue
        Queue<Node> q1 = new LinkedList<>();
        q1.add(root);
        q1.add(new Node(-1)); // This is to track end of current level
        while(!q1.isEmpty()){
            Node temp = q1.poll();

            if(temp.left != null){
                q1.add(temp.left);
            }
            if(temp.right != null){
                q1.add(temp.right);
            }
            System.out.print(temp.val + " ");

            // This is to handle when current level is completed
            if(!q1.isEmpty() && q1.peek().val == -1 && (q1.size() > 1 || root.val == temp.val) ){
                q1.add(new Node(-1));
                q1.poll();
                System.out.println();
            }

            //This is edge case where all nodes of tree have been traversed but last -1 needs to be ignored
            if(!q1.isEmpty() && q1.peek().val == -1 && (q1.size() == 1 || root.val != temp.val) ){
                q1.poll();
                System.out.println();
            }
        }
        return root;
    }

    public void levelOrderTreeWithoutNull(Node root){
        if(root == null){
            return;
        }
        Queue<Node> q1 = new LinkedList<>();
        q1.offer(root);

        while(!q1.isEmpty()){
            Node temp = q1.poll();
            System.out.print(temp.val + " ");

            if(temp.left != null){
                q1.offer(temp.left);
            }
            if(temp.right != null){
                q1.offer(temp.right);
            }
        }
    }

    //Variation of the above code with DFS approach : TC: O(n) , SC: O(h)
    public Node dfs(Node root) {
        if(root == null) {
            return null;
        }

        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            if(root.next == null){
                root.right.next = null;
            } else {
                root.right.next = root.next.left;
            }
        }

        dfs(root.left);
        dfs(root.right);

        return root;
    }

    public static void main(String[] args) {

        Q2_LevelOrderBT lo = new Q2_LevelOrderBT();
        Node rootVal = lo.createNode();

        System.out.println("Pre Order");
        lo.recursivePreOrder(rootVal);
        System.out.println();

        System.out.println();
        System.out.println("Level Order BFS Initial without Null Approach");
        lo.levelOrderTreeWithoutNull(rootVal);

        System.out.println();
        System.out.println("Level Order BFS Approach");
        lo.levelOrderTree(rootVal);

        System.out.println();
        System.out.println("Level Order DFS Approach");
        Node dfsNode = lo.dfs(rootVal);
        lo.levelOrderTreeWithoutNull(dfsNode);

    }
}

class Node {

    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

}