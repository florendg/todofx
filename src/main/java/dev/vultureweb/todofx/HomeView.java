package dev.vultureweb.todofx;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.vultureweb.todofx.model.TodoItem;
import dev.vultureweb.todofx.service.RestService;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeView extends View {

   public HomeView() {
      IntegerProperty secondSizeProperty = new SimpleIntegerProperty(0);

      VBox container = new VBox();
      container.setAlignment(Pos.CENTER);

      Button helloButton = new Button("Retrieve Data");

      helloButton.setOnAction(actionEvent -> {

         ObservableList<TodoItem> secondList = new RestService().getTodoItems();
         secondList.addListener((ListChangeListener<TodoItem>) change -> {
            if( change.next() ) {
               secondSizeProperty.setValue(change.getAddedSize());
            }
         });
         secondSizeProperty.setValue(secondList.size());
      });

      Label secondSize = new Label("received 0 bytes");
      secondSize.textProperty().bind(secondSizeProperty.asString());

      container.getChildren().addAll(helloButton,secondSize);
      setCenter(container);

   }

   @Override
   protected void updateAppBar(AppBar appBar) {
      appBar.setNavIcon(MaterialDesignIcon.MENU.button((e) -> getAppManager().getDrawer().open()));
      appBar.setTitleText("Home View");
   }
}
