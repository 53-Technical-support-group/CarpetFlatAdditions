package dev.dubhe.flatworld;

import dev.dubhe.flatworld.gen.FlatWorldGeneratorTypes;
import dev.dubhe.flatworld.mixin.GeneratorTypeAccessor;
import net.fabricmc.api.ClientModInitializer;

public class FlatWorldClient implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    GeneratorTypeAccessor.getValues().add(FlatWorldGeneratorTypes.SKYBLOCK);
  }
}
