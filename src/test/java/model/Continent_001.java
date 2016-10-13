package model;

import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


/**
 * Created by Muhammad Syafiq on 10/11/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
class Topology implements UndirectedGraph<City, Integer>, UniformCostSearch<City>
{
    private PriorityQueue<Integer> fringe;
    private LinkedHashMap<City, LinkedHashMap<City, Integer>> topology;

    public Topology()
    {
        this.topology = new LinkedHashMap<>();
        this.fringe = new PriorityQueue<>();
    }

    @Override public int degreeOf(City city)
    {
        return this.containsVertex(city) ? this.topology.get(city).size() : -1;
    }

    @Override public Set<Integer> getAllEdges(City source, City destination)
    {
        System.err.printf("this method is not defined\n");
        return null;
    }

    @Override public Integer getEdge(City source, City destination)
    {
        int edge;
        if(this.containsVertex(source))
        {
            if(this.containsVertex(destination))
            {
                if(this.topology.get(source).containsKey(destination))
                {
                    edge = this.topology.get(source).get(destination);
                }
                else
                {
                    System.err.printf("No link between two cities\n");
                    edge = -1;
                }
            }
            else
            {
                System.err.printf("%s not defined in topology\n", destination);
                edge = -1;
            }
        }
        else
        {
            System.err.printf("%s not defined in topology\n", source);
            edge = -1;
        }
        return edge;
    }

    @Override public EdgeFactory<City, Integer> getEdgeFactory()
    {
        System.err.printf("this method is not defined\n");
        return null;
    }

    @Override public Integer addEdge(City source, City destination)
    {
        System.err.printf("this method is not defined\n");
        return -1;
    }

    @Override public boolean addEdge(City source, City destination, Integer distance)
    {
        boolean success = false;
        if(this.containsVertex(source))
        {
            if(this.containsVertex(destination))
            {
                this.topology.get(source).put(destination, distance);
                this.topology.get(destination).put(source, distance);
                success = true;
            }
            else
            {
                System.err.printf("%s not defined in topology\n", destination);
            }
        }
        else
        {
            System.err.printf("%s not defined in topology\n", source);
        }
        return success;
    }

    @Override public boolean addVertex(City city)
    {
        if(this.containsVertex(city))
        {
            System.err.printf("%s is Already Defined\n", city);
            return false;
        }
        else
        {
            this.topology.put(city, new LinkedHashMap<>());
            return true;
        }
    }

    @Override public boolean containsEdge(City source, City destination)
    {
        System.err.printf("this method is not defined\n");
        return false;
    }

    @Override public boolean containsEdge(Integer integer)
    {
        System.err.printf("this method is not defined\n");
        return false;
    }

    @Override public boolean containsVertex(City city)
    {
        return this.topology.containsKey(city);
    }

    @Override public Set<Integer> edgeSet()
    {
        System.err.printf("this method is not defined\n");
        return null;
    }

    @Override public Set<Integer> edgesOf(City city)
    {
        System.err.printf("this method is not defined\n");
        return null;
    }

    @Override public boolean removeAllEdges(Collection<? extends Integer> edges)
    {
        System.err.printf("this method is not defined\n");
        return false;
    }

    @Override public Set<Integer> removeAllEdges(City source, City destination)
    {
        System.err.printf("this method is not defined\n");
        return null;
    }

    @Override public boolean removeAllVertices(Collection<? extends City> cities)
    {
        boolean success = true;
        for(final City city : cities)
        {
            success &= this.removeVertex(city);
        }
        return success;
    }

    @Override public Integer removeEdge(City source, City destination)
    {
        int distance = -1;
        if(this.containsVertex(source))
        {
            if(this.containsVertex(destination))
            {
                distance = this.topology.get(source).remove(destination);
                this.topology.get(destination).remove(source);
            }
            else
            {
                System.err.printf("%s not defined in topology\n", destination);
            }
        }
        else
        {
            System.err.printf("%s not defined in topology\n", source);
        }
        return distance;
    }

    @Override public boolean removeEdge(Integer integer)
    {
        System.err.printf("this method is not defined\n");
        return false;
    }

    @Override public boolean removeVertex(City city)
    {
        boolean success = false;
        if(this.containsVertex(city))
        {
            this.topology.remove(city);
            success = true;
        }
        else
        {
            System.err.printf("%s not defined in topology\n", city);
        }
        return success;
    }

    @Override public Set<City> vertexSet()
    {
        return this.topology.keySet();
    }

    @Override public City getEdgeSource(Integer integer)
    {
        System.err.printf("this method is not defined\n");
        return null;
    }

    @Override public City getEdgeTarget(Integer integer)
    {
        System.err.printf("this method is not defined\n");
        return null;
    }

    @Override public double getEdgeWeight(Integer integer)
    {
        return WeightedGraph.DEFAULT_EDGE_WEIGHT;
    }

    @Override public LinkedList<City> getDirection(City source, City destination)
    {
        PriorityQueue<Map.Entry<Map.Entry<City, City>, Integer>> queue = new PriorityQueue<>((o1, o2) -> (int) Math.signum(o1.getValue() - o2.getValue()));
        TransverseNode node = new TransverseNode(null, null);
        queue.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(null, source), 0));

        boolean isFinish = true;
        while((!queue.isEmpty()))
        {
            System.out.printf("%-30s : %s\n", "Node data", node.data);
            Map.Entry<Map.Entry<City, City>, Integer> ct = queue.poll();
            final City ctdestination =ct.getKey().getValue();
            final City ctsource = ct.getKey().getKey();
            System.out.printf("%-30s : %s\n", "Remaining Queue", queue);
            ctdestination.isVisited = true;
            for(Map.Entry<City, Integer> neighbour : this.topology.get(ctdestination).entrySet())
            {
                if(!neighbour.getKey().isVisited)
                {
                    queue.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(ctdestination, neighbour.getKey()), neighbour.getValue()));
                }
            }
            System.out.printf("%-30s : %s\n", "Queue now", queue);
            if(node.parent == null)
            {
                System.out.printf("%-30s : %s\n", "", "IF");
                node = new TransverseNode(ctdestination, node);
                System.out.printf("%-30s : %s\n", "Node Data", node.data);
            }
            else
            {
                System.out.printf("%-30s : %s\n", "", "ELSE");
                node = new TransverseNode(ctdestination, doParentTranverse(node, ctsource));
                System.out.printf("%-30s : %s\n", "Node Data", node.data);
            }
            TransverseNode nodee = node;
            while(nodee != null)
            {
                System.out.printf("%s\t", nodee.data);
                nodee = nodee.parent;
            }
            System.out.printf("\n%-30s : %s\n\n\n", "", "END");
        }
        return null;



    }

    private TransverseNode doParentTranverse(TransverseNode node, City data)
    {
        TransverseNode tmp = null;
        if(node == null)
        {
            tmp = null;
        }
        else
        {
            if(node.data.compareTo(data) == 0)
            {
                tmp = node;
            }
            else
            {
                if(node.parent != null)
                {
                    tmp = doParentTranverse(node.parent, data);
                }
                if(tmp == null)
                {
                    for(int i = -1, is = node.neighbour.size(); ++i < is; )
                    {
                        tmp = doParentTranverse(node.neighbour.get(i), data);
                        if(tmp != null)
                        {
                            break;
                        }
                    }
                }
            }
        }
        return tmp;
    }
}

class TransverseNode
{
    public final City data;
    public TransverseNode parent;
    public ArrayList<TransverseNode> neighbour;
    public TransverseNode(City source, TransverseNode parent)
    {
        this.neighbour = new ArrayList<>();
        this.parent = parent;
        this.data = source;
    }
}

public class Continent_001
{
    @Test public void Test_001()
    {
        LinkedHashMap<Character, City> cities = new LinkedHashMap<>('D' - 'A' + 1);
        for(char letter = 'A' - 1, size = 'D'; ++letter <= size; )
        {
            cities.put(letter, new City(String.valueOf(letter)));
        }

        Topology tp = new Topology();
        tp.addVertex(cities.get('A'));
        tp.addVertex(cities.get('B'));
        tp.addVertex(cities.get('C'));
        tp.addVertex(cities.get('D'));
        tp.addEdge(cities.get('A'), cities.get('B'), 10);
        tp.addEdge(cities.get('A'), cities.get('C'), 20);
        tp.addEdge(cities.get('C'), cities.get('D'), 20);
        tp.getDirection(cities.get('A'), cities.get('D'));

        for(Map.Entry<Character, City> city : cities.entrySet())
        {
            //System.out.println(city.getValue());
        }


    }
}
