import java.lang.reflect.Array;
import java.util.ArrayList;

public class MatchData {
    /*
    Similar to the "Player" Class, will hold live data of match history, used when looking
    at previous matches in the app
     */
    ArrayList<String> maps;
    ArrayList<String> lobby;
    ArrayList<String> team1;
    ArrayList<String> team2;
    int winner;
    int teamOneScore;
    int teamTwoScore;
    int bestOf; // i.e. bo3, bo5, bo7??
    int mapsPlayed;

    public MatchData(String Map, int teamOneScore, int teamTwoScore, int bestOf){

    }
}
