package com.sergey.michael.sergey.Engine.Util.data;

public class Queue<E>{

    private E[] array;
    private int counter = 0;
    private int start;
    private int size;

    public Queue(int size){
        this.size = size;
        array = (E[]) new Object[size];
    }

    public void enqueue(E newint){

        if(counter==size) {
            extendArray();
        }

        array[counter] = newint;
        counter++;
    }

    public E dequeue(){
        if(start*2 >= size) {
            shrinkArray();
        }
        E value = array[start];
        start+=1;
        return value;
    }

    private void extendArray(){
        E[] newarray = (E[]) new Object[2*size];
        System.arraycopy(array, 0, newarray, 0, counter);
        size = size*2;
        array = newarray;
    }

    private void shrinkArray() {
        int newSize = (counter-start);
        E[] newArray = (E[]) new Object[newSize];
        System.arraycopy(array, start, newArray, 0, counter - start);
        size = counter = newSize;
        start = 0;
        array = newArray;
    }

    public E[] getArray(){
        return array;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i =start; i<counter; i++){
            string.append(" ").append(array[i]);
        }

        return string.toString();
    }
}

