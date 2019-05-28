package com.loren.elevator.model;

import org.junit.Assert;

import static com.loren.elevator.model.Passenger.PASSENGER_ID;

public class testElevator {
    public static void main(String[] args) {
        int max = 20;
        int p = 12;
        Elevator e = new Elevator(max, p);
        Passenger[] pass = new Passenger[p];

        Assert.assertTrue(e.open());
        Assert.assertTrue(e.open());
        Assert.assertTrue(e.close());
        Assert.assertTrue(e.open());
        Assert.assertFalse(e.up());
        Assert.assertFalse(e.down());
        Assert.assertTrue(e.close());
        Assert.assertTrue(e.down());
        for(int i = 0; i < max + 1; i++)
            Assert.assertTrue(e.up());
        for(int i = 0; i < max + 1; i++)
            Assert.assertTrue(e.down());
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
}
