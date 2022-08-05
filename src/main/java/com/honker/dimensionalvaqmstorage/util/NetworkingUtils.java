package com.honker.dimensionalvaqmstorage.util;

import com.honker.dimensionalvaqmstorage.block.PipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkingUtils {
    @SuppressWarnings("deprecation")
    public static boolean IsNetworkingNode(Block block){
        return Registry.BLOCK
                .getHolderOrThrow(Registry.BLOCK.getResourceKey(block).get())
                .is(TagsUtils.Blocks.NETWORK_NODES);
    }

    public static boolean NodeConnectionFound(LevelAccessor level, BlockPos pos, Direction direction){
        BlockState blockState = level.getBlockState(pos.relative(direction));
        return NetworkingUtils.IsNetworkingNode(blockState.getBlock());
    }
}
