package ch.fhnw.oop2.swissmountainsfx.view;

import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import ch.fhnw.oop2.swissmountainsfx.presentationmodel.MountainApplicationPM;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Class containing the code (view) for the UI selector area (list with list
 * options).
 *
 * @author Linus, Manuel
 */
public class MountainApplicationSelector extends VBox {

    private final MountainApplicationPM model;

    private ListView<MountainVM> listView;

    private ComboBox<String> boxSort;

    private HBox hBoxSort;
    private Label labelSort;

    public MountainApplicationSelector(MountainApplicationPM model) {
        this.model = model;

        initializeControls();
        layoutControls();
        setupValueChangeListeners();
    }

    private void initializeControls() {
        listView = new ListView<>();
        listView.setCellFactory((ListView<MountainVM> param) -> new MountainListCell());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setItems(model.getMountainData());

        if (model.getMountainData().size() > 0) {
            listView.getSelectionModel().select(0);
        }

        labelSort = new Label("Sortieren nach:");
        labelSort.setId("sortby-label");

        boxSort = new ComboBox<>();
        boxSort.setItems(model.getSortOptions());
        boxSort.setId("drop-box");
        boxSort.setValue("Name aufsteigend");

        hBoxSort = new HBox();
        hBoxSort.setId("sort-all");
    }

    private void layoutControls() {
        listView.setPadding(new Insets(5));
        listView.setMaxHeight(Double.MAX_VALUE);

        labelSort.setPrefWidth(100);

        boxSort.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(boxSort, Priority.ALWAYS);

        hBoxSort.setPadding(new Insets(5));
        hBoxSort.setAlignment(Pos.CENTER);
        hBoxSort.getChildren().addAll(labelSort, boxSort);

        VBox.setVgrow(listView, Priority.ALWAYS);
        this.setSpacing(5);
        this.getChildren().addAll(hBoxSort, listView);

    }

    private void setupValueChangeListeners() {
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends MountainVM> observable, MountainVM oldMountain, MountainVM newMountain) -> {
                    model.setSelectedMountain(newMountain);
                });

        boxSort.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                model.sortByValue(newValue);
            }
        });
    }

    public ListView<MountainVM> getListView() {
        return listView;
    }

}
