package com.dsa.techInterview;


/*

TC:  O(n)
SC: O(1)
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Q1_SuperIterator<T> implements Iterator<T> {
    private final List<Iterator<T>> iterators;

    Integer overallIteratorIndex;
    Integer length;
    Boolean parallelFlag = false;

    public Q1_SuperIterator(List<Iterator<T>> iterators, boolean b) {
        this.iterators = iterators;
        length = iterators == null ? 0: iterators.size();
        overallIteratorIndex =-1;
        parallelFlag = b;

    }

    @Override
    public boolean hasNext() {

        if(length == 0) return false;
        if (parallelFlag) return hasNextParallel();
        else return hasNextSeqeuntial();

    }

    private boolean hasNextSeqeuntial() {
        if(overallIteratorIndex == -1) overallIteratorIndex++;
        while(overallIteratorIndex <length){
            if(iterators.get(overallIteratorIndex).hasNext()) return true;
            overallIteratorIndex++;
        }

        return false;
    }

    private boolean hasNextParallel() {
        calculateIndex();
        int count = 0;
        return traverseIteratorList(count);
    }

    private boolean traverseIteratorList(int count) {
        while (count < length){
            if(iterators.get(overallIteratorIndex).hasNext())
                return true;
            else{
                calculateIndex();
            }
            count++;
        }
        return false;
    }

    private void calculateIndex() {
        if(overallIteratorIndex +1 < length){
            overallIteratorIndex++;
        } else{
            overallIteratorIndex = 0;
        }
    }


    @Override
    public T next() {
        return iterators.get(overallIteratorIndex).next();


    }




    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported by SuperIterator");
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);

        List<Iterator<Integer>> iterators = Arrays.asList(list1.iterator(), list2.iterator(), list3.iterator());

        Q1_SuperIterator<Integer> superIterator = new Q1_SuperIterator<>(iterators, true);
            while (superIterator.hasNext()) {
                System.out.print(superIterator.next()+" ");
            }



    }
}
