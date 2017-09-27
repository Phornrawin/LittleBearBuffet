package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UrlStorage {
    private ArrayList<String> imageGrilledUrl;
    private ArrayList<String> imageDelicatessenUrl;
    private ArrayList<String> imageDessertUrl;
    private ArrayList<String> imageBeverageUrl;

    public UrlStorage(){
        imageGrilledUrl = new ArrayList<String>();
        imageDelicatessenUrl = new ArrayList<String>();
        imageDessertUrl = new ArrayList<String>();
        imageBeverageUrl = new ArrayList<String>();

        putallImageUrl();

    }

    public ArrayList<String> getImageGrilledUrl() {
        return imageGrilledUrl;
    }

    public ArrayList<String> getImageDelicatessenUrl() {
        return imageDelicatessenUrl;
    }

    public ArrayList<String> getImageDessertUrl() {
        return imageDessertUrl;
    }

    public ArrayList<String> getImageBeverageUrl() {
        return imageBeverageUrl;
    }

    public void putallImageUrl(){
        putImageGrilledUrl();
//        putImageDelicatessenUrl();
//        putImageDessertUrl();
//        putImageBeverageUrl();
    }

    public void putImageGrilledUrl(){
        imageGrilledUrl.add("Asparagus");
        imageGrilledUrl.add("Baby-Corn");
        imageGrilledUrl.add("Bacon");
        imageGrilledUrl.add("Bamboo-Pulp");
        imageGrilledUrl.add("Beef slide");
        imageGrilledUrl.add("Black-buna-shimeji");
        imageGrilledUrl.add("Cabbage");
        imageGrilledUrl.add("Carrot");
        imageGrilledUrl.add("Chicken");
        imageGrilledUrl.add("Shrimp");
        imageGrilledUrl.add("cow-tongue");
        imageGrilledUrl.add("Egg");
        imageGrilledUrl.add("Enoki-Mushroom");
        imageGrilledUrl.add("Eringi-Mushroom");
        imageGrilledUrl.add("Fish-noodle");
        imageGrilledUrl.add("Guangdong");
        imageGrilledUrl.add("heart-pork");
        imageGrilledUrl.add("Japanese-Vermicelli");
        imageGrilledUrl.add("Jellyfish");
        imageGrilledUrl.add("Kamakobo");
        imageGrilledUrl.add("Lean beef");
        imageGrilledUrl.add("Mahi-Mahi-Fish");
        imageGrilledUrl.add("Narutomaki");
        imageGrilledUrl.add("Pork slide");
        imageGrilledUrl.add("Pork-Liver");
        imageGrilledUrl.add("Pumpkin");
        imageGrilledUrl.add("Raw-Ham");
        imageGrilledUrl.add("Salmon-Fillet");
        imageGrilledUrl.add("Scallop");
        imageGrilledUrl.add("Shiitake");
        imageGrilledUrl.add("Squid-Ball");
        imageGrilledUrl.add("Squid-Tentacle");
        imageGrilledUrl.add("Squid");
        imageGrilledUrl.add("streaky-pork");
        imageGrilledUrl.add("Tofu");
        imageGrilledUrl.add("Udon");
        imageGrilledUrl.add("White-buna-shimeji");
        imageGrilledUrl.add("White-Fungus");
    }

//    public void putImageDelicatessenUrl(){
//        imageDelicatessenUrl.put("Bacon-With-Corn", "https://goo.gl/1f1PNt");
//        imageDelicatessenUrl.put("Bacon-With-Mushroom-And-Asparagus", "https://goo.gl/XiFwqU");
//        imageDelicatessenUrl.put("Crab-Stick-Salad", "https://goo.gl/DL98bJ");
//        imageDelicatessenUrl.put("Fried-Eringi-Mushroom-With-Soy-Sauce", "https://goo.gl/Y4sURy");
//        imageDelicatessenUrl.put("Fried-Gyoza", "https://goo.gl/8c6kNG");
//        imageDelicatessenUrl.put("Garlic-Riceedit", "https://goo.gl/eXXmLC");
//        imageDelicatessenUrl.put("Grilled-Shishamo-In-Soy-Sauce", "https://goo.gl/oWHHKE");
//        imageDelicatessenUrl.put("Kimji", "https://goo.gl/tNC86W");
//        imageDelicatessenUrl.put("Salad", "https://goo.gl/o6SU61");
//        imageDelicatessenUrl.put("Yakisoba", "https://goo.gl/5GQKEK");
//    }
//
//    public void putImageDessertUrl(){
//        imageDessertUrl.put("Chocolate-Ice-Cream", "https://goo.gl/KYT53P");
//        imageDessertUrl.put("Coffee-Jelly-Ice-Cream", "https://goo.gl/hhZbAq");
//        imageDessertUrl.put("Coffee-Jelly", "https://goo.gl/iQS69F");
//        imageDessertUrl.put("Custard-Jelly-Ice-Cream", "https://goo.gl/6oLV7m");
//        imageDessertUrl.put("Custard-Jelly", "https://goo.gl/8q5Ead");
//        imageDessertUrl.put("Green-Tea-Ice-Cream-With-Azuka-Bean-Paste", "https://goo.gl/jU2yKh");
//        imageDessertUrl.put("Green-Tea-Ice-Cream", "https://goo.gl/r9jRek");
//        imageDessertUrl.put("Red-Bean", "https://goo.gl/TSjwFx");
//        imageDessertUrl.put("Vanilla-Ice-Cream", "https://goo.gl/W13ghp");
//        imageDessertUrl.put("Zalacca-In-Light-Syrup", "https://goo.gl/LBQ4z6");
//    }
//
//    public void putImageBeverageUrl(){
//        imageBeverageUrl.put("Coffee-Float", "https://goo.gl/SWrryn");
//        imageBeverageUrl.put("Coke-Float", "https://goo.gl/qiB9Tj");
//        imageBeverageUrl.put("Coke", "https://goo.gl/74VHDB");
//        imageBeverageUrl.put("Ice-Coffee", "https://goo.gl/L5q6DT");
//        imageBeverageUrl.put("Lemon-Tea", "https://goo.gl/g8sHUq");
//        imageBeverageUrl.put("Lime-Soda", "https://goo.gl/Yc81ai");
//        imageBeverageUrl.put("Lime-Squash", "https://goo.gl/HW9dsS");
//        imageBeverageUrl.put("Milk-Tea-Float", "https://goo.gl/hzmUY9");
//        imageBeverageUrl.put("Milk-Tea", "https://goo.gl/RqC5gW");
//        imageBeverageUrl.put("Orange-Juice", "https://goo.gl/kfyaRX");
//        imageBeverageUrl.put("Plum-Juice", "https://goo.gl/uMvSPx");
//        imageBeverageUrl.put("Sugar-Cane-Squash", "https://goo.gl/BYFsHG");
//        imageBeverageUrl.put("Water-Melon-Smoothies", "https://goo.gl/61zC8v");
//    }
}
