import java.util.ArrayList;

public class SpellsToJson {
    public static void main(String[] args) {

        Spell Alter_Self = new Spell("Alter Self", 2, null, false);

        ArrayList<Spell> spells  = new ArrayList<Spell>();
        spells.add(Alter_Self);

        GenerateJson(spells);
    }

    public static void GenerateJson(ArrayList<Spell> spells) {
        for (Spell sp: spells) {
            System.out.println(sp.toJSON());
        }
    }


}
