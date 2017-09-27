package models;

import java.util.HashMap;
import java.util.Map;

public class UrlStorage {
    private Map<String, String> imageGrilledUrl;
    private Map<String, String> imageDelicatessenUrl;
    private Map<String, String> imageDessertUrl;
    private Map<String, String> imageBeverageUrl;

    public UrlStorage(){
        imageGrilledUrl = new HashMap<String, String>();
        imageDelicatessenUrl = new HashMap<String, String>();
        imageDessertUrl = new HashMap<String, String>();
        imageBeverageUrl = new HashMap<String, String>();

        putallImageUrl();

    }

    public Map<String, String> getImageGrilledUrl() {
        return imageGrilledUrl;
    }

    public Map<String, String> getImageDelicatessenUrl() {
        return imageDelicatessenUrl;
    }

    public Map<String, String> getImageDessertUrl() {
        return imageDessertUrl;
    }

    public Map<String, String> getImageBeverageUrl() {
        return imageBeverageUrl;
    }

    public void putallImageUrl(){
        putImageGrilledUrl();
        putImageDelicatessenUrl();
        putImageDessertUrl();
        putImageBeverageUrl();
    }

    public void putImageGrilledUrl(){
        imageGrilledUrl.put("Asparagus", "https://goo.gl/Az5j7H");
        imageGrilledUrl.put("BabyCorn", "https://goo.gl/mfZjrM");
        imageGrilledUrl.put("Bacon", "https://goo.gl/313Vpr");
        imageGrilledUrl.put("BambooPulp", "https://goo.gl/hVWVr4");
        imageGrilledUrl.put("BeefSlide", "https://goo.gl/G8LpAQ");
        imageGrilledUrl.put("BlackBunaShimeji", "https://goo.gl/1DXgUh");
        imageGrilledUrl.put("Cabbage", "https://goo.gl/HcundB");
        imageGrilledUrl.put("Carrot", "https://goo.gl/q3gPai");
        imageGrilledUrl.put("Chicken", "https://goo.gl/bFSXRz");
        imageGrilledUrl.put("Shrimp", "https://goo.gl/kSF7m4");
        imageGrilledUrl.put("CowTongue", "https://goo.gl/MQq3tE");
        imageGrilledUrl.put("Egg", "https://goo.gl/fVt6RN");
        imageGrilledUrl.put("EnokiMashroom", "https://goo.gl/vSbaq6");
        imageGrilledUrl.put("EringiMashroom", "https://goo.gl/kQPvYN");
        imageGrilledUrl.put("FishNoodle", "https://goo.gl/Mqq1Fr");
        imageGrilledUrl.put("Guangdong", "https://goo.gl/FzRW3A");
        imageGrilledUrl.put("HeartPork", "https://goo.gl/WQ4ELx");
        imageGrilledUrl.put("JapaneseVermicelli", "https://goo.gl/G7daJJ");
        imageGrilledUrl.put("JellyFish", "https://goo.gl/uNiU1i");
        imageGrilledUrl.put("Kamakobo", "https://goo.gl/7wuKAm");
        imageGrilledUrl.put("LeanBeef", "https://goo.gl/T5vRzc");
        imageGrilledUrl.put("MahiMahiFish", "https://goo.gl/YDB2sH");
        imageGrilledUrl.put("Narutomaki", "https://goo.gl/wmXiP3");
        imageGrilledUrl.put("PorkSlide", "https://goo.gl/LCRdbM");
        imageGrilledUrl.put("PorkLiver", "https://goo.gl/3jedio");
        imageGrilledUrl.put("Pumpkin", "https://goo.gl/c2Gguj");
        imageGrilledUrl.put("RawHam", "https://goo.gl/FNise7");
        imageGrilledUrl.put("SalmonFillet", "https://goo.gl/retMY1");
        imageGrilledUrl.put("Scallop", "https://goo.gl/Mu5DaA");
        imageGrilledUrl.put("Shiitake", "https://goo.gl/shPhZG");
        imageGrilledUrl.put("SquidBall", "https://goo.gl/fT2kAm");
        imageGrilledUrl.put("SquidTentacle", "https://goo.gl/sK1d9u");
        imageGrilledUrl.put("Squid", "https://goo.gl/M1nFut");
        imageGrilledUrl.put("StreakyPork", "https://goo.gl/9QmmEa");
        imageGrilledUrl.put("Tofu", "https://goo.gl/pRJR34");
        imageGrilledUrl.put("Udon", "https://goo.gl/smCtcM");
        imageGrilledUrl.put("WhiteBunaShimeji", "https://goo.gl/cPVAzq");
        imageGrilledUrl.put("WhiteFungus", "https://goo.gl/1skVji");
    }

