package dev.dubhe.flatworld.mixin;

import dev.dubhe.flatworld.gen.FlatWorldChunkGenerator;
import dev.dubhe.flatworld.gen.FlatWorldStructures.SpawnPlatform;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.level.ServerWorldProperties;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

  @Inject(
    method = "setupSpawn",
    locals = LocalCapture.CAPTURE_FAILHARD,
    at =
    @At(
      value = "JUMP",
      opcode = Opcodes.IFEQ,
      ordinal = 1,
      shift = At.Shift.BEFORE))
  private static void generateSpawnPlatform(ServerWorld world, ServerWorldProperties worldProperties, boolean bonusChest, boolean debugWorld, CallbackInfo ci) {
    if (world.getChunkManager().getChunkGenerator() instanceof FlatWorldChunkGenerator) {
      BlockPos worldSpawn = new BlockPos(worldProperties.getSpawnX(), worldProperties.getSpawnY(), worldProperties.getSpawnZ());
      new SpawnPlatform(worldSpawn).generate(world, world.random);
    }
  }
}
