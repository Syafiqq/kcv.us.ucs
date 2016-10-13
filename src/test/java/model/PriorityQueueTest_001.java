package model;

import org.junit.Test;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Muhammad Syafiq on 10/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class PriorityQueueTest_001
{
    @Test public void Test_001()
    {
        final PriorityQueue<Integer> queue = new PriorityQueue<>();
        final Random random = ThreadLocalRandom.current();
        final int queueSize = 10;
        for(int i = -1, is = queueSize; ++i < is;)
        {
            queue.add(random.nextInt(100));
        }
        while(!queue.isEmpty())
        {
            System.out.printf("%d\t", queue.poll());
        }
    }
}
