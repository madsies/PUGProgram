import org.json.simple.JSONObject;

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

    public Player(String un, int mmr, boolean sp, boolean tk, boolean DPS, int w, int l, int mw, int md, int ml){
        // Input will be the JSON Data, save to the variables in class.
        username = un;
        MMR = mmr;
        support = sp;
        tank = tk;
        dps = DPS;
        wins = w;
        losses = l;
        mapWins = mw;
        mapDraws = md;
        mapLosses = ml;
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

    public void toggleSupport(){
        support = !support;
    }

    public boolean isTank(){
        return tank;
    }

    public void toggleTank(){
        tank = !tank;
    }

    public boolean isDps(){
        return dps;
    }

    public void toggleDps(){
        dps = !dps;
    }

    public String exportDataToJson(){
        JSONObject obj = new JSONObject();
        JSONObject statsObj = new JSONObject();
        obj.put(username, statsObj);
        statsObj.put("mmr", MMR);
        statsObj.put("support", support);
        statsObj.put("tank", tank);
        statsObj.put("dps", dps);
        statsObj.put("wins", wins);
        statsObj.put("losses", losses);
        statsObj.put("mapwins", mapWins);
        statsObj.put("mapdraws", mapDraws);
        statsObj.put("maplosses", mapLosses);

        return obj.toJSONString();
    }

    public boolean validRoles(){
        // Check if a player has at least a single role chosen.
        return support || tank || dps;
    }


}
