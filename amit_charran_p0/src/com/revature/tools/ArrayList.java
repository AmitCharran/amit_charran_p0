package com.revature.tools;
import java.lang.Exception.*;

/**
 * This application is an ArrayList
 * It is an array with dynamic size
 * It uses a normal array of generic type and
 * creates more when the size needs to be change
 * @param <T>
 * @exception ArrayIndexOutOfBoundsException
 * @author Amit Charran
 * @version 1.0
 * @ Since 2021-07-31
 */



// Using Generics for this class
// E = Element
// K = Key
// N = Number
// T = Type
// V = Value
// S,U,V etc. - End, 3rd, 4th types

public class ArrayList<T> {
    private int capacity;
    private T[] array;
    private int size;

    /**
     * This is the non-arguments constructor
     * It calls the parameterized constructor with the initial capacity of 100
     */
    public ArrayList(){
        this(100);
    }

    /**
     * This parameterized constructor initializes the capacity of the array to 100
     * @param cap
     * size is initialized to 0 because that is what we are going to use to keep track of the index
     * The array itself is generic type, so whoever creates an instance of this class will have to choose the type.
     * All elements in the array is also set to null since java's default values for number types are 0
     */
    public ArrayList(int cap){
        capacity = cap; // sets the initial capacity for the ArrayList
        size = 0; // keeps track of the current index of the ArrayList
        array = (T[]) new Object[capacity];

        this.clear(); // initialize full capacity to null
    }

    /**
     * Add one item to the end of the array and increases the size
     * @param item
     */
    public void append(T item){
        if(size == capacity){
            this.increaseCapacity();
        }

        array[size++] = item;
    }

    /**
     * Utilizes the appends function to add an item to the array
     * @param item
     */
    public void insert(T item){
        this.append(item);
    }

    /**
     * Utilizes the appends function to add an item to the array
     * @param item
     */
    public void add(T item){
        this.append(item);
    }

    /**
     * Gets an element at a particular index in an array
     * @param index
     * @return returns an element from the array
     */
    public T get(int index){
        if(index >= size || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Adds an item to the front of the array
     * @param item
     */
    public void push(T item){
        this.addAtIndex(item, 0);
    }

    /**
     * removes an item from the front of the array
     */
    public void pop(){
        this.removeAtIndex(0);
    }

    /**
     * Adds an item into any valid index
     * @param index
     * @param item
     */
    public void addAtIndex(int index, T item){
        this.addAtIndex(item, index);
    }

    /**
     * Adds an item to any valid index in the ArrayList
     * @param item
     * @param index
     */
    public void addAtIndex(T item, int index){
        if(index >= size || index < 0){
           throw new ArrayIndexOutOfBoundsException();
        }

        if((size + 1) >= capacity){
            this.increaseCapacity();
        }
        size++;
        for(int i = size - 1; i > index; i--){
            array[i] = array[i - 1];
        }
        array[index] = item;

    }

    public void removeAtIndex(int index){
        if(index >= size || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        moveElementOver(index);
        size--;

    }

    /**
     * Removes the very first instance of an item in an array
     * @param item
     */
    public void removeElement(T item){
        if(!this.contains(item)){
            return;
        }
        int index = this.indexOf(item);

        if(index == -1){
            System.out.println("Element does not exist");
        }else{
            this.removeAtIndex(index);
        }
    }

    /**
     * Returns all index of a particular item if there are duplicates
     * @param item
     * @return
     */
    public int[] getAllIndexOfItem(T item){
        int counter = 0;
        if(item == null){
            return new int[0]; // the array is initialized with null values
        }

        for(int i =0; i < size; i++){
            if(array[i] == item || array[i].equals(item)){
                counter++;
            }
        }

        if(counter == 0){
            return new int[0]; // returns an empty array
        }

        int[] ans = new int[counter];
        int ans_index = 0;
        for(int i =0; i < size; i++){
            if(array[i] == item || array[i].equals(item)){
                ans[ans_index++] = i;
                if(ans_index == counter){
                    break;
                }
            }
        }
        return ans;

    }

    /**
     * This is used to shift elements over in an array
     * It is used for adding items at a particular index of an array
     * @param index
     */
    private void moveElementOver(int index){
        // when removing element the array needs to be shifted over
        for(int i = index; i < size - 1; i++){
            array[i] = array[i+1];
        }
        array[size - 1] = null;

    }

    /**
     * Finds the index of the first occurance of an item
     * @param item
     * @return
     */
    public int indexOf(T item){
        // get the first occurrence of object
        int counter = 0; // keeps track of current index
        if(item == null){
            return -1; // Array is initialized to null
        }
        for(int i =0; i < size; i++){
            if(array[i] == item || array[i].equals(item)){
                counter++;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the first occurance of an item
     * @param item
     * @return
     */
    public int find(T item){
        return this.indexOf(item);
    }

    /**
     * returns the current size of the array
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * returns true if the array is empty
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * initializes all elements in the array to null and sets size = 0;
     */
    public void clear(){
        for(int i =0; i < capacity; i++){
            array[i] = null; // initialize all values to null
        }
        size = 0;
    }

    public boolean contains(T item){
        if(item == null){
            return false;
        }
        for(int i =0; i < size; i++){
            if(array[i] == item || array[i].equals(item)){
                return true;
            }
        }
        return false;
    }

    /**
     * doubles the capacity of the array if size is full.
     * Specifically it creates a new array of twice the size
     * and adds all elements from the previous array to the new one
     * and that will become the array we use moving forward
     */
    private void increaseCapacity(){
        capacity = capacity * 2; // Doubles the current size
        T[] tempArray = (T[]) new Object[capacity];

        // initialize all to null ... I might not need to do this...
        for(int i= 0; i < tempArray.length; i++){
            tempArray[i] = null;
        }

        // copy values over
        for (int i = 0; i < size; i++){
            tempArray[i] = array[i];
        }

        array = tempArray;
    }

    /**
     * Formats the array for output
     * @return
     */
    public String toString(){
        StringBuilder output = new StringBuilder("[");
        for(int i = 0; i < size; i++){

            output.append((array[i] + ", "));

        }
        // removing " " and ","
        output.deleteCharAt(output.length() - 1);
        output.deleteCharAt(output.length() - 1);
        output.append("]");
        return output.toString();
    }








}
