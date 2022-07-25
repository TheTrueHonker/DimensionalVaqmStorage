package com.honker.dimensionalvacuumstroage.init;

import com.honker.dimensionalvacuumstroage.DVSMain;
import com.honker.dimensionalvacuumstroage.DVSTab;
import com.honker.dimensionalvacuumstroage.items.FuelItem;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DVSMain.MODID);

    public static final RegistryObject<Item> SMILE = ITEMS.register("smile",
            () -> new Item(new Item.Properties()
                    .tab(DVSTab.instance)
                    .stacksTo(10)
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationMod(2)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 200, 99), 0.5F)
                            .alwaysEat()
                            .build())));
    public static final RegistryObject<Item> FUELITEM =  ITEMS.register("fuelitem",
            () -> new FuelItem(new Item.Properties().tab(DVSTab.instance),20));

    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
}
