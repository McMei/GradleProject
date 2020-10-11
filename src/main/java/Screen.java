public class Screen implements ScreenSpec {

    private String resolution;
    private int refreshrate = 0;
    private int responsetime = 0;

    Screen(String resolution, int refreshrate, int responsetime) {
        this.resolution = resolution;
        this.refreshrate = refreshrate;
        this.responsetime = responsetime;
    }

    public String getResolution() {
        return resolution;
    }

    public int getRefreshRate() {
        return refreshrate;
    }

    public int getResponseTime() {
        return responsetime;
    }

    public String toString(){
        return "\nScreen:" + "\nResolution: " + getResolution()
                + "\nRefresh rate: " + getRefreshRate() + "\nResponse time: " + getResponseTime();
    }
}
