package com.honker.dimensionalvaqmstorage.util;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;

public class NetworkingUtils {
    @SuppressWarnings("deprecation")
    public static Boolean IsNetworkingNode(Block block){
        return Registry.BLOCK
                .getHolderOrThrow(Registry.BLOCK.getResourceKey(block).get())
                .is(TagsUtils.Blocks.NETWORK_NODES);
    }
}
