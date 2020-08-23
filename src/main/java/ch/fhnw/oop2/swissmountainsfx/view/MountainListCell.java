package ch.fhnw.oop2.swissmountainsfx.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import ch.fhnw.oop2.swissmountainsfx.utils.ImageLoader;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Customzed listcell for the mountain list. Contains the mountain image and 3
 * labels with bindings.
 *
 * @author Linus, Manuel
 */
public class MountainListCell extends ListCell<MountainVM> {

    Label labelMountainName = new Label();
    Label labelHoehe = new Label();
    Label labelRange = new Label();
    Image image;
    Circle circle;

    private final ImageLoader imageLoader = new ImageLoader();

    public MountainListCell() {
        super();
        itemProperty().addListener((list, oldValue, newValue) -> {

            if (newValue != null) {
                labelMountainName.textProperty().bind(newValue.mountainnameProperty());
                labelHoehe.textProperty().bind(Bindings.convert(newValue.heightProperty()));
                labelRange.textProperty().bind(newValue.rangeProperty());
            }
            if (oldValue != null) {
                labelMountainName.textProperty().unbind();
                labelHoehe.textProperty().unbind();
                labelRange.textProperty().unbind();
            }
        });
    }

    /**
     * Custom cell item update implementation which is bound to the properties
     * of the given mountain. Loads also the mountain image based on the id.
     * 
     * @param mountain
     * @param empty 
     */
    @Override
    protected void updateItem(MountainVM mountain, boolean empty) {
        super.updateItem(mountain, empty);

        if (empty || mountain == null || mountain.getMountainname() == null) {
            setText(null);
        } else {

            HBox rootH = new HBox();
            VBox rootV = new VBox();
            
            setPadding(new Insets(8));
            
            labelMountainName.textProperty().bind(mountain.mountainnameProperty());
            labelHoehe.textProperty().bind(Bindings.convert(mountain.heightProperty()));
            labelRange.textProperty().bind(mountain.rangeProperty());
            
            labelMountainName.setId("list-cell-Name");
            labelHoehe.setId("list-cell-height-region");
            labelRange.setId("list-cell-height-region");
            

            try {
                image = new Image(new FileInputStream(imageLoader.getMountainImagePath(mountain.getImagename())));
                ImageView imageView = new ImageView(image);

                circle = new Circle(40, 35, 33);
                
                imageView.setClip(circle);
                imageView.setFitHeight(68.9);
                imageView.setFitWidth(98);

                rootV.getChildren().addAll(labelMountainName, labelHoehe, labelRange);
                setGraphic(rootV);
                rootH.getChildren().addAll(imageView, rootV);
                setGraphic(rootH);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
