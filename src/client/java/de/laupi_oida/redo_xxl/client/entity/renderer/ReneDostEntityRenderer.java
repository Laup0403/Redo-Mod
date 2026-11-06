package de.laupi_oida.redo_xxl.client.entity.renderer;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import de.laupi_oida.redo_xxl.RedoXXL;
import de.laupi_oida.redo_xxl.client.entity.models.ReneDostGeoModel;
import de.laupi_oida.redo_xxl.entities.ReneDostEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.EntityType;

public class ReneDostEntityRenderer<R extends EntityRenderState & GeoRenderState>
        extends GeoEntityRenderer<ReneDostEntity, R> {

    public ReneDostEntityRenderer(EntityRendererProvider.Context context,
                                  EntityType<ReneDostEntity> entityType) {

        super(context, new ReneDostGeoModel());
    }
}