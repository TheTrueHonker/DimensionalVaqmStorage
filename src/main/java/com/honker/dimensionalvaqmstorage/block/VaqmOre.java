package com.honker.dimensionalvaqmstorage.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class VaqmOre extends Block {
    private static final int EXP_DROP = 5;

    public VaqmOre() {
        super(BlockBehaviour.Properties
                .of(Material.STONE)
                .strength(2f)
                .requiresCorrectToolForDrops());
    }

    public static int getExpDrop() {
        return EXP_DROP;
    }
}
