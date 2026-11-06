
package de.laupi_oida.redo_xxl.entities;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.constant.DefaultAnimations;
import com.geckolib.util.GeckoLibUtil;

import de.laupi_oida.redo_xxl.RedoXXL;
import de.laupi_oida.redo_xxl.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import org.jspecify.annotations.NonNull;

public class ReneDostEntity extends PathfinderMob implements GeoEntity, Merchant {

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private MerchantOffers offers = new MerchantOffers();
    private Player tradingPlayer;
    private int xp = 0;

    public ReneDostEntity(Level world) {
        this(ModEntityTypes.RENE_DOST, world);
    }

    public ReneDostEntity(EntityType<? extends ReneDostEntity> entityType, Level world) {
        super(entityType, world);
        buildOffers();
    }

    public static AttributeSupplier.Builder createReneDostAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.TEMPT_RANGE, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1) {
            @Override
            public boolean canUse() {
                return !ReneDostEntity.this.isDancing() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !ReneDostEntity.this.isDancing() && super.canContinueToUse();
            }
        });

        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this) {
            @Override
            public boolean canUse() {
                return !ReneDostEntity.this.isDancing() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !ReneDostEntity.this.isDancing() && super.canContinueToUse();
            }
        });
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return RedoXXL.SCHNEEDO;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return switch (this.random.nextInt(3)) {
            case 0 -> RedoXXL.GIG;
            case 1 -> RedoXXL.DUSTIN;
            default -> RedoXXL.DRUCK;
        };
    }

    @Override
    public int getAmbientSoundInterval() {
        return 400;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        AnimationController<ReneDostEntity> controller = new AnimationController<>(test -> {
            if (this.isDancing()) {
                return test.setAndContinue(RawAnimation.begin().thenLoop("Dance"));
            }

            if (test.isMoving()) {
                return test.setAndContinue(DefaultAnimations.WALK);
            }

            return test.setAndContinue(DefaultAnimations.IDLE);
        });

        controller.setAnimationSpeed(3f);
        controllers.add(controller);
    }

    private boolean isDancing = false;

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            BlockPos pos = this.blockPosition();
            boolean foundJukebox = false;

            for (BlockPos checkPos : BlockPos.betweenClosed(
                    pos.offset(-4, -2, -4),
                    pos.offset(4, 2, 4))) {

                if (this.level().getBlockState(checkPos).is(Blocks.JUKEBOX)) {
                    if (this.level().getBlockState(checkPos).getValue(JukeboxBlock.HAS_RECORD)) {
                        foundJukebox = true;
                        break;
                    }
                }
            }

            this.isDancing = foundJukebox;
        }
    }

    public boolean isDancing() {
        return this.isDancing;
    }

    @Override
    public @NonNull AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    protected @NonNull InteractionResult mobInteract(@NonNull Player player, @NonNull InteractionHand hand) {
        if (!this.level().isClientSide()) {
            this.openTradingScreen(
                    player,
                    Component.literal("Rene Dost"),
                    getTradeLevel()
            );
        }

        return InteractionResult.SUCCESS;
    }

    private int getTradeLevel() {
        if (xp >= 250) return 5;
        if (xp >= 150) return 4;
        if (xp >= 70) return 3;
        if (xp >= 10) return 2;
        return 1;
    }

    private void buildOffers() {
        offers.clear();

        // Level 1
        offers.add(new MerchantOffer(
                new ItemCost(Items.EMERALD, 7),
                new ItemStack(ModItems.XXL_SCHNITZEL, 1),
                999, 5, 0.05F
        ));

        offers.add(new MerchantOffer(
                new ItemCost(Items.EMERALD, 6),
                new ItemStack(ModItems.XXL_CURRYWURST, 1),
                999, 5, 0.05F
        ));

        // Level 2
        if (getTradeLevel() >= 2) {
            offers.add(new MerchantOffer(
                    new ItemCost(Items.DEEPSLATE, 41),
                    new ItemStack(ModItems.XXL_BURGER, 1),
                    999, 10, 0.05F
            ));

            offers.add(new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.XXL_JAEGERSCHNITZEL, 1),
                    999, 10, 0.05F
            ));
        }

        // Level 3
        if (getTradeLevel() >= 3) {
            offers.add(new MerchantOffer(
                    new ItemCost(Items.ACACIA_DOOR, 10),
                    new ItemStack(ModItems.XXL_FISHSTAEBCHEN, 1),
                    999, 15, 0.05F
            ));
        }

        // Level 4
        if (getTradeLevel() >= 4) {
            offers.add(new MerchantOffer(
                    new ItemCost(Items.GOLDEN_APPLE, 2),
                    new ItemStack(ModItems.BUBENSAHNE, 1),
                    999, 20, 0.05F
            ));
        }
    }

    @Override
    public void setTradingPlayer(Player player) {
        this.tradingPlayer = player;
    }

    @Override
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public @NonNull MerchantOffers getOffers() {
        return this.offers;
    }

    @Override
    public void overrideOffers(@NonNull MerchantOffers offers) {
        this.offers = offers;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        int oldLevel = getTradeLevel();

        offer.increaseUses();
        this.xp += offer.getXp();

        int newLevel = getTradeLevel();

        if (newLevel > oldLevel) {
            buildOffers();

            this.level().playSound(
                    null,
                    this.blockPosition(),
                    SoundEvents.VILLAGER_YES,
                    net.minecraft.sounds.SoundSource.NEUTRAL,
                    1.0F,
                    1.0F
            );

            if (this.tradingPlayer != null) {
                this.tradingPlayer.sendSystemMessage(
                        Component.literal("Rene Level: "+ newLevel)
                );
            }
        }
    }
    @Override
    public void notifyTradeUpdated(@NonNull ItemStack stack) {
    }

    @Override
    public int getVillagerXp() {
        return this.xp;
    }

    @Override
    public void overrideXp(int xp) {
        this.xp = xp;
        buildOffers();
    }

    @Override
    public boolean showProgressBar() {
        return true;
    }

    @Override
    public @NonNull SoundEvent getNotifyTradeSound() {
        return RedoXXL.GIG;
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide();
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return this.isAlive() && this.distanceToSqr(player) <= 64.0;
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("ReneTradeXp", this.xp);
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.xp = input.getInt("ReneTradeXp").orElse(0);
        buildOffers();
    }
}
