public class

PassengerBO {
    static int id = 1;
    int passengerID;
    String passengerName;
    int passengerAge;
    String preferedBerth;
    String allotedBerth;
    int seatNumber;

    PassengerBO(String name, int age, String berth) {
        passengerID = id++;
        passengerName = name;
        passengerAge = age;
        preferedBerth = berth;
        seatNumber = -1;
        allotedBerth = "";
    }
}