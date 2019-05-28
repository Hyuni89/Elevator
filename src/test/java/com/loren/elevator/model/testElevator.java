package com.loren.elevator.model;

import org.junit.Assert;

public class testElevator {

    static int TESTCNT = 30;

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
        Assert.assertFalse(e.enter(new Passenger(max)));
        Assert.assertTrue(e.open());
        for(int i = 0; i < p; i++) {
            pass[i] = new Passenger(max);
            e.setFloor(pass[i].getFloor());
            Assert.assertTrue(e.enter(pass[i]));
            Assert.assertEquals(i + 1, e.getPassengerSize());
        }
        Assert.assertFalse(e.enter(new Passenger(max)));
        Assert.assertFalse(e.up());
        Assert.assertTrue(e.close());
        Assert.assertTrue(e.open());
        for(int i = 0; i < p; i++) {
            e.setFloor(pass[i].getFloor());
            Assert.assertTrue(e.exit(pass[i]));
            Assert.assertEquals(p - i - 1, e.getPassengerSize());
        }
        Assert.assertEquals(e.getPassengerSize(), 0);
        for(int i = 0; i < p; i++) {
            e.setFloor(pass[i].getFloor());
            Assert.assertTrue(e.exit(pass[i]));
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
