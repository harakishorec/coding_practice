package com.dsa.coding;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class Q1_SuperIterator<T> implements Iterator<T> {
    private final List<Iterator<T>> iterators;

    Integer index;
    Integer length;
    Boolean parallelFlag = false;

    public Q1_SuperIterator(List<Iterator<T>> iterators, boolean b) {
        this.iterators = iterators;
        length = iterators == null ? 0: iterators.size();
        index =-1;
        parallelFlag = b;

    }

    @Override
    public boolean hasNext() {

        if(length == 0) return false;
        if (parallelFlag) return hasNextParallel();
        else return hasNextSeqeuntial();

    }

    private boolean hasNextSeqeuntial() {
        if(index == -1) index++;
        while(index<length){
            if(iterators.get(index).hasNext()) return true;
            index++;
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
            if(iterators.get(index).hasNext())
                return true;
            else{
                calculateIndex();
            }
            count++;
        }
        return false;
    }

    private void calculateIndex() {
        if(index +1 < length){
            index++;
        } else{
            index = 0;
        }
    }


    @Override
    public T next() {
        if(index == -1 || !iterators.get(index).hasNext()){
            throw new NoSuchElementException();
        }
        return iterators.get(index).next();


    }

/*
    @Override
    public boolean hasNext() {
        int index = currentIteratorIndex.get();
        while (index < iterators.size()) {
            if (iterators.get(index).hasNext()) {
                return true;
            } else {
                index = currentIteratorIndex.incrementAndGet();
            }
        }
        return false;


    @Override
    public T next() {
        int index = currentIteratorIndex.get();
        while (index < iterators.size()) {
            Iterator<T> iterator = iterators.get(index);
            if (iterator.hasNext()) {
                return iterator.next();
            } else {
                index = currentIteratorIndex.incrementAndGet();
            }
        }
        throw new NoSuchElementException();
    }

 */

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported by SuperIterator");
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);

        List<Iterator<Integer>> iterators = Arrays.asList(list1.iterator(), list2.iterator(), list3.iterator());

        Q1_SuperIterator<Integer> superIterator = new Q1_SuperIterator<>(iterators, false);
            while (superIterator.hasNext()) {
                System.out.print(superIterator.next()+" ");
            }



    }
}
