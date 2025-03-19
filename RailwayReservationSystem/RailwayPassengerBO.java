public class

RailwayPassengerBO {
    static int id = 1;
    int passengerID;
    String passengerName;
    int passengerAge;
    String preferedBerth;
    String allotedBerth;
    int seatNumber;

    RailwayPassengerBO(String name, int age, String berth) {
        passengerID = id++;
        passengerName = name;
        passengerAge = age;
        preferedBerth = berth;
        seatNumber = -1;
        allotedBerth = "";
    }
}