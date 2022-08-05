package dev.dubhe.flatworld.criterion;

import dev.dubhe.flatworld.mixin.CriteriaAccessor;

public class Criteria {
  public static final GenerateGeodeCriterion GENERATE_GEODE = new GenerateGeodeCriterion();
  public static final ConvertSpiderCriterion CONVERT_SPIDER = new ConvertSpiderCriterion();

  public static void registerAll() {
    CriteriaAccessor.register(GENERATE_GEODE);
    CriteriaAccessor.register(CONVERT_SPIDER);
  }
}
