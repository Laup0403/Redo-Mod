package de.laupi_oida.redo_xxl.client.entity;

import de.laupi_oida.redo_xxl.client.entity.renderer.ReneDostEntityRenderer;
import de.laupi_oida.redo_xxl.entities.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class RedoXxlCustomEntityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRenderers.register(ModEntityTypes.RENE_DOST, context -> new ReneDostEntityRenderer(context, ModEntityTypes.RENE_DOST));
    }
}