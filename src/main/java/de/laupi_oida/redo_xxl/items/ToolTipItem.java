package de.laupi_oida.redo_xxl.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class ToolTipItem extends Item {

    private String tooltipKey;

    public ToolTipItem(Properties properties, String newTooltipKey) {
        super(properties);
        this.tooltipKey = newTooltipKey;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable(tooltipKey).withStyle(ChatFormatting.GOLD));
        textConsumer.accept(Component.translatable(tooltipKey + ".2").withStyle(ChatFormatting.GOLD));
    }
}
