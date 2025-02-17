package dev.dubhe.flatworld.gen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;


public class FlatWorldStructures {
  protected static class StructureOrientation {
    protected BlockRotation rotation;
    protected BlockMirror mirror;

    public StructureOrientation(@Nullable BlockRotation rotation, @Nullable BlockMirror mirror) {
      this.rotation = rotation == null ? BlockRotation.NONE : rotation;
      this.mirror = mirror == null ? BlockMirror.NONE : mirror;
    }

    protected int applyXTransform(int x, int z, BlockBox boundingBox) {
      if ((rotation == BlockRotation.NONE && mirror != BlockMirror.FRONT_BACK) || (rotation == BlockRotation.CLOCKWISE_180 && mirror == BlockMirror.FRONT_BACK)) {
        return boundingBox.getMinX() + x;
      } else if (rotation == BlockRotation.NONE || rotation == BlockRotation.CLOCKWISE_180) {
        return boundingBox.getMaxX() - x;
      } else if ((rotation == BlockRotation.COUNTERCLOCKWISE_90 && mirror != BlockMirror.LEFT_RIGHT) || (rotation == BlockRotation.CLOCKWISE_90 && mirror == BlockMirror.LEFT_RIGHT)) {
        return boundingBox.getMinX() + z;
      } else {
        return boundingBox.getMaxX() - z;
      }
    }

    protected int applyZTransform(int x, int z, BlockBox boundingBox) {
      if ((rotation == BlockRotation.NONE && mirror != BlockMirror.LEFT_RIGHT) || (rotation == BlockRotation.CLOCKWISE_180 && mirror == BlockMirror.LEFT_RIGHT)) {
        return boundingBox.getMinZ() + z;
      } else if (rotation == BlockRotation.NONE || rotation == BlockRotation.CLOCKWISE_180) {
        return boundingBox.getMaxZ() - z;
      } else if ((rotation == BlockRotation.CLOCKWISE_90 && mirror != BlockMirror.FRONT_BACK) || (rotation == BlockRotation.COUNTERCLOCKWISE_90 && mirror == BlockMirror.LEFT_RIGHT)) {
        return boundingBox.getMinZ() + x;
      } else {
        return boundingBox.getMaxZ() - x;
      }
    }
  }

  protected static abstract class FlatWorldStructure {
    protected BlockBox boundingBox;
    protected StructureOrientation orientation;
    protected BlockRotation rotation;
    protected BlockMirror mirror;

    public FlatWorldStructure(StructurePiece piece) {
      this.boundingBox = piece.getBoundingBox();
      this.rotation = piece.getRotation();
      this.mirror = piece.getMirror();
      this.orientation = new StructureOrientation(this.rotation, this.mirror);
    }

    protected int applyXTransform(int x, int z) {
      return this.orientation.applyXTransform(x, z, this.boundingBox);
    }

    protected int applyYTransform(int y) {
      return y + this.boundingBox.getMinY();
    }

    protected int applyZTransform(int x, int z) {
      return this.orientation.applyZTransform(x, z, this.boundingBox);
    }

    protected BlockPos.Mutable offsetPos(int x, int y, int z) {
      return new BlockPos.Mutable(this.applyXTransform(x, z), this.applyYTransform(y), this.applyZTransform(x, z));
    }

    protected void addBlock(ServerWorldAccess world, BlockState block, int x, int y, int z, BlockBox bounds) {
      BlockPos.Mutable blockPos = this.offsetPos(x, y, z);
      if (!bounds.contains(blockPos)) {
        return;
      }
      if (this.mirror != BlockMirror.NONE) {
        block = block.mirror(this.mirror);
      }
      if (this.rotation != BlockRotation.NONE) {
        block = block.rotate(this.rotation);
      }
      world.setBlockState(blockPos, block, Block.NOTIFY_LISTENERS);
    }

    protected void fillBlocks(
      ServerWorldAccess world,
      BlockState block,
      int minX,
      int minY,
      int minZ,
      int maxX,
      int maxY,
      int maxZ,
      BlockBox bounds) {
      for (int x = minX; x <= maxX; ++x) {
        for (int y = minY; y <= maxY; ++y) {
          for (int z = minZ; z <= maxZ; ++z) {
            this.addBlock(world, block, x, y, z, bounds);
          }
        }
      }
    }

    public abstract void generate(ServerWorldAccess world, BlockBox bounds, Random random);
  }

  public static class EndPortalStructure extends FlatWorldStructure {
    public EndPortalStructure(StructurePiece piece) {
      super(piece);
    }

