package com.dsa.coding;

import java.util.*;

class MaxStack {

    List<Node> stack = new LinkedList<>();
    List<Integer> maxStack = new LinkedList<>();

    public void push(int val){
        if(!stack.isEmpty()) {
            stack.add(0, new Node(val, stack.get(0)));
        }else{
            stack.add(new Node(val));
        }
        addToMaxStack(val);
    }

    private void addToMaxStack(int val) {
        if(maxStack.isEmpty()){
            maxStack.add(val);
        }else{
            if( val >= maxStack.get(0)){
                maxStack.add(0,val);
            }else{
                int index = 0;
                while(!maxStack.isEmpty() && index<maxStack.size() && maxStack.get(index) > val){
                    index++;
                }
                maxStack.add(index, val);
            }
        }
    }

    public Integer pop(){
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        Node node = stack.get(0);
        stack.remove(node);
        if(maxStack.get(0) == node.val){
            maxStack.remove(0);
        }
        return node.val;
    }

    public Integer max(){
        if(maxStack.isEmpty() || stack.isEmpty()){
            throw new EmptyStackException();
        }
        return maxStack.get(0);
    }

    public Integer popMax(){
        if(maxStack.isEmpty() || stack.isEmpty()){
            throw new EmptyStackException();
        }


        int val = maxStack.get(0);
        int index = 0;
        while(!stack.isEmpty() && index<stack.size()){
           Node node = stack.get(index);
           if(node.val == val){
               break;
           }
           index++;
        }
        stack.remove(index);
        maxStack.remove(0);
        return val;

    }

    @Override
    public String toString() {
        return "MaxStack{" +
                "stack=" + stack +
                ", maxStack=" + maxStack +
                '}';
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(1);
        stack.push(5);
        stack.push(6);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.max());
        System.out.println(stack);
        stack.push(3);
        stack.push(8);
        System.out.println(stack);
        System.out.println(stack.max());
        System.out.println(stack.popMax());
        System.out.println(stack);
    }


}
class Node{
    public int val;
    public Node next;

    public Node(){}

    public Node(int val){
        this.val = val;
        this.next = null;
    }

    public Node(int val, Node next){
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                            '}';
    }
}

