package net.ryozu.speedwheelmod.speedmod;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerSpeedProvider  implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerSpeedMod> SPEED_MOD = CapabilityManager.get(new CapabilityToken<PlayerSpeedMod>() {});

    private PlayerSpeedMod speed = null;

    private final LazyOptional<PlayerSpeedMod> optional = LazyOptional.of(this::createPlayerSpeed);

    private @NotNull PlayerSpeedMod createPlayerSpeed() {
        if(this.speed == null) {
            speed = new PlayerSpeedMod();
        }
        return this.speed;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == SPEED_MOD) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerSpeed().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerSpeed().loadNBTData(nbt);

    }
}
