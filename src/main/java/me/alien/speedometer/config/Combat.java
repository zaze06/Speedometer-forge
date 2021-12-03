package me.alien.speedometer.config;

import dev.toma.configuration.api.IConfigWriter;
import dev.toma.configuration.api.IObjectSpec;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ObjectType;

public class Combat extends ObjectType {

    public static BooleanType isEnabled;
    public static Combo combo;

    public Combat(IObjectSpec spec) {
        super(spec);

        IConfigWriter writer = spec.getWriter();

        isEnabled = writer.writeBoolean("Enable the combat", true, "If the combat shold be enabled or not");
        combo = writer.writeObject(spec1 -> new Combo(spec1), "Setting for Combo display", "");
    }

    public class Combo extends ObjectType{

        public static BooleanType isEnabled;

        public Combo(IObjectSpec spec) {
            super(spec);

            IConfigWriter writer = spec.getWriter();

            isEnabled = writer.writeBoolean("Enable the combat", true, "If the combat shold be enabled or not");
        }
    }

}
