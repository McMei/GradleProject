/**
 * Product types that can be used.
 *
 * @author Michelle Quan
 */
public enum ItemType {

    /**
     * AUDIO
     */
    AUDIO("AU"),

    /**
     * VISUAL
     */
    VISUAL("VI"),

    /**
     * AUDIO_MOBILE
     */
    AUDIO_MOBILE("AM"),

    /**
     * VISUAL_MOBILE
     */
    VISUAL_MOBILE("VM");

    /**
     * Abbreviation code.
     */
    private final String code;

    /**
     * Creates an abbreviation code.
     *
     * @param code Item type.
     */
    ItemType(String code) {
        this.code = code;
    }

    /**
     * Gets product code.
     *
     * @return A String representing the code.
     */
    public String getCode() {
        return code;
    }


}
