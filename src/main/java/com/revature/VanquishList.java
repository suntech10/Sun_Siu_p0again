package com.revature;

public class VanquishList {
        private int size = 0;
        private int capacity = 10;
        private static final int DEFAULT_SIZE = 10;
        Object[] elementData = {};

        public VanquishList(){
            this.elementData = new Object[DEFAULT_SIZE];
        }

        public VanquishList(int initialCapacity) {
            if (initialCapacity >= 0) {
                this.elementData = new Object[initialCapacity * 2];
                this.setCapacity(initialCapacity * 2);
            } else {
                throw new IllegalArgumentException();
            }
        }

        public Object clone(){
            VanquishList clone = new VanquishList(size);
            for(int x = 0; x < size; x++){
                clone.add(elementData[x]);
            }
            return clone;
        }

        private void setCapacity(int capacity){
            this.capacity = capacity;
        }

        private void grow(){
            this.capacity *= 2;
            Object[] newElementData = new Object[capacity * 2];
            for(int x = 0; x < size; x++){
                newElementData[x] = elementData[x];
            }
            this.elementData = newElementData;
        }

        public boolean isEmpty(){
            if (size == 0){
                return true;
            } else{
                return false;
            }
        }

        public void add(Object o){
            if (this.size >= capacity){
                this.grow();
            }
            this.elementData[size++] = o;
        }

        public void add(int index, Object o){
            if (size >= capacity){
                this.grow();
            }
            Object[] newElementData = new Object[this.capacity];
            size++;
            for(int x = 0; x < size; x++){
                if (x < index){
                    newElementData[x] = elementData[x];
                } else if (x == index){
                    newElementData[x] = o;
                } else{
                    newElementData[x] = elementData[x-1];
                }
            }
            this.elementData = newElementData;
        }

        public Object get(int index){
            if (index > 0 && index < size){
                return elementData[index];
            }
            else{
                throw new IllegalArgumentException();
            }
        }

        public void set(int index, Object o){
            if (index > 0 && index < size){
                elementData[index] = o;
            }
            else{
                throw new IllegalArgumentException();
            }
        }

        public boolean Contains(Object o){
            for(int x = 0; x < size; x++){
                if (elementData[x].equals(o)){
                    return true;
                }
            }
            return false;
        }

        public int indexOf(Object o){
            for(int x = 0; x < size; x++){
                if (elementData[x].equals(o)){
                    return x;
                }
            }
            return -1;
        }

        public void remove(int index){
            if (index >= size || index < 0){
                throw new IllegalArgumentException();
            }
            size--;
            for(int x = index; x < size; x++){
                elementData[x] = elementData[x + 1];
            }
            elementData[size] = null;
        }

        public void remove(Object o){
            size--;
            for(int x = 0; x < size; x++){
                if(elementData[x].equals(o)){
                    remove(x);
                    break;
                }
            }
        }

        public void removeAll(Object o){
            for(int x = 0; x < size; x++){
                if(elementData[x].equals(o)){
                    remove(--x);
                }
            }
        }
    }
