import StreetSim.TrafficLight;
import java.util.Scanner;

public class TrafficSimulation {

    public static void main(String[] args) {

        runSim();
    }

    public static void runSim() {

        TrafficLight myTrafficLight = new TrafficLight();
        Scanner scan = new Scanner(System.in);

        System.out.println();

        while (myTrafficLight.getRunning()) {

            myTrafficLight.runsMovement();

            myTrafficLight.printIntersection(myTrafficLight.getNorth(), myTrafficLight.getSouth(), myTrafficLight.getEast(), myTrafficLight.getWest());

            System.out.println();

            System.out.println("Please hit Enter to continue.");

            scan.nextLine();
        }
    }
}