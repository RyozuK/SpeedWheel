package net.ryozu.speedwheelmod.keybinds;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class Keybinds {
    private static Keybinds instance;

    private static final String CATEGORY = "key.categories.speedwheelmod";
    private static final String MOD_KEY = "key.speedwheelmod.speed_modifier_key";
    private static final String UP_KEY = "key.speedwheelmod.speed_up_key";
    private static final String DOWN_KEY = "key.speedwheelmod.speed_down_key";

    public final KeyMapping speed_mod_key = new KeyMapping(
            MOD_KEY,
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_LALT, -1),
            CATEGORY
    );

    public final KeyMapping speed_up_key = new KeyMapping(
            UP_KEY,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.MOUSE,
            241,
            CATEGORY
    );

    public final KeyMapping speed_down_key = new KeyMapping(
            DOWN_KEY,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.MOUSE,
            242,
            CATEGORY
    );

    private Keybinds() {
    }

    public static Keybinds getInstance() {
        if (instance == null) {
            instance = new Keybinds();
        }
        return instance;
    }
}
