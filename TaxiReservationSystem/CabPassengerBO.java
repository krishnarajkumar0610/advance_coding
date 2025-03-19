public class CabPassengerBO {
    static int id = 0;
    int customerID;
    char pickUpPoint;
    char dropPoint;
    int pickUpTime;

    CabPassengerBO(char pickUp, char drop, int time) {
        customerID = ++id;
        pickUpPoint = pickUp;
        dropPoint = drop;
        pickUpTime = time;
    }
}
