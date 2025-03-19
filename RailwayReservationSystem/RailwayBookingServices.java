abstract class RailwayBookingServices {
    abstract void book(RailwayPassengerBO passenger, int berthPosition, String givenBerth);

    abstract void bookInWaitingList(RailwayPassengerBO passenger, int berthPosition, String givenBerth);

    abstract void bookInRACList(RailwayPassengerBO passenger, int berthPosition, String givenBerth);

    abstract void cancel(int passengerID);

    abstract void fetchAvailableTickets();

    abstract void fetchBookedTickets();
}