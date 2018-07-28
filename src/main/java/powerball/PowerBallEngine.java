package powerball;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PowerBallEngine {
    private Integer[] winningBalls = new Integer[6];
    private Map<Long, Integer[]> registeredTickets = new HashMap<>();
    private Map<Integer, ArrayList<Long>> winners = new HashMap<>();

    /**
     * @return the array of winning balls.
     */
    public Integer[] getWinningBalls() {
        return winningBalls;
    }

    /**
     * @return the Map of registered tickets.
     */
    public Map<Long, Integer[]> getRegisteredTickets() {
        return registeredTickets;
    }

    /**
     * @return the Map of winner IDs.
     */
    public Map<Integer, ArrayList<Long>> getWinners() {
        return winners;
    }

    /**
     * Clears the registered tickets.
     */
    public void clearTickets() {
        registeredTickets = new HashMap<>();
    }

    /**
     * Adds tickets to the collection of this class.
     *
     * @param ID     the ID of the player.
     * @param ticket the array with size 6, in which first 5 numbers are white balls, last number is the power ball.
     * @throws IllegalArgumentException if the arguments numbers are not within the game rules.
     */
    public void registerTicket(Long ID, Integer[] ticket) throws IllegalArgumentException {
        if (ticket.length != 6) {
            throw new IllegalArgumentException("Number of balls is not 6");
        }
        for (int i = 0; i < 5; i++) {
            if (ticket[i] < 1 | ticket[i] > 69) {
                throw new IllegalArgumentException("Illegal number on ball");
            }
        }
        if (ticket[5] < 1 | ticket[5] > 26) {
            throw new IllegalArgumentException("Illegal number on ball");
        }

        registeredTickets.put(ID, ticket);
    }

    @Test
    public void randomBallsGenerationTest() {
        Integer[] randomBalls = generateRandomBalls(); //test the randomBalls generation
        for (int i = 0; i < randomBalls.length; i++) {
            System.out.print(randomBalls[i] + " ");
        }
    }

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
     * Generates draw and determines winners.
     */
    public void play() {
        winningBalls = generateRandomBalls();
        determineWinners();
    }

    /**
     * Determines winners. Fills the Map of the winners. In the above Map the
     * key is the winning combination, and the value is the ArrayList
     * of the winner IDs.
     */
    private void determineWinners() {

        // Populates winners Map with empty values for each winning combination
        for (int combination = 3; combination < 16; combination++) {
            switch (combination) {
                case 3: // (3 white balls + 0 power balls)
                case 4: // (4 white balls + 0 power balls)
                case 5: // (5 white balls + 0 power balls)
                case 10: // (0 white balls + 1 power ball)
                case 11: // (1 white balls + 1 power ball)
                case 12: // (2 white balls + 1 power ball)
                case 13: // (3 white balls + 1 power ball)
                case 14: // (4 white balls + 1 power ball)
                case 15: // (5 white balls + 1 power ball)
                    winners.put(combination, new ArrayList<Long>());
                    break;
            }
        }

        for (Map.Entry<Long, Integer[]> entry : registeredTickets.entrySet()) {
            int whiteBallsMatch = 0;
            int powerBallMatch = 0;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (winningBalls[i].equals(entry.getValue()[j])) {
                        whiteBallsMatch++;
                    }
                }
            }

            if (winningBalls[5].equals(entry.getValue()[5])) {
                powerBallMatch = 1;
            }

            int combination = powerBallMatch * 10 + whiteBallsMatch;

            switch (combination) {
                case 3: //(3 white balls + 0 power balls)
                case 4: //(4 white balls + 0 power balls)
                case 5: //(5 white balls + 0 power balls)
                case 10: //(0 white balls + 1 power ball)
                case 11: //(1 white balls + 1 power ball)
                case 12: //(2 white balls + 1 power ball)
                case 13: //(3 white balls + 1 power ball)
                case 14: //(4 white balls + 1 power ball)
                case 15: //(5 white balls + 1 power ball)
                    ArrayList<Long> IDs = winners.get(combination);
                    IDs.add(entry.getKey());
                    winners.put(combination, IDs);
                    break;
            }
        }
    }
}
