import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private final boolean[] seats;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketBookingSystem(int numberOfSeats) {
        this.seats = new boolean[numberOfSeats];
    }

    public void bookSeat(String user, int seatNumber, boolean isVIP) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println(user + ": Invalid seat number!");
            return;
        }
        
        lock.lock();
        try {
            if (!seats[seatNumber - 1]) {
                seats[seatNumber - 1] = true;
                System.out.println(user + " booked seat " + seatNumber);
            } else {
                System.out.println(user + ": Seat " + seatNumber + " is already booked!");
            }
        } finally {
            lock.unlock();
        }
    }
}

class UserThread extends Thread {
    private final TicketBookingSystem system;
    private final String userName;
    private final int seatNumber;
    private final boolean isVIP;

    public UserThread(TicketBookingSystem system, String userName, int seatNumber, boolean isVIP) {
        this.system = system;
        this.userName = userName;
        this.seatNumber = seatNumber;
        this.isVIP = isVIP;
        if (isVIP) {
            setPriority(Thread.MAX_PRIORITY);
        } else {
            setPriority(Thread.NORM_PRIORITY);
        }
    }

    @Override
    public void run() {
        system.bookSeat(userName, seatNumber, isVIP);
    }
}
//lakshay uid 22BCS15481
public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);
        
        // Test Cases
        new UserThread(system, "Anish (VIP)", 1, true).start(); // Test Case 2
        new UserThread(system, "Bobby (Regular)", 2, false).start(); // Test Case 2
        new UserThread(system, "Charlie (VIP)", 3, true).start(); // Test Case 2
        new UserThread(system, "Bobby (Regular)", 4, false).start(); // Test Case 3
        new UserThread(system, "Anish (VIP)", 4, true).start(); // Test Case 3
        new UserThread(system, "Bobby (Regular)", 1, false).start(); // Test Case 4
        new UserThread(system, "Regular User", 3, false).start(); // Test Case 5
        new UserThread(system, "Invalid User", 0, false).start(); // Test Case 6
        new UserThread(system, "Invalid User", 6, false).start(); // Test Case 6
        
        // Test Case 7: Simultaneous Bookings
        for (int i = 0; i < 10; i++) {
            new UserThread(system, "User" + i, (i % 5) + 1, i % 2 == 0).start();
        }
    }
}
