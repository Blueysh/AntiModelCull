package net.thearke.arketweaks.mixin.world.entity.decoration;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.thearke.arketweaks.util.ArkeUtil;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ArmorStandEntity.class})
public abstract class ArmorStandMixin extends LivingEntity {
    public ArmorStandMixin(EntityType<? extends ArmorStandEntity> entityType, World level) { super(entityType, level); }

    public Box getVisibilityBoundingBox() {
        return ArkeUtil.connectedToServer() ? this.getVisibilityBoundingBox().expand(4.5) : super.getVisibilityBoundingBox();
    }
}
