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
            JSONObject playerWrapper = new JSONObject();
            playerWrapper.put("players", "");
            fileArray.add(playerWrapper);

            try (FileWriter file = new FileWriter("players.json")){
                file.write(playerWrapper.toJSONString());
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
            JSONObject matchWrapper = new JSONObject();
            matchWrapper.put("matches", "");
            fileArray.add(matchWrapper);

            try (FileWriter file = new FileWriter("matches.json")){
                file.write(matchWrapper.toJSONString());
                file.flush();
            }
            catch (IOException ee){
                ee.printStackTrace();
            }

        }












    }

}
