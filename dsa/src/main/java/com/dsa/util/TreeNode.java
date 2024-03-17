package com.dsa.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

     public int val;
    public TreeNode left;
    public TreeNode right;

    public  TreeNode(int val){ this.val =val;}


    public void print() {
        print("", this, false);
    }

    public void print(String prefix, TreeNode n, boolean isLeft) {
        if (n != null) {
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.val);
            print(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }

    @Override
    public String toString() {
        print();
        return "TreeNode{}";
    }
}
