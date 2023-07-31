import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AppController {

    /*
    Creates JSON (If nonexistent), and initialises necessary data into player array etc.
     */

    HashMap<String, Player> players = new HashMap<>();

    public AppController() {

        /*

            Creates Data JSON Files if non-existent.

         */

        try (FileReader read = new FileReader("players.json")){
            //idk do something, load data etc.
            System.out.println("players exist!");
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(read);
            JSONArray data = (JSONArray) obj;

            // Iter through data in array, make player object, put in players array bla bla bla
            for (Object object : data){
                JSONObject jsonObj = (JSONObject) object;
                Set<Object> values = (Set<Object>) jsonObj.keySet();
                String name = (String) values.iterator().next();

                JSONObject pData = (JSONObject) jsonObj.get(name);
                int x = ((Long) pData.get("mmr")).intValue();
                System.out.println(x);
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
            System.out.println("matches exist!");
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
            file.flush();
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

    public HashMap<String, Integer> seedPlayers(){
        HashMap<String, Integer> seedings = new HashMap<>();

        // Needs to order the players by MMR rating, if seeding is same, random choice.

        return seedings;
    }

    public void startMatch(ArrayList<String> team1, ArrayList<String> team2, int bestOf){
        new Match(team1, team2, players, bestOf);
    }

}
