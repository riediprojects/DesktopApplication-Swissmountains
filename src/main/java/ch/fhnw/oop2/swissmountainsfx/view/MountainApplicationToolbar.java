package ch.fhnw.oop2.swissmountainsfx.view;

import ch.fhnw.oop2.swissmountainsfx.presentationmodel.MountainApplicationPM;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class containing the code (view) for the UI toolbar at the top of the
 * application.
 *
 * @author Linus, Manuel
 */
public class MountainApplicationToolbar extends HBox {

    private final MountainApplicationPM model;
    private final MountainApplicationUI mainUI;

    private Button buttonSave, buttonAdd, buttonDelete;

    private ImageView imageViewAdd;
    private ImageView imageViewSave;
    private ImageView imageViewDelete;

    public MountainApplicationToolbar(MountainApplicationPM model, MountainApplicationUI mainUI) {
        this.model = model;
        this.mainUI = mainUI;

        initializeControls();
        layoutControls();
        setupEventHandlers();
    }

    public void disableSelectionRelatedButtons(boolean disable) {
        buttonDelete.setDisable(disable);
    }

    private void initializeControls() {
        prepareIcons();

        buttonSave = new Button("Speichern", imageViewSave);
        buttonAdd = new Button("Hinzufügen", imageViewAdd);
        buttonDelete = new Button("Löschen", imageViewDelete);
    }

    private void layoutControls() {
        this.getStyleClass().add("toolbar-Background");
        this.setPadding(new Insets(3));

        this.getChildren().addAll(buttonSave, buttonAdd, buttonDelete);
    }

    private void setupEventHandlers() {
        buttonDelete.setOnAction((ActionEvent e) -> {
            model.deleteSelectedMountain(mainUI.getMountainApplicationForm().getSelectedMountain());
        });

        buttonSave.setOnAction((ActionEvent e) -> {
            model.saveAllMountains();
        });

        buttonAdd.setOnAction((ActionEvent e) -> {
            model.addNewMountain();
            ListView mountainList = mainUI.getMountainApplicationSelector().getListView();

            mountainList.getSelectionModel().select(mainUI.getMountainApplicationForm().getSelectedMountain());
            mountainList.scrollTo(mainUI.getMountainApplicationForm().getSelectedMountain());
        });
    }

    private void prepareIcons() {
        Image imageAdd = new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/swissmountainsfx/customresources/view/add.png"));
        Image imageSave = new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/swissmountainsfx/customresources/view/save.png"));
        Image imageDelete = new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/swissmountainsfx/customresources/view/delete.png"));

        imageViewAdd = getIconImageView(imageAdd);
        imageViewSave = getIconImageView(imageSave);
        imageViewDelete = getIconImageView(imageDelete);
    }

    private ImageView getIconImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(24);
        imageView.setFitWidth(24);

        return imageView;
    }
}
