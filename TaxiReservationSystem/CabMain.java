import java.util.List;
import java.util.Scanner;

public class CabMain {

    static void book(List<CabBO> taxis) {
        Scanner sc = new Scanner(System.in);
        int n = taxis.size();
        if (n < 1) {
            System.out.println("Invalid cab counts");
            return;
        }
        char minLocation = 'A';
        char maxLocation = 'E';

        while (true) {
            try {
                System.out.println("Enter Pick up point (Between " + minLocation + " and " + maxLocation + "):");
                char pickUpPoint = sc.next().toUpperCase().charAt(0);
                System.out.println("Enter Drop point (Between " + minLocation + " and " + maxLocation + "):");
                char dropPoint = sc.next().toUpperCase().charAt(0);
                if (pickUpPoint == dropPoint) {
                    System.out.println("Pick up and drop location are same only");
                    return;
                }
                if (pickUpPoint < minLocation || pickUpPoint > maxLocation ||
                        dropPoint < minLocation || dropPoint > maxLocation) {
                    System.out.println(
                            "Invalid locations! Please enter values between " + minLocation + " and " + maxLocation);
                    continue;
                }
                System.out.println("Enter Pick up time:");
                int pickUpTime = sc.nextInt();
                CabPassengerBO passenger = new CabPassengerBO(pickUpPoint, dropPoint, pickUpTime);
                List<CabBO> freeTaxis = CabBooking.getFreeTaxis(taxis, passenger);
                CabBookingServices taxiBooking = new CabBooking();
                taxiBooking.bookTaxi(freeTaxis, passenger);
                break;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of taxis to create:");
        List<CabBO> createdTaxis = CabBooking.createTaxis(sc.nextInt());
        boolean loop = true;
        while (loop) {
            System.out.println("1.Book");
            System.out.println("2.View Cabs");
            System.out.println("3.Exit");
            System.out.println("Select the choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    book(createdTaxis);
                    break;
                case 2:
                    for (CabBO taxi : createdTaxis) {
                        taxi.printTaxiBasicInfo();
                    }
                    for (CabBO taxi : createdTaxis) {
                        taxi.fetchTaxiInfo();
                    }
                    break;
                case 3:
                    loop = false;
                    System.out.println("See you in next ride bye....");
                    break;
                default:
                    System.out.println("Invalid choice, Please select correct choice.");
                    break;
            }
        }
    }
}
