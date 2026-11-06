package de.laupi_oida.redo_xxl.blocks;

import de.laupi_oida.redo_xxl.RedoXXL;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class ModBlockItemIds {
    public static final BlockItemId GRILL = create(
            "grill"
    );

    private static BlockItemId create(String name) {
        Identifier id = RedoXXL.id(name);
        return BlockItemId.create(id, id);
    }
}