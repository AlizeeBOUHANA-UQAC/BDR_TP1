import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SpellsToJson {
    public static void GenerateJson(ArrayList<Spell> spells) {
        JSONObject obj = new JSONObject();

        Integer i=0;
        for (Spell sp: spells) {
            obj.put(i.toString(), sp.toJSON());
            i++;
        }

        try (FileWriter file = new FileWriter("Spells.json")) {
            file.write(obj.toString());
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
