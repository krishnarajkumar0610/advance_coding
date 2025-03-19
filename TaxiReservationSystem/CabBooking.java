import java.util.ArrayList;
import java.util.List;

public class CabBooking extends CabBookingServices {

    @Override
    void bookTaxi(List<CabBO> availableTaxis, CabPassengerBO passenger) {
        int minutes = Integer.MAX_VALUE;
        int distanceFromPickUpAndDrop = 0;
        int earnings = 0;
        int nextFreeTime = 0;
        char nextSpot = 'Z';
        CabBO bookedTaxi = null;
        String tripDetail = "";
        for (CabBO taxi : availableTaxis) {
            int distanceFromTaxiToCustomer = Math.abs((taxi.currentLocation - '0') - (passenger.pickUpPoint - '0'))
                    * 15;
            if (distanceFromTaxiToCustomer < minutes) {
                bookedTaxi = taxi;
                distanceFromPickUpAndDrop = Math.abs((passenger.pickUpPoint - '0') - (passenger.dropPoint - '0')) * 15;
                earnings = (distanceFromPickUpAndDrop - 5) * 10 + 100;
                int dropTime = passenger.pickUpTime + distanceFromPickUpAndDrop / 15;
                nextFreeTime = dropTime;
                nextSpot = passenger.dropPoint;
                tripDetail = taxi.taxiID + "               " + passenger.customerID + "          "
                        + passenger.pickUpPoint + "      "
                        + passenger.dropPoint + "       " + passenger.pickUpTime + "          " + dropTime
                        + "           " + earnings;

                minutes = distanceFromTaxiToCustomer;
            }
        }
        if (bookedTaxi != null) {
            bookedTaxi.setDetails(true, nextSpot, nextFreeTime, (bookedTaxi.totalEarnings + earnings), tripDetail);
            System.out.println("Taxi booked...............");
        } else {
            System.out.println("Taxi not available....");
        }
    }

    static List<CabBO> createTaxis(int n) {
        try {
            List<CabBO> createdTaxis = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                createdTaxis.add(new CabBO());
            }
            return createdTaxis;
        } catch (Exception e) {
            System.out.println("--------------------");
            System.err.println("Something went wrong while creating taxis");
            System.out.println("--------------------");
            return new ArrayList<>();
        }
    }

    static List<CabBO> getFreeTaxis(List<CabBO> taxis, CabPassengerBO passenger) {
        try {
            List<CabBO> freeTaxis = new ArrayList<>();
            for (CabBO taxi : taxis) {
                if (taxi.freeTime <= passenger.pickUpTime
                        && (Math.abs(
                                (taxi.currentLocation - '0') - (passenger.pickUpPoint - '0')) <= passenger.pickUpTime
                                        - taxi.freeTime)) {
                    freeTaxis.add(taxi);
                }
            }
            return freeTaxis;
        } catch (Exception e) {
            System.out.println("--------------------");
            System.err.println("Something went wrong while checking free taxis");
            System.out.println("--------------------");
            return new ArrayList<>();
        }
    }

}
