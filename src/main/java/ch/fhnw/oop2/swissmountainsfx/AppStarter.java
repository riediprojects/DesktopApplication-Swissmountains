package ch.fhnw.oop2.swissmountainsfx;

import ch.fhnw.oop2.swissmountainsfx.presentationmodel.MountainApplicationPM;
import ch.fhnw.oop2.swissmountainsfx.view.MountainApplicationUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppStarter extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        DataLoader dataLoader = new DataLoader();
        dataLoader.setupDatabase();

        MountainApplicationPM pm = new MountainApplicationPM();

        MountainApplicationUI rootPanel = new MountainApplicationUI(pm, primaryStage);

        Scene scene = new Scene(rootPanel);

        primaryStage.titleProperty().bind(pm.applicationTitleProperty());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(
                getClass().getResourceAsStream("/ch/fhnw/oop2/swissmountainsfx/customresources/view/mountain.png")));

        primaryStage.setWidth(1230);
        primaryStage.setMinWidth(850);
        primaryStage.setHeight(710);
        primaryStage.setMinHeight(440);
        
        primaryStage.setOnCloseRequest(rootPanel.confirmCloseEventHandler);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
