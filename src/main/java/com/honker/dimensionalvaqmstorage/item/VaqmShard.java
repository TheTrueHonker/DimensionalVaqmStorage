package com.honker.dimensionalvaqmstorage.item;

import com.honker.dimensionalvaqmstorage.DVS;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class VaqmShard extends Item {
    public VaqmShard() {
        super(new Item.Properties()
                .tab(DVS.CREATIVE_MODE_TAB)
                .setNoRepair()
                .rarity(Rarity.RARE));
    }
}
