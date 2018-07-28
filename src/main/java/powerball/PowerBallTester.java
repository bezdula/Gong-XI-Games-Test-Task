package powerball;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class PowerBallTester {

    /**
     * Generates array of the random numbers for 5 white balls and 1 power ball.
     *
     * @return the array of numbers with size 6, for 5 white balls and 1 power ball.
     */
    private Integer[] generateRandomBalls() {
        Integer[] randomBalls = new Integer[6];

        ArrayList<Integer> whiteBalls = new ArrayList<>();
        for (int i = 1; i < 70; i++) {
            whiteBalls.add(i);
        }
        for (int i = 0; i < 5; i++) {
            int a = ((int) Math.ceil(Math.random() * (69 - i))) - 1;
            if (a == -1) { // Since the Math.random function can return 0.0
                a = 0;
            }
            randomBalls[i] = whiteBalls.get(a);
            whiteBalls.remove(a);
        }

        int a = (int) Math.ceil(Math.random() * 26);
        randomBalls[5] = (a == 0 ? 1 : a); // Since the Math.random function can return 0.0

        return randomBalls;
    }

    /**
     * Register tickets for the provided PowerBallEngine instance. Tickets a randomly generated.
     *
     * @param powerBallEngine the PowerBallEngine instance to register tickets.
     * @param numberOfTickets the number of tickets to be registered.
     */
    public void registerRandomTickets(PowerBallEngine powerBallEngine, long numberOfTickets) {
        for (long j = 0; j < numberOfTickets; j++) {
            powerBallEngine.registerTicket(j, generateRandomBalls());
        }
    }

    /**
     * Tests the provided PowerBallEngine instance. Collect winners and
     * compares the outcome with theoretical probabilities.
     *
     * @param powerBallEngine the PowerBallEngine instance to be tested.
     */
    public void test(PowerBallEngine powerBallEngine) {

        powerBallEngine.getWinners();

        long numberOfTickets = powerBallEngine.getRegisteredTickets().size();

        for (Map.Entry<Integer, ArrayList<Long>> winner : powerBallEngine.getWinners().entrySet()) {
            switch (winner.getKey()) {
                case 3: // (3 white balls + 0 power balls)
                    System.out.println("Number of winners for " + winner.getKey() % 10 + " White Balls and "
                            + winner.getKey() / 10 + " Power Balls is: " + winner.getValue().size());
                    System.out.printf("Total outcome: %1$,28.10f \nTheoretical probability: %2$,18.10f\n",
                            (float) winner.getValue().size() / (float) numberOfTickets, 1f / 579.76f);
                    break;
                case 4: // (4 white balls + 0 power balls)
                    System.out.println("Number of winners for " + winner.getKey() % 10 + " White Balls and "
                            + winner.getKey() / 10 + " Power Balls is: " + winner.getValue().size());
                    System.out.printf("Total outcome: %1$,28.10f \nTheoretical probability: %2$,18.10f\n",
                            (float) winner.getValue().size() / (float) numberOfTickets, 1f / 36525.17f);
                    break;
                case 5: // (5 white balls + 0 power balls)
                    System.out.println("Number of winners for " + winner.getKey() % 10 + " White Balls and "
                            + winner.getKey() / 10 + " Power Balls is: " + winner.getValue().size());
                    System.out.printf("Total outcome: %1$,28.10f \nTheoretical probability: %2$,18.10f\n",
                            (float) winner.getValue().size() / (float) numberOfTickets, 1f / 11688053.52f);
                    break;
                case 10: // (0 white balls + 1 power ball)
                    System.out.println("Number of winners for " + winner.getKey() % 10 + " White Balls and "
                            + winner.getKey() / 10 + " Power Balls is: " + winner.getValue().size());
                    System.out.printf("Total outcome: %1$,28.10f \nTheoretical probability: %2$,18.10f\n",
                            (float) winner.getValue().size() / (float) numberOfTickets, 1f / 38.32f);
                    break;
                case 11: // (1 white balls + 1 power ball)
                    System.out.println("Number of winners for " + winner.getKey() % 10 + " White Balls and "
                            + winner.getKey() / 10 + " Power Balls is: " + winner.getValue().size());
                    System.out.printf("Total outcome: %1$,28.10f \nTheoretical probability: %2$,18.10f\n",
                            (float) winner.getValue().size() / (float) numberOfTickets, 1f / 91.98f);
                    break;
                case 12: // (2 white balls + 1 power ball)
                    System.out.println("Number of winners for " + winner.getKey() % 10 + " White Balls and "
                            + winner.getKey() / 10 + " Power Balls is: " + winner.getValue().size());
                    System.out.printf("Total outcome: %1$,28.10f \nTheoretical probability: %2$,18.10f\n",
                            (float) winner.getValue().size() / (float) numberOfTickets, 1f / 701.33f);
                    break;
                case 13: // (3 white balls + 1 power ball)
                    System.out.println("Number of winners for " + winner.getKey() % 10 + " White Balls and "
                            + winner.getKey() / 10 + " Power Balls is: " + winner.getValue().size());
                    System.out.printf("Total outcome: %1$,28.10f \nTheoretical probability: %2$,18.10f\n",
                            (float) winner.getValue().size() / (float) numberOfTickets, 1f / 14494.11f);
                    break;
                case 14: // (4 white balls + 1 power ball)
                    System.out.println("Number of winners for " + winner.getKey() % 10 + " White Balls and "
                            + winner.getKey() / 10 + " Power Balls is: " + winner.getValue().size());
                    System.out.printf("Total outcome: %1$,28.10f \nTheoretical probability: %2$,18.10f\n",
                            (float) winner.getValue().size() / (float) numberOfTickets, 1f / 913129.18f);
                    break;
                case 15: // (5 white balls + 1 power ball)
                    System.out.println("Number of winners for " + winner.getKey() % 10 + " White Balls and "
                            + winner.getKey() / 10 + " Power Balls is: " + winner.getValue().size());
                    System.out.printf("Total outcome: %1$,28.10f \nTheoretical probability: %2$,18.10f\n",
                            (float) winner.getValue().size() / (float) numberOfTickets, 1f / 292201338f);
                    break;
            }
        }
    }
}
