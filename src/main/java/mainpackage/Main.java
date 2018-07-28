package mainpackage;

import powerball.PowerBallEngine;
import powerball.PowerBallTester;

public class Main {
    public static void main(String[] args) {

        PowerBallEngine powerBallEngine = new PowerBallEngine();
        PowerBallTester powerBallTester = new PowerBallTester();

        powerBallTester.registerRandomTickets(powerBallEngine, 1000000);

        powerBallEngine.play();

        powerBallTester.test(powerBallEngine);

    }
}
