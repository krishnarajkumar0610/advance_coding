abstract class Booking {
    abstract void book(PassengerBO passenger, int berthPosition, String givenBerth);

    abstract void bookInWaitingList(PassengerBO passenger, int berthPosition, String givenBerth);

    abstract void bookInRACList(PassengerBO passenger, int berthPosition, String givenBerth);

    abstract void cancel(int passengerID);

    abstract void fetchAvailableTickets();

    abstract void fetchBookedTickets();
}