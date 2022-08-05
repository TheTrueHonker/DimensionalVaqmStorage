package com.honker.dimensionalvaqmstorage.util;

import net.minecraft.util.StringRepresentable;

public class EnumUtils {
    public enum DirectionsNone implements StringRepresentable {
        NONE,
        NORTH,
        SOUTH,
        EAST,
        WEST,
        UP,
        DOWN;

        @Override
        public String getSerializedName() {
            return toString().toLowerCase();
        }
    }
}
