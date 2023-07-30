import java.util.ArrayList;
import java.util.HashMap;

public class Match {
    boolean winner;
    ArrayList<String> teamOne;
    ArrayList<String> teamTwo;
    ArrayList<String> lobby;
    HashMap<String, Player> data;
    int teamOneAvg;
    int teamTwoAvg;


    public Match(ArrayList<String> team1, ArrayList<String> team2, HashMap<String, Player> players){
        teamOne = team1;
        teamTwo = team2;

        lobby = team1;
        lobby.addAll(team2);

        data = players;

        calculateMMRAverage();

    }

    public void calculateMMRAverage(){
        int temp = 0;
        for(String p : teamOne){
            temp += data.get(p).getMMR();
        }
        teamOneAvg = temp / teamOne.size();

        for(String p : teamTwo){
            temp += data.get(p).getMMR();
        }
        teamTwoAvg = temp / teamTwo.size();
    }

    public int calculateMMRChange(){
        int val = 25; // Base MMR value is 25, changed by factors.
        int resultScalar = 0;
        int playTimeScalar = 1;

        // Need to handle match etc. etc. may put this in seperate match class, unsure.
        for (String player : lobby) {

            // +- depending on win or losses

            if ((winner && teamOne.contains(player)) || (!winner && teamTwo.contains(player))) {
                resultScalar = 1;
            } else {
                resultScalar = -1;
            }

            // Map diff, +1 = 0MMR, +2 = 5MMR, +3 (if BO5) = 10MMR

            // MMR Diff, every enemy +25 avg is +1 more MMR, up to +-10MMR

            // Player MMR Diff, individual MMR compared to match average +-5

            //? Map played Scalar, 2/3 maps == 66% MMR gain/loss etc. etc.
        }

        // wont actually return will just change players MMR Stat eventually.
        return val*resultScalar*playTimeScalar;
    }

    public void reportMatch(){
        // Code will update matches json file with match data.
    }

    public void endGame(){
        // All logic for ending game
        calculateMMRChange();
        reportMatch();
    }
}
