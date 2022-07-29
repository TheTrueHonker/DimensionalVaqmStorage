package com.honker.dimensionalvaqmstorage.init;

import com.honker.dimensionalvaqmstorage.DVS;
import com.honker.dimensionalvaqmstorage.block.VaqmOre;
import com.honker.dimensionalvaqmstorage.block.PipeBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DVS.MODID);

    public static final RegistryObject<VaqmOre> VAQM_ORE;
    public static final RegistryObject<PipeBlock> VAQM_TUBE;

    static {
        VAQM_ORE = BLOCKS.register("vaqm_ore", VaqmOre::new);
        VAQM_TUBE = BLOCKS.register("pipe_block", PipeBlock::new);
    }

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
