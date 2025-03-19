import java.util.List;

abstract class CabBookingServices {
    abstract void bookTaxi(List<CabBO> availableTaxis, CabPassengerBO passenger);
}