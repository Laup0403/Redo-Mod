package de.laupi_oida.redo_xxl.entities;

import de.laupi_oida.redo_xxl.RedoXXL;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeIds {
    public static ResourceKey<EntityType<?>> RENE_DOST = create("rene_dost");

    private static ResourceKey<EntityType<?>> create(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, RedoXXL.id(name));
    }
}