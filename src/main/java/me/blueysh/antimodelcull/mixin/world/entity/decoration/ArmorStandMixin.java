package me.blueysh.antimodelcull.mixin.world.entity.decoration;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ArmorStandEntity.class})
public abstract class ArmorStandMixin extends LivingEntity {
    public ArmorStandMixin(EntityType<? extends ArmorStandEntity> entityType, World level) { super(entityType, level); }

    public Box getVisibilityBoundingBox() {
        try {
            this.getBoundingBox().expand(4.5);
        } catch (Throwable ignored) {
        }
        return null;
    }
}
