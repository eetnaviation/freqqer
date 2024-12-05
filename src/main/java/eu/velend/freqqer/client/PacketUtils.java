package eu.velend.freqqer.client;

import net.minecraft.network.PacketByteBuf;

public class PacketUtils {
    public byte[] ReadPacketByteBufDataArray(PacketByteBuf pbb) {
        byte[] bytes = pbb.readByteArray(32767);
        return bytes;
    }
}
