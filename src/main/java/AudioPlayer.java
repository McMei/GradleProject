public class AudioPlayer extends Product implements MultimediaControl {

    private String supportedAudioFormats;
    private String supportedPlaylistFormats;

    AudioPlayer(String name, String manufacturer, String supportedAudioFormats, String supportedPlaylistFormats) {
        super(name, manufacturer, ItemType.AUDIO);
        this.supportedAudioFormats = supportedAudioFormats;
        this.supportedPlaylistFormats = supportedPlaylistFormats;
    }

    public void play() {
        System.out.println("Playing");
    }

    public void stop() {
        System.out.println("Stopped playing");
    }

    public void previous() {
        System.out.println("Previous song");
    }

    public void next() {
        System.out.println("Next song");
    }

    public String toString() {
        return super.toString() + "\nSupportedAudioFormats: " + supportedAudioFormats
                + "\nsupportedPlaylistFormats: " + supportedPlaylistFormats;
    }
}
