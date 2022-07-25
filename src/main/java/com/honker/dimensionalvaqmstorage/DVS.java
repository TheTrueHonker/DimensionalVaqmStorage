package com.honker.dimensionalvaqmstorage;

import com.honker.dimensionalvaqmstorage.init.BlockInit;
import com.honker.dimensionalvaqmstorage.init.ItemInit;
import com.honker.dimensionalvaqmstorage.item.group.MainCreativeModeTab;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(DVS.MODID)
public class DVS
{
    public static final String MODID = "dimensionalvacuumstorage";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab CREATIVE_MODE_TAB = new MainCreativeModeTab();

    public DVS()
    {
        BlockInit.register();
        ItemInit.register();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
