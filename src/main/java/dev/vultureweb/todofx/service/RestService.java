package dev.vultureweb.todofx.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vultureweb.todofx.model.TodoItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;


public class RestService {

   public ObservableList<TodoItem> getAll() {
      return FXCollections.emptyObservableList();
   }

   public ObservableList<TodoItem> getTodoItems() {
      ObservableList<TodoItem> list = FXCollections.observableArrayList();
      try {
         HttpRequest request = HttpRequest.newBuilder()
               .GET()
               .uri(new URI("http://localhost:8080/demo/generate"))
               .build();

         HttpClient.newBuilder()
               .build().sendAsync(request, HttpResponse.BodyHandlers.ofString())
               .thenApply(HttpResponse::body)
               .thenApply(this::parseData)
               .thenAccept(todoItems -> Platform.runLater(()-> list.addAll(todoItems)));
      } catch (Exception exception) {
         System.err.println("Failed to retrieve data:" + exception.getMessage());
      }
      return list;
   }

   private List<TodoItem> parseData(String response) {
      try(StringReader reader = new StringReader(response)) {
         ObjectMapper mapper = new ObjectMapper();
         TodoItem[] items = mapper.readValue(reader,TodoItem[].class);
         return Arrays.stream(items).toList();
      } catch (IOException ioException) {
         System.err.println("Failed " + ioException.getMessage());
         return List.of();
      }
   }
}
