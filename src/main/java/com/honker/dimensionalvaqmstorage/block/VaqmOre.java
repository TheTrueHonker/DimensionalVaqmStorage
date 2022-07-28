package com.honker.dimensionalvaqmstorage.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class VaqmOre extends OreBlock {
    public VaqmOre() {
        super(BlockBehaviour.Properties
                .of(Material.HEAVY_METAL)
                .strength(2f)
                .requiresCorrectToolForDrops(),
                UniformInt.of(3,5));
    }
}
