package com.dsa.techInterview;

import java.util.*;

/*

My assumption is the stack will hold integers for the purpose of this problem.

1. For max, do you want me to return max element or pop it?
2. In case, stack is empty, what should max return ? should I return inter.min/max value or any other number?
3. Are the elements in the stacks unique? will stack hold negative numbers?

Pop and push straight forward,
max and popmax, there are two ways:
1. track stack at each node level, however, I feel it would be better to maintain a separate maxstack to track the max element in the stack.
    This would help in optimizing max function.

Approach:
For the stack, it would contain a node(each element), def value and next
I will define another linkedlist maxstack which would track max in stack.

push: if stack is empty, I will add new node with value. else, I will add new node to start of the stacked LL. I will also, add it to correct position in maxstack
pop: If stack is not empty, I will pop and remove 0th element from LL. and will remove it from max stack
max: If maxstack is not empty, I will return top element from maxstack
popmax: If maxstack is not empty, I will pop 0th ele and remove it from maxstack


TC: O(n),  SC: O(n)
 */


class Q3_MaxStack {

    List<Node1> stack = new LinkedList<>();
    List<Integer> maxStack = new LinkedList<>();


//TC: O(n), SC: O(n), n = number of elements
    public void push(int val){
        if(!stack.isEmpty()) {
            Node1 head = stack.get(0);
            Node1 newNode = new Node1(val);
            newNode.next = head;
            stack.add(0, newNode);
        }else{
            stack.add(new Node1(val));
        }
        addToMaxStack(val);
    }

    //TC: O(n), SC: O(n), n = number of elements
    private void addToMaxStack(int val) {
        if(maxStack.isEmpty()){
            maxStack.add(val);
        }else{
            if( val >= maxStack.get(0)){
                maxStack.add(0,val);
            }
        }
    }


    //TC: O(n), SC: O(n), n = number of elements
    public Integer pop(){
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        Node1 node1 = stack.get(0);
        stack.remove(node1);

        if(node1.val == maxStack.get(0)){
            maxStack.remove(0);
        }
        return node1.val;
    }


    //TC: O(1), SC: O(1), n = number of elements
    public Integer max(){
        if(maxStack.isEmpty() || stack.isEmpty()){
            throw new EmptyStackException();
        }
        return maxStack.get(0);
    }


    //TC: O(n), SC: O(1), n = number of elements
    public Integer popMax(){
        if(maxStack.isEmpty() || stack.isEmpty()){
            throw new EmptyStackException();
        }

        int val = maxStack.get(0);
        int index = 0;
        while(!stack.isEmpty() && index<stack.size()){
           Node1 node1 = stack.get(index);
           if(node1.val == val){
               break;
           }
           index++;
        }
        stack.remove(index);
        maxStack.remove(0);

        int max = Integer.MIN_VALUE;

        for(int i =0; i< stack.size();i++){
            int temp = stack.get(i).val;
            if(temp>max){
                max= temp;
            }

        }
        if(!maxStack.contains(max))
            maxStack.add(0,max);

        return val;

    }

    @Override
    public String toString() {
        return
                "stack=" + stack +
                ", maxStack=" + maxStack;
    }

    public static void main(String[] args) {
        Q3_MaxStack stack = new Q3_MaxStack();
        stack.push(5);
        stack.push(1);
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
        System.out.println(stack.popMax());
        System.out.println(stack);
        stack.push(1);
        System.out.println(stack);
        stack.push(0);
        System.out.println(stack);
    }


}
class Node1 {
    public int val;
    public Node1 next;

    public Node1(){}

    public Node1(int val){
        this.val = val;
        this.next = null;
    }

    @Override
    public String toString() {
        return val+"";
    }
}

