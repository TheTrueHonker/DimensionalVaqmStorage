package com.honker.dimensionalvaqmstorage.init;

import com.honker.dimensionalvaqmstorage.DVS;
import com.honker.dimensionalvaqmstorage.item.VaqmCrystal;
import com.honker.dimensionalvaqmstorage.item.VaqmShard;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DVS.MODID);

    public static final RegistryObject<VaqmShard> VAQM_SHARD;
    public static final RegistryObject<VaqmCrystal> VAQM_CRYSTAL;

    static {
        VAQM_SHARD = ITEMS.register("vaqm_shard", VaqmShard::new);
        VAQM_CRYSTAL = ITEMS.register("vaqm_crystal", VaqmCrystal::new);

        registerBlockItemFor(BlockInit.VAQM_ORE);
        registerBlockItemFor(BlockInit.VAQM_TUBE);
    }

    private static <T extends Block> RegistryObject<BlockItem> registerBlockItemFor(RegistryObject<T> block) {
        return ITEMS.register(
                block.getId().getPath(),
                () -> new BlockItem(block.get(), new Item.Properties().tab(DVS.CREATIVE_MODE_TAB)));
    }

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
