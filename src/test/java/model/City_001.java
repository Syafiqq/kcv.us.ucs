package model;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Muhammad Syafiq on 10/11/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class City_001
{
    private int citySize;

    @Before public void init()
    {
        this.citySize = 10;
    }

    @Test public void testCity_001()
    {
        City a[] = new City[this.citySize];
        char cityName = 'A' - 1;
        for(int i = -1, is = this.citySize; ++i < is;)
        {
            a[i] = new City(String.valueOf(++cityName));
        }

        for(final City city : a)
        {
            System.out.println(city);
        }
    }
}
