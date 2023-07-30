import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

        JSONArray fileArray = new JSONArray();

        try (FileReader read = new FileReader("players.json")){
            //idk do something, load data etc.
            System.out.println("players exist!");
            JSONParser parser = new JSONParser();

            // Need to translate JSON data the same way it is encoded, CHECK Player::exportDataToJson()

            Object obj = parser.parse(read);
        }
        catch(IOException e){

            try (FileWriter file = new FileWriter("players.json")){
                file.flush();
            }
            catch (IOException ee){
                ee.printStackTrace();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
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

        createPlayer("jeff1");
        createPlayer("jeff2");
        createPlayer("jeff3");
        createPlayer("jeff4");
        createPlayer("jeff5");
        createPlayer("jeff6");

        savePlayers();
    }

    public void createPlayer(String username, Integer MMR, boolean support, boolean tank, boolean dps){
        players.put(username, new Player(username, MMR, support, tank, dps, 0, 0, 0, 0, 0));
    }

    // Mimicking optional Args using method overloading.

    public void createPlayer(String username){
        players.put(username, new Player(username, 1000,false, false, false, 0, 0, 0, 0, 0));
    }

    public void savePlayers(){
        // Function needs to save data from players

        try (FileWriter file = new FileWriter("players.json")){

            for (Player p : players.values()){
                String jsonString = p.exportDataToJson();
                file.write(jsonString);
            }
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

    public HashMap<String, ArrayList<String>> createBalancedMatch(int teamSize, ArrayList<String> playerPool, boolean tryRolePreference){
        HashMap<String, ArrayList<String>> teams = new HashMap<>();

        // Compare MMR of players and try to balance teams as fair as possible.

        return teams;
    }

    public HashMap<String, Integer> seedPlayers(){
        HashMap<String, Integer> seedings = new HashMap<>();

        // Needs to order the players by MMR rating, if seeding is same, random choice.

        return seedings;
    }

    public void startMatch(ArrayList<String> team1, ArrayList<String> team2){
        new Match(team1, team2, players);
    }

}
