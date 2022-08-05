package dev.dubhe.flatworld.mixin;

import dev.dubhe.flatworld.FlatWorldSettings;
import dev.dubhe.flatworld.criterion.Criteria;
import dev.dubhe.flatworld.helpers.GeodeGenerator;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.Random;

@Mixin(LavaFluid.class)
public class LavaFluidMixin {
  @Inject(method = "onRandomTick", locals = LocalCapture.CAPTURE_FAILSOFT, at = @At(value = "HEAD"))
  private void tryCreateGeode(
      World world, BlockPos pos, FluidState state, Random random, CallbackInfo ci) {
    if (FlatWorldSettings.renewableBuddingAmethysts) {
      if (random.nextInt(GeodeGenerator.CONVERSION_RATE) == 0) {
        if (GeodeGenerator.checkGeodeFormation(world, pos)) {
          world.setBlockState(pos, Blocks.BUDDING_AMETHYST.getDefaultState());
          world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 2.6f + (random.nextFloat() - random.nextFloat()) * 0.8f);
          world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0f, 0.5f + world.random.nextFloat() * 1.2f);

          Iterator<ServerPlayerEntity> nearbyPlayers =
              world
                  .getNonSpectatingEntities(
                      ServerPlayerEntity.class, (new Box(pos)).expand(50.0D, 20.0D, 50.0D))
                  .iterator();
          nearbyPlayers.forEachRemaining(Criteria.GENERATE_GEODE::trigger);
        }
      }
    }
  }
}