    @Override
    public void generate(ServerWorldAccess world, BlockBox bounds, Random random) {
      BlockState northFrame = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
      BlockState southFrame = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
      BlockState eastFrame = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
      BlockState westFrame = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
      boolean complete = true;
      boolean[] hasEye = new boolean[12];
      for (int l = 0; l < hasEye.length; ++l) {
        hasEye[l] = random.nextFloat() > 0.9f;
        complete &= hasEye[l];
      }
      this.addBlock(world, southFrame.with(EndPortalFrameBlock.EYE, hasEye[0]), 4, 3, 3, bounds);
      this.addBlock(world, southFrame.with(EndPortalFrameBlock.EYE, hasEye[1]), 5, 3, 3, bounds);
      this.addBlock(world, southFrame.with(EndPortalFrameBlock.EYE, hasEye[2]), 6, 3, 3, bounds);
      this.addBlock(world, northFrame.with(EndPortalFrameBlock.EYE, hasEye[3]), 4, 3, 7, bounds);
      this.addBlock(world, northFrame.with(EndPortalFrameBlock.EYE, hasEye[4]), 5, 3, 7, bounds);
      this.addBlock(world, northFrame.with(EndPortalFrameBlock.EYE, hasEye[5]), 6, 3, 7, bounds);
      this.addBlock(world, eastFrame.with(EndPortalFrameBlock.EYE, hasEye[6]), 3, 3, 4, bounds);
      this.addBlock(world, eastFrame.with(EndPortalFrameBlock.EYE, hasEye[7]), 3, 3, 5, bounds);
      this.addBlock(world, eastFrame.with(EndPortalFrameBlock.EYE, hasEye[8]), 3, 3, 6, bounds);
      this.addBlock(world, westFrame.with(EndPortalFrameBlock.EYE, hasEye[9]), 7, 3, 4, bounds);
      this.addBlock(world, westFrame.with(EndPortalFrameBlock.EYE, hasEye[10]), 7, 3, 5, bounds);
      this.addBlock(world, westFrame.with(EndPortalFrameBlock.EYE, hasEye[11]), 7, 3, 6, bounds);

      if (complete) {
        this.fillBlocks(world, Blocks.END_PORTAL.getDefaultState(), 4, 3, 4, 6, 3, 6, bounds);
      }
    }
  }

  public static class SpawnerStructure extends FlatWorldStructure {
    private final BlockPos spawnerPos;
    private final EntityType<?> spawnerType;

    public SpawnerStructure(StructurePiece piece, BlockPos spawnerPos, EntityType<?> spawnerType) {
      super(piece);
      this.spawnerPos = spawnerPos;
      this.spawnerType = spawnerType;
    }

    @Override
    public void generate(ServerWorldAccess world, BlockBox bounds, Random random) {
      BlockPos.Mutable spawnerAbsolutePos = this.offsetPos(spawnerPos.getX(), spawnerPos.getY(), spawnerPos.getZ());
      if (bounds.contains(spawnerAbsolutePos)) {
        world.setBlockState(spawnerAbsolutePos, Blocks.SPAWNER.getDefaultState(), Block.NOTIFY_LISTENERS);
        BlockEntity blockEntity = world.getBlockEntity(spawnerAbsolutePos);
        if (blockEntity instanceof MobSpawnerBlockEntity spawnerEntity) {
          spawnerEntity.getLogic().setEntityId(spawnerType);
        }
      }
    }
  }

  public static class SilverfishSpawnerStructure extends SpawnerStructure {
    public SilverfishSpawnerStructure(StructurePiece piece) {
      super(piece, new BlockPos(5, 3, 9), EntityType.SILVERFISH);
    }
  }

  public static class MagmaCubeSpawner extends SpawnerStructure {
    public MagmaCubeSpawner(StructurePiece piece) {
      super(piece, new BlockPos(11, 7, 19), EntityType.MAGMA_CUBE);
    }
  }

  public record SpawnPlatform(BlockPos worldSpawn) {
    public void generate(ServerWorldAccess world, Random random) {
      Structure structure = Objects.requireNonNull(world.getServer()).getStructureManager().getStructure(new Identifier("flatworld", "spawn_platform")).orElseThrow();
      BlockPos structureOrigin = worldSpawn.subtract(new BlockPos(4, 3, 1));
      structure.place(world, structureOrigin, worldSpawn, new StructurePlacementData(), random, Block.NOTIFY_LISTENERS);
    }
  }
}
