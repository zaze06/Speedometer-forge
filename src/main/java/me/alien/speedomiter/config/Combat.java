package me.alien.speedomiter.config;

import dev.toma.configuration.api.IConfigWriter;
import dev.toma.configuration.api.IObjectSpec;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ObjectType;

public class Combat extends ObjectType {

    public static BooleanType isEnabled;

    public Combat(IObjectSpec spec) {
        super(spec);

        IConfigWriter writer = spec.getWriter();

        isEnabled = writer.writeBoolean("Enable the combat", true, "If the combat shold be enabled or not");

    }
}
