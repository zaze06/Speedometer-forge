package me.alien.speedomiter;

import dev.toma.configuration.api.IConfigPlugin;
import dev.toma.configuration.api.IConfigWriter;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.StringType;

@dev.toma.configuration.api.Config
public class Config implements IConfigPlugin {
    public static ColorType speedColor;
    public static IntType speedType;
    public static BooleanType useKnotInBoat;
    public static StringType xPos;
    public static StringType yPos;

    @Override
    public void buildConfig(IConfigWriter writer) {
        writer.writeString("Thx to user aqswde40 on curseforge for suggesting the idea of having this config", "");
        speedColor = writer.writeColorRGB("Color of your speed", "#10929e", "This is the color of the text that shows you your speed");
        speedType = writer.writeBoundedInt("Speed displacement type", 1, 1, 4, "1: Meter/s", "2: Blocks/s", "3: km/h", "4: mph");
        useKnotInBoat = writer.writeBoolean("Use knot when your in a boat", true, "If this is true when you ar in a boat the speed is displayed in knot");
        xPos = writer.writeString("X position of the speedometer", "W-70", "W = the width of the screen", "w = half the width of the screen");
        yPos = writer.writeString("Y position of the speedometer", "H-15", "H = the height of the screen", "h = half the height of the screen");
    }

    @Override
    public String getConfigFileName() {
        return "Speedometer";
    }

    @Override
    public String getModID() {
        return Main.MODID;
    }
}
