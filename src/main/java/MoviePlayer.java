/**
 * Represents a Movie Player.
 * Implements MultimediaControl.
 * Extends Product.
 *
 * @author Michelle Quan
 */
public class MoviePlayer extends Product implements MultimediaControl {

    /**
     * Screen of the Movie Player.
     */
    private final Screen screen;

    /**
     * Monitor Type of the Movie Player.
     */
    private final MonitorType monitorType;

    /**
     * Creates a Movie Player with name, manufacturer,screen, and monitorType
     *
     * @param name         The name of the movie player.
     * @param manufacturer The manufacturer of the movie player.
     * @param screen       The screen of the movie player.
     * @param monitorType  The monitorType of the movie player.
     */
    MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
        super(name, manufacturer, ItemType.VISUAL);
        this.screen = screen;
        this.monitorType = monitorType;
    }

    /**
     * Prints out "Playing".
     */
    public void play() {
        System.out.println("Playing");
    }

    /**
     * Prints out "Stopped playing".
     */
    public void stop() {
        System.out.println("Stopped Playing");
    }

    /**
     * Prints out "Previous movie".
     */
    public void previous() {
        System.out.println("Previous Movie");
    }

    /**
     * Prints out "Next movie".
     */
    public void next() {
        System.out.println("Next Movie");
    }

    /**
     * Gets information about MoviePlayer.
     *
     * @return A Sting with information about MoviePlayer.
     */
    public String toString() {
        return super.toString() + screen.toString() + "\nMonitor Type: " + monitorType;
    }
}
