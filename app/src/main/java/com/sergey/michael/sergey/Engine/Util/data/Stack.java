package com.sergey.michael.sergey.Engine.Util.data;
public class Stack<E>{

    private E[] array;
    private int counter = 0;
    private int size;

    public Stack(int size){
        this.size = size;
        array = (E[]) new Object[size];
    }

    public void push(E newint){

        if(counter==size) {
            extendArray();
        }

        array[counter] = newint;
        counter++;
    }

    public E pop(){
        if(size >= counter*2) {
            shrinkArray();
        }
        counter--;
        E value = array[counter];
        return value;
    }

    private void extendArray(){
        E[] newarray = (E[]) new Object[2*size];
        for(int i =0; i<counter; i++){
            newarray[i] = array[i];
        }
        size = size*2;
        array = newarray;
    }

    private void shrinkArray() {
        E[] newarray = (E[]) new Object[counter];
        for(int i = 0; i<counter-1;i++){
            newarray[i] = array[i];
        }
        size = counter;
        array = newarray;
    }

    public E[] getArray(){
        return array;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        String string = "";
        for(int i =0; i<counter; i++){
            string = string+" "+array[i];
        }
        return string;
    }
}
