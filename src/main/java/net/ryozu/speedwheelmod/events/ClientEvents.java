package net.ryozu.speedwheelmod.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ryozu.speedwheelmod.Config;
import net.ryozu.speedwheelmod.keybinds.Keybinds;
import net.ryozu.speedwheelmod.packets.ModMessages;
import net.ryozu.speedwheelmod.SpeedWheelMod;
import net.ryozu.speedwheelmod.packets.SpeedDownC2SPacket;
import net.ryozu.speedwheelmod.packets.SpeedUpC2SPacket;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = SpeedWheelMod.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        public static int id = 0;

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
                if (Keybinds.getInstance().speed_up_key.consumeClick()
                        && (Keybinds.getInstance().speed_mod_key.isDown() || !Config.needsModKey)) {
                    //Minecraft.getInstance().player.sendSystemMessage(Component.literal(++id + "Pressed UP key"));
                    ModMessages.sendToServer(new SpeedUpC2SPacket());
                }
                if (Keybinds.getInstance().speed_down_key.consumeClick()
                        && (Keybinds.getInstance().speed_mod_key.isDown() || !Config.needsModKey)) {
                    //Minecraft.getInstance().player.sendSystemMessage(Component.literal(++id + "Pressed DOWN key"));
                    ModMessages.sendToServer(new SpeedDownC2SPacket());
                }
        }

        @SubscribeEvent
        public static void onMouseScrolled(InputEvent.MouseScrollingEvent event) {
            if(Keybinds.getInstance().speed_mod_key.isDown()) {
                if (event.getScrollDelta() < 0) {
                    ModMessages.sendToServer(new SpeedDownC2SPacket());
                    event.setCanceled(true);
                }
                if (event.getScrollDelta() > 0) {
                    ModMessages.sendToServer(new SpeedUpC2SPacket());
                    event.setCanceled(true);
                }
            }
        }
    }

    @Mod.EventBusSubscriber(modid = SpeedWheelMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinds.getInstance().speed_mod_key);
            event.register(Keybinds.getInstance().speed_up_key);
            event.register(Keybinds.getInstance().speed_down_key);
        }
    }
}
