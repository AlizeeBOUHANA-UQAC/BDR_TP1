import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.*;

import java.util.ArrayList;
import java.util.regex.*;

import java.io.IOException;
import java.util.HashSet;
 //push test Romain
public class Crawler {

    public static Spell crawlSpells(String link){
        try {
            Document page = Jsoup.connect(link).get();
            Elements spell = page.select("div.SpellDiv");
            Elements spdet = spell.select("p.SPDet"); // div contenant les infos component, level, spellResist

            //name
            String name = spell.select("div.heading p").toString().replaceAll("</*p>", "");

            //components
            ArrayList<String> components = new ArrayList<>();
            String comp = spdet.select("p:matches(Components)").toString();
            comp = comp.substring(comp.indexOf("<b>Components</b>")+17);
            Pattern pattern = Pattern.compile("[A-Z]*(/?[A-Z]+)");
            Matcher matcher = pattern.matcher(comp);
            while(matcher.find()){
                components.add(matcher.group());
            }

            //level
            int level = 0;
            String lev = spdet.select("p:matches(Level)").toString();
            lev = lev.substring(lev.indexOf("<b>Level</b>")+12);
            pattern = Pattern.compile("[0-9]+");
            matcher = pattern.matcher(lev);
            while(matcher.find()){
                int i = Integer.parseInt(matcher.group());
                if(i > level){
                    level = i;
                }
            }

            //spellResist
            boolean spellResist;
            String resist = spdet.select("p:contains(Spell Resistance yes)").toString();
            spellResist = !resist.equals("");


            // Spell
            return new Spell(name, level, components, spellResist);


        } catch (IOException e) {
            System.err.println("For '" + link + "': " + e.getMessage());
        }

        return new Spell(null, 0, new ArrayList<>(), false);
    }

    public static void main(String[] args) {
        ArrayList<Spell> arraySpells = new ArrayList<>();
        // boucle pour tous les spells
        for (int i = 1 ; i<1501 ; i++) {
            String link = "http://www.dxcontent.com/SDB_SpellBlock.asp?SDBID="+i;
            Spell s = crawlSpells(link);
            if(s.getName() != null){
                arraySpells.add(s);
            }
        }

        SpellsToJson.GenerateJson(arraySpells);
    }


}
