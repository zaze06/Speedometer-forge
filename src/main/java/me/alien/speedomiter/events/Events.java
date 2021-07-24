package me.alien.speedomiter.events;

import me.alien.speedomiter.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
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
            Entity entity = Minecraft.getInstance().player;
            if(((ClientPlayerEntity) entity).getRidingEntity() != null){
                entity = ((ClientPlayerEntity) entity).getRidingEntity();
            }
            World world = entity.world;
            double x = entity.getPosX();
            double y = entity.getPosY();
            double z = entity.getPosZ();

            Vector3d vec = entity.getMotion();
            Color color = new ColorUIResource(Config.speedColor.getColor());

            double yOffset = 0.0784000015258789;
            double xOffset = 0;
            double zOffset = 0;

            if(entity instanceof PlayerEntity){
                PlayerEntity e = (PlayerEntity) entity;
                if(!e.isOnGround() && e.isCreative()){
                    yOffset = 0;
                }else if(e.isInWater()){
                    yOffset = 0;
                }
            }else if(entity instanceof BoatEntity){
                yOffset = 0;
            }

            double speed = Math.sqrt(Math.pow(vec.x+xOffset, 2) + Math.pow(vec.y+yOffset, 2) + Math.pow(vec.z+zOffset, 2))*20;
            //Minecraft.getInstance().
            if(speeds.size() >= 30){
                speeds.remove(0);
            }
            speeds.add(speed);
            speed = 0;
            for(int i = 0; i < speeds.size(); i++){
                speed += speeds.get(i);
            }
            speed = speed/speeds.size();

            String speedType = "";
            if(entity instanceof BoatEntity && Config.useKnotInBoat.get()){
                speed = speed * 1.94384449;
                speedType = "knot";
            }else{
                switch (Config.speedType.get()){
                    case 2:
                        speedType = "Blocks/s";
                        break;
                    case 3:
                        speedType = "km/h";
                        speed = speed * 3.6;
                        break;
                    case 4:
                        speedType = "mph";
                        speed = speed * 2.23693629;
                        break;
                    default:
                        speedType = "m/s";
                }
            }

            if (true) {

                String format = String.format("%.2f", speed);
                Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), format +" "+speedType, (event.getWindow().getScaledWidth())-(70/*+((format.length()-4)*10)*/), event.getWindow().getScaledHeight()-15, color.getRGB());
            }



        }
    }
}
