package de.laupi_oida.redo_xxl.blocks;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {

    public static final Block GRILL = register(
            ModBlockItemIds.GRILL,
            FrontFaceBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.IRON).requiresCorrectToolForDrops().strength(5.0f,6.0f)
    );

    private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        // Create the block instance
        Block block = Registry.register(
                BuiltInRegistries.BLOCK,
                id.block(),
                blockFactory.apply(properties.setId(id.block()))
        );

        // Create the block item instance
        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

        return block;
    }

    public static void initialize() {
        setItemGroups();
    }

    public static void setItemGroups() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(ModBlocks.GRILL.asItem());
        });
    }
}