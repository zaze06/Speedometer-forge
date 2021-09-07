package me.alien.speedomiter.config;

import dev.toma.configuration.api.IConfigWriter;
import dev.toma.configuration.api.INameable;
import dev.toma.configuration.api.IObjectSpec;
import dev.toma.configuration.api.NumberDisplayType;
import dev.toma.configuration.api.type.*;
import me.alien.speedomiter.Config;

public class Speedometer extends ObjectType {

    public static ColorType speedColor;
    public static IntType speedType;
    public static BooleanType useKnotInBoat;
    public static StringType xPos;
    public static StringType yPos;
    public static BooleanType isEnabled;

    public Speedometer(IObjectSpec spec) {
        super(spec);

        IConfigWriter writer = spec.getWriter();

        isEnabled = writer.writeBoolean("Enable the speedometer", true, "If the speedometer shold be enabled or not");
        speedColor = writer.writeColorRGB("Color of your speed", "#10929e", "This is the color of the text that shows you your speed");
        speedType =
        //writer.writeEnum("Speed displacement type", Speeds.mps, "What format shuld the speed be displad as");
        writer.writeBoundedInt("Speed displacement type", 1, 1, 4, "1: Meter/s", "2: Blocks/s", "3: km/h", "4: mph").setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER);
        useKnotInBoat = writer.writeBoolean("Use knot when your in a boat", true, "If this is true when you ar in a boat the speed is displayed in knot");
        xPos = writer.writeString("X position of the speedometer", "W-70", "W = the width of the screen", "w = half the width of the screen");
        yPos = writer.writeString("Y position of the speedometer", "H-15", "H = the height of the screen", "h = half the height of the screen");
    }
}
