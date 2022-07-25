package com.honker.dimensionalvaqmstorage.item.group;

import com.honker.dimensionalvaqmstorage.DVS;
import com.honker.dimensionalvaqmstorage.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MainCreativeModeTab extends CreativeModeTab {
    public MainCreativeModeTab(){
        super(DVS.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return ItemInit.VAQM_CRYSTAL.get().getDefaultInstance();
    }
}
