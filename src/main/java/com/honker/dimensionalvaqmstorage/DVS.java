package com.honker.dimensionalvaqmstorage;

import com.honker.dimensionalvaqmstorage.init.BlockInit;
import com.honker.dimensionalvaqmstorage.init.ItemInit;
import com.honker.dimensionalvaqmstorage.item.group.MainCreativeModeTab;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(DVS.MODID)
public class DVS
{
    public static final String MODID = "dimensionalvaqmstorage";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab CREATIVE_MODE_TAB = new MainCreativeModeTab();

    public DVS()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::clientSetup);

        BlockInit.register();
        ItemInit.register();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(BlockInit.VAQM_TUBE.get(), RenderType.cutout());
    }
}
