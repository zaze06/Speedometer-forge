package me.alien.speedomiter;

import ca.weblite.objc.Proxy;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static final ForgeConfigSpec clientConfig;
    public static final Config.Client CLIENT;

    static {
        final Pair<Client, ForgeConfigSpec> clientConfigPair = new ForgeConfigSpec.Builder().configure(Config.Client::new);
        clientConfig = clientConfigPair.getRight();
        CLIENT = clientConfigPair.getLeft();
    }

    public static void saveClientConfig(){
        clientConfig.save();
    }

    public static class Client{
        public static ForgeConfigSpec.ConfigValue color;
        public static ForgeConfigSpec.BooleanValue useKnotInBoat;
        public static ForgeConfigSpec.BooleanValue enabled;
        public static ForgeConfigSpec.ConfigValue speedType;
        public static ForgeConfigSpec.ConfigValue xPos;
        public static ForgeConfigSpec.ConfigValue yPos;

        public Client(ForgeConfigSpec.Builder builder){
            builder.push("client");
            {
                enabled = builder.comment("If the speedomiter shuld be shown or not").define("show", true);
                color = builder.comment("The color you wish for the text that is displaid").define("color", "#10929e");
                useKnotInBoat = builder.comment("Shuld it display the spped in know when in a boat").define("use knot in boat", true);
                speedType = builder.comment("The speed type to use", "Blocks/s", "km/h", "mph", "m/s", "Will defualt to m/s").define("speedType", "m/s");
                xPos = builder.comment("X position of the speedometer", "W = the width of the screen", "w = half the width of the screen").define("xPos", "W-70");
                yPos = builder.comment("Y position of the speedometer", "H = the height of the screen", "h = half the height of the screen").define("yPos", "H-15");
                yPos = builder.comment("Y position of the speedometer", "H = the height of the screen", "h = half the height of the screen").define("yPos", "H-15");
            }
            builder.pop();
        }
    }
}
