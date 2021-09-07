package me.alien.speedomiter;

import dev.toma.configuration.api.IConfigPlugin;
import dev.toma.configuration.api.IConfigWriter;
import dev.toma.configuration.api.IObjectSpec;
import dev.toma.configuration.api.Restrictions;
import dev.toma.configuration.api.type.*;
import dev.toma.configuration.internal.ObjectSpec;
import me.alien.speedomiter.config.Combat;
import me.alien.speedomiter.config.Speedometer;
import net.minecraftforge.fml.config.IConfigSpec;

import java.util.function.Function;
import java.util.regex.Pattern;

@dev.toma.configuration.api.Config
public class Config implements IConfigPlugin {

    public static Speedometer speedometer;
    public static Combat combat;

    @Override
    public void buildConfig(IConfigWriter writer) {
        speedometer = writer.writeObject(spec -> new Speedometer(spec), "Speedometer config", "");
        combat = writer.writeObject(spec -> new Combat(spec), "Combat config", "");
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
