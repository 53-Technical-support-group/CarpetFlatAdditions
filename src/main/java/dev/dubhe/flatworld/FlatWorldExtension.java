package dev.dubhe.flatworld;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import dev.dubhe.flatworld.criterion.Criteria;
import dev.dubhe.flatworld.gen.FlatWorldChunkGenerator;
import dev.dubhe.flatworld.gen.FlatWorldGenerationSettings;
import dev.dubhe.flatworld.mixin.SpawnRestrictionAccessor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

public class FlatWorldExtension implements CarpetExtension, ModInitializer {
  private static SettingsManager settingsManager;

  public FlatWorldExtension() {
    CarpetServer.manageExtension(this);
  }

  @Override
  public void onInitialize() {
    settingsManager = new SettingsManager(Build.VERSION, Build.ID, Build.NAME);
    settingsManager.parseSettingsClass(FlatWorldSettings.class);
    // Restrict Piglin Brute spawning to the ground
    SpawnRestrictionAccessor.register(EntityType.PIGLIN_BRUTE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PiglinBruteEntity::canSpawnInDark);
    Registry.register(
        Registry.CHUNK_GENERATOR, FlatWorldGenerationSettings.NAME, FlatWorldChunkGenerator.CODEC);
    Criteria.registerAll();
  }

  @Override
  public SettingsManager customSettingsManager() {
    return settingsManager;
  }

  @Override
  public String version() {
    return Build.ID + " " + Build.VERSION;
  }
}
