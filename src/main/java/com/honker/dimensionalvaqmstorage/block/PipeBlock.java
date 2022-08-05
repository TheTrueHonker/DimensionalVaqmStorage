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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import javax.annotation.ParametersAreNonnullByDefault;

public class PipeBlock extends Block {
    private static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static final DirectionProperty WALL_TO_ANCHOR = DirectionProperty.create("wall_to_anchor");
    private static final BooleanProperty PIPE_CONNECTION_UP = BooleanProperty.create("pipe_connection_up");
    private static final BooleanProperty PIPE_CONNECTION_DOWN = BooleanProperty.create("pipe_connection_down");
    private static final BooleanProperty PIPE_CONNECTION_EAST = BooleanProperty.create("pipe_connection_east");
    private static final BooleanProperty PIPE_CONNECTION_WEST = BooleanProperty.create("pipe_connection_west");
    private static final BooleanProperty PIPE_CONNECTION_NORTH = BooleanProperty.create("pipe_connection_north");
    private static final BooleanProperty PIPE_CONNECTION_SOUTH = BooleanProperty.create("pipe_connection_south");


    public PipeBlock() {
        super(BlockBehaviour.Properties
                .copy(Blocks.GLASS)
                .noOcclusion());
        this.registerDefaultState(defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WALL_TO_ANCHOR, Direction.NORTH)
                .setValue(PIPE_CONNECTION_UP, false)
                .setValue(PIPE_CONNECTION_DOWN, false)
                .setValue(PIPE_CONNECTION_EAST, false)
                .setValue(PIPE_CONNECTION_WEST, false)
                .setValue(PIPE_CONNECTION_NORTH, false)
                .setValue(PIPE_CONNECTION_SOUTH, false));
    }

    /* FACING */
    @Override
    @ParametersAreNonnullByDefault
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return getState(pContext);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(WALL_TO_ANCHOR);
        pBuilder.add(PIPE_CONNECTION_UP);
        pBuilder.add(PIPE_CONNECTION_DOWN);
        pBuilder.add(PIPE_CONNECTION_EAST);
        pBuilder.add(PIPE_CONNECTION_WEST);
        pBuilder.add(PIPE_CONNECTION_NORTH);
        pBuilder.add(PIPE_CONNECTION_SOUTH);
    }

    private boolean nonNetworkBlockFound(LevelAccessor level, BlockPos pos, Direction direction) {
        BlockState blockState = level.getBlockState(pos.relative(direction));
        if(blockState.isAir())
            return false;
        return !NetworkingUtils.IsNetworkingNode(blockState.getBlock());
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

        boolean[] nodeConnections = new boolean[6];
        nodeConnections[0] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.UP);
        nodeConnections[1] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.DOWN);
        nodeConnections[2] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.EAST);
        nodeConnections[3] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.WEST);
        nodeConnections[4] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.NORTH);
        nodeConnections[5] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.SOUTH);

        return state
                .setValue(FACING, pContext.getNearestLookingDirection().getOpposite())
                .setValue(WALL_TO_ANCHOR, direction)
                .setValue(PIPE_CONNECTION_UP, nodeConnections[0])
                .setValue(PIPE_CONNECTION_DOWN, nodeConnections[1])
                .setValue(PIPE_CONNECTION_EAST, nodeConnections[2])
                .setValue(PIPE_CONNECTION_WEST, nodeConnections[3])
                .setValue(PIPE_CONNECTION_NORTH, nodeConnections[4])
                .setValue(PIPE_CONNECTION_SOUTH, nodeConnections[5]);

    }
}