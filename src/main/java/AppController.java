import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AppController {

    /*
    Creates JSON (If nonexistent), and initialises necessary data into player array etc.
     */

    ArrayList<Player> players = new ArrayList<>();

    public AppController() {

        /*

            Creates Data JSON Files if non-existent.

         */

        JSONArray fileArray = new JSONArray();

        try (FileReader read = new FileReader("players.json")){
            //idk do something, load data etc.
            System.out.println("players exist!");
            JSONParser parser = new JSONParser();

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

        players.add(new Player("jeff2", 1000, true, false, false, 4, 2, 5, 2, 1));

        savePlayers();
    }

    public void savePlayers(){
        // Function needs to save data from players

        try (FileWriter file = new FileWriter("players.json")){

            for (Player p : players){
                String jsonString = p.exportDataToJson();
                file.write(jsonString);
            }
            file.flush();
        }
        catch (Exception e){
            System.out.println("Failure when saving players... " + e);
        }


    }
}
