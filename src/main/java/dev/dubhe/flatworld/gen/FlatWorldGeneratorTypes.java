package dev.dubhe.flatworld.gen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.GeneratorType;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.chunk.ChunkGenerator;

@Environment(EnvType.CLIENT)
public class FlatWorldGeneratorTypes {
  public static final GeneratorType SKYBLOCK =
      new GeneratorType("flatworld") {
        @Override
        protected ChunkGenerator getChunkGenerator(DynamicRegistryManager drm, long seed) {
          return FlatWorldGenerationSettings.createOverworldGenerator(drm, seed);
        }

        @Override
        public GeneratorOptions createDefaultOptions(
            DynamicRegistryManager drm,
            long seed,
            boolean generateStructures,
            boolean bonusChest) {
          return new GeneratorOptions(
              seed,
              generateStructures,
              bonusChest,
              FlatWorldGenerationSettings.getFlatWorldDimensionOptionsRegistry(drm, seed));
        }
      };
}
