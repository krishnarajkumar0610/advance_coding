import java.util.ArrayList;
import java.util.List;

public class CabBO {
    static int id = 0;
    int taxiID;
    boolean isBooked;
    int totalEarnings;
    char currentLocation;
    int freeTime;
    List<String> tripDetails;

    CabBO() {
        taxiID = ++id;
        isBooked = false;
        totalEarnings = 0;
        currentLocation = 'A';
        freeTime = 6;
        tripDetails = new ArrayList<String>();
    }

    public void setDetails(boolean booked, char currentSpot, int freeTime, int totalEarnings, String tripDetail) {
        this.isBooked = booked;
        this.currentLocation = currentSpot;
        this.freeTime = freeTime;
        this.totalEarnings = totalEarnings;
        this.tripDetails.add(tripDetail);
    }

    void fetchTaxiInfo() {
        System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
        for (String trip : tripDetails) {
            System.out.println(this.taxiID + "          " + trip);
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    void printTaxiBasicInfo() {
        System.out.println("Taxi - " + this.taxiID + " Total Earnings - " + this.totalEarnings);
    }
}
