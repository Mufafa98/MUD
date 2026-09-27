package com.mufafa98.mud.client;

public class FpsConfig {
    public boolean enabled = true;
    public boolean showLabel = true; // "FPS: 60" vs just "60"
    public int margin = 4;
    public int textColor = 0xFFFFFFFF;

    public boolean showBackground = false;
    public int backgroundColor = 0x80000000;
    public int backgroundPadding = 2;

    public ScreenCorner corner = ScreenCorner.TOP_LEFT;

    public FpsConfig() {
    }

    public enum ScreenCorner {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }
}
