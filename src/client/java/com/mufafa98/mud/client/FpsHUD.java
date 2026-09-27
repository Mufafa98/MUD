package com.mufafa98.mud.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class FpsHUD implements HUDInterface {

    private final FpsConfig config;

    public FpsHUD() {
        this.config = Config.getInstance().getFpsConfig();
    }

    @Override
    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        if (!config.enabled)
            return;

        Minecraft client = Minecraft.getInstance();
        String text = config.showLabel ? "FPS: " + client.getFps() : String.valueOf(client.getFps());

        int textWidth = client.font.width(text);
        int textHeight = client.font.lineHeight;
        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        FpsConfig.ScreenCorner corner = config.corner != null ? config.corner : FpsConfig.ScreenCorner.TOP_LEFT;

        int x, y;
        switch (corner) {
            case TOP_RIGHT:
                x = screenWidth - textWidth - config.margin;
                y = config.margin;
                break;
            case BOTTOM_LEFT:
                x = config.margin;
                y = screenHeight - textHeight - config.margin;
                break;
            case BOTTOM_RIGHT:
                x = screenWidth - textWidth - config.margin;
                y = screenHeight - textHeight - config.margin;
                break;
            case TOP_LEFT:
            default:
                x = config.margin;
                y = config.margin;
                break;
        }

        if (config.showBackground) {
            int p = config.backgroundPadding;
            graphics.fill(x - p, y - p, x + textWidth + p, y + textHeight + p, config.backgroundColor);
        }

        graphics.text(client.font, text, x, y, config.textColor);
    }
}
