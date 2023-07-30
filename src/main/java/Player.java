import static java.lang.Math.round;

public class Player
{
    // Handles Player Stats, to and from json, adding, etc.
    private int MMR;
    private String username;
    private boolean support;
    private boolean tank;
    private boolean dps;
    private int wins;
    private int losses;
    private int mapWins;
    private int mapDraws;
    private int mapLosses;

    public Player(){
        // Input will be the JSON Data, save to the variables in class.
    }

    public int getMMR(){
        return MMR;
    }

    public void addMMR(int value){
        MMR += value;
    }

    public void removeMMR(int value){
        MMR -= value;
    }

    public void addWin(){
        wins++;
    }

    public int getWins(){
        return wins;
    }

    public void addLoss(){
        losses++;
    }

    public int getLosses(){
        return losses;
    }

    public void addMapWins(int amount){
        mapWins += amount;
    }

    public int getMapWins(){
        return mapWins;
    }

    public void addMapDraws(int amount){
        mapDraws += amount;
    }

    public int getMapDraws(){
        return mapDraws;
    }

    public void addMapLosses(int amount){
        mapLosses += amount;
    }

    public int getMapLosses(){
        return mapLosses;
    }

    /*
    Very fucky way of getting 1d.p
     */
    public float getWinRate(){
        return (float) (round((float) wins /(wins+losses))*100)/10;
    }

    /*
    Very fucky way of getting 1d.p
     */
    public float getMapWinRate(){
        return (float) (round((float) mapWins / (mapWins + mapLosses)) * 1000) /10;
    }

    public boolean isSupport(){
        return support;
    }

    public boolean isTank(){
        return tank;
    }

    public boolean isDps(){
        return dps;
    }

    public String exportDataToJson(){
        // WIP Used when saving data back to json.
        return "WIP";
    }


}
