package StreetSim;

import java.util.Random;

import Vehicles.*;

public class TrafficLight {

    //four arrays for each direction of traffic (holds 5 vehicles)

    private Vehicle[] north = new Vehicle[5];
    private Vehicle[] south = new Vehicle[5];
    private Vehicle[] east = new Vehicle[5];
    private Vehicle[] west = new Vehicle[5];

    //indicates whether sim is running or not

    private boolean running = false;

    //if NS and EW have a green light or not

    private static boolean greenNS = false;
    private static boolean greenEW = false;

    //how long light stays green for one direction or the other

    private final int GREEN_TIME = 25;

    //default constructor

    public TrafficLight() {

        greenNS = true;

        placeVehicles(north);
        placeVehicles(south);
        placeVehicles(east);
        placeVehicles(west);

        running = true;

        printIntersection(north, south, east, west);
    }

    public void placeVehicles(Vehicle[] array) {

        Random rand = new Random();

        //generates random number between 1 and 5 (random number of vehicles)

        int carNum = rand.nextInt(4) + 1;

        for (int i = 0; i <= carNum; i++) {

            //generates a random number between 1 and 3 (used for giving each vehicle a type)

            int vehicleTypeNum = rand.nextInt(3) + 1;

            switch (vehicleTypeNum) {

                case 1:

                    array[i] = new Car();
                    break;

                case 2:

                    array[i] = new Truck();
                    break;

                case 3:

                    array[i] = new Semi();
                    break;
            }
        }
    }

    //getters for the instance variables

    public Vehicle[] getNorth() {
        return this.north;
    }

    public Vehicle[] getSouth() {
        return this.south;
    }

    public Vehicle[] getEast() {
        return this.east;
    }

    public Vehicle[] getWest() {
        return this.west;
    }

    public boolean getRunning() {
        return this.running;
    }


    public void printIntersection(Vehicle[] north, Vehicle[] south, Vehicle[] east, Vehicle[] west) {

        if (greenNS) {

            System.out.println("NS: GREEN");
            System.out.println("EW: RED");

        } else if (greenEW) {

            System.out.println("NS: RED");
            System.out.println("EW: GREEN");
        }

        lettersToVehiclesVertical(north, 1);
        System.out.println("----------     ----------");
        lettersToVehiclesHorizontal(east, 1);
        System.out.print("    ");
        lettersToVehiclesHorizontal(west, 2);
        System.out.println();
        System.out.println("----------     ----------");
        lettersToVehiclesVertical(south, 2);

    }

    //method to build vertical lanes

    public static void lettersToVehiclesVertical(Vehicle[] array, int direction) {

        if (direction == 1) {

            for (int i = array.length - 1; i >= 0; i--) {

                assignVehicleLetter(array, i);
            }
        } else if (direction == 2) {

            for (int i = 0; i < array.length; i++) {

                assignVehicleLetter(array, i);
            }
        }
    }

    //assigns a letter to each vehicle in the vertical lanes

    public static void assignVehicleLetter(Vehicle[] array, int i) {

        if (array[i] == null) {

            System.out.println("          |   |");

        } else if (array[i].getTime() == 10) {

            System.out.println("          | C |");

        } else if (array[i].getTime() == 15) {

            System.out.println("          | T |");

        } else if (array[i].getTime() == 20) {

            System.out.println("          | S |");

        } else if (array[i].getTime() == 5) {

            System.out.println("          | E |");
        }
    }

    //method to build horizontal lanes

    public static void lettersToVehiclesHorizontal(Vehicle[] array, int direction) {

        if (direction == 1) {

            for (int i = array.length - 1; i >= 0; i--) {

                assignVehicleLetter2(array, i);
            }
        } else if (direction == 2) {

            for (int i = 0; i < array.length; i++) {

                assignVehicleLetter2(array, i);
            }
        }
    }

    //assigns a letter to each vehicle in the horizontal lanes

    public static void assignVehicleLetter2(Vehicle[] array, int i) {

        if (array[i] == null) {

            System.out.print("  ");

        } else if (array[i].getTime() == 10) {

            System.out.print(" C");

        } else if (array[i].getTime() == 15) {

            System.out.print(" T");

        } else if (array[i].getTime() == 20) {

            System.out.print(" S");

        } else if (array[i].getTime() == 5) {

            System.out.print(" E");
        }
    }

    //method to add a vehicle to a direction indicated by a parameter, checks for emergency

    public void addVehicle(Vehicle[] array, boolean emergency) {

        Random rand = new Random();
        int vehicleTypeNum = rand.nextInt(3) + 1;

        if (!emergency) {

            switch (vehicleTypeNum) {

                case 1:

                    for (int i = 0; i < array.length; i++) {

                        if (array[i] == null) {

                            array[i] = new Car();

                            break;
                        }
                    }

                    break;

                case 2:
                    for (int i = 0; i < array.length; i++) {

                        if (array[i] == null) {

                            array[i] = new Truck();

                            break;
                        }
                    }

                    break;

                case 3:
                    for (int i = 0; i < array.length; i++) {

                        if (array[i] == null) {

                            array[i] = new Semi();

                            break;
                        }

                        break;
                    }
            }

        } else {

            for (int i = 0; i < array.length; i++) {

                if (array[i] == null) {

                    array[i] = new Emergency();

                    break;
                }
            }
        }
    }

    //returns true if there are no vehicles left in any direction

