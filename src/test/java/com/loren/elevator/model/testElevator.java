package com.loren.elevator.model;

import org.junit.Assert;

import java.util.Arrays;

public class testElevator {

    static int TESTCNT = 30;
    static int TIME = 0;

    public static void main(String[] args) {
        int max = 20;
        int p = 12;
        Elevator e = new Elevator(max, p);
        Passenger[] pass = new Passenger[p];

        testOPEN(e);
        testCLOSE(e);
        testSTOP(e);
        testUP(e);
        testDOWN(e);
        Assert.assertTrue(e.close());
        Assert.assertTrue(e.open());
        Assert.assertFalse(e.up());
        Assert.assertFalse(e.down());
        Assert.assertTrue(e.close());
        Assert.assertTrue(e.down());
        testUP(e);
        testDOWN(e);
        Assert.assertFalse(e.enter(Arrays.asList(new Passenger(max, TIME++))));
        Assert.assertTrue(e.open());
        for(int i = 0; i < p; i++) {
            pass[i] = new Passenger(max, TIME++);
            e.setFloor(pass[i].getFloor());
            Assert.assertTrue(e.enter(Arrays.asList(pass[i])));
            Assert.assertEquals(i + 1, e.getPassengerSize());
        }
        Assert.assertFalse(e.enter(Arrays.asList(new Passenger(max, TIME++))));
        Assert.assertFalse(e.up());
        Assert.assertTrue(e.close());
        Assert.assertTrue(e.open());
        for(int i = 0; i < p; i++) {
            e.setFloor(pass[i].getFloor());
            Assert.assertTrue(e.exit(Arrays.asList(pass[i])));
            Assert.assertEquals(p - i - 1, e.getPassengerSize());
        }
        Assert.assertEquals(e.getPassengerSize(), 0);
        for(int i = 0; i < p; i++) {
            e.setFloor(pass[i].getFloor());
            Assert.assertTrue(e.exit(Arrays.asList(pass[i])));
        }
    }

    public static void testOPEN(Elevator e) {
        for(int i = 0; i < TESTCNT; i++) {
            Assert.assertTrue(e.open());
        }
    }

    public static void testCLOSE(Elevator e) {
        for(int i = 0; i < TESTCNT; i++) {
            Assert.assertTrue(e.close());
        }
    }

    public static void testUP(Elevator e) {
        for(int i = 0; i < TESTCNT; i++) {
            Assert.assertTrue(e.up());
        }
        Assert.assertTrue(e.stop());
    }

    public static void testDOWN(Elevator e) {
        for(int i = 0; i < TESTCNT; i++) {
            Assert.assertTrue(e.down());
        }
        Assert.assertTrue(e.stop());
    }

    public static void testSTOP(Elevator e) {
        for(int i = 0; i < TESTCNT; i++) {
            Assert.assertTrue(e.stop());
        }
    }
}
