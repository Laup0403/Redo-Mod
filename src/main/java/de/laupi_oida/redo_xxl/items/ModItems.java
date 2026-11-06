package de.laupi_oida.redo_xxl.items;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import javax.tools.Tool;
import java.util.function.Function;

public class ModItems {
    public static final Item XXL_SCHNITZEL = register(
            ModItemIds.XXL_SCHNITZEL,
            properties -> new ToolTipItem( properties, "itemTooltip.redo-xxl.xxl_schnitzel"),
            new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(5)
            .build()));
    public static final Item XXL_CURRYWURST = register(
            ModItemIds.XXL_CURRYWURST,
            properties -> new ToolTipItem( properties, "itemTooltip.redo-xxl.xxl_currywurst"),
            new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(5)
            .build()));
    public static final Item XXL_BURGER = register(
            ModItemIds.XXL_BURGER,
            properties -> new ToolTipItem( properties, "itemTooltip.redo-xxl.xxl_burger"),
            new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(15)
            .saturationModifier(10)
            .build()));
    public static final Item XXL_JAEGERSCHNITZEL = register(
            ModItemIds.XXL_JAEGERSCHNITZEL,
            properties -> new ToolTipItem( properties, "itemTooltip.redo-xxl.xxl_jaegerschnitzel"),
            new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(15)
            .saturationModifier(10)
            .build()));
    public static final Item XXL_FISHSTAEBCHEN = register(
            ModItemIds.XXL_FISCHSTAEBCHEN,
            properties -> new ToolTipItem( properties, "itemTooltip.redo-xxl.xxl_fischstaebchen"),
            new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(10)
            .build()));
    public static final Item BUBENSAHNE = register(
            ModItemIds.BUBENSAHNE,
            properties -> new ToolTipItem( properties, "itemTooltip.redo-xxl.bubensahne"),
            new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(20)
            .build(),
                    Consumables.defaultFood()
                            .onConsume(
                                    new ApplyStatusEffectsConsumeEffect(
                                            new MobEffectInstance(
                                                    MobEffects.NAUSEA,
                                                    20 * 30,
                                                    2
                                            ),
                                            1.0F
                                    )
                            )
                            .build()
            ));

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        // Create the item instance.
        Item item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
    public static void initialize() {
        setItemGroups();
    }

    public static void setItemGroups() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> creativeTab.accept(ModItems.XXL_SCHNITZEL));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> creativeTab.accept(ModItems.XXL_CURRYWURST));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> creativeTab.accept(ModItems.XXL_JAEGERSCHNITZEL));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> creativeTab.accept(ModItems.XXL_BURGER));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> creativeTab.accept(ModItems.XXL_FISHSTAEBCHEN));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> creativeTab.accept(ModItems.BUBENSAHNE));

    }
}
