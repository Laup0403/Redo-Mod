package de.laupi_oida.redo_xxl;

import de.laupi_oida.redo_xxl.blocks.ModBlocks;
import de.laupi_oida.redo_xxl.entities.ModEntityTypes;
import de.laupi_oida.redo_xxl.items.ModItems;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import net.minecraft.sounds.SoundEvent;
import org.apache.commons.codec.language.Soundex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedoXXL implements ModInitializer {
	public static final String MOD_ID = "redo-xxl";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static SoundEvent DRUCK;
	public static SoundEvent DUSTIN;
	public static SoundEvent GIG;
	public static SoundEvent SCHNEEDO;

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModEntityTypes.initialize();
		ModBlocks.initialize();

		DRUCK = Registry.register(
				BuiltInRegistries.SOUND_EVENT,
				id("druck"),
				SoundEvent.createVariableRangeEvent(id("druck"))
		);
		DUSTIN = Registry.register(
				BuiltInRegistries.SOUND_EVENT,
				id("dustin"),
				SoundEvent.createVariableRangeEvent(id("dustin"))
		);
		GIG = Registry.register(
				BuiltInRegistries.SOUND_EVENT,
				id("gig"),
				SoundEvent.createVariableRangeEvent(id("gig"))
		);
		SCHNEEDO = Registry.register(
				BuiltInRegistries.SOUND_EVENT,
				id("schneedo"),
				SoundEvent.createVariableRangeEvent(id("schneedo"))
		);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
