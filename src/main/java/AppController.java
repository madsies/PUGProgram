import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AppController {

    /*
    Creates JSON (If nonexistent), and initialises necessary data into player array etc.
     */

    HashMap<String, Player> players = new HashMap<>();
    HashMap<UUID, Match> matchPool = new HashMap<>();

    public AppController() {

        /*

            Creates Data JSON Files if non-existent.

         */

        try (FileReader read = new FileReader("players.json")){
            //idk do something, load data etc.
            System.out.println("Loading players...");
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(read);
            JSONArray data = (JSONArray) obj;

            // Iter through data in array, make player object, put in players array bla bla bla
            for (Object object : data) {

                JSONObject jsonObj = (JSONObject) object;
                Set<Object> values = (Set<Object>) jsonObj.keySet();
                String name = (String) values.iterator().next();

                JSONObject pData = (JSONObject) jsonObj.get(name);

                // Stores JSON data to variables
                int mmr = ((Long) pData.get("mmr")).intValue();
                int win = ((Long) pData.get("wins")).intValue();
                int loss = ((Long) pData.get("losses")).intValue();
                int mwins = ((Long) pData.get("mapwins")).intValue();
                int mdraw = ((Long) pData.get("mapdraws")).intValue();
                int mloss = ((Long) pData.get("maplosses")).intValue();
                boolean tank = (boolean) pData.get("tank");
                boolean dps = (boolean) pData.get("dps");
                boolean support = (boolean) pData.get("support");

                // Calls create player to add new instance to hash map
                createPlayer(name,mmr,support,tank,dps,win,loss,mwins,mdraw,mloss);
            }

        }
        catch(IOException e){

            try (FileWriter file = new FileWriter("players.json")){
                file.flush();
            }
            catch (IOException ee){
                ee.printStackTrace();
            }
        }
        catch (ParseException e){
            System.out.println("OOPSIE "+e);
        }

        try (FileReader read = new FileReader("matches.json")){
            //idk do something, load data etc.
            System.out.println("Loading Matches...");
        }
        catch(IOException e){
            try (FileWriter file = new FileWriter("matches.json")){
                file.flush();
            }
            catch (IOException ee){
                ee.printStackTrace();
            }

        }

        // Testing code to check that players are being serialised properly
        savePlayers();
    }

    // For when loading from file.

    public void createPlayer(String username, Integer MMR, boolean support, boolean tank, boolean dps, int win, int loss, int mwin, int mdraw, int mloss){
        players.put(username, new Player(username, MMR, support, tank, dps, win, loss, mwin, mdraw, mloss));
    }

    // New players who specified roles.

    public void createPlayer(String username, Integer MMR, boolean support, boolean tank, boolean dps){
        players.put(username, new Player(username, MMR, support, tank, dps, 0, 0, 0, 0, 0));
    }

    // Mimicking optional Args using method overloading.

    public void createPlayer(String username){
        players.put(username, new Player(username, 1000,false, false, false, 0, 0, 0, 0, 0));
    }

    /*
    Returns null if name does not exist

    Function if another part of the program needs to access player data independently.
     */

    public Player getPlayer(String username){
        return players.get(username);
    }

    public void savePlayers(){
        // Function needs to save data from players
        JSONArray arr = new JSONArray();

        try (FileWriter file = new FileWriter("players.json")){

            for (Player p : players.values()){
                JSONObject jsonString = p.exportDataToJson();
                arr.add(jsonString);
            }
            file.write(arr.toJSONString());
        }
        catch (Exception e){
            System.out.println("Failure when saving players... " + e);
        }

    }

    /*
    For when team captains are picked at random.
    exclusion array is for players who don't want to/already are captains.
     */

    public int randomPlayerRoll(int playerCount, ArrayList<Integer> exclusion){
        int target = -1;
        Random rand = new Random();
        while (target < 0 || exclusion.contains(target)){
            target = rand.nextInt(playerCount);
        }
        return target;
    }
    /*
        Useful check for when trying to add player, don't want to erase data.
     */

    public boolean playerExists(String name){
        return players.containsKey(name);
    }


    /*
    Boolean returns to tell program if player was deleted or not
    don't see why this would be needed in live but good for testing.
     */

    public boolean removePlayer(String name, Boolean sure, Boolean reallySure){
        if (!sure || !reallySure) return false;
        if (players.remove(name) == null) return false;

        savePlayers();
        return true;
    }

    /*
    ENUM for mode, i.e.
    HYBRID - 1 - Utils.HYBRID
    PAYLOAD - 2 - Utils.PAYLOAD
    etc. etc.
     */
    public String randomMap(int mode){
        Random r = new Random();
        int index = 0;

        // code would check the enum entered and then select randomly from the list of maps in Utils.java
        switch (mode) {
            case Utils.HYBRID -> { // 1
                index = r.nextInt(Utils.HYBRID_MAPS.size());
                return Utils.HYBRID_MAPS.get(index);
            }
            case Utils.PAYLOAD -> { // 2
                index = r.nextInt(Utils.PAYLOAD_MAPS.size());
                return Utils.PAYLOAD_MAPS.get(index);
            }
            case Utils.CONTROL -> { // 3
                index = r.nextInt(Utils.CONTROL_MAPS.size());
                return Utils.CONTROL_MAPS.get(index);
            }
            case Utils.PUSH -> { // 4
                index = r.nextInt(Utils.PUSH_MAPS.size());
                return Utils.PUSH_MAPS.get(index);
            }
            case Utils.FLASHPOINT -> { // 5 WIP
                break;
            }
            case Utils.ANY -> { // 6
                index = r.nextInt(Utils.HYBRID_MAPS.size() + Utils.PAYLOAD_MAPS.size() + Utils.CONTROL_MAPS.size() + Utils.PUSH_MAPS.size()); // Add flashpoint when it exists
                ArrayList<String> maps = new ArrayList<>(){{
                    addAll(Utils.HYBRID_MAPS);
                    addAll(Utils.PAYLOAD_MAPS);
                    addAll(Utils.CONTROL_MAPS);
                    addAll(Utils.PUSH_MAPS);
                }};
                return maps.get(index);
            }
            default -> {
                return "error";
            }
        }
        return "error";
    }

    public HashMap<String, ArrayList<String>> createBalancedMatch(int teamSize, ArrayList<String> playerPool, boolean tryRolePreference){
        HashMap<String, ArrayList<String>> teams = new HashMap<>();

        // Compare MMR of players and try to balance teams as fair as possible.

        // return value "team1" : playerNameArray,  "team2" : playerNameArray
        return teams;
    }

    /*
    Returns the difference in mmr between two players, if positive, player1 is higher, if negative, player2 is higher
     */
    public int MMRDifference(Player player1, Player player2){
        return player1.getMMR() - player2.getMMR();
    }

    public ArrayList<Player> seedPlayers(){
        Collection<Player> temp = players.values();
        Player[] temp2 = temp.toArray(new Player[0]);

        Arrays.sort(temp2, Comparator.comparingInt(Player::getMMR));

        ArrayList<Player> seedings = new ArrayList<>(Arrays.asList(temp2));

        // Needs to order the players by MMR rating, if seeding is same, random choice.

        return seedings;
    }

    /*
        Returns the id of match (for tracking)
     */

    public UUID startMatch(Team team1, Team team2, int bestOf){
        UUID uuid = UUID.randomUUID();
        matchPool.put(uuid, new Match(team1, team2, players, bestOf));
        return uuid;
    }

    /*
        For UI to talk to controller instead of match class.
        returns if successful.
     */

    public boolean nextMap(UUID uuid, int winner, String map){
        Match m;
        if ((m = matchPool.get(uuid)) == null) return false;

        m.mapFinished(winner);
        if (!m.isGameOver()) {
            m.startNewMap(map);
            return true;
        }
        return false;
    }

    // checks if game is over first
    public void endMatch(UUID uuid){
        if (matchPool.get(uuid).isGameOver()){
            matchPool.get(uuid).endGame();
            System.out.println("Game "+uuid+" Over.");
            matchPool.remove(uuid);
        }
    }

    // force ends match

    public void forceEndMatch(UUID uuid){
        matchPool.get(uuid).endGame();
        matchPool.remove(uuid);
    }

    /*
        Purely for testing
     */
    public ArrayList<Team> createDummyTeams(){
        createPlayer("a", 0, true, false, false);
        createPlayer("b", 0, false, true, true);
        createPlayer("c", 0, true, false, false);
        createPlayer("d", 0, false, true, true);
        createPlayer("e", 0, true, false, false);
        createPlayer("f", 0, false, true, true);
        createPlayer("g", 0, true, false, false);
        createPlayer("h", 0, false, true, true);
        createPlayer("i", 0, true, false, false);
        createPlayer("j", 0, false, true, true);

        Team t1 = new Team();
        t1.addBench(players.get("a"));
        t1.setTank(players.get("a"));
        t1.addBench(players.get("b"));
        t1.setDPS(players.get("b"), 1);
        t1.addBench(players.get("c"));
        t1.setDPS(players.get("c"), 2);
        t1.addBench(players.get("d"));
        t1.setSupport(players.get("d"), 1);
        t1.addBench(players.get("e"));
        t1.setSupport(players.get("e"), 2);

        Team t2 = new Team();
        t2.addBench(players.get("f"));
        t2.setTank(players.get("f"));
        t2.addBench(players.get("g"));
        t2.setDPS(players.get("g"), 1);
        t2.addBench(players.get("h"));
        t2.setDPS(players.get("h"), 2);
        t2.addBench(players.get("i"));
        t2.setSupport(players.get("i"), 1);
        t2.addBench(players.get("j"));
        t2.setSupport(players.get("j"), 2);
        System.out.println(t2);

        return new ArrayList<>(){{add(t1);add(t2);}};
    }

}
