package net.ryozu.speedwheelmod.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;
import net.ryozu.speedwheelmod.SpeedWheelMod;
import net.ryozu.speedwheelmod.speedmod.PlayerSpeedProvider;

import java.util.function.Supplier;

public class SpeedUpC2SPacket
{
    public SpeedUpC2SPacket() {}

    public SpeedUpC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            player.getCapability(PlayerSpeedProvider.SPEED_MOD).ifPresent(speed -> {
                if(speed.speedUp()) {
                    player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SpeedWheelMod.SPEED_MOD_UUID);
                    AttributeModifier speed_attrib = new AttributeModifier(SpeedWheelMod.SPEED_MOD_UUID, "speed_mult", speed.getSpeed(), AttributeModifier.Operation.MULTIPLY_BASE);
                    player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(speed_attrib);
                }
//                player.sendSystemMessage(Component.literal(speed.getSpeed() + " Speed"));
            });
        });
        return true;
    }
}
