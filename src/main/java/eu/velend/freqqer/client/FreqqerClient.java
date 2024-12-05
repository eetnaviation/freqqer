package eu.velend.freqqer.client;

import io.netty.buffer.ByteBuf;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.*;
import net.minecraft.util.Identifier;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

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

//        String chatMessage = packet.getContent().getString();
//        System.out.println("Server Message: " + chatMessage);

        /*ClientPlayNetworking.registerGlobalReceiver(Identifier.of("example:log_packets"), (client, handler, buf, responseSender) -> {
            MinecraftClient.getInstance().execute(() -> {
                PacketByteBuf packetByteBuf = new(bytes.buffer);
                packetByteBuf.setBytes(buf);
                ByteBuf byteBuf = new ByteBuf();

                PacketUtils packetUtils = new PacketUtils();

                byte[] packetDataBytes = packetUtils.ReadPacketByteBufDataArray(packetByteBuf);

                String packetData = new String(packetDataBytes, StandardCharsets.UTF_8);
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player != null) {
                    mc.player.sendMessage(Text.literal("Packet received: " + packetData), false);
                }
            });
        });*/
    }
}