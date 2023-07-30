import java.util.ArrayList;

public class MatchData {
    /*
    Similar to the "Player" Class, will hold live data of match history, used when looking
    at previous matches in the app
     */
    String map;
    ArrayList<String> lobby;
    ArrayList<String> team1;
    ArrayList<String> team2;
    boolean winner; // true = t1, false = t2
    int teamOneScore;
    int teamTwoScore;
    int bestOf; // i.e. bo3, bo5, bo7??
    int mapsPlayed;

    public MatchData(){

    }
}
