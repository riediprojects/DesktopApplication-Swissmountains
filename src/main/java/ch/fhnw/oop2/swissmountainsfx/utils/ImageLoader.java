package ch.fhnw.oop2.swissmountainsfx.utils;

/**
 * Helper class to load images.
 *
 * @author Linus
 */
public class ImageLoader {

    /**
     * Use this method to get the path to the image for the given mountain id
     *
     * @param imagename name of the image for the mountain
     * @return path to the image for the fiven mountain id or the path to a
     * default image if no image for the given id was found
     */
    public String getMountainImagePath(String imagename) {
        String fullPath;

        try {
            fullPath = getClass().getResource("/ch/fhnw/oop2/swissmountainsfx/resources/mountainpictures/" + imagename).getFile();
        } catch (Exception e) {
            fullPath = getClass().getResource("/ch/fhnw/oop2/swissmountainsfx/resources/mountainpictures/noPicture.jpg").getFile();
        }

        return fullPath;
    }
}
