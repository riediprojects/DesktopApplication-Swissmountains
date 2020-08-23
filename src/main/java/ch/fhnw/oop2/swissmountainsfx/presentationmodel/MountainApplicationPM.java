package ch.fhnw.oop2.swissmountainsfx.presentationmodel;

import ch.fhnw.oop2.swissmountainsfx.dao.JpaDaoFactory;
import ch.fhnw.oop2.swissmountainsfx.dao.MountainAccess;
import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import ch.fhnw.oop2.swissmountainsfx.utils.ImageLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.util.Collections;
import java.util.List;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;

/**
 * Mountain application presentation model, containg properties and logic.
 *
 * @author Linus, Manuel
 */
public class MountainApplicationPM {

	private final MountainAccess mountainAccess;
	private final ImageLoader imageLoader = new ImageLoader();

	// Needed to monitor changes in elements for saving only these elements
	ObservableList<MountainVM> mountainData = FXCollections.observableArrayList((MountainVM param) -> new Observable[] {
			param.mountainIdProperty(), param.mountainnameProperty(), param.heightProperty(),
			param.mountaintypeProperty(), param.regionProperty(), param.cantonsProperty(), param.rangeProperty(),
			param.mountainisolationProperty(), param.isolationpointProperty(), param.prominenceProperty(),
			param.captionProperty(), param.prominencepointProperty(), param.imagenameProperty() });

	HashSet<Long> idsOfUnsavedMountains = new HashSet<>();

	ObservableList<String> sortOptions = FXCollections.observableArrayList();

	private final ObjectProperty<MountainVM> selectedMountain = new SimpleObjectProperty<>();

	private final StringProperty applicationTitle = new SimpleStringProperty("Ultra Swiss Mountain Peaks");

	private final ObjectProperty<javafx.scene.image.Image> mountainImageProperty = new SimpleObjectProperty<>();

	private MountainVM mountainProxy;

	private boolean initialLoad;

	public MountainApplicationPM() {

		mountainAccess = JpaDaoFactory.getInstance().getMountainAccessInstance();

		sortOptions.add("Name aufsteigend");
		sortOptions.add("Name absteigend");
		sortOptions.add("Höhe aufsteigend");
		sortOptions.add("Höhe absteigend");
		sortOptions.add("Gebiet aufsteigend");
		sortOptions.add("Gebiet absteigend");

		setupMountainDataList();

		mountainProxy = new MountainVM();
		setupMountainProxy();
		selectedMountain.set(mountainData.get(0));
		sortByValue("Name aufsteigend");

	}

	/**
	 * Deletes the selected mountain from the datasource and the listview
	 *
	 * @param m
	 *            Mountain to delete
	 */
	public void deleteSelectedMountain(MountainVM m) {
		if (mountainAccess.mountainExists(m)) {
			mountainAccess.deleteMountain(m);
		}

		mountainData.remove(m);
	}

	/**
	 * Saves all selected mountains since the last save
	 */
	public void saveAllMountains() {
		System.out.println("Unsaved Mountains: " + idsOfUnsavedMountains.size());

		for (long id : idsOfUnsavedMountains) {
			MountainVM m = mountainData.stream().filter((MountainVM mn) -> mn.getMountainId() == id).limit(1)
					.findFirst().orElse(null);

			if (m != null) {
				saveMountain(m);
			}
		}

		idsOfUnsavedMountains.clear();
	}

	/**
	 * Saves the given mountain or adds a new mountain to the datasource if the
	 * mountain does not exist
	 *
	 * @param m
	 *            Mountain to save
	 */
	public void saveMountain(MountainVM m) {
		if (mountainAccess.mountainExists(m)) {
			mountainAccess.updateMountain(m);
		} else {
			mountainAccess.addMountain(m);
		}
	}

