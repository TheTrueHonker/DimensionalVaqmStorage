package com.honker.dimensionalvaqmstorage.item;

import com.honker.dimensionalvaqmstorage.DVS;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class VaqmCrystal extends Item {
    public VaqmCrystal() {
        super(new Properties()
                .tab(DVS.CREATIVE_MODE_TAB)
                .setNoRepair()
                .rarity(Rarity.RARE));
    }
}
