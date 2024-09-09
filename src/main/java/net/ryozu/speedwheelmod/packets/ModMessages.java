package net.ryozu.speedwheelmod.packets;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.ryozu.speedwheelmod.SpeedWheelMod;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(SpeedWheelMod.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(SpeedUpC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SpeedUpC2SPacket::new)
                .encoder(SpeedUpC2SPacket::toBytes)
                .consumerMainThread(SpeedUpC2SPacket::handle)
                .add();
        net.messageBuilder(SpeedDownC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SpeedDownC2SPacket::new)
                .encoder(SpeedDownC2SPacket::toBytes)
                .consumerMainThread(SpeedDownC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
