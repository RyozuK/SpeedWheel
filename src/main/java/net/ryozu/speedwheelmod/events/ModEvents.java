package net.ryozu.speedwheelmod.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ryozu.speedwheelmod.SpeedWheelMod;
import net.ryozu.speedwheelmod.speedmod.PlayerSpeedMod;
import net.ryozu.speedwheelmod.speedmod.PlayerSpeedProvider;

@Mod.EventBusSubscriber(modid = SpeedWheelMod.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerSpeedProvider.SPEED_MOD).isPresent()) {
                event.addCapability(new ResourceLocation(SpeedWheelMod.MODID, "properties"), new PlayerSpeedProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {

            event.getOriginal().getCapability(PlayerSpeedProvider.SPEED_MOD).ifPresent(oldCap -> {
                event.getOriginal().getCapability(PlayerSpeedProvider.SPEED_MOD).ifPresent(newCap -> {
                    newCap.copyFrom(oldCap);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerSpeedMod.class);
    }

}
