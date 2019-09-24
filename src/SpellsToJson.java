import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SpellsToJson {
    public static void main(String[] args) {

        ArrayList<String> comp = new ArrayList<String>();
        comp.add("V");
        comp.add("S");
        comp.add("M");
        Spell Alter_Self = new Spell("Alter Self", 2, comp, false);

        comp = new ArrayList<String>();
        comp.add("V");
        comp.add("S");
        comp.add("F/DF");
        Spell Alarm = new Spell("Alarm", 2, comp, false);

        ArrayList<Spell> spells  = new ArrayList<Spell>();
        spells.add(Alter_Self);
        spells.add(Alarm);

        GenerateJson(spells);
    }

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
            System.out.println("\nJSON Object: " + obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
