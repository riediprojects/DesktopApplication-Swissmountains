package ch.fhnw.oop2.swissmountainsfx.view;

import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainHeightException;
import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainIsolationException;
import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainProminenceException;
import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import ch.fhnw.oop2.swissmountainsfx.presentationmodel.MountainApplicationPM;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * Class containing the code (view) for the UI editor area. 
 * 
 * @author Linus, Manuel
 */
public class MountainApplicationForm extends VBox {

    private final MountainApplicationPM model;
    private final MountainApplicationUI mainUI;

    private HBox hBoxEditor;
    private GridPane gridLeft, gridRight, gridUnder;
    private Label la_name, la_hoehe, la_dominanz, la_schartenhoehe, la_kmBis, la_mBis, la_typ, la_region, la_kanton, la_gebiet, la_bildunterschrift;
    private TextField name, hoehe, dominanz, schartenhoehe, kmBis, mBis, typ, region, kanton, gebiet, bildunterschrift;

    private boolean internalUpdate;
    private MountainVM selectedMountain;
    
    private HBox hBoxCopyRight;
    private Label labelCopyRight;

    public MountainApplicationForm(MountainApplicationPM model, MountainApplicationUI mainUI) {
        this.model = model;
        this.mainUI = mainUI;

        initializeControls();
        layoutControls();
        setupValueChangeListeners();
        setupBindings();
    }

    private void initializeControls() {
    	VBox.setVgrow(this, Priority.ALWAYS);
    	this.setPadding(new Insets(15));
    	
        hBoxEditor = new HBox();
        gridLeft = new GridPane();
        gridRight = new GridPane();
        gridUnder = new GridPane();

        la_name = getEditorLabel("Name");
        name = getTextField();
        la_hoehe = getEditorLabel("Höhe (m)");
        hoehe = getTextField();
        la_dominanz = getEditorLabel("Dominanz");
        dominanz = getTextField();
        la_schartenhoehe = getEditorLabel("Schartenhöhe");
        schartenhoehe = getTextField();
        la_kmBis = getEditorLabel("km bis");
        kmBis = getTextField();
        la_mBis = getEditorLabel("m bis");
        mBis = getTextField();
        la_typ = getEditorLabel("Typ");
        typ = getTextField();
        la_region = getEditorLabel("Region");
        region = getTextField();
        la_kanton = getEditorLabel("Kantone");
        kanton = getTextField();
        la_gebiet = getEditorLabel("Gebiet");
        gebiet = getTextField();
        la_bildunterschrift = getEditorLabel("Bildunterschrift");
        bildunterschrift = getTextField();

        selectedMountain = model.getSelectedMountain();
        setMountain(selectedMountain);
        
        hBoxCopyRight = new HBox();
        labelCopyRight = new Label("@ Copyright");
        labelCopyRight.setId("copyright-label");
    }

    private void layoutControls() {    	
        //editorHBox
        hBoxEditor.setSpacing(50);
        HBox.setHgrow(hBoxEditor, Priority.ALWAYS);
        hBoxEditor.getChildren().addAll(gridLeft, gridRight);

        //gridLeft 
        HBox.setHgrow(gridLeft, Priority.ALWAYS);
        gridLeft.setVgap(15);
        gridLeft.add(la_name, 0, 0);
        gridLeft.add(name, 1, 0);
        gridLeft.add(la_dominanz, 0, 1);
        gridLeft.add(dominanz, 1, 1);
        gridLeft.add(la_kmBis, 0, 2);
        gridLeft.add(kmBis, 1, 2);
        gridLeft.add(la_typ, 0, 3);
        gridLeft.add(typ, 1, 3);
        gridLeft.add(la_kanton, 0, 4);
        gridLeft.add(kanton, 1, 4);

        //gridRight
        HBox.setHgrow(gridRight, Priority.ALWAYS);
        gridRight.setVgap(15);
        gridRight.add(la_hoehe, 0, 0);
        gridRight.add(hoehe, 1, 0);
        gridRight.add(la_schartenhoehe, 0, 1);
        gridRight.add(schartenhoehe, 1, 1);
        gridRight.add(la_mBis, 0, 2);
        gridRight.add(mBis, 1, 2);
        gridRight.add(la_region, 0, 3);
        gridRight.add(region, 1, 3);
        gridRight.add(la_gebiet, 0, 4);
        gridRight.add(gebiet, 1, 4);

        //gridUnder   
        gridUnder.add(la_bildunterschrift, 0, 0);
        gridUnder.add(bildunterschrift, 1, 0);
            
        this.setSpacing(15);
        this.getChildren().addAll(hBoxEditor, gridUnder, hBoxCopyRight);

    	//Set copyright label
        VBox.setVgrow(hBoxCopyRight, Priority.ALWAYS); 
        hBoxCopyRight.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxCopyRight.getChildren().add(labelCopyRight);
        
   
    }

