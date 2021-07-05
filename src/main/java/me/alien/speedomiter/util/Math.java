package me.alien.speedomiter.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class Math {
    public static float combineVector3(Vector3d vec){



        return (float) java.lang.Math.sqrt(java.lang.Math.pow(vec.x, 2)+java.lang.Math.pow(vec.y, 2)+java.lang.Math.pow(vec.z, 2));
    }
}
