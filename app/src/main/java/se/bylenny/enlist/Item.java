package se.bylenny.enlist;

public class Item {
    private int background;
    private int color;
    private String string;

    public Item(int color, int background, String string) {
        this.color = color;
        this.background = background;
        this.string = string;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
