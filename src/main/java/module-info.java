module dev.vultureweb.todofx {

   requires javafx.controls;
   requires java.net.http;
   requires jakarta.json;
   requires com.fasterxml.jackson.databind;

   requires com.gluonhq.charm.glisten;
   requires com.gluonhq.attach.display;
   requires com.gluonhq.attach.lifecycle;
   requires com.gluonhq.attach.statusbar;
   requires com.gluonhq.attach.storage;
   requires com.gluonhq.attach.util;

   exports dev.vultureweb.todofx;
   exports dev.vultureweb.todofx.service;
   exports dev.vultureweb.todofx.model to com.fasterxml.jackson.databind;
}