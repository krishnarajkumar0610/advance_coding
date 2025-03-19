import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class TicketCounter extends RailwayBookingServices {
    static int availableUpperBerth = 1;
    static int availableLowerBerth = 1;
    static int availableMiddleBerth = 1;
    static int availableWaiting = 1;
    static int availableRAC = 1;
    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList = new LinkedList<>();
    static ArrayList<RailwayPassengerBO> bookedPassengers = new ArrayList<>();
    static ArrayList<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));
    static ArrayList<Integer> racListPositions = new ArrayList<>(Arrays.asList(1));
    static ArrayList<Integer> upperBerthPositions = new ArrayList<>(Arrays.asList(1));
    static ArrayList<Integer> lowerBerthPositions = new ArrayList<>(Arrays.asList(1));
    static ArrayList<Integer> middleBerthPositions = new ArrayList<>(Arrays.asList(1));
    static HashMap<Integer, RailwayPassengerBO> passengers = new HashMap<>();

    @Override
    void book(RailwayPassengerBO passenger, int berthPosition, String givenBerth) {
        passenger.seatNumber = berthPosition;
        passenger.allotedBerth = givenBerth;
        passengers.put(passenger.passengerID, passenger);
        bookedPassengers.add(passenger);
        System.out.println("Passenger booked successfully...");

    }

    @Override
    void bookInWaitingList(RailwayPassengerBO passenger, int berthPosition, String givenBerth) {
        passenger.seatNumber = berthPosition;
        passenger.allotedBerth = givenBerth;
        passengers.put(passenger.passengerID, passenger);
        waitingList.add(passenger.passengerID);
        waitingListPositions.remove(0);
        availableWaiting--;
    }

    @Override
    void bookInRACList(RailwayPassengerBO passenger, int berthPosition, String givenBerth) {
        passenger.seatNumber = berthPosition;
        passenger.allotedBerth = givenBerth;
        passengers.put(passenger.passengerID, passenger);
        TicketCounter.racList.add(passenger.passengerID);
        TicketCounter.racListPositions.remove(0);
        TicketCounter.availableRAC--;
    }

    @Override
    void cancel(int passengerID) {
        RailwayPassengerBO cancelTicketPassenger = passengers.get(passengerID);
        if (cancelTicketPassenger.allotedBerth.equals("U")) {
            availableUpperBerth++;
            upperBerthPositions.add(cancelTicketPassenger.seatNumber);
            bookedPassengers.remove(cancelTicketPassenger);
            _moveRacToBookedPassenger();
        } else if (cancelTicketPassenger.allotedBerth.equals("L")) {
            availableLowerBerth++;
            lowerBerthPositions.add(cancelTicketPassenger.seatNumber);
            bookedPassengers.remove(cancelTicketPassenger);
            _moveRacToBookedPassenger();

        } else if (cancelTicketPassenger.allotedBerth.equals("M")) {
            availableMiddleBerth++;
            middleBerthPositions.add(cancelTicketPassenger.seatNumber);
            bookedPassengers.remove(cancelTicketPassenger);
            _moveRacToBookedPassenger();
        } else if (cancelTicketPassenger.allotedBerth.equals("RAC")) {
            racList.remove(passengerID);
            racListPositions.add(cancelTicketPassenger.seatNumber);
            availableRAC++;
            _moveWaitingListToRac();
        } else if (cancelTicketPassenger.allotedBerth.equals("WL")) {
            availableWaiting++;
            waitingList.remove(passengerID);
            waitingListPositions.add(cancelTicketPassenger.seatNumber);
        }
        passengers.remove(passengerID);
    }

    void _moveRacToBookedPassenger() {
        if (availableRAC < 1) {
            RailwayPassengerBO racPassenger = passengers.get(racList.poll());
            racListPositions.add(racPassenger.seatNumber);
            availableRAC++;
            RailwayMain.bookTicket(racPassenger);
            _moveWaitingListToRac();
        }
    }

    void _moveWaitingListToRac() {
        if (availableWaiting < 1) {
            RailwayPassengerBO wlPassenger = passengers.get(waitingList.poll());
            waitingListPositions.add(wlPassenger.seatNumber);
            availableWaiting++;
            bookInRACList(wlPassenger, racListPositions.get(0), "RAC");

        }
    }

    @Override
    void fetchAvailableTickets() {
        System.out.println("Available U:" + availableUpperBerth);
        System.out.println("Available L:" + availableLowerBerth);
        System.out.println("Available M:" + availableMiddleBerth);
        System.out.println("Available RAC:" + availableRAC);
        System.out.println("Available WL:" + availableWaiting);
        System.out.println("Available RAC LIST:" + racList);
        System.out.println("Available WL LIST:" + waitingList);
    }

    @Override
    void fetchBookedTickets() {
        if (bookedPassengers.isEmpty()) {
            System.out.println("No passengers booked ticket");
            return;
        }

        for (RailwayPassengerBO passengerBO : bookedPassengers) {
            System.out.println("Passenger ID:" + passengerBO.passengerID);
            System.out.println("Passenger Name:" + passengerBO.passengerName);
            System.out.println("Passenger Age:" + passengerBO.passengerAge);
            System.out.println("Passenger prefered berth:" + passengerBO.preferedBerth);
            System.out.println("Passenger allowed berth:" + passengerBO.allotedBerth);
            System.out.println("--------------------------------------------------------");
        }
    }

}
