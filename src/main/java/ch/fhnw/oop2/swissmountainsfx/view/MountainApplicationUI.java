package ch.fhnw.oop2.swissmountainsfx.view;

import ch.fhnw.oop2.swissmountainsfx.presentationmodel.MountainApplicationPM;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Class containing the code (view) for the main UI (container for all other
 * views) and alert dialogs for the application.
 *
 * @author Linus, Manuel
 */
public class MountainApplicationUI extends VBox {

    private final MountainApplicationPM model;

    private Stage mainStage;

    private MountainApplicationToolbar mountainApplicationToolbar;
    private MountainApplicationSelector mountainApplicationSelector;
    private MountainApplicationHeader mountainApplicationHeader;
    private MountainApplicationForm mountainApplicationForm;

    private SplitPane splitPane;
    private VBox vBoxEditorArea;

    public MountainApplicationUI(MountainApplicationPM model, Stage mainStage) {
        this.model = model;
        this.mainStage = mainStage;

        initializeSelf();
        initializeControls();
        layoutControls();
    }

    /**
     * Shows an error message in a popup
     *
     * @param headerText the header text
     * @param contentText the content text
     */
    public void showErrorMessage(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("alertstyle.css").toExternalForm());
        dialogPane.getStyleClass().add("alertdialog");

        Platform.runLater(() -> alert.showAndWait());
    }

    /**
     * Shows a confirmation dialog before closing the application if there are
     * unsaved changes.
     */
    public EventHandler<WindowEvent> confirmCloseEventHandler = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            if (!model.getIdsOfUnsavedMountains().isEmpty()) {
                Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
                        "Wollen Sie die Applikation wirklich schliessen? "
                        + "Es gibt noch ungespeicherte Änderungen.");
                Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
                exitButton.setText("Trotzdem schliessen");
                closeConfirmation.setHeaderText("Schliessen bestätigen");
                closeConfirmation.initModality(Modality.APPLICATION_MODAL);
                closeConfirmation.initOwner(mainStage);

                DialogPane dialogPane = closeConfirmation.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("alertstyle.css").toExternalForm());
                dialogPane.getStyleClass().add("alertdialog");

                Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
                if (!ButtonType.OK.equals(closeResponse.get())) {
                    event.consume();
                }
            }
        }
    };

    private void initializeSelf() {
        getStylesheets().add("https://fonts.googleapis.com/css?family=Nunito");
        getStylesheets().add("https://fonts.googleapis.com/css?family=Lato");

        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);

    }

    private void initializeControls() {
        mountainApplicationToolbar = new MountainApplicationToolbar(model, this);
        mountainApplicationSelector = new MountainApplicationSelector(model);
        mountainApplicationHeader = new MountainApplicationHeader(model);
        mountainApplicationForm = new MountainApplicationForm(model, this);

        vBoxEditorArea = new VBox();

        splitPane = new SplitPane();
        splitPane.setId("split-pane-all");
    }

    private void layoutControls() {
        setEditorArea();
        setSplitPane();

        getChildren().addAll(mountainApplicationToolbar, splitPane);
    }

    private void setEditorArea() {

        vBoxEditorArea.setPadding(new Insets(15));
        vBoxEditorArea.setSpacing(25);
        vBoxEditorArea.getChildren().addAll(mountainApplicationHeader, mountainApplicationForm);
    }

    private void setSplitPane() {

        splitPane.setDividerPositions(0.2);
        splitPane.getItems().addAll(mountainApplicationSelector, vBoxEditorArea);
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        HBox.setHgrow(splitPane, Priority.ALWAYS);
    }

    public MountainApplicationToolbar getMountainApplicationToolbar() {
        return mountainApplicationToolbar;
    }

    public MountainApplicationSelector getMountainApplicationSelector() {
        return mountainApplicationSelector;
    }

    public MountainApplicationHeader getMountainApplicationHeader() {
        return mountainApplicationHeader;
    }

    public MountainApplicationForm getMountainApplicationForm() {
        return mountainApplicationForm;
    }
}
