module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.logging;

    opens com.example.game to javafx.fxml;
    exports com.example.game;
    exports com.example.game.animation;
    opens com.example.game.animation to javafx.fxml;
    exports com.example.game.handler;
    opens com.example.game.handler to javafx.fxml;
    exports com.example.game.scene;
    opens com.example.game.scene to javafx.fxml;
}
