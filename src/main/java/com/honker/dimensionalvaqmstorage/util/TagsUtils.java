package com.honker.dimensionalvaqmstorage.util;

import com.honker.dimensionalvaqmstorage.DVS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsUtils {
    public static class Blocks {
        public static final TagKey<Block> NETWORK_NODES = tag("network_nodes");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(DVS.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(DVS.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
