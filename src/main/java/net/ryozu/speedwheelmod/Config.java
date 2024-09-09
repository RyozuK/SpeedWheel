package net.ryozu.speedwheelmod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = SpeedWheelMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.DoubleValue SPEED_MAX = BUILDER
            .comment("Maximum speed multiplier for player. 0.0 is normal speed, 1.0 is double speed")
            .defineInRange("speedMax", 0.1, -1.0, 10.0);
    private static final ForgeConfigSpec.DoubleValue SPEED_MIN = BUILDER
            .comment("Minimum speed multiplier, -1.0 means not moving")
            .defineInRange("speedMin", -0.75, -1.0, 0.0);
    private static final ForgeConfigSpec.DoubleValue SPEED_STEP = BUILDER
            .comment("Amount to change the speed multiplier each time.")
            .defineInRange("speedStep", 0.05, 0.01,10.0);
    private static final ForgeConfigSpec.BooleanValue MOD_KEY = BUILDER
            .comment("Is mod key needed for keybinds? (Always needed for mouse wheel)")
            .define("needsModKey", false);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static double speedMax;
    public static double speedMin;
    public static double speedStep;
    public static boolean needsModKey;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        speedMax = SPEED_MAX.get();
        speedMin = SPEED_MIN.get();
        speedStep = SPEED_STEP.get();
        needsModKey = MOD_KEY.get();
    }
}
