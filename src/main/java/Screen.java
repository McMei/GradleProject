/**
 * Represents a Screen of MoviePlayer.
 * Implements ScreenSpec.
 *
 * @author Michelle Quan
 */
public class Screen implements ScreenSpec {

    /**
     * Resolution of the Screen.
     */
    private final String resolution;

    /**
     * Refresh rate of the Screen.
     */
    private final int refreshRate;

    /**
     * Response time of the Screen.
     */
    private final int responseTime;


    /**
     * Creates a Screen with resolution,refresh rate, and response time.
     *
     * @param resolution   Screen resolution.
     * @param refreshRate  Screen refresh rate.
     * @param responseTime Screen response time.
     */
    Screen(String resolution, int refreshRate, int responseTime) {
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
    }

    /**
     * Gets resolution of the Screen.
     *
     * @return An integer representing the screen resolution.
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Gets refresh rate of the Screen.
     *
     * @return An integer representing the refresh rate of the screen.
     */
    public int getRefreshRate() {
        return refreshRate;
    }

    /**
     * Gets response time of the Screen.
     *
     * @return An integer representing the response time of the Screen.
     */
    public int getResponseTime() {
        return responseTime;
    }

    /**
     * Gets screen resolution, refresh rate and response time.
     *
     * @return A String with Screen information.
     */
    public String toString() {
        return "\nScreen:" + "\nResolution: " + getResolution()
                + "\nRefresh rate: " + getRefreshRate() + "\nResponse time: " + getResponseTime();
    }
}
