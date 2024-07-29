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

        public static LinkedList<Integer> stack = new LinkedList<>();
        public static LinkedList<Integer> maxStack = new LinkedList<>();


        public static void push(int val){
            stack.add(0,val);
            if(maxStack.isEmpty() || val > maxStack.get(0)){
                maxStack.add(0,val);
            }
        }

        public static int pop(){

            if(stack.isEmpty() || maxStack.isEmpty()){
                throw new EmptyStackException();
            }
            int val = stack.get(0);
            stack.remove(0);
            if(maxStack.get(0) == val){
                maxStack.remove(0);
            }
            checkMax();
            return val;
        }

        public static int max(){

            if(maxStack.isEmpty()){
                throw new EmptyStackException();
            }
            return maxStack.get(0);
        }

        public static int popMax(){

            if(stack.isEmpty() || maxStack.isEmpty()){
                throw new EmptyStackException();
            }

            int val = maxStack.get(0);
            maxStack.remove(0);

            int index = -1;

            for(int i =0; i<stack.size(); i++){
                if(val == stack.get(i) && index == -1){
                    index = i;
                    break;
                }
            }

            stack.remove(index);
            checkMax();

            return val;
        }

        public static void checkMax(){

            int maxv = Integer.MIN_VALUE;

            for(int i =0; i<stack.size(); i++){
                if(maxv < stack.get(i)){
                    maxv = stack.get(i);
                }
            }

            if(maxStack.get(0) < maxv){
                maxStack.add(0,maxv);
            }

        }

        @Override
        public String toString(){
            return "Stack: "+stack + " "+ "MaxStack: "+ maxStack;
        }


        public static void main(String[] args) {

            Q3_Stack st = new Q3_Stack();
            st.push(1);
            st.push(1);
            st.push(1);
            st.push(4);
            st.push(4);
            st.push(4);
            st.push(4);
            st.push(5);
            st.push(5);
            st.push(5);
            st.push(6);
            st.push(6);
            st.push(10);
            st.push(13);
            st.push(13);
            st.push(13);
            System.out.println(st);
            st.push(1);
            st.push(1);
            st.push(1);
            st.push(4);
            st.push(4);
            System.out.println(st);

            System.out.println(st.max());
            System.out.println("Max: "+st);
//
            System.out.println(st.popMax());
            System.out.println("PopMax: "+st);
//
            System.out.println(st.popMax());
            System.out.println("PopMax: "+st);
//
            System.out.println(st.popMax());
            System.out.println("PopMax: "+st);

            System.out.println(st.popMax());
            System.out.println("PopMax: "+st);

            System.out.println(st.max());
            System.out.println("Max: "+st);

            System.out.println(st.pop());
            System.out.println("Pop: "+st);

            System.out.println(st.pop());
            System.out.println("Pop: "+st);

            System.out.println(st.popMax());
            System.out.println("PopMax: "+st);

            System.out.println(st.popMax());
            System.out.println("PopMax: "+st);

            System.out.println(st.popMax());
            System.out.println("PopMax: "+st);

//        System.out.println(st.pop());
//        System.out.println("Pop: "+st);
        }
    }

