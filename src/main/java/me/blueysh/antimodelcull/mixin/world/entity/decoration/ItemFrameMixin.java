package me.blueysh.antimodelcull.mixin.world.entity.decoration;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ItemFrameEntity.class})
public abstract class ItemFrameMixin extends AbstractDecorationEntity {
    public ItemFrameMixin(EntityType<? extends ItemFrameEntity> entityType, World level) { super(entityType, level); }

    public Box getVisibilityBoundingBox() {
        try {
            this.getBoundingBox().expand(2.5);
        } catch (Throwable ignored) {
        }
        return null;
    }
}
