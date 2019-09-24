import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.*;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {

    public static void crawlSpells(String link){
        try {

            Document page = Jsoup.connect(link).get();
            Elements spell = page.select("div.SpellDiv");
            Elements spdet = spell.select("p.SPDet"); // div contenant les infos component, level

            //name
            String name = spell.select("div.heading p").toString().replaceAll("</*p>", "");
            System.out.println("name :"+name+"\n\n");


            //components
            String comp = spdet.select("p:contains(Components)").toString();
            System.out.println(comp);
            comp = comp.substring(comp.indexOf("</b>")+4);
            Pattern patternComp = Pattern.compile("[A-Z]*(/?[A-Z]+)");
            Matcher matchComp = patternComp.matcher(comp);
            ArrayList<String> components = new ArrayList<>();
            while(matchComp.find()){
                components.add(matchComp.group());
            }
            for(int i = 0;i<components.size();i++){
                System.out.println(components.get(i));
            }

            //level
            String lev = spdet.select("p:contains(Level)").toString();
            //lev = lev.substring(comp.indexOf("</b>")+4);
            System.out.println(lev);


            //String level = spell.select("div.heading p").toString().replaceAll("</*p>", "");
            //String lev = spell.select("div.heading p").toString().replaceAll("</*p>", "");
            //String resist = spell.select("div.heading p").toString().replaceAll("</*p>", "");



        } catch (IOException e) {
            System.err.println("For '" + link + "': " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // boucle pour tous les spells
        //for (int i = 1 ; i<1501 ; i++) {
            String link = "http://www.dxcontent.com/SDB_SpellBlock.asp?SDBID="+19;
            crawlSpells(link);
            //TODO new spell
        //}

    }


}
