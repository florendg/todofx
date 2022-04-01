package dev.vultureweb.todofx;

import dev.vultureweb.todofx.model.TodoItem;
import dev.vultureweb.todofx.service.RestService;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage stage) {

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

      Scene scene = new Scene(container, 200, 600);

      stage.setScene(scene);
      stage.show();

   }
}
