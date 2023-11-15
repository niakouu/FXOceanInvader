module edu.vanier.ufo {
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.base;
  requires javafx.controls;
  requires javafx.media;

  opens edu.vanier.ufo.engine to javafx.fxml;
  opens edu.vanier.ufo.game to javafx.fxml;
  opens edu.vanier.ufo.helpers to javafx.fxml;
  opens edu.vanier.ufo.ui.controller to javafx.fxml;
  opens edu.vanier.ufo.ui.hud to javafx.fxml;
  opens edu.vanier.ufo.ui to javafx.fxml;
  opens edu.vanier.ufo to javafx.graphics;

  exports edu.vanier.ufo.engine;
  exports edu.vanier.ufo.game;
  exports edu.vanier.ufo.helpers;
  exports edu.vanier.ufo.ui;

  opens sounds;
  opens fxml;
  opens images;
  opens images.heartbar.HUD;
}