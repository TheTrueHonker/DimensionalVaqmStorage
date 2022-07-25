package com.honker.dimensionalvaqmstorage.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier countPerChunk, PlacementModifier distribution) {
        return List.of(countPerChunk, InSquarePlacement.spread(), distribution, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int veinsPerChunk, PlacementModifier distribution) {
        return orePlacement(CountPlacement.of(veinsPerChunk), distribution);
    }

    public static List<PlacementModifier> rareOrePlacement(int chancePerChunk, PlacementModifier distribution) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chancePerChunk), distribution);
    }
}
