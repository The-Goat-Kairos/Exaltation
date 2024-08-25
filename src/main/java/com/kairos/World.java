package com.kairos;

import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;

import java.util.List;

public class World {
    public static void generate(GenerationUnit unit) {
        unit.modifier().fillHeight(0, 40, Block.STONE);
        Point start = unit.absoluteStart();
        unit.fork(setter -> {
            for (int x = 0; x <= 16; x++){
                for (int z = 0; z <= 16; z++) {
                    Block randomBlock = ListUtils.random(List.of(
                            Block.GRASS_BLOCK,
                            Block.MOSS_BLOCK,
                            Block.GREEN_CONCRETE_POWDER)
                    );
                    setter.setBlock(start.add(x, 0, z).withY(40), randomBlock);
                }
            }
        });

    }
}
