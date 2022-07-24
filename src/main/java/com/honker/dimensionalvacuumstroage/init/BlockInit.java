package com.honker.dimensionalvacuumstroage.init;

import com.honker.dimensionalvacuumstroage.DVSMain;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, DVSMain.MODID);
}
