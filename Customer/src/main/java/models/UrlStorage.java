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
        imageGrilledUrl.put("shrimp", "https://goo.gl/kSF7m4");
        imageGrilledUrl.put("cowTongue", "https://goo.gl/MQq3tE");
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

    }

    public void putImageDessertUrl(){

    }

    public void putImageBeverageUrl(){

    }
}
