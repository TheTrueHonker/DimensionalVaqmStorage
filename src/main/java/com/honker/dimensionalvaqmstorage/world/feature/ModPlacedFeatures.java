package com.honker.dimensionalvaqmstorage.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> VAQM_ORE_PLACED = PlacementUtils.register("vaqm_ore_placed",
            ModConfiguredFeatures.VAQM_ORE, ModOrePlacement.commonOrePlacement(20,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-24), VerticalAnchor.aboveBottom(80))));

}