    public boolean checkCars() {

        boolean noVehiclesLeft = true;

        for (int i = 0; i < north.length; i++) {

            if (north[0] != null) {

                noVehiclesLeft = false;
            }
        }

        for (int i = 0; i < south.length; i++) {

            if (south[0] != null) {

                noVehiclesLeft = false;
            }
        }

        for (int i = 0; i < east.length; i++) {

            if (east[0] != null) {

                noVehiclesLeft = false;
            }
        }

        for (int i = 0; i < west.length; i++) {

            if (west[0] != null) {

                noVehiclesLeft = false;
            }
        }

        return noVehiclesLeft;
    }

    //returns true if there are any emergency vehicles left in any direction

    public boolean checkEmergency() {

        for (int i = 0; i < north.length; i++) {

            if (north[i] != null && north[i].getTime() == 5) {

                return true;
            }
        }

        for (int i = 0; i < south.length; i++) {

            if (south[i] != null && south[i].getTime() == 5) {

                return true;
            }
        }

        for (int i = 0; i < east.length; i++) {

            if (east[i] != null && east[i].getTime() == 5) {

                return true;
            }
        }

        for (int i = 0; i < west.length; i++) {

            if (west[i] != null && west[i].getTime() == 5) {

                return true;
            }
        }

        return false;
    }

    //returns true if there are any emergency vehicles left in the direction specified in the parameter

    public boolean laneHasEmergency(Vehicle[] array) {

        for (int i = 0; i < array.length; i++) {

            if (array[i].getTime() == 5) {

                return true;
            }
        }

        return false;
    }

    //finds which direction has an emergency vehicle in it
    //moves that vehicle to the front of that direction
    //turns light green for that direction

    public void emergencyPriority() {

        for (int i = 0; i < north.length; i++) {

            if (north[i] != null && north[i].getTime() == 5) {

                moveEmergency(north);

                greenNS = true;
                greenEW = false;
            }
        }

        for (int i = 0; i < south.length; i++) {

            if (south[i] != null && south[i].getTime() == 5) {

                moveEmergency(south);

                greenNS = true;
                greenEW = false;
            }
        }

        for (int i = 0; i < east.length; i++) {

            if (east[i] != null && east[i].getTime() == 5) {

                moveEmergency(east);

                greenEW = true;
                greenNS = false;
            }
        }

        for (int i = 0; i < west.length; i++) {

            if (west[i] != null && west[i].getTime() == 5) {

                moveEmergency(west);

                greenEW = true;
                greenNS = false;
            }
        }
    }

    //moves the emergency vehicle to the front

    public void moveEmergency(Vehicle[] array) {

        if (laneHasEmergency(array)) {

            int emergencyIndex = 0;

            for (int i = 0; i < array.length; i++) {

                if (array[i] != null && array[i].getTime() == 5) {

                    emergencyIndex = i;

                    break;
                }
            }

            //stores emergency vehicle

            Vehicle emergency = array[emergencyIndex];

            //shift all other vehicles backwards

            for (int i = emergencyIndex; i > 0; i--) {

                array[i] = array[i - 1];
            }

            //put stored emergency variable back in the front

            array[0] = emergency;
        }
    }

    //method that moves all the cars up when one goes away

    public void moveVehicles(Vehicle[] array) {

        //remove the vehicle in front

        if (array[0] != null) {

            array[0] = null;

        }

        //shift all the other vehicles forwards

        for (int i = 0; i < array.length - 1; i++) {

            array[i] = array[i + 1];
        }

        //delete that last vehicle

        array[array.length - 1] = null;
    }

    //“runs” movement during a green light

    public void runsMovement() {

        if (greenNS) {

            runsMovementEx(north, south);

            greenNS = false;
            greenEW = true;

            placeRandomVehicle();

            if (checkEmergency()) {
                emergencyPriority();
            }


        } else if (greenEW) {

            runsMovementEx(east, west);

            greenNS = true;
            greenEW = false;

            placeRandomVehicle();

            if (checkEmergency()) {
                emergencyPriority();
            }
        }
    }

    //if there are any remaining vehicles in the intersection after the simulation runs,

    public void placeRandomVehicle() {

        if (checkCars()) {

            running = false;

        } else {

            boolean emergency;

            Random rand = new Random();
            int directionNum = rand.nextInt(4) + 1;

            Random coinFlip = new Random();
            int flip = coinFlip.nextInt(2) + 1;

            if (flip == 1) {

                emergency = false;

            } else {

                emergency = true;
            }

            switch (directionNum) {

                case 1:

                    addVehicle(north, emergency);
                    break;

                case 2:

                    addVehicle(south, emergency);
                    break;

                case 3:

                    addVehicle(east, emergency);
                    break;

                case 4:

                    addVehicle(west, emergency);
                    break;

            }
        }
    }

    //this takes care of the time and then moves the vehicles as necessary

    public void runsMovementEx(Vehicle[] array1, Vehicle[] array2) {

        int timeLeft = GREEN_TIME;

        while (timeLeft > 0) {

            if (array1[0] == null && array2[0] == null) {

                timeLeft = 0;

            } else if ((array1[0] != null && array1[0].getTime() < timeLeft) && (array2[0] != null && array2[0].getTime() < timeLeft)) {

                if (array1[0].getTime() >= array2[0].getTime()) {

                    timeLeft -= array1[0].getTime();

                } else {

                    timeLeft -= array2[0].getTime();
                }

                moveVehicles(array1);
                moveVehicles(array2);

            } else if (array1[0] != null && array1[0].getTime() < timeLeft) {

                timeLeft -= array1[0].getTime();
                moveVehicles(array1);

            } else if (array2[0] != null && array2[0].getTime() < timeLeft) {

                timeLeft -= array2[0].getTime();
                moveVehicles(array2);

            } else {

                timeLeft = 0;
            }
        }
    }
}