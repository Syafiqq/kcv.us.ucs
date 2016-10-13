package model;

/**
 * Created by Muhammad Syafiq on 10/11/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class City implements Comparable<City>
{
    private static int totalInstance;

    private final int id;
    private String name;
    public boolean isVisited;

    public City(String name)
    {
        this.id = ++City.totalInstance;
        this.name = name;
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override public String toString()
    {
        return "City{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    @Override public int compareTo(City o)
    {
        return Integer.compare(this.getId(), o.getId());
    }
}
