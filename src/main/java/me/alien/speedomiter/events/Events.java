package me.alien.speedomiter.events;

//import me.alien.speedomiter.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Events {

    static ArrayList<Double> speeds = new ArrayList<Double>();

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void Speed(RenderGameOverlayEvent event){
        if(!event.isCanceled() && event.getType() == RenderGameOverlayEvent.ElementType.ALL){
            int posX = (event.getWindow().getGuiScaledWidth()) / 2;
            int posY = (event.getWindow().getGuiScaledHeight()) / 2;
            Entity entity = Minecraft.getInstance().player;
            if(((LocalPlayer) entity).getVehicle() != null){
                entity = ((LocalPlayer) entity).getVehicle();
            }
            Level world = entity.level;
            double x = entity.position().x;
            double y = entity.position().y;
            double z = entity.position().z;

            Vec3 vec = entity.getDeltaMovement();
            Color color = new ColorUIResource(16, 146, 158/*Config.speedColor.getColor()*/);

            double yOffset = 0.0784000015258789;
            double xOffset = 0;
            double zOffset = 0;

            if(entity instanceof Player){
                Player e = (Player) entity;
                if(!e.isOnGround() && e.isCreative()){
                    yOffset = 0;
                }else if(e.isInWater()){
                    yOffset = 0;
                }
            }else if(entity instanceof Boat){
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
            if(entity instanceof Boat && true/*Config.useKnotInBoat.get()*/){
                speed = speed * 1.94384449;
                speedType = "knot";
            }else{
                switch (1/*Config.speedType.get()*/){
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
                //Minecraft.getInstance().fo
                Minecraft.getInstance().font.draw(
                        event.getMatrixStack(),
                        format +" "+speedType,
                        getPos(event, /*Config.xPos.get()*//*"X-70"*/"a", 0, false),
                        getPos(event, /*Config.xPos.get()*//*"Y-15"*/"a", 1, true),
                        color.getRGB());
            }



        }
    }

    static boolean flag = true;

    private static int getPos(RenderGameOverlayEvent event, String input, int type, boolean changeFlag) {
        ArrayList<String> paserdPos = new ArrayList<String>();
        final char[] s = input.toCharArray();
        try{
            for(int i = 0; i <s.length; i++){
                if(s[i] == 'X' || s[i] == 'Y'){
                    if(type == 0) paserdPos.add(event.getWindow().getGuiScaledWidth()+"");
                    else if(type == 1) paserdPos.add(event.getWindow().getGuiScaledHeight()+"");
                }else if(s[i] == 'x' || s[i] == 'y'){
                    if(type == 0) paserdPos.add(((int)(event.getWindow().getGuiScaledWidth()/2))+"");
                    else if(type == 1) paserdPos.add(((int)(event.getWindow().getGuiScaledHeight()/2))+"");
                }else if(s[i] == '+'){
                    paserdPos.add("+");
                }else if(s[i] == '-'){
                    paserdPos.add("-");
                }else if(s[i] == '*'){
                    paserdPos.add("/");
                }else if(s[i] == '/'){
                    paserdPos.add("/");
                }else if(testIfInt(s[i])){
                    try{
                        Integer.parseInt(paserdPos.get(i-1));
                        paserdPos.add(i-1,paserdPos.get(i-1)+s[i]);
                    }catch (NumberFormatException e){
                        paserdPos.add(Character.toString(s[i]));
                    }
                }else{
                    throw new Exception();
                }
            }
        }catch (Exception e){
            paserdPos.clear();
            if(type == 0){
                paserdPos.add(event.getWindow().getGuiScaledWidth()+"");
                paserdPos.add("-");
                paserdPos.add("70");
            }else if(type == 1){
                paserdPos.add(event.getWindow().getGuiScaledHeight()+"");
                paserdPos.add("-");
                paserdPos.add("15");
            }
        }



        int xPos = 0;
        xPos = Integer.parseInt(paserdPos.get(0));
        for(int i = 1; i < paserdPos.size(); i++){
            boolean first = i == 0;
            String s1 = paserdPos.get(i);
            String s2 = "";
            try{
                s2 = paserdPos.get(i+1);
            }catch (Exception e){
                first = true;
            }

            if(s1 == "+" && !first){
                xPos += Integer.parseInt(s2);
            }else if(s1 == "-" && !first){
                xPos -= Integer.parseInt(s2);
            }else if(s1 == "*" && !first){
                xPos *= Integer.parseInt(s2);
            }else if(s1 == "/" && !first){
                xPos /= Integer.parseInt(s2);
            }
        }
        if(flag) {
            System.out.println(Arrays.toString(paserdPos.toArray()));
            System.out.println(xPos+"");
            flag = !changeFlag;
        }
        return xPos;
    }

    private static boolean testIfInt(char c) {
        int i = Integer.parseInt(Character.toString(c));
        return (i == 0 || i == 1 || i == 2 ||
                i == 3 || i == 4 || i == 5 ||
                i == 6 || i == 7 || i == 8 ||
                i == 9);
    }
}
