package dev.dubhe.flatworld.mixin;

import dev.dubhe.flatworld.FlatWorldSettings;
import dev.dubhe.flatworld.gen.FlatWorldChunkGenerator;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {

  // This is the height in the structure where the actual portal will spawn.
  // The structure continues above and below this.
  private static final int EXIT_PORTAL_HEIGHT = 60;

  @Shadow
  private BlockPos exitPortalLocation;

  @Shadow
  @Final
  private ServerWorld world;

  @Shadow
  private boolean previouslyKilled;

  @Inject(method = "generateEndPortal", at = @At(value = "HEAD"))
  private void setExitPortalLocation(boolean previouslyKilled, CallbackInfo ci) {
    if (this.world.getChunkManager().getChunkGenerator() instanceof FlatWorldChunkGenerator) {
      if (this.exitPortalLocation == null) {
        this.exitPortalLocation = this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPortalFeature.ORIGIN).down();
      }
    }
  }

  @Inject(
    method = "dragonKilled",
    at =
    @At(
      value = "FIELD",
      target = "Lnet/minecraft/entity/boss/dragon/EnderDragonFight;previouslyKilled:Z",
      opcode = Opcodes.PUTFIELD))
  private void spawnShulkerOnDragonReKill(EnderDragonEntity dragon, CallbackInfo ci) {
    if (FlatWorldSettings.shulkerSpawning) {
      // On top of bedrock pillar
      BlockPos shulkerPosition = this.exitPortalLocation.add(0, 4, 0);
      if (this.previouslyKilled && this.world.getBlockState(shulkerPosition).isOf(Blocks.AIR)) {
        ShulkerEntity shulker =
          EntityType.SHULKER.create(
            world, null, null, null, shulkerPosition, SpawnReason.EVENT, true, false);
        if (shulker != null && world.isSpaceEmpty(shulker)) {
          world.spawnEntity(shulker);
        }
      }
    }
  }
}
