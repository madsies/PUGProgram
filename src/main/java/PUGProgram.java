
class PUGProgram {

    private WindowController window;
    private AppController controller;
    private boolean active = true;

    public static void main(String[] args) {
        new PUGProgram();

    }
    PUGProgram(){
        window = new WindowController();
        controller = new AppController();

        controller.createPlayer("JohnnyG", 9001, true, false, false);
        controller.createPlayer("super", 6969, false, true, true);

        mainLoop();
        shutDown();
    }

    private void mainLoop(){
        while (active){
            window.update();
            active = window.isActive();
        }
    }

    private void shutDown(){
        controller.savePlayers();
        System.out.println("Shutting down... ");
    }



}