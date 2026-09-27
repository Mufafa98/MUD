package com.mufafa98.mud.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mufafa98.mud.MUD;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class MUDClient implements ClientModInitializer {
  private static KeyMapping configKeyBinding;
  private static final KeyMapping.Category CATEGORY =
      KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MUD.MOD_ID, "mud"));

  @Override
  public void onInitializeClient() {
    HUDInterface[] elements = {new ArmorHUD(), new FpsHUD()};

    configKeyBinding =
        KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                "key.MUD.config",
                InputConstants.Type.KEYBOARD,
                InputConstants.KEY_RSHIFT,
                CATEGORY));

    ClientTickEvents.END_CLIENT_TICK.register(
        client -> {
          while (configKeyBinding.consumeClick()) {
            client.gui.setScreen(new CenteredDashboardScreen(client.gui.screen()));
          }
        });

    HudElementRegistry.attachElementBefore(
        VanillaHudElements.HOTBAR,
        Identifier.fromNamespaceAndPath(MUD.MOD_ID, "armor_layer"),
        elements[0]::render);

		HudElementRegistry.attachElementBefore(
        VanillaHudElements.HOTBAR,
        Identifier.fromNamespaceAndPath(MUD.MOD_ID, "fps_layer"),
        elements[1]::render);
  }
}
