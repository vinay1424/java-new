package com.interview.graphs.algorithm;



import java.util.*;

/**
 * Created by abhimaloo on 8/18/14.
 */
public class SimpleGraph {

    public Map<Integer, List<SimpleEdge>> graph = new HashMap<>();

    public void insertEdge(Integer from, Integer to, Integer weight, boolean isDirected) {
        if(!graph.containsKey(from)) {
            graph.put(from, new ArrayList<SimpleEdge>());
        }
        if(!graph.containsKey(to)) {
            graph.put(to, new ArrayList<SimpleEdge>());
        }


        graph.get(from).add(new SimpleEdge(from, to, weight));
        if(!isDirected) {
            graph.get(to).add(new SimpleEdge(to, from, weight));
        }
    }

    public void insertEdge(Integer from, Integer to, Integer weight) {
       insertEdge(from, to, weight, true);
    }

    public List<SimpleEdge> getOutgoingEdges(Integer vertex) {
       return  getOutgoingEdges(vertex, true);
    }

    public Set<Integer> getAdjacentNeighbours(Integer vertex, boolean isDirected) {
       Set<Integer> neighbours = new HashSet<>();
       for(SimpleEdge e: getOutgoingEdges(vertex, isDirected)) {
           if(e.to != vertex) {
               neighbours.add(e.to);
           } else {
               if(!isDirected) {
                   neighbours.add(e.from);
               }
           }
       }
       return neighbours;
    }


    public List<SimpleEdge> getOutgoingEdges(Integer vertex, boolean isDirected) {
        List<SimpleEdge> edges = new ArrayList<>();
        if(!isDirected) {
            // all edges which go to vertex
            for( SimpleEdge e : getAllEdges()) {
                if(e.to == vertex) {
                    edges.add(e);
                }
            }
        }

        // all the edges which starts from vertex
        edges.addAll(graph.get(vertex));
        return edges;
    }



    public Set<Integer> getAllVertex() {
       return new HashSet<>(graph.keySet());
    }

    public List<SimpleEdge> getAllEdges() {
        List<SimpleEdge> edgeList = new ArrayList<>();
        for(Integer vertex : getAllVertex()) {
            edgeList.addAll(getOutgoingEdges(vertex));
        }
        return edgeList;
    }


    public void dfs(Integer sourceVertex) {
        Map<Integer, Boolean> visited = new HashMap<>();
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(sourceVertex);

        while(!stack.isEmpty()) {
            Integer vertex = stack.pop();
            if(!visited.containsKey(vertex)) {
                System.out.println("Visit : "+ vertex);
                visited.put(vertex, true);
            }
            List<SimpleEdge> edges = getOutgoingEdges(vertex);
            if(edges != null) {
                for(SimpleEdge e : getOutgoingEdges(vertex)) {
                    if(!visited.containsKey(e.to)) {
                        stack.push(e.to);
                    }
                }
            }

        }
    }

    public void bfs(Integer sourceVertex) {

        Map<Integer, Boolean> visited = new HashMap<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(sourceVertex);
        while(!queue.isEmpty()) {
            Integer vertex = queue.removeFirst();
            if(!visited.containsKey(vertex)) {
                System.out.println("Visit :" + vertex);
                visited.put(vertex, true);
            }

            List<SimpleEdge> edges = getOutgoingEdges(vertex);
            if(edges != null) {
                for( SimpleEdge e: edges) {
                    if(!visited.containsKey(e.to)){
                        queue.addLast(e.to);
                    }

                }
            }
        }


    }


    public static void main(String[] args) {
        SimpleGraph graph = new SimpleGraph();
        graph.insertEdge(1,2,1);
        graph.insertEdge(2,3,5);
        graph.insertEdge(3,4,2);
        graph.insertEdge(1,5,3);
        graph.insertEdge(5,6,1);
        graph.insertEdge(6,4,2);

        graph.bfs(1);
    }



}
