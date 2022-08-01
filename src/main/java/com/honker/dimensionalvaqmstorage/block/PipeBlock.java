package com.honker.dimensionalvaqmstorage.block;

import com.honker.dimensionalvaqmstorage.util.NetworkingUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class PipeBlock extends Block {
    private static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static final DirectionProperty NEXT_BLOCK = DirectionProperty.create("nextblock");

    public PipeBlock() {
        super(BlockBehaviour.Properties
                .copy(Blocks.GLASS)
                .noOcclusion());
        this.registerDefaultState(defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(NEXT_BLOCK, Direction.NORTH));
    }

    /* FACING */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return getState(pContext);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(NEXT_BLOCK);
    }

    private boolean nonNetworkBlockFound(LevelAccessor level, BlockPos pos, Direction direction) {
        BlockState blockState = level.getBlockState(pos.relative(direction));
        if (blockState == null)
            return false;
        if(blockState.isAir())
            return false;
        if (!NetworkingUtils.IsNetworkingNode(blockState.getBlock()))
            return true;
        return false;
    }

    private BlockState getState(BlockPlaceContext pContext) {
        BlockState state = defaultBlockState();
        LevelAccessor world = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();

        boolean blockUp = nonNetworkBlockFound(world, pos, Direction.UP);
        boolean blockEast = nonNetworkBlockFound(world, pos, Direction.EAST);
        boolean blockDown = nonNetworkBlockFound(world,pos,Direction.DOWN);
        boolean blockWest = nonNetworkBlockFound(world, pos, Direction.WEST);
        Direction direction = Direction.NORTH; // North = None

        if(blockUp)
            direction = Direction.UP;
        else if(blockEast)
            direction = Direction.EAST;
        else if(blockWest)
            direction = Direction.WEST;
        else if(blockDown)
            direction = Direction.DOWN;

        return state
                .setValue(FACING, pContext.getNearestLookingDirection().getOpposite())
                .setValue(NEXT_BLOCK, direction);
    }
}