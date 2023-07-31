
class PUGProgram {

    private final WindowController window;
    private final AppController controller;
    private boolean active = true;

    public static void main(String[] args) {
        new PUGProgram();

    }

    /*
    Startup logic, before window etc.
     */
    PUGProgram(){
        // All controller functions here
        controller = new AppController();

        controller.createPlayer("JohnnyG", 9001, true, false, false);
        controller.createPlayer("super", 6969, false, true, true);

        // All window functions here
        window = new WindowController();

        System.out.println("Startup Completed...");
        mainLoop();
        shutDown();
    }

    /*
    Updated constantly, checks for values etc.
     */
    private void mainLoop(){
        while (active){
            // Controller Functions

            //Window Functions
            window.update();


            active = window.isActive();
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