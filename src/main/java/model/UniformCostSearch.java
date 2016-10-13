package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Muhammad Syafiq on 10/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public interface UniformCostSearch<E>
{
    LinkedList<E> getDirection(E source, E destination);
}
