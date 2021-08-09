package com.revature.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest {

    private MyArrayList<String> stringMyArrayList;
    private MyArrayList<Integer> integerMyArrayList;

    @Before
    public void setUp() throws Exception{
        stringMyArrayList = new MyArrayList<>();
        integerMyArrayList = new MyArrayList<>();

    }
    @After
    public void tearDown() throws Exception{
        stringMyArrayList = null;
        integerMyArrayList = null;
    }

    @Test
    public void append() {
        stringMyArrayList.append("Hi");
        assertEquals("Hi", stringMyArrayList.get(0));
    }

    @Test
    public void insert() {
        integerMyArrayList.insert(1);
        integerMyArrayList.insert(2);
        integerMyArrayList.insert(3);
        assertEquals(3, integerMyArrayList.size());
    }

    @Test
    public void add() {
        for(int i = 0; i < 200; i++){
            integerMyArrayList.add(i);
            // adding over the capacity of 100
        }
        assertEquals((Integer)165, integerMyArrayList.get(165));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void get() {
        assertEquals(ArrayIndexOutOfBoundsException.class, stringMyArrayList.get(1));
    }

    @Test
    public void push() {
        for(int i = 0; i < 10; i++){
            integerMyArrayList.push(i);
        }
        assertEquals((Integer)4, integerMyArrayList.get(4));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void pop() {
         integerMyArrayList.pop();
    }

    @Test
    public void popTest2(){
        for(int i =0; i < 10; i++){
            integerMyArrayList.push(i);
        }
        for(int i=0;i < 10;i++){
            integerMyArrayList.pop();
        }
        assertEquals(0, integerMyArrayList.size());
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void addAtIndex() {
        integerMyArrayList.addAtIndex(10, 10);
    }

    @Test
    public void AddAtIndex2() {
        for(int i = 0; i < 10; i++){
            integerMyArrayList.add(i);
        }

        integerMyArrayList.addAtIndex(2,5);
        assertEquals((Integer)2, integerMyArrayList.get(5));
    }

    @Test
    public void removeAtIndex() {
        for(int i = 0; i < 10; i++){
            integerMyArrayList.add(1);
        }
        integerMyArrayList.addAtIndex(2,5);
        integerMyArrayList.removeAtIndex(5);
        assertEquals((Integer)1, integerMyArrayList.get(5));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void removeAtIndex2(){
        integerMyArrayList.removeAtIndex(10);
    }

    @Test
    public void removeElement() {
        integerMyArrayList.removeElement(10);
    }

    @Test
    public void removeElement2() {
        integerMyArrayList.add(10);
        integerMyArrayList.removeElement(10);
        assertEquals(0, integerMyArrayList.size());

    }

    @Test
    public void getAllIndexOfItem() {
        for(int i = 0; i < 10; i++){
            integerMyArrayList.add(10);
        }

        int[] a = new int[10];
        for (int i=0; i < 10; i++){
            a[i] = i;
        }
        assertArrayEquals(a, integerMyArrayList.getAllIndexOfItem(10));
    }
    @Test
    public void getAllIndexOfItem2() {
        int[] a = new int[0];

        assertArrayEquals(a, integerMyArrayList.getAllIndexOfItem(10));
    }

    @Test
    public void indexOf() {
        for(int i =0; i < 10; i++){
            integerMyArrayList.add(i);
        }
        assertEquals(2, integerMyArrayList.indexOf(2));
    }

    @Test
    public void indexOf2() {
        for(int i =0; i < 10; i++){
            integerMyArrayList.add(i);
        }
        assertEquals(-1, integerMyArrayList.indexOf(15));
    }

    @Test
    public void find() {
        for(int i = 0; i < 100; i++){
            integerMyArrayList.add(i);
        }
        assertEquals(10, integerMyArrayList.find(10));
    }

    @Test
    public void find2() {
        for(int i = 0; i < 100; i++){
            integerMyArrayList.add(i);
        }
        assertEquals(-1, integerMyArrayList.find(1000));
    }

    @Test
    public void size() {
        assertEquals(0, integerMyArrayList.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(integerMyArrayList.isEmpty());
    }

    @Test
    public void clear() {
        for(int i = 0; i < 100; i++){
            integerMyArrayList.add(i);
        }
        integerMyArrayList.clear();
        assertEquals(0, integerMyArrayList.size());


    }

    @Test
    public void contains() {
        assertFalse(integerMyArrayList.contains(102));
    }

    @Test
    public void contains3() {
        integerMyArrayList.add(102);
        assertTrue(integerMyArrayList.contains(102));
    }

    @Test
    public void testToString() {
        integerMyArrayList.add(1);
        assertEquals("[1]", integerMyArrayList.toString());
    }
}