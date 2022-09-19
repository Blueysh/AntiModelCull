package net.thearke.arketweaks.mixin.world.entity.decoration;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.thearke.arketweaks.util.ArkeUtil;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ItemFrameEntity.class})
public abstract class ItemFrameMixin extends AbstractDecorationEntity {
    public ItemFrameMixin(EntityType<? extends ItemFrameEntity> entityType, World level) { super(entityType, level); }

    public Box getVisibilityBoundingBox() {
        try {
            return ArkeUtil.connectedToServer() ? this.getBoundingBox().expand(2.5) : super.getVisibilityBoundingBox();
        } catch (Throwable ignored) {
        }
        return null;
    }
}