	/**
	 * Adds a new mountain to the listview. This method does not add the mountain to
	 * the datasource. Call
	 * {@link ch.fhnw.oop2.swissmountainsfx.presentationmodel.MountainApplicationPM#saveMountain(MountainVM m)
	 * saveMountain(MountainVM m)} to add the new mountain to the database.
	 */
	public void addNewMountain() {
		MountainVM newMountain = new MountainVM();
		newMountain.setMountainId(getMaxId() + 1);
		newMountain.setImagename("noPicture.jpg");

		mountainData.add(newMountain);
		selectedMountain.set(mountainData.get(mountainData.size() - 1));
	}

	/*
	 * Sort the mountain-list by specific drop-down selection
	 * 
	 * @return sorted mountain list.
	 */
	public ObservableList<MountainVM> sortByValue(String option) {
		switch (option) {
		case "Name aufsteigend":
			Collections.sort(mountainData, (c1, c2) -> c1.getMountainname().compareToIgnoreCase(c2.getMountainname()));
			break;
		case "Name absteigend":
			Collections.sort(mountainData, (c1, c2) -> c2.getMountainname().compareToIgnoreCase(c1.getMountainname()));
			break;
		case "Höhe aufsteigend":
			Collections.sort(mountainData, (c1, c2) -> Double.compare(c1.getHeight(), c2.getHeight()));
			break;
		case "Höhe absteigend":
			Collections.sort(mountainData, (c1, c2) -> Double.compare(c2.getHeight(), c1.getHeight()));
			break;
		case "Gebiet aufsteigend":
			Collections.sort(mountainData, (c1, c2) -> c1.getRange().compareToIgnoreCase(c2.getRange()));
			break;
		case "Gebiet absteigend":
			Collections.sort(mountainData, (c1, c2) -> c2.getRange().compareToIgnoreCase(c1.getRange()));
			break;
		default:
			break;
		}

		return mountainData;
	}

