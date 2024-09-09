package net.ryozu.speedwheelmod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = SpeedWheelMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue SPEED_MAX = BUILDER
            .comment("Maximum speed multiplier for player. 100 is normal speed, 200 is double speed")
            .defineInRange("speedMax", 110, 100, 1000);
    private static final ForgeConfigSpec.IntValue SPEED_MIN = BUILDER
            .comment("Minimum speed multiplier, 0 means not moving")
            .defineInRange("speedMin", 25, 0, 100);
    private static final ForgeConfigSpec.IntValue SPEED_STEP = BUILDER
            .comment("Amount to change the speed multiplier each time.")
            .defineInRange("speedStep", 5, 1,100);
    private static final ForgeConfigSpec.BooleanValue MOD_KEY = BUILDER
            .comment("Is mod key needed for keybinds? (Always needed for mouse wheel)")
            .define("needsModKey", false);
    private static final ForgeConfigSpec.IntValue CONFIG_VERSION = BUILDER
            .comment("Used to invalidate old configs, changing this value will cause a config reset")
            .defineInRange("configVer", 0, 0, 1000);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int speedMax;
    public static int speedMin;
    public static int speedStep;
    public static boolean needsModKey;
    public static int configVer;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        int targetVersion = 1;
        configVer = CONFIG_VERSION.get();

        if (configVer != targetVersion) {
            CONFIG_VERSION.set(targetVersion);
            SPEED_MAX.set(SPEED_MAX.getDefault());
            SPEED_MIN.set(SPEED_MIN.getDefault());
            SPEED_STEP.set(SPEED_STEP.getDefault());
            CONFIG_VERSION.save();
            SPEED_MAX.save();
            SPEED_MIN.save();
            SPEED_STEP.save();
        }

        speedMax = SPEED_MAX.get();
        speedMin = SPEED_MIN.get();
        speedStep = SPEED_STEP.get();
        needsModKey = MOD_KEY.get();
    }
}
