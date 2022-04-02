package dev.vultureweb.todofx;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

   private final AppManager appManager = AppManager.initialize(this::postInit);

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void init() {
      appManager.addViewFactory(AppManager.HOME_VIEW, HomeView::new);
      updateDrawer();
   }

   @Override
   public void start(Stage stage) {
      appManager.start(stage);
   }

   private void postInit(Scene scene) {
      Swatch.BLUE.assignTo(scene);
   }

   private void updateDrawer() {
      NavigationDrawer navigationDrawer = appManager.getDrawer();
      NavigationDrawer.Item homeView  = new NavigationDrawer.Item("Home", MaterialDesignIcon.HOME.graphic());
      navigationDrawer.getItems().addAll(homeView);
      navigationDrawer.selectedItemProperty().addListener((obs, oldItem, newItem)->{
         if(newItem.equals(homeView)) {
            appManager.switchView(AppManager.HOME_VIEW);
         }
      });
   }

}