    private void setupValueChangeListeners() {
        model.selectedMountainProperty().addListener((observable, oldMountain, newMountain) -> {
            if (newMountain != null) {
                mainUI.getMountainApplicationToolbar().disableSelectionRelatedButtons(false);
                disableEditorFields(false);
                selectedMountain = newMountain;

                internalUpdate = true;
                setMountain(newMountain);
                internalUpdate = false;
            } else {
                System.out.println("No mountain is selected");

                mainUI.getMountainApplicationToolbar().disableSelectionRelatedButtons(true);
                disableEditorFields(true);
            }

        });

        name.textProperty().addListener((observable, oldText, newText) -> {
            if (!internalUpdate) {
                selectedMountain.setMountainname(newText);
                changedMountainname(selectedMountain);
            }
        });

        hoehe.textProperty().addListener((observable, oldText, newText) -> {
            if (!internalUpdate) {
                try {
                    selectedMountain.setHeight(Double.parseDouble(newText));
                } catch (NumberFormatException | InvalidMountainHeightException ex) {
                    if (ex instanceof NumberFormatException) {
                        mainUI.showErrorMessage("Keine Zahl", "Geben Sie einen nummerischen Wert ein!");
                    }
                    if (ex instanceof InvalidMountainHeightException) {
                        mainUI.showErrorMessage("UngÃ¼ltiger Wert", ex.getMessage());
                    }
                    changedHeight(selectedMountain);
                }
            }
        });

        schartenhoehe.textProperty().addListener((observable, oldText, newText) -> {
            if (!internalUpdate) {
                try {
                    selectedMountain.setProminence(Double.parseDouble(newText));
                } catch (NumberFormatException | InvalidMountainProminenceException ex) {
                    if (ex instanceof NumberFormatException) {
                        mainUI.showErrorMessage("Keine Zahl", "Geben Sie einen nummerischen Wert ein!");
                    }
                    if (ex instanceof InvalidMountainProminenceException) {
                        mainUI.showErrorMessage("Ungültiger Wert", ex.getMessage());
                    }
                    changedProminence(selectedMountain);
                }
            }
        });

        dominanz.textProperty().addListener((observable, oldText, newText) -> {
            if (!internalUpdate) {
                try {
                    selectedMountain.setMountainisolation(Double.parseDouble(newText));
                } catch (NumberFormatException | InvalidMountainIsolationException ex) {
                    if (ex instanceof NumberFormatException) {
                        mainUI.showErrorMessage("Keine Zahl", "Geben Sie einen nummerischen Wert ein!");
                    }
                    if (ex instanceof InvalidMountainIsolationException) {
                        mainUI.showErrorMessage("Ungültiger Wert", ex.getMessage());
                    }
                    changedMountainisolation(selectedMountain);
                }
            }
        });

        gebiet.textProperty().addListener((observable, oldText, newText) -> {
            if (!internalUpdate) {
                selectedMountain.setRange(newText);
                changedPlace(selectedMountain);
            }
        });
    }

    private void setupBindings() {
        MountainVM proxy = model.getMountaionProxy();

        StringConverter<Number> converter = new NumberStringConverter();

        typ.textProperty().bindBidirectional(proxy.mountaintypeProperty());
        kmBis.textProperty().bindBidirectional(proxy.isolationpointProperty());
        mBis.textProperty().bindBidirectional(proxy.prominencepointProperty());
        region.textProperty().bindBidirectional(proxy.regionProperty());
        kanton.textProperty().bindBidirectional(proxy.cantonsProperty());
        bildunterschrift.textProperty().bindBidirectional(proxy.captionProperty());
    }

    private Label getEditorLabel(String name) {
        Label label = new Label(name);
        label.setPrefSize(100, 15);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setId("editor-labels");
        return label;
    }

    private TextField getTextField() {
        TextField textField = new TextField();
        textField.setPrefWidth(80);
        textField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        textField.setId("editor-textfield");
        return textField;
    }

    private void disableEditorFields(boolean disable) {
        name.setDisable(disable);
        hoehe.setDisable(disable);
        dominanz.setDisable(disable);
        schartenhoehe.setDisable(disable);
        kmBis.setDisable(disable);
        mBis.setDisable(disable);
        typ.setDisable(disable);
        region.setDisable(disable);
        kanton.setDisable(disable);
        gebiet.setDisable(disable);
        bildunterschrift.setDisable(disable);
    }

    private void setMountain(MountainVM mountain) {
        changedMountainname(mountain);
        changedPlace(mountain);
        changedHeight(mountain);
        changedProminence(mountain);
        changedMountainisolation(mountain);
    }

    private void changedMountainname(MountainVM m) {
        name.textProperty().set(m.getMountainname());
    }

    private void changedPlace(MountainVM m) {
        gebiet.textProperty().set(m.getRange());
    }

    private void changedHeight(MountainVM m) {
        hoehe.textProperty().set(String.valueOf(m.getHeight()));
    }

    private void changedProminence(MountainVM m) {
        schartenhoehe.textProperty().set(String.valueOf(m.getProminence()));
    }

    private void changedMountainisolation(MountainVM m) {
        dominanz.textProperty().set(String.valueOf(m.getMountainisolation()));
    }

    public MountainVM getSelectedMountain() {
        return selectedMountain;
    }

}
