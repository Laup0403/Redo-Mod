package de.laupi_oida.redo_xxl.client.entity.models;

import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import de.laupi_oida.redo_xxl.entities.ReneDostEntity;
import net.minecraft.resources.Identifier;

public class ReneDostGeoModel extends GeoModel<ReneDostEntity> {

	private final Identifier MODEL = Identifier.fromNamespaceAndPath(
			"redo-xxl", "entity/rene_dost"
	);

	private final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
			"redo-xxl", "textures/entity/rene_dost.png"
	);

	private final Identifier ANIMATION = Identifier.fromNamespaceAndPath(
			"redo-xxl", "entity/rene_dost"
	);

	@Override
	public Identifier getModelResource(GeoRenderState renderState) {
		return MODEL;
	}

	@Override
	public Identifier getTextureResource(GeoRenderState renderState) {
		return TEXTURE;
	}

	@Override
	public Identifier getAnimationResource(ReneDostEntity animatable) {
		return ANIMATION;
	}
}