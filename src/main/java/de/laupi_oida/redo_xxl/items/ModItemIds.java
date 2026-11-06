package de.laupi_oida.redo_xxl.items;

import de.laupi_oida.redo_xxl.RedoXXL;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

public class ModItemIds {
    public static final ResourceKey<Item> XXL_SCHNITZEL = create("xxl_schnitzel");
    public static final ResourceKey<Item> XXL_CURRYWURST = create("xxl_currywurst");
    public static final ResourceKey<Item> XXL_BURGER = create("xxl_burger");
    public static final ResourceKey<Item> XXL_FISCHSTAEBCHEN = create("xxl_fischstaebchen");
    public static final ResourceKey<Item> XXL_JAEGERSCHNITZEL = create("xxl_jaegerschnitzel");
    public static final ResourceKey<Item> BUBENSAHNE = create("bubensahne");

    public static ResourceKey<Item> create(String name) {
        // Create the item key.
        return ResourceKey.create(Registries.ITEM, RedoXXL.id(name));
    }
}