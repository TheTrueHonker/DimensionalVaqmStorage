package com.honker.dimensionalvacuumstroage;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static com.honker.dimensionalvacuumstroage.init.ItemInit.SMILE;

public class DVSTab {
    public static final DVSCreativeTab instance = new DVSCreativeTab(CreativeModeTab.TABS.length,
            "dvs");

    private static class DVSCreativeTab extends CreativeModeTab {
        private DVSCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SMILE.get());
        }
    }
}
