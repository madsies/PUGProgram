import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.*;

public class Match {
    int matchWinner;
    Team teamOne;
    Team teamTwo;
    ArrayList<Player> lobby;
    int teamOneAvg;
    int teamTwoAvg;
    HashMap<String, Integer> mapsPlayed;
    int teamOneScore = 0;
    int teamTwoScore = 0;
    int bestOf;
    int currentMap;
    HashMap<String, String> teamOneRoles = new HashMap<>();
    HashMap<String, String> teamTwoRoles = new HashMap<>();
    String map;

    // TODO: Data Struct and function that links maps and who won them, for data later, im too lazy rn.


    public Match(Team team1,Team team2, HashMap<String, Player> players, int bo){
        teamOne = team1;
        teamTwo = team2;

        lobby = team1.getFullTeam();
        lobby.addAll(team2.getFullTeam());

        bestOf = bo;
        currentMap = 1;

        mapsPlayed = new HashMap<>(){{
            for (Player p : lobby) {
                put(p.getName(), 0);
            }
        }};

        calculateMMRAverage();
    }

    /*
    Active Players on each team
     */
    public void setRoles(int team, HashMap<String, String> roles){
        if (team == 1) teamOneRoles = roles;
        if (team == 2) teamTwoRoles = roles;
    }

    /*
    Increments Score
    */
    public void mapFinished(int winner){
        if (winner == 1){
            teamOneScore++;
            for (Player p : teamOne.getActiveTeam()){
                p.addMapWins(1);
            }
            for (Player p : teamTwo.getActiveTeam()){
                p.addMapLosses(1);
            }
        }
        else if (winner == 2){
            teamTwoScore++;
            for (Player p : teamTwo.getActiveTeam()){
                p.addMapWins(1);
            }
            for (Player p : teamOne.getActiveTeam()){
                p.addMapLosses(1);
            }

        }
        else if (winner == 0){
            for (Player p : lobby){
                p.addMapDraws(1);
            }
        }
        else{
            System.out.println("ERROR: false winner");
        }

        // Recording stuff for file bla bla, map played, winner, who played
    }

    /*
    Starts new map and records who is playing for MMR calc
    */

    public void startNewMap(String nextMap){
        currentMap++;
        map = nextMap;

        // Add 1 to maps played
        for (Player p : teamOne.getActiveTeam()){
            mapsPlayed.put(p.getName(), mapsPlayed.get(p.getName())+1);
        }
        for (Player p : teamTwo.getActiveTeam()){
            mapsPlayed.put(p.getName(), mapsPlayed.get(p.getName())+1);
        }

    }

    public boolean isGameOver(){
        return ((currentMap >= bestOf && teamOneScore != teamTwoScore) || teamOneScore > ceil((double) bestOf /2) || teamTwoScore > ceil((double) bestOf /2));
    }

    public void calculateMMRAverage(){
        int temp = 0;
        for(Player p : teamOne.getFullTeam()){
            temp += p.getMMR();
        }
        teamOneAvg = temp / teamOne.getFullTeam().size();

        temp = 0;

        for(Player p : teamTwo.getFullTeam()){
            temp += p.getMMR();
        }
        teamTwoAvg = temp / teamTwo.getFullTeam().size();
    }

    public int getWinner(){
        if (teamTwoScore-teamOneScore > 0) return 2;
        else return 1;
    }

    public void calculateMMRChange(){
        int val = 25; // Base MMR value is 25, changed by factors.
        int resultScalar = 0;
        int diff = (teamOneAvg - teamTwoAvg)/25;
        if (diff > 10) diff = 10;
        if (diff < -10) diff = -10;

        // Map diff, +1 = 0MMR, +2 = 5MMR, +3 (if BO5) = 10MMR
        int mapDiff = (abs(teamTwoScore-teamOneScore)-1)*5;

        // Need to handle match etc. etc. may put this in seperate match class, unsure.
        for (Player player : lobby) {
            val = 25;

            // +- depending on win or losses

            if ((matchWinner == 1 && teamOne.getFullTeam().contains(player)) || (matchWinner == 2 && teamTwo.getFullTeam().contains(player))) {
                resultScalar = 1;
                player.addWin();
            } else {
                resultScalar = -1;
                player.addLoss();
            }

            val += mapDiff;

            // MMR Diff, every enemy +25 avg is +1 more MMR, up to +-10MMR

            if(matchWinner == 1 && diff > 0 || matchWinner == 2 && diff < 0){
                val -= diff;
            }
            else{
                val += diff;
            }

            // Player MMR Diff, individual MMR compared to match average +-5
            // add later

            // Map played Scalar, 2/3 maps == 66% MMR gain/loss etc. etc.
            val = round(val * ((float) mapsPlayed.get(player.getName()) /(currentMap)));

            player.addMMR(val*resultScalar);
        }

        // won't actually return will just change players MMR Stat eventually.

    }

    public void reportMatch(){
        // Code will update matches json file with match data.
    }

    public void endGame(){
        // All logic for ending game
        matchWinner = getWinner();
        calculateMMRChange();
        reportMatch();
    }
}
