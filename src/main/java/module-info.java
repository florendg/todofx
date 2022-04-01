module dev.vultureweb.todofx {

   requires javafx.controls;
   requires java.net.http;
   requires jakarta.json;
   requires com.fasterxml.jackson.databind;

   opens dev.vultureweb.todofx to javafx.graphics;

   exports dev.vultureweb.todofx;
   exports dev.vultureweb.todofx.service;
   exports dev.vultureweb.todofx.model to com.fasterxml.jackson.databind;
}