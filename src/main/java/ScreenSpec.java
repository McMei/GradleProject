/**
 * This is an interface for Multimedia Control.
 *
 * @author Michelle Quan
 */
public interface ScreenSpec {

    /**
     * Gets resolution of the Screen.
     *
     * @return An integer representing the screen resolution.
     */
    String getResolution();

    /**
     * Gets refresh rate of the Screen.
     *
     * @return An integer representing the refresh rate of the screen.
     */
    int getRefreshRate();

    /**
     * Gets response time of the Screen.
     *
     * @return An integer representing the response time of the Screen.
     */
    int getResponseTime();
}
