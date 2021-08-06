package com.revature.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest {

    private ArrayList<String> stringArrayList;
    private ArrayList<Integer> integerArrayList;

    @Before
    public void setUp() throws Exception{
        stringArrayList = new ArrayList<>();
        integerArrayList = new ArrayList<>();

    }
    @After
    public void tearDown() throws Exception{
        stringArrayList = null;
        integerArrayList = null;
    }

    @Test
    public void append() {
        stringArrayList.append("Hi");
        assertEquals("Hi", stringArrayList.get(0));
    }

    @Test
    public void insert() {
        integerArrayList.insert(1);
        integerArrayList.insert(2);
        integerArrayList.insert(3);
        assertEquals(3, integerArrayList.size());
    }

    @Test
    public void add() {
        for(int i = 0; i < 200; i++){
            integerArrayList.add(i);
            // adding over the capacity of 100
        }
        assertEquals((Integer)165, integerArrayList.get(165));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void get() {
        assertEquals(ArrayIndexOutOfBoundsException.class, stringArrayList.get(1));
    }

    @Test
    public void push() {
        for(int i = 0; i < 10; i++){
            integerArrayList.push(i);
        }
        assertEquals((Integer)4, integerArrayList.get(4));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void pop() {
         integerArrayList.pop();
    }

    @Test
    public void popTest2(){
        for(int i =0; i < 10; i++){
            integerArrayList.push(i);
        }
        for(int i=0;i < 10;i++){
            integerArrayList.pop();
        }
        assertEquals(0, integerArrayList.size());
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void addAtIndex() {
        integerArrayList.addAtIndex(10, 10);
    }

    @Test
    public void AddAtIndex2() {
        for(int i = 0; i < 10; i++){
            integerArrayList.add(i);
        }

        integerArrayList.addAtIndex(2,5);
        assertEquals((Integer)2, integerArrayList.get(5));
    }

    @Test
    public void removeAtIndex() {
        for(int i = 0; i < 10; i++){
            integerArrayList.add(1);
        }
        integerArrayList.addAtIndex(2,5);
        integerArrayList.removeAtIndex(5);
        assertEquals((Integer)1,integerArrayList.get(5));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void removeAtIndex2(){
        integerArrayList.removeAtIndex(10);
    }

    @Test
    public void removeElement() {
        integerArrayList.removeElement(10);
    }

    @Test
    public void removeElement2() {
        integerArrayList.add(10);
        integerArrayList.removeElement(10);
        assertEquals(0, integerArrayList.size());

    }

    @Test
    public void getAllIndexOfItem() {
        for(int i = 0; i < 10; i++){
            integerArrayList.add(10);
        }

        int[] a = new int[10];
        for (int i=0; i < 10; i++){
            a[i] = i;
        }
        assertArrayEquals(a, integerArrayList.getAllIndexOfItem(10));
    }
    @Test
    public void getAllIndexOfItem2() {
        int[] a = new int[0];

        assertArrayEquals(a, integerArrayList.getAllIndexOfItem(10));
    }

    @Test
    public void indexOf() {
        for(int i =0; i < 10; i++){
            integerArrayList.add(i);
        }
        assertEquals(2, integerArrayList.indexOf(2));
    }

    @Test
    public void indexOf2() {
        for(int i =0; i < 10; i++){
            integerArrayList.add(i);
        }
        assertEquals(-1, integerArrayList.indexOf(15));
    }

    @Test
    public void find() {
        for(int i = 0; i < 100; i++){
            integerArrayList.add(i);
        }
        assertEquals(10,integerArrayList.find(10));
    }

    @Test
    public void find2() {
        for(int i = 0; i < 100; i++){
            integerArrayList.add(i);
        }
        assertEquals(-1,integerArrayList.find(1000));
    }

    @Test
    public void size() {
        assertEquals(0, integerArrayList.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(integerArrayList.isEmpty());
    }

    @Test
    public void clear() {
        for(int i = 0; i < 100; i++){
            integerArrayList.add(i);
        }
        integerArrayList.clear();
        assertEquals(0, integerArrayList.size());


    }

    @Test
    public void contains() {
        assertFalse(integerArrayList.contains(102));
    }

    @Test
    public void contains3() {
        integerArrayList.add(102);
        assertTrue(integerArrayList.contains(102));
    }

    @Test
    public void testToString() {
        integerArrayList.add(1);
        assertEquals("[1]", integerArrayList.toString());
    }
}