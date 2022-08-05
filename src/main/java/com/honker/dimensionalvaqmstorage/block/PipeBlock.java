package com.honker.dimensionalvaqmstorage.block;

import com.honker.dimensionalvaqmstorage.util.NetworkingUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import static com.honker.dimensionalvaqmstorage.util.EnumUtils.DirectionsNone;

import javax.annotation.ParametersAreNonnullByDefault;

public class PipeBlock extends Block {
    private static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static final EnumProperty<DirectionsNone> WALL_TO_ANCHOR = EnumProperty.create("wall_to_anchor", DirectionsNone.class);
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
                .setValue(WALL_TO_ANCHOR, DirectionsNone.NONE)
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
        return getState(defaultBlockState(), pContext.getLevel(), pContext.getClickedPos())
                .setValue(FACING, pContext.getNearestLookingDirection().getOpposite());
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

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
        pLevel.setBlockAndUpdate(pPos, getState(pState, pLevel, pPos));
    }

    private boolean nonNetworkBlockFound(LevelAccessor level, BlockPos pos, Direction direction) {
        BlockState blockState = level.getBlockState(pos.relative(direction));
        if(blockState.isAir())
            return false;
        return !NetworkingUtils.IsNetworkingNode(blockState.getBlock());
    }

    private BlockState getState(BlockState state, LevelAccessor world, BlockPos pos) {
        boolean blockUp = nonNetworkBlockFound(world, pos, Direction.UP);
        boolean blockEast = nonNetworkBlockFound(world, pos, Direction.EAST);
        boolean blockDown = nonNetworkBlockFound(world,pos,Direction.DOWN);
        boolean blockWest = nonNetworkBlockFound(world, pos, Direction.WEST);
        boolean blockNorth = nonNetworkBlockFound(world, pos, Direction.NORTH);
        boolean blockSouth = nonNetworkBlockFound(world, pos, Direction.SOUTH);

        DirectionsNone direction = DirectionsNone.NONE;
        if(blockUp)
            direction = DirectionsNone.UP;
        else if(blockEast)
            direction = DirectionsNone.EAST;
        else if(blockWest)
            direction = DirectionsNone.WEST;
        else if(blockNorth)
            direction = DirectionsNone.NORTH;
        else if(blockSouth)
            direction = DirectionsNone.SOUTH;
        else if(blockDown)
            direction = DirectionsNone.DOWN;


        boolean[] nodeConnections = new boolean[6];
        nodeConnections[0] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.UP);
        nodeConnections[1] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.DOWN);
        nodeConnections[2] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.EAST);
        nodeConnections[3] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.WEST);
        nodeConnections[4] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.NORTH);
        nodeConnections[5] = NetworkingUtils.NodeConnectionFound(world, pos, Direction.SOUTH);

        return state
                .setValue(WALL_TO_ANCHOR, direction)
                .setValue(PIPE_CONNECTION_UP, nodeConnections[0])
                .setValue(PIPE_CONNECTION_DOWN, nodeConnections[1])
                .setValue(PIPE_CONNECTION_EAST, nodeConnections[2])
                .setValue(PIPE_CONNECTION_WEST, nodeConnections[3])
                .setValue(PIPE_CONNECTION_NORTH, nodeConnections[4])
                .setValue(PIPE_CONNECTION_SOUTH, nodeConnections[5]);



    }
}