package net.videki.templateutils.template.core.configuration;

/**
 * Configurable font styles.
 *
 * @see FontConfig
 *
 * @author Levente Ban
 */
public enum FontStyle {

    BOLD("bold"),
    BOLD_ITALIC("boldItalic"),
    ITALIC("italic"),
    NORMAL("normal");

    private String value;

    FontStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
