package net.ryozu.speedwheelmod.speedmod;

import net.minecraft.nbt.CompoundTag;
import net.ryozu.speedwheelmod.Config;

public class PlayerSpeedMod {
    private double speed = bound(100, Config.speedMin, Config.speedMax);

    public double bound(double value, double min, double max) {
        return Math.min(Math.max(min, value), max);
    }

    public double getSpeed() {
        //Technically, 0.0 is natural speed, so -1 = no movement, and +1 = double movement.
        // Config values however treat 100 as natural speed and 200 as double (IE percentage)
        return (speed - 100) / 100.0;
    }

    public int getSpeedPercent() {
        // Calculate the range between Config.speedMin and Config.speedMax
        double speedRange = Config.speedMax - Config.speedMin;

        // Calculate the current speed's position within that range
        double speedPosition = speed - Config.speedMin;

        // The percentage of the range that the current speed represents
        double percentage = (speedPosition / speedRange) * 100;

        // Return the percentage as an integer (rounded if needed)
        return (int)Math.round(percentage);
    }


    public boolean speedUp() {
        double oldSpeed = speed;
        speed = bound(speed + Config.speedStep, Config.speedMin, Config.speedMax);
        return speed != oldSpeed;
    }

    public boolean speedDown() {
        double oldSpeed = speed;
        speed = bound(speed - Config.speedStep, Config.speedMin, Config.speedMax);
        return speed != oldSpeed;
    }

    public void copyFrom(PlayerSpeedMod source) {
        this.speed = source.speed;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putDouble("speedMod", speed);
    }

    public void loadNBTData(CompoundTag nbt) {
        speed = nbt.getDouble("speedMod");
    }


}
