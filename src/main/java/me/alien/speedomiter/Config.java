package me.alien.speedomiter;

import dev.toma.configuration.api.IConfigPlugin;
import dev.toma.configuration.api.IConfigWriter;
import dev.toma.configuration.api.INameable;
import dev.toma.configuration.api.Restrictions;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.StringType;

import javax.swing.plaf.ColorUIResource;
import java.util.regex.Pattern;

@dev.toma.configuration.api.Config
public class Config implements IConfigPlugin {
    public static ColorType speedColor;
    public static IntType speedType;
    public static BooleanType useKnotInBoat;

    @Override
    public void buildConfig(IConfigWriter writer) {
        speedColor = writer.writeColorRGB("Color of your speed", "#10929e", "This is the color of the text that shows you your speed");
        speedType = writer.writeBoundedInt("Speed displacement type", 1, 1, 4, "1: Meter/s", "2: Blocks/s", "3: km/h", "4: mph");
        useKnotInBoat = writer.writeBoolean("Use knot when your in a boat", true, "If this is true when you ar in a boat the speed is displayed in knot");
    }

    @Override
    public String getConfigFileName() {
        return "SpeedometerConfig";
    }

    @Override
    public String getModID() {
        return Main.MODID;
    }
}
