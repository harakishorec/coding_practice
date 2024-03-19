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

Node class is not required as it contains next and value, which is handled by inbuilt Linkedin class of java

TC: O(n),  SC: O(n)
 */


public class Q3_MaxStck_Updated {

    List<Integer> stack = new LinkedList<>();
    List<Integer> maxStack = new LinkedList<>();

    //TC:O(1)
    public void push(int val){
        stack.add(0,val);
        if(maxStack.isEmpty() || val>=maxStack.get(0)){
            maxStack.add(0,val);
        }
    }

    //TC:O(1)
    public Integer pop(){
        if(stack.isEmpty() || maxStack.isEmpty()){
            throw new EmptyStackException();
        }
        int val = stack.get(0);
        stack.remove(0);
        if(val == maxStack.get(0)){
            maxStack.remove(0);
        }
        return val;
    }

    //TC:O(1)
    public Integer max(){
        if(stack.isEmpty() || maxStack.isEmpty()){
            throw new EmptyStackException();
        }
        return maxStack.get(0);
    }

    //O(n)
    public Integer popMax(){
        if(stack.isEmpty() || maxStack.isEmpty()){
            throw new EmptyStackException();
        }
        int val = maxStack.get(0);
        maxStack.remove(0);
        int max = Integer.MIN_VALUE;
        int index =-1;

        for(int i =0; i< stack.size();i++){
            if(stack.get(i) == val && index ==-1){
                index = i;
                break;
            }
        }
        stack.remove(index);

        for(int i =0; i< stack.size();i++){
            int temp = stack.get(i);
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
                ", maxStack=" + maxStack
                ;
    }

    public static void main(String[] args) {
        Q3_MaxStck_Updated stack = new Q3_MaxStck_Updated();
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

    }
}
