package me.alien.speedomiter.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;

public class Events {

    static ArrayList<Double> speeds = new ArrayList<Double>();

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void Speed(RenderGameOverlayEvent event){
        if(!event.isCanceled() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET){
            int posX = (event.getWindow().getScaledWidth()) / 2;
            int posY = (event.getWindow().getScaledHeight()) / 2;
            PlayerEntity entity = Minecraft.getInstance().player;
            World world = entity.world;
            double x = entity.getPosX();
            double y = entity.getPosY();
            double z = entity.getPosZ();
            Vector3d vec = entity.getMotion();
            Color color = new ColorUIResource(16, 146, 158);

            double speed = Math.sqrt(Math.pow(vec.x, 2) + Math.pow(vec.y+0.0784000015258789, 2) + Math.pow(vec.z, 2))*20;
            if(speeds.size() >= 30){
                speeds.remove(0);
                speeds.add(speed);
            }else{
                speeds.add(speed);
            }
            speed = 0;
            for(int i = 0; i < speeds.size(); i++){
                speed += speeds.get(i);
            }
            speed = speed/speeds.size();


            if (true) {

                String format = String.format("%.2f", speed);
                Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), format +" Blocks/s", (event.getWindow().getScaledWidth())-(70+((format.length()-4)*10)), event.getWindow().getScaledHeight()-15, color.getRGB());
            }

        }
    }
}
