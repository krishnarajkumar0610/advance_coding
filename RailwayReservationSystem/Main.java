import java.util.Scanner;

// Done until booking

public class Main {
    static void bookTicket(PassengerBO passenger) {
        if (TicketCounter.availableWaiting < 1) {
            System.out.println("Tickets not available to book...");
            return;
        }
        Booking ticketCounter = new TicketCounter();
        if ((passenger.preferedBerth.equals("U") && TicketCounter.availableUpperBerth > 0)
                || (passenger.preferedBerth.equals("L") && TicketCounter.availableLowerBerth > 0)
                || (passenger.preferedBerth.equals("M") && TicketCounter.availableMiddleBerth > 0)) {
            switch (passenger.preferedBerth) {
                case "U":
                    ticketCounter.book(passenger, TicketCounter.upperBerthPositions.get(0), "U");
                    TicketCounter.availableUpperBerth--;
                    TicketCounter.upperBerthPositions.remove(0);
                    break;
                case "L":
                    ticketCounter.book(passenger, TicketCounter.lowerBerthPositions.get(0), "L");
                    TicketCounter.availableLowerBerth--;
                    TicketCounter.lowerBerthPositions.remove(0);
                    break;

                case "M":
                    ticketCounter.book(passenger, TicketCounter.middleBerthPositions.get(0), "M");
                    TicketCounter.availableMiddleBerth--;
                    TicketCounter.middleBerthPositions.remove(0);
                    break;

                default:
                    System.err.println("Something went wrong....");
                    break;
            }
        } else if (TicketCounter.availableUpperBerth > 0) {
            passenger.seatNumber = TicketCounter.upperBerthPositions.get(0);
            ticketCounter.book(passenger, TicketCounter.upperBerthPositions.get(0), "U");
            TicketCounter.upperBerthPositions.remove(0);
            TicketCounter.availableUpperBerth--;
        } else if (TicketCounter.availableLowerBerth > 0) {
            passenger.seatNumber = TicketCounter.lowerBerthPositions.get(0);
            ticketCounter.book(passenger, TicketCounter.lowerBerthPositions.get(0), "L");
            TicketCounter.lowerBerthPositions.remove(0);
            TicketCounter.availableLowerBerth--;
        } else if (TicketCounter.availableMiddleBerth > 0) {
            passenger.seatNumber = TicketCounter.middleBerthPositions.get(0);
            ticketCounter.book(passenger, TicketCounter.middleBerthPositions.get(0), "M");
            TicketCounter.middleBerthPositions.remove(0);
            TicketCounter.availableMiddleBerth--;
        } else if (TicketCounter.availableRAC > 0) {
            passenger.seatNumber = TicketCounter.racListPositions.get(0);
            ticketCounter.bookInRACList(passenger, TicketCounter.racListPositions.get(0), "RAC");
        } else if (TicketCounter.availableWaiting > 0) {
            passenger.seatNumber = TicketCounter.waitingListPositions.get(0);
            ticketCounter.bookInWaitingList(passenger, TicketCounter.waitingListPositions.get(0), "WL");
        } else {
            System.err.println("Something went wrong....");
        }
    }

    static void cancelTicket(int id) {
        Booking ticketCounter = new TicketCounter();
        if (!TicketCounter.passengers.containsKey(id)) {
            System.out.println("Please enter registered ticket ID");
            return;
        }
        ticketCounter.cancel(id);

    }

    static void displayAvailableTickets() {
        Booking ticketCounter = new TicketCounter();
        ticketCounter.fetchAvailableTickets();
    }

    static void displayBookedTickets() {
        Booking ticketCounter = new TicketCounter();
        ticketCounter.fetchBookedTickets();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            boolean loop = true;
            while (loop) {

                System.out.println(
                        "\n1. Book Ticket\n2. Cancel Ticket\n3. Display Available Tickets\n4. Display Booked Tickets\n5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        try {
                            System.out.println("Enter passenger name:");
                            String name = sc.next().toUpperCase();
                            if (!name.matches("[a-zA-Z]+")) {
                                System.out.println("Invalid name try again...");
                                continue;
                            }
                            System.out.println("Enter passenger age:");
                            int age = sc.nextInt();

                            System.out.println("Enter preferred berth (U/L/M):");
                            String berth = sc.next().toUpperCase();
                            if (!berth.equals("U") && !berth.equals("L") && !berth.equals("M")) {
                                System.out.println("Invalid berth try again");
                                continue;
                            }
                            PassengerBO passenger = new PassengerBO(name, age, berth);
                            bookTicket(passenger);
                        } catch (Exception e) {
                            System.out.println("Invalid age try again");
                            sc.next();
                            continue;
                        }
                        break;

                    case 2:
                        try {
                            System.out.println("Enter Ticket ID to cancel:");
                            int ticketId = sc.nextInt();
                            cancelTicket(ticketId);
                        } catch (Exception e) {
                            System.err.println("Invalid ticket ID try again");
                            continue;
                        }
                        break;

                    case 3:
                        displayAvailableTickets();
                        break;

                    case 4:
                        displayBookedTickets();
                        break;

                    case 5:
                        System.out.println("Exiting the system...");
                        loop = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            sc.nextLine();
        }
        sc.close();
    }
}