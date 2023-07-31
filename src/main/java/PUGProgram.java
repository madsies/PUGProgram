import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

class PUGProgram {

    private WindowController window;
    private AppController controller;
    private boolean active = true;
    private UUID matchUUID;
    private Random test;
    ArrayList<Team> teams; // test vari
    private int games = 0;
    public static void main(String[] args) {
        new PUGProgram();
    }

    /*
    Startup logic, before window etc.
     */
    PUGProgram() {
        // All controller functions here
        controller = new AppController();
        test = new Random();

        teams = controller.createDummyTeams();
        games = 0;

        // All window functions here
        window = new WindowController(controller);

        System.out.println("Startup Completed...");

        active = true;
        mainLoop();
        shutDown();
    }

    /*
    Updated constantly, checks for values etc.
     */
    private void mainLoop() {
        while (active){
            // Controller Functions

            // Window Functions
            window.update();

            //test code
            if (games < 100 && matchUUID == null) {
                matchUUID = controller.startMatch(teams.get(0), teams.get(1), 7);
            }

            if(matchUUID != null) {
                if (!controller.nextMap(matchUUID, test.nextInt(0, 3), "wip")) {
                    controller.endMatch(matchUUID);
                    games++;
                    matchUUID = null;
                }
            }

            // end of test code

            active = window.isActive();
            try{
                // Constant updates with no wait causes bad, approx 60Hz
                TimeUnit.MILLISECONDS.sleep(16);
            }
            catch (InterruptedException e){
                System.out.println("how the hell");
            }
        }
    }

    /*
    Code to be run at end of program, mostly saving data, etc.
     */
    private void shutDown(){
        controller.savePlayers();
        System.out.println("Shutting down... ");
    }

}