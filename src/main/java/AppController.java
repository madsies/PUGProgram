import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.*;

public class AppController {

    /*
    Creates JSON (If nonexistant), and initialises neccessary data
     */
    public AppController() {

        JSONObject playerWrapper = new JSONObject();
        playerWrapper.put("players", "");

        JSONObject matchWrapper = new JSONObject();
        matchWrapper.put("matches", "");

        JSONArray fileArray = new JSONArray();
        fileArray.add(playerWrapper);
        fileArray.add(matchWrapper);

        try (FileWriter file = new FileWriter("players.json")){
            file.write(playerWrapper.toJSONString());
            file.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter("matches.json")){
            file.write(playerWrapper.toJSONString());
            file.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