    public void putImageDelicatessenUrl(){
        imageDelicatessenUrl.put("Bacon-With-Corn", "https://goo.gl/1f1PNt");
        imageDelicatessenUrl.put("Bacon-With-Mushroom-And-Asparagus", "https://goo.gl/XiFwqU");
        imageDelicatessenUrl.put("Crab-Stick-Salad", "https://goo.gl/DL98bJ");
        imageDelicatessenUrl.put("Fried-Eringi-Mushroom-With-Soy-Sauce", "https://goo.gl/Y4sURy");
        imageDelicatessenUrl.put("Fried-Gyoza", "https://goo.gl/8c6kNG");
        imageDelicatessenUrl.put("Garlic-Riceedit", "https://goo.gl/eXXmLC");
        imageDelicatessenUrl.put("Grilled-Shishamo-In-Soy-Sauce", "https://goo.gl/oWHHKE");
        imageDelicatessenUrl.put("Kimji", "https://goo.gl/tNC86W");
        imageDelicatessenUrl.put("Salad", "https://goo.gl/o6SU61");
        imageDelicatessenUrl.put("Yakisoba", "https://goo.gl/5GQKEK");
    }

    public void putImageDessertUrl(){
        imageDessertUrl.put("Chocolate-Ice-Cream", "https://goo.gl/KYT53P");
        imageDessertUrl.put("Coffee-Jelly-Ice-Cream", "https://goo.gl/hhZbAq");
        imageDessertUrl.put("Coffee-Jelly", "https://goo.gl/iQS69F");
        imageDessertUrl.put("Custard-Jelly-Ice-Cream", "https://goo.gl/6oLV7m");
        imageDessertUrl.put("Custard-Jelly", "https://goo.gl/8q5Ead");
        imageDessertUrl.put("Green-Tea-Ice-Cream-With-Azuka-Bean-Paste", "https://goo.gl/jU2yKh");
        imageDessertUrl.put("Green-Tea-Ice-Cream", "https://goo.gl/r9jRek");
        imageDessertUrl.put("Red-Bean", "https://goo.gl/TSjwFx");
        imageDessertUrl.put("Vanilla-Ice-Cream", "https://goo.gl/W13ghp");
        imageDessertUrl.put("Zalacca-In-Light-Syrup", "https://goo.gl/LBQ4z6");
    }

    public void putImageBeverageUrl(){
        imageBeverageUrl.put("Coffee-Float", "https://goo.gl/SWrryn");
        imageBeverageUrl.put("Coke-Float", "https://goo.gl/qiB9Tj");
        imageBeverageUrl.put("Coke", "https://goo.gl/74VHDB");
        imageBeverageUrl.put("Ice-Coffee", "https://goo.gl/L5q6DT");
        imageBeverageUrl.put("Lemon-Tea", "https://goo.gl/g8sHUq");
        imageBeverageUrl.put("Lime-Soda", "https://goo.gl/Yc81ai");
        imageBeverageUrl.put("Lime-Squash", "https://goo.gl/HW9dsS");
        imageBeverageUrl.put("Milk-Tea-Float", "https://goo.gl/hzmUY9");
        imageBeverageUrl.put("Milk-Tea", "https://goo.gl/RqC5gW");
        imageBeverageUrl.put("Orange-Juice", "https://goo.gl/kfyaRX");
        imageBeverageUrl.put("Plum-Juice", "https://goo.gl/uMvSPx");
        imageBeverageUrl.put("Sugar-Cane-Squash", "https://goo.gl/BYFsHG");
        imageBeverageUrl.put("Water-Melon-Smoothies", "https://goo.gl/61zC8v");
    }
}
