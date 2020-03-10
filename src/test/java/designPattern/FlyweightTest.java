package designPattern;

import designPattern.structural.flyweight.Forest;
import org.junit.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;


public class FlyweightTest {
    private static final int CANVAS_SIZE = 500;
    private static final int TREES_TO_DRAW = 1000000;
    private static final int TREE_TYPES = 2;

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    @Test
    public void test_printForest() throws InterruptedException {
        Forest forest = new Forest();
        for (int i = 0; i < Math.floor(TREES_TO_DRAW / TREE_TYPES); i++) {
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Summer Oak", Color.GREEN, "Oak texture stub");
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Autumn Oak", Color.RED, "Autumn Oak texture stub");
        }
        forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
        forest.setVisible(true);

        System.out.println(TREES_TO_DRAW + " trees drawn");
        System.out.println("---------------------");
        System.out.println("Memory usage:");
        System.out.println("Tree size (8 bytes) * " + TREES_TO_DRAW);
        System.out.println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "");
        System.out.println("---------------------");
        System.out.println("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
                "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");

        TimeUnit.SECONDS.sleep(5);
    }
}
