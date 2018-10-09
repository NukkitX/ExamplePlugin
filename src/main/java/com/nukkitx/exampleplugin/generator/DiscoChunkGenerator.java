package com.nukkitx.exampleplugin.generator;

import com.flowpowered.math.vector.Vector3f;
import com.nukkitx.api.Server;
import com.nukkitx.api.block.BlockState;
import com.nukkitx.api.block.BlockType;
import com.nukkitx.api.block.BlockTypes;
import com.nukkitx.api.level.Level;
import com.nukkitx.api.level.chunk.Chunk;
import com.nukkitx.api.level.chunk.generator.ChunkGenerator;

import java.util.Random;

public class DiscoChunkGenerator implements ChunkGenerator {
    private static final Vector3f SPAWN = new Vector3f(0, 3, 0);
    private static final BlockType[] TYPES = new BlockType[] {
            BlockTypes.COAL_BLOCK, BlockTypes.DIAMOND_BLOCK, BlockTypes.GOLD_BLOCK, BlockTypes.IRON_BLOCK,
            BlockTypes.REDSTONE_BLOCK, BlockTypes.EMERALD_BLOCK, BlockTypes.LAPIS_LAZULI_BLOCK
    };
    private final Server server;
    private final BlockState bedrock;

    public DiscoChunkGenerator(Server server) {
        this.server = server;
        bedrock = server.blockStateBuilder().setBlockType(BlockTypes.BEDROCK).build();
    }

    @Override
    public void generateChunk(Level level, Chunk chunk, Random random) {
        for (int x1 = 0; x1 < 16; x1++) {
            for (int z1 = 0; z1 < 16; z1++) {
                chunk.setBlock(x1, 0, z1, bedrock, false);
                BlockType type = TYPES[random.nextInt(TYPES.length)];
                chunk.setBlock(x1, 1, z1, server.blockStateBuilder().setBlockType(type).build(), false);
            }
        }

        if (chunk.getX() == 0 && chunk.getZ() == 0) {
            chunk.setBlock(0, 4, 0, bedrock, false);
        }
    }

    @Override
    public void populateChunk(Level level, Chunk chunk, Random random) {
        // Nothing to do... yet
    }

    @Override
    public Vector3f getDefaultSpawn() {
        return SPAWN;
    }
}
