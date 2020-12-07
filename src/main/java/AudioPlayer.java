/**
 * Represents a Audio Player.
 * Implements MultimediaControl.
 * Extends Product.
 *
 * @author Michelle Quan
 */
public class AudioPlayer extends Product implements MultimediaControl {

    /**
     * Audio Formats.
     */
    private final String supportedAudioFormats;

    /**
     * Playlist Formats.
     */
    private final String supportedPlaylistFormats;

    /**
     * Creates a Audio Player with name, manufacturer, Audio Formats, and Playlist Formats.
     *
     * @param name                     The name of the audio player.
     * @param manufacturer             The manufacturer of the audio player.
     * @param supportedAudioFormats    The audio specification for the audio player
     * @param supportedPlaylistFormats The media type it is able to play.
     */
    AudioPlayer(String name, String manufacturer, String supportedAudioFormats, String supportedPlaylistFormats) {
        super(name, manufacturer, ItemType.AUDIO);
        this.supportedAudioFormats = supportedAudioFormats;
        this.supportedPlaylistFormats = supportedPlaylistFormats;
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
        System.out.println("Stopped playing");
    }

    /**
     * Prints out "Previous song".
     */
    public void previous() {
        System.out.println("Previous song");
    }

    /**
     * Prints out "Next song".
     */
    public void next() {
        System.out.println("Next song");
    }

    /**
     * Gets audio formats and playlist formats.
     *
     * @return A String with audio player formats.
     */
    @Override
    public String toString() {
        return super.toString() + "\nSupportedAudioFormats: " + supportedAudioFormats
                + "\nsupportedPlaylistFormats: " + supportedPlaylistFormats;
    }
}
