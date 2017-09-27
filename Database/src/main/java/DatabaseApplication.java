import controllers.CoreController;
import controllers.MainController;
import controllers.SQLiteManager;
import javafx.application.Application;
import javafx.stage.Stage;
import models.Category;
import models.Item;
import models.Order;
import models.Package;

import java.util.ArrayList;
import java.util.List;

public class DatabaseApplication extends Application {

    public static void main(String[] args) {
        CoreController controller = new MainController();
//        controller.start();
//        System.out.println("test db jar");
        SQLiteManager db = new SQLiteManager();

        List<Integer> categoryIds = db.getCategoryIds();
        System.out.println("categoryIds = " + categoryIds);

        List<Category> categories = new ArrayList<Category>();
        for(int id:categoryIds) {
            Category category = db.getCategory(id);
            if(category != null)
                categories.add(category);
        }
        System.out.println("categories = " + categories);

        List<Integer> packageIds = db.getPackageIds();
        System.out.println("packageIds = " + packageIds);

        List<Package> packages = new ArrayList<Package>();
        for(int id:packageIds){
            Package packageObj = db.getPackage(id);
            if (packageObj != null)
                packages.add(packageObj);
        }
        System.out.println("packages = " + packages);

        List<Integer> itemIdsP1 = db.getItemIds(packages.get(0).getId());
        System.out.println("itemIdsP1 = " + itemIdsP1);
        List<Integer> itemIdsP2 = db.getItemIds(packages.get(1).getId());
        System.out.println("itemIdsP2 = " + itemIdsP2);
        List<Integer> itemIdsP3 = db.getItemIds(packages.get(2).getId());
        System.out.println("itemIdsP3 = " + itemIdsP3);
        List<Integer> itemIdsP4 = db.getItemIds(packages.get(3).getId());
        System.out.println("itemIdsP4 = " + itemIdsP4);

        List<Item> items = new ArrayList<Item>();
        for (int id : itemIdsP4){
            Item item = db.getItem(id);
            if(item != null)
                items.add(item);
        }
        System.out.println("items = " + items);

        Item item = new Item(1, "test item", 2);

        Order order = new Order(0, 3, item, 1);
        Order newOrder = db.addOrder(order);
        System.out.println(newOrder);

//        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
