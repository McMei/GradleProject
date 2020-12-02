public class MoviePlayer extends Product implements MultimediaControl {

    private final Screen screen;
    private final MonitorType monitorType;

    MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
        super(name, manufacturer, ItemType.VISUAL);
        this.screen = screen;
        this.monitorType = monitorType;
    }

    public void play() {
        System.out.println("Playing");
    }

    public void stop() {
        System.out.println("Stopping");
    }

    public void previous() {
        System.out.println("Previous");
    }

    public void next() {
        System.out.println("Next");
    }

    public String toString() {
        return super.toString() + screen.toString() + "\nMonitor Type: " + monitorType;
    }
}
