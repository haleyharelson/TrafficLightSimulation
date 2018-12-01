package Vehicles;

public class Car extends Vehicle {

    public Car() {

        super(10, false);
    }

    @Override
    public int getTime() {
        return 10;
    }

    @Override
    public boolean getIsEmergency() {
        return false;
    }
}
