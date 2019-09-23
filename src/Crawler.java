import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {

    public static void crawlSpells(String link){
        try {

            Document page = Jsoup.connect(link).get();
            Elements spell = page.select("div.SpellDiv");
            String name = spell.select("div.heading p").toString().replaceAll("</*p>", "");
            Elements spdet = spell.select("p.SPDet");
            String level = spdet.select("p:contains(Components)").toString();
            level = level.substring(level.indexOf("</b>")+4);
            System.out.println("level :"+level+"\n\n");
            String[] lev = level.split("[^A-Z]");
            //String level = spell.select("div.heading p").toString().replaceAll("</*p>", "");
            String comp = spell.select("div.heading p").toString().replaceAll("</*p>", "");
            String resist = spell.select("div.heading p").toString().replaceAll("</*p>", "");


            System.out.println("level :"+level+"\n\n");

        } catch (IOException e) {
            System.err.println("For '" + link + "': " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // boucle pour tous les spells
        //for (int i = 1 ; i<1501 ; i++) {
            String link = "http://www.dxcontent.com/SDB_SpellBlock.asp?SDBID="+1;
            crawlSpells(link);
            //TODO new spell
        //}

    }


}
