package com.dsa.coding;
import java.util.*;
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
