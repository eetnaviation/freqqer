package eu.velend.freqqer.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.net.InetSocketAddress;

public class FreqqerClient implements ClientModInitializer {
    private static String serverIp;
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player != null) {
                ClientPlayNetworkHandler networkHandler = mc.getNetworkHandler();
                if (networkHandler != null) {
                    serverIp = networkHandler.getConnection().getAddress().toString();
                }

                mc.player.sendMessage(Text.of(("[FREQQER] Connected to server, Got IP: " + serverIp)), false);
            }
        });
    }
}