package dev.dubhe.flatworld.mixin;

import dev.dubhe.flatworld.gen.FlatWorldGenerationSettings;
import net.minecraft.server.dedicated.ServerPropertiesHandler;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GeneratorOptions.class)
public class GeneratorOptionsMixin {

  @Inject(
      method = "fromProperties",
      at = @At("RETURN"),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true)
  private static void addFlatWorldGeneratorOptionWhenLoadingProperties(
      DynamicRegistryManager drm,
      ServerPropertiesHandler.WorldGenProperties worldGenProperties,
      CallbackInfoReturnable<GeneratorOptions> cir,
      long seed,
      Registry<DimensionType> dimensionTypeRegistry,
      Registry<Biome> biomeRegistry,
      Registry<StructureSet> structureSetRegistry
  ) {
    if (FlatWorldGenerationSettings.NAME.equals(worldGenProperties.levelType())) {
      GeneratorOptions generatorOptions = new GeneratorOptions(
          seed,
          worldGenProperties.generateStructures(),
          false,
          FlatWorldGenerationSettings.getFlatWorldDimensionOptionsRegistry(drm, seed)
      );
      cir.setReturnValue(generatorOptions);
    }
  }
}