	/**
	 * Adds a listener to the mountain datalist in order to monitor changes in the
	 * list elements to know which elements need to be saved. Adds all mountains of
	 * the datasource to the mountain data list.
	 */
	private void setupMountainDataList() {
		initialLoad = true;

		mountainData.addListener(new ListChangeListener<MountainVM>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends MountainVM> c) {
				// Do not add elements to save list if the are loaded from the DB at startup
				if (!initialLoad) {
					while (c.next()) {
						if (c.wasUpdated()) {
							for (int i = c.getFrom(); i < c.getTo(); ++i) {
								idsOfUnsavedMountains.add(mountainData.get(i).getMountainId());
							}
						} else {
							for (MountainVM removedItem : c.getRemoved()) {
								System.out.println("Removed: " + removedItem);
							}
							for (MountainVM addedItem : c.getAddedSubList()) {
								System.out.println("Added: " + addedItem);
								idsOfUnsavedMountains.add(addedItem.getMountainId());
							}
						}
					}
				}
			}
		});

		List<MountainVM> allMountains = mountainAccess.getAllMountains();
		allMountains.forEach((m) -> {
			mountainData.add(m);
		});

		initialLoad = false;
	}

	/**
	 * Adds the listener to the selected mountain and bind the elments to the
	 * mountain proxy.
	 */
	private void setupMountainProxy() {
		selectedMountain.addListener((observable, oldValue, newValue) -> {

			if (oldValue != null) {
				mountainProxy.mountainIdProperty().unbindBidirectional(oldValue.mountainIdProperty());
				mountainProxy.mountainnameProperty().unbindBidirectional(oldValue.mountainnameProperty());
				mountainProxy.heightProperty().unbindBidirectional(oldValue.heightProperty());
				mountainProxy.mountaintypeProperty().unbindBidirectional(oldValue.mountaintypeProperty());
				mountainProxy.regionProperty().unbindBidirectional(oldValue.regionProperty());
				mountainProxy.cantonsProperty().unbindBidirectional(oldValue.cantonsProperty());
				mountainProxy.rangeProperty().unbindBidirectional(oldValue.rangeProperty());
				mountainProxy.mountainisolationProperty().unbindBidirectional(oldValue.mountainisolationProperty());
				mountainProxy.isolationpointProperty().unbindBidirectional(oldValue.isolationpointProperty());
				mountainProxy.prominenceProperty().unbindBidirectional(oldValue.prominenceProperty());
				mountainProxy.captionProperty().unbindBidirectional(oldValue.captionProperty());
				mountainProxy.prominencepointProperty().unbindBidirectional(oldValue.prominencepointProperty());
				mountainProxy.imagenameProperty().unbindBidirectional(oldValue.imagenameProperty());
			}

			if (newValue != null) {
				mountainProxy.mountainIdProperty().bindBidirectional(newValue.mountainIdProperty());
				mountainProxy.mountainnameProperty().bindBidirectional(newValue.mountainnameProperty());
				mountainProxy.heightProperty().bindBidirectional(newValue.heightProperty());
				mountainProxy.mountaintypeProperty().bindBidirectional(newValue.mountaintypeProperty());
				mountainProxy.regionProperty().bindBidirectional(newValue.regionProperty());
				mountainProxy.cantonsProperty().bindBidirectional(newValue.cantonsProperty());
				mountainProxy.rangeProperty().bindBidirectional(newValue.rangeProperty());
				mountainProxy.mountainisolationProperty().bindBidirectional(newValue.mountainisolationProperty());
				mountainProxy.isolationpointProperty().bindBidirectional(newValue.isolationpointProperty());
				mountainProxy.prominenceProperty().bindBidirectional(newValue.prominenceProperty());
				mountainProxy.captionProperty().bindBidirectional(newValue.captionProperty());
				mountainProxy.prominencepointProperty().bindBidirectional(newValue.prominencepointProperty());
				mountainProxy.imagenameProperty().bindBidirectional(newValue.imagenameProperty());

				// Reload the image
				loadMountainImage();
			}
		});
	}

	/**
	 * Updates the mountain imageproperty by loading mountain image of the currently
	 * selected mountain
	 */
	private void loadMountainImage() {
		Image image;
		try {
			image = new Image(new FileInputStream(imageLoader.getMountainImagePath(mountainProxy.getImagename())));
			mountainImageProperty.set(image);
		} catch (FileNotFoundException ex) {
			System.out.println("Image loading failed!");
		}
	}

	/**
	 * The method returns the highest mountain id which is currently in the mountain
	 * list.
	 *
	 * @return highest found mountain id
	 */
	private long getMaxId() {
		long maxId = 0;
		long id;

		for (MountainVM mountainVM : mountainData) {
			id = mountainVM.getMountainId();

			if (id > maxId) {
				maxId = id;
			}
		}

		return maxId;
	}

	public MountainVM getMountaionProxy() {
		return mountainProxy;
	}

	public MountainVM getSelectedMountain() {
		return selectedMountain.get();
	}

	public ObjectProperty<MountainVM> selectedMountainProperty() {
		return selectedMountain;
	}

	public void setSelectedMountain(MountainVM mountain) {
		this.selectedMountain.set(mountain);
	}

	public ObservableList<MountainVM> getMountainData() {
		return mountainData;
	}

	public ObservableList<String> getSortOptions() {
		return sortOptions;
	}

	public String getApplicationTitle() {
		return applicationTitle.get();
	}

	public StringProperty applicationTitleProperty() {
		return applicationTitle;
	}

	public void setApplicationTitle(String applicationTitle) {
		this.applicationTitle.set(applicationTitle);
	}

	public Image getMountainImage() {
		return mountainImageProperty.get();
	}

	public ObjectProperty<Image> mountainImageProperty() {
		return mountainImageProperty;
	}

	public void setMountainImage(Image image) {
		this.mountainImageProperty.set(image);
	}

	public HashSet<Long> getIdsOfUnsavedMountains() {
		return idsOfUnsavedMountains;
	}

}
