package com.honker.dimensionalvaqmstorage.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

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

    private boolean blockFound(LevelAccessor level, BlockPos pos, Direction direction) {
        BlockState blockState = level.getBlockState(pos.relative(direction));
        if (blockState == null)
            return false;
        if (!blockState.getBlock().defaultBlockState().equals(this.defaultBlockState())) { // FIXME: Check if Pipe via capabilities or tags
            if(!blockState.isAir()) // FIXME Check for other invalid blocks such as rails
                return true;
        }
        return false;
    }

    private BlockState getState(BlockPlaceContext pContext) {
        BlockState state = defaultBlockState();
        LevelAccessor world = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();

        boolean blockup = blockFound(world, pos, Direction.UP);
        boolean blockeast = blockFound(world, pos, Direction.EAST);
        boolean blockdown = blockFound(world,pos,Direction.DOWN);
        boolean blockwest = blockFound(world, pos, Direction.WEST);
        Direction direction = Direction.NORTH; // North = None

        if(blockup)
            direction = Direction.UP;
        else if(blockeast)
            direction = Direction.EAST;
        else if(blockwest)
            direction = Direction.WEST;
        else if(blockdown)
            direction = Direction.DOWN;

        return state
                .setValue(FACING, pContext.getNearestLookingDirection().getOpposite())
                .setValue(NEXT_BLOCK, direction);
    }
}