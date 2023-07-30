import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
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

        players.put("jeff2", new Player("jeff2", 1000, true, false, false, 4, 2, 5, 2, 1));

        savePlayers();
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

    public HashMap<String, Integer> seedPlayers(){
        HashMap<String, Integer> seedings = new HashMap<>();

        // Needs to order the players by MMR rating, if seeding is same, random choice.

        return seedings;
    }

    public void startMatch(ArrayList<String> team1, ArrayList<String> team2){
        new Match(team1, team2, players);
    }

}
