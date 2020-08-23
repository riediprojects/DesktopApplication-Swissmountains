package ch.fhnw.oop2.swissmountainsfx.view;

import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import ch.fhnw.oop2.swissmountainsfx.presentationmodel.MountainApplicationPM;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 *  Class containing the code (view) for the UI header area.
 * 
 * @author Linus, Manuel
 */
public class MountainApplicationHeader extends HBox {

    private final MountainApplicationPM model;

    private VBox vBoxTitels;
    private HBox hBoxPicture;
    private Label title, height, place;

    private ImageView picture;
    private Circle circle;

    public MountainApplicationHeader(MountainApplicationPM model) {
        this.model = model;

        initializeControls();
        layoutControls();
        setupBindings();
    }

    private void initializeControls() {
    	setPadding(new Insets(15));
    	
        vBoxTitels = new VBox();
        hBoxPicture = new HBox();

        title = new Label("Titel");
        title.setId("mountain-titel");
        height = new Label("Height");
        height.setId("mountain-under-titel");
        place = new Label("Place");
        place.setId("mountain-under-titel");

        circle = new Circle(260, 140, 140);
        //circle = new Circle(270, 130, 130);

        picture = new ImageView();
        picture.setFitHeight(280);
        picture.setFitWidth(400);
    }

    private void layoutControls() {
        vBoxTitels.setSpacing(10);
        vBoxTitels.getChildren().addAll(title, height, place);
        
        picture.setClip(circle);
        hBoxPicture.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(hBoxPicture, Priority.ALWAYS);
        hBoxPicture.getChildren().add(picture);

        this.getChildren().addAll(vBoxTitels, hBoxPicture);
    }

    private void setupBindings() {
        MountainVM proxy = model.getMountaionProxy();

        title.textProperty().bind(proxy.mountainnameProperty());
        height.textProperty().bind(Bindings.convert(proxy.heightProperty()));
        place.textProperty().bind(proxy.rangeProperty());
        Bindings.bindBidirectional(picture.imageProperty(), model.mountainImageProperty());
    }

}
