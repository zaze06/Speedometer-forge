package me.alien.speedomiter;

import me.alien.speedomiter.events.Events;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MODID)
public class Main {

    public static final String MODID = "speedometer";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger("Speedometer");

    public Main() {
        // Register the setup method for modloading
        MinecraftForge.EVENT_BUS.register(Events.class);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientConfig);
    }
}
