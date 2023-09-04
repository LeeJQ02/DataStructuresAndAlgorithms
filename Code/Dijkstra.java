import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dijkstra {
  static Scanner input = new Scanner(System.in);

  public static void calculateShortestPathFromSource(Node source) { 
    // to calculate the shortest path from the source node
    source.setDistance(0); // to set distance

    Set<Node> settledNodes = new HashSet<>();
    Set<Node> unsettledNodes = new HashSet<>();
    unsettledNodes.add(source);// unsettled node will be added to unsettled nodes hash set

    while (unsettledNodes.size() != 0) { // loop while the size for unsettle nodes is not 0
      Node currentNode = getLowestDistanceNode(unsettledNodes); 
      // get lowest distance node from unsettled nodes hash set
      unsettledNodes.remove(currentNode); // remove node
      for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
        // get adjacent nodes and input it to Node and
        // get entry set and input it to integer
        Node adjacentNode = adjacencyPair.getKey(); // adjacent pair will get key and set it adjacent node
        Integer edgeWeight = adjacencyPair.getValue(); // adjacent pair will get value and set edge weight

        if (!settledNodes.contains(adjacentNode)) { // if settle node does not have adjacent node
          // calculate the minimun distance by using adjacent node, edge weight,and current node
          CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
          unsettledNodes.add(adjacentNode); // add adjacent node to unsettled nodes hash set
        }
      }
      settledNodes.add(currentNode); // add current node to settled nodes hash set
    }
  }

  public static void welcome() {// print welcome
    System.out.println();
    System.out.println("Welcome To Dijkstra Algorithm!");
    System.out.println();
    System.out.println("Please Enter a MINIMUM of 5 and a MAXIMUM of 10 Different Node Name: ");
    System.out.println("For Empty Node Please Enter '-'");
  }

  public static void select() { // print menu
    System.out.println();
    System.out.println("Please Select:");
    System.out.println("1 - Continue");
    System.out.println("2 - Result");
    System.out.println("0 - Exit");
  }

  public static void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
    Integer sourceDistance = sourceNode.getDistance(); // get distance from source node and assigned to source distance
    // if distance from evaluation node is greater than the total amount of source distance and edge weight
    if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
      // set distance from evaluation node with the total amount of source distance
      // and edge weight
      evaluationNode.setDistance(sourceDistance + edgeWeight);
      // insert shortest path from source node and insert it to the shortest path
      // linked list
      LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
      shortestPath.add(sourceNode); // source node is added to shortest path
      evaluationNode.setShortestPath(shortestPath); // shortest path is set to evaluation node
    }
  }

  public static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
    Node lowestDistanceNode = null; // lowest distance node is set to null
    int lowestDistance = Integer.MAX_VALUE; // Integer.MAX_VALUE is used to initialise all node distances, to create an infinite distance.
    for (Node node : unsettledNodes) { // for loop for unsettle node
      int nodeDistance = node.getDistance(); // get distance from node, set it to nodeDistance
      if (nodeDistance < lowestDistance) { // if node distance lesser than lowest distance
        lowestDistance = nodeDistance; // lowest distance equal to node distance
        lowestDistanceNode = node; // lowest distance node equal to node
      }
    }
    return lowestDistanceNode;
  }

  static class Graph {
    public Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
      // add node A to node
      nodes.add(nodeA);
    }

    public Set<Node> getNodes() { // get node from Node set
      return nodes;
    }

    public void setNodes(Set<Node> nodes) { // set nodes from Node set
      this.nodes = nodes;
    }
  }

  static class Node {
    public String name;
    public List<Node> shortestPath = new LinkedList<>(); // create shortest path linked list
    public Integer distance = Integer.MAX_VALUE; // Integer.MAX_VALUE is used to initialise all node distances, to create an infinite distance.
    public Map<Node, Integer> adjacentNodes = new HashMap<>(); // create hash map that allow Node and Integer to be inserted

    public Node(String name) { // set Node Name
      this.name = name;
    }

    public void addDestination(Node destination, int distance) {
      // obtain node destination and distance value to add destination to adjacent nodes hash map
      adjacentNodes.put(destination, distance);
    }

    public String getName() { // get node name
      return name;
    }

    public void setName(String name) { // set node name
      this.name = name;
    }

    public Map<Node, Integer> getAdjacentNodes() { // get adjacent node and input it to hash map
      return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) { 
      // get adjacent node from hash map and set adjacentNode
      this.adjacentNodes = adjacentNodes;
    }

    public Integer getDistance() { // get distance as integer
      return distance;
    }

    public void setDistance(Integer distance) { // get and set distance
      this.distance = distance;
    }

    public List<Node> getShortestPath() { // get shortest path from Node Linked List
      return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
      // get shortest path from node linked list and set it to the shortest path
      this.shortestPath = shortestPath;
    }

  }

  public static void printPaths(List<Node> nodes) { // print path from Node Linked List
    nodes.forEach(node -> {
      String path = node.getShortestPath().stream()
          .map(Node::getName)
          .collect(Collectors.joining(" -> "));
      System.out.println((path.isBlank()
          ? "%s : %s".formatted(node.getName(), node.getDistance())
          : "%s -> %s : %s".formatted(path, node.getName(), node.getDistance())) // print data from get name, get distance
      );
    });
  }

  // introduction for dijkstra algorithm
  public static void runD() {
    try {
      welcome();
      System.out.println("Enter Node Name Number 1:");
      String node1 = input.next(); // Allow user to input Node 1 Name
      Dijkstra.Node n1 = new Dijkstra.Node(node1);

      System.out.println("Enter Node Name Number 2:");
      String node2 = input.next();
      Dijkstra.Node n2 = new Dijkstra.Node(node2);

      System.out.println("Enter Node Name Number 3:");
      String node3 = input.next();
      Dijkstra.Node n3 = new Dijkstra.Node(node3);

      System.out.println("Enter Node Name Number 4:");
      String node4 = input.next();
      Dijkstra.Node n4 = new Dijkstra.Node(node4);

      System.out.println("Enter Node Name Number 5:");
      String node5 = input.next();
      Dijkstra.Node n5 = new Dijkstra.Node(node5);

      System.out.println("Enter Node Name Number 6:");
      String node6 = input.next();
      Dijkstra.Node n6 = new Dijkstra.Node(node6);

      System.out.println("Enter Node Name Number 7:");
      String node7 = input.next();
      Dijkstra.Node n7 = new Dijkstra.Node(node7);

      System.out.println("Enter Node Name Number 8:");
      String node8 = input.next();
      Dijkstra.Node n8 = new Dijkstra.Node(node8);

      System.out.println("Enter Node Name Number 9:");
      String node9 = input.next();
      Dijkstra.Node n9 = new Dijkstra.Node(node9);

      System.out.println("Enter Node Name Number 10:");
      String node10 = input.next();
      Dijkstra.Node n10 = new Dijkstra.Node(node10);

      // while loop for error handling when user input an invalid input
      main: while (true) {
        System.out.println();
        System.out.println("Select A From Node to add Destination: ");
        System.out.println("Node 1 = " + node1);
        System.out.println("Node 2 = " + node2);
        System.out.println("Node 3 = " + node3);
        System.out.println("Node 4 = " + node4);
        System.out.println("Node 5 = " + node5);
        System.out.println("Node 6 = " + node6);
        System.out.println("Node 7 = " + node7);
        System.out.println("Node 8 = " + node8);
        System.out.println("Node 9 = " + node9);
        System.out.println("Node 10 = " + node10);
        System.out.println("For Example: Enter 1 To Select Node 1");
        int sn = input.nextInt();

        // if user input 1,2,3,4,5,6,7,8,9 or 10 it will continue the code
        if ((sn == 1) || (sn == 2) || (sn == 3) || (sn == 4) || (sn == 5)
            || (sn == 6) || (sn == 7) || (sn == 8) || (sn == 9) || (sn == 10)) {
          if (sn == 1) { // if user select 1 it will continue the code
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 2) {
              System.out.println();
              System.out.println("Enter Distance From Node 1 to Node 2:");
              int d2 = input.nextInt();
              n1.addDestination(n2, d2);
            } else if (add == 3) {
              System.out.println();
              System.out.println("Enter Distance From Node 1 to Node 3:");
              int d3 = input.nextInt();
              n1.addDestination(n3, d3);
            } else if (add == 4) {
              System.out.println();
              System.out.println("Enter Distance From Node 1 to Node 4:");
              int d4 = input.nextInt();
              n1.addDestination(n4, d4);
            } else if (add == 5) {
              System.out.println();
              System.out.println("Enter Distance From Node 1 to Node 5:");
              int d5 = input.nextInt();
              n1.addDestination(n5, d5);
            } else if (add == 6) {
              System.out.println();
              System.out.println("Enter Distance From Node 1 to Node 6:");
              int d6 = input.nextInt();
              n1.addDestination(n6, d6);
            } else if (add == 7) {
              System.out.println();
              System.out.println("Enter Distance From Node 1 to Node 7:");
              int d7 = input.nextInt();
              n1.addDestination(n7, d7);
            } else if (add == 8) {
              System.out.println();
              System.out.println("Enter Distance From Node 1 to Node 8:");
              int d8 = input.nextInt();
              n1.addDestination(n8, d8);
            } else if (add == 9) {
              System.out.println();
              System.out.println("Enter Distance From Node 1 to Node 9:");
              int d9 = input.nextInt();
              n1.addDestination(n9, d9);
            } else if (add == 10) {
              System.out.println();
              System.out.println("Enter Distance From Node 1 to Node 10:");
              int d10 = input.nextInt();
              n1.addDestination(n10, d10);
            } else { 
              // if user input an invalid code it will prompt user invalid input and will loop again
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }

          } else if (sn == 2) {
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 1) {
              System.out.println();
              System.out.println("Enter Distance From Node 2 to Node 1:");
              int d1 = input.nextInt();
              n2.addDestination(n1, d1);
            } else if (add == 3) {
              System.out.println();
              System.out.println("Enter Distance From Node 2 to Node 3:");
              int d3 = input.nextInt();
              n2.addDestination(n3, d3);
            } else if (add == 4) {
              System.out.println();
              System.out.println("Enter Distance From Node 2 to Node 4:");
              int d4 = input.nextInt();
              n2.addDestination(n4, d4);
            } else if (add == 5) {
              System.out.println();
              System.out.println("Enter Distance From Node 2 to Node 5:");
              int d5 = input.nextInt();
              n2.addDestination(n5, d5);
            } else if (add == 6) {
              System.out.println();
              System.out.println("Enter Distance From Node 2 to Node 6:");
              int d6 = input.nextInt();
              n2.addDestination(n6, d6);
            } else if (add == 7) {
              System.out.println();
              System.out.println("Enter Distance From Node 2 to Node 7:");
              int d7 = input.nextInt();
              n2.addDestination(n7, d7);
            } else if (add == 8) {
              System.out.println();
              System.out.println("Enter Distance From Node 2 to Node 8:");
              int d8 = input.nextInt();
              n2.addDestination(n8, d8);
            } else if (add == 9) {
              System.out.println();
              System.out.println("Enter Distance From Node 2 to Node 9:");
              int d9 = input.nextInt();
              n2.addDestination(n9, d9);
            } else if (add == 10) {
              System.out.println();
              System.out.println("Enter Distance From Node 2 to Node 10:");
              int d10 = input.nextInt();
              n2.addDestination(n10, d10);
            } else { 
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }

          } else if (sn == 3) {
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 1) {
              System.out.println();
              System.out.println("Enter Distance From Node 3 to Node 1:");
              int d1 = input.nextInt();
              n3.addDestination(n1, d1);
            } else if (add == 2) {
              System.out.println();
              System.out.println("Enter Distance From Node 3 to Node 2:");
              int d2 = input.nextInt();
              n3.addDestination(n2, d2);
            } else if (add == 4) {
              System.out.println();
              System.out.println("Enter Distance From Node 3 to Node 4:");
              int d4 = input.nextInt();
              n3.addDestination(n4, d4);
            } else if (add == 5) {
              System.out.println();
              System.out.println("Enter Distance From Node 3 to Node 5:");
              int d5 = input.nextInt();
              n3.addDestination(n5, d5);
            } else if (add == 6) {
              System.out.println();
              System.out.println("Enter Distance From Node 3 to Node 6:");
              int d6 = input.nextInt();
              n3.addDestination(n6, d6);
            } else if (add == 7) {
              System.out.println();
              System.out.println("Enter Distance From Node 3 to Node 7:");
              int d7 = input.nextInt();
              n3.addDestination(n7, d7);
            } else if (add == 8) {
              System.out.println();
              System.out.println("Enter Distance From Node 3 to Node 8:");
              int d8 = input.nextInt();
              n3.addDestination(n8, d8);
            } else if (add == 9) {
              System.out.println();
              System.out.println("Enter Distance From Node 3 to Node 9:");
              int d9 = input.nextInt();
              n3.addDestination(n9, d9);
            } else if (add == 10) {
              System.out.println();
              System.out.println("Enter Distance From Node 3 to Node 10:");
              int d10 = input.nextInt();
              n3.addDestination(n10, d10);
            } else { 
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }

          } else if (sn == 4) {
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 1) {
              System.out.println();
              System.out.println("Enter Distance From Node 4 to Node 1:");
              int d1 = input.nextInt();
              n4.addDestination(n1, d1);
            } else if (add == 2) {
              System.out.println();
              System.out.println("Enter Distance From Node 4 to Node 2:");
              int d2 = input.nextInt();
              n4.addDestination(n2, d2);
            } else if (add == 3) {
              System.out.println();
              System.out.println("Enter Distance From Node 4 to Node 3:");
              int d3 = input.nextInt();
              n4.addDestination(n3, d3);
            } else if (add == 5) {
              System.out.println();
              System.out.println("Enter Distance From Node 4 to Node 5:");
              int d5 = input.nextInt();
              n4.addDestination(n5, d5);
            } else if (add == 6) {
              System.out.println();
              System.out.println("Enter Distance From Node 4 to Node 6:");
              int d6 = input.nextInt();
              n4.addDestination(n6, d6);
            } else if (add == 7) {
              System.out.println();
              System.out.println("Enter Distance From Node 4 to Node 7:");
              int d7 = input.nextInt();
              n4.addDestination(n7, d7);
            } else if (add == 8) {
              System.out.println();
              System.out.println("Enter Distance From Node 4 to Node 8:");
              int d8 = input.nextInt();
              n4.addDestination(n8, d8);
            } else if (add == 9) {
              System.out.println();
              System.out.println("Enter Distance From Node 4 to Node 9:");
              int d9 = input.nextInt();
              n4.addDestination(n9, d9);
            } else if (add == 10) {
              System.out.println();
              System.out.println("Enter Distance From Node 4 to Node 10:");
              int d10 = input.nextInt();
              n4.addDestination(n10, d10);
            } else { 
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }

          } else if (sn == 5) {
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 1) {
              System.out.println();
              System.out.println("Enter Distance From Node 5 to Node 1:");
              int d1 = input.nextInt();
              n5.addDestination(n1, d1);
            } else if (add == 2) {
              System.out.println();
              System.out.println("Enter Distance From Node 5 to Node 2:");
              int d2 = input.nextInt();
              n5.addDestination(n2, d2);
            } else if (add == 3) {
              System.out.println();
              System.out.println("Enter Distance From Node 5 to Node 3:");
              int d3 = input.nextInt();
              n5.addDestination(n3, d3);
            } else if (add == 4) {
              System.out.println();
              System.out.println("Enter Distance From Node 5 to Node 4:");
              int d4 = input.nextInt();
              n5.addDestination(n4, d4);
            } else if (add == 6) {
              System.out.println();
              System.out.println("Enter Distance From Node 5 to Node 6:");
              int d6 = input.nextInt();
              n5.addDestination(n6, d6);
            } else if (add == 7) {
              System.out.println();
              System.out.println("Enter Distance From Node 5 to Node 7:");
              int d7 = input.nextInt();
              n5.addDestination(n7, d7);
            } else if (add == 8) {
              System.out.println();
              System.out.println("Enter Distance From Node 5 to Node 8:");
              int d8 = input.nextInt();
              n5.addDestination(n8, d8);
            } else if (add == 9) {
              System.out.println();
              System.out.println("Enter Distance From Node 5 to Node 9:");
              int d9 = input.nextInt();
              n5.addDestination(n9, d9);
            } else if (add == 10) {
              System.out.println();
              System.out.println("Enter Distance From Node 5 to Node 10:");
              int d10 = input.nextInt();
              n5.addDestination(n10, d10);
            } else { 
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }

          } else if (sn == 6) {
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 1) {
              System.out.println();
              System.out.println("Enter Distance From Node 6 to Node 1:");
              int d1 = input.nextInt();
              n6.addDestination(n1, d1);
            } else if (add == 2) {
              System.out.println();
              System.out.println("Enter Distance From Node 6 to Node 2:");
              int d2 = input.nextInt();
              n6.addDestination(n2, d2);
            } else if (add == 3) {
              System.out.println();
              System.out.println("Enter Distance From Node 6 to Node 3:");
              int d3 = input.nextInt();
              n6.addDestination(n3, d3);
            } else if (add == 4) {
              System.out.println();
              System.out.println("Enter Distance From Node 6 to Node 4:");
              int d4 = input.nextInt();
              n6.addDestination(n4, d4);
            } else if (add == 5) {
              System.out.println();
              System.out.println("Enter Distance From Node 6 to Node 5:");
              int d5 = input.nextInt();
              n6.addDestination(n5, d5);
            } else if (add == 7) {
              System.out.println();
              System.out.println("Enter Distance From Node 6 to Node 7:");
              int d7 = input.nextInt();
              n6.addDestination(n7, d7);
            } else if (add == 8) {
              System.out.println();
              System.out.println("Enter Distance From Node 6 to Node 8:");
              int d8 = input.nextInt();
              n6.addDestination(n8, d8);
            } else if (add == 9) {
              System.out.println();
              System.out.println("Enter Distance From Node 6 to Node 9:");
              int d9 = input.nextInt();
              n6.addDestination(n9, d9);
            } else if (add == 10) {
              System.out.println();
              System.out.println("Enter Distance From Node 6 to Node 10:");
              int d10 = input.nextInt();
              n6.addDestination(n10, d10);
            } else { 
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }
          } else if (sn == 7) {
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 1) {
              System.out.println();
              System.out.println("Enter Distance From Node 7 to Node 1:");
              int d1 = input.nextInt();
              n7.addDestination(n1, d1);
            } else if (add == 2) {
              System.out.println();
              System.out.println("Enter Distance From Node 7 to Node 2:");
              int d2 = input.nextInt();
              n7.addDestination(n2, d2);
            } else if (add == 3) {
              System.out.println();
              System.out.println("Enter Distance From Node 7 to Node 3:");
              int d3 = input.nextInt();
              n7.addDestination(n3, d3);
            } else if (add == 4) {
              System.out.println();
              System.out.println("Enter Distance From Node 7 to Node 4:");
              int d4 = input.nextInt();
              n7.addDestination(n4, d4);
            } else if (add == 5) {
              System.out.println();
              System.out.println("Enter Distance From Node 7 to Node 5:");
              int d5 = input.nextInt();
              n7.addDestination(n5, d5);
            } else if (add == 6) {
              System.out.println();
              System.out.println("Enter Distance From Node 7 to Node 6:");
              int d6 = input.nextInt();
              n7.addDestination(n6, d6);
            } else if (add == 8) {
              System.out.println();
              System.out.println("Enter Distance From Node 7 to Node 8:");
              int d8 = input.nextInt();
              n7.addDestination(n8, d8);
            } else if (add == 9) {
              System.out.println();
              System.out.println("Enter Distance From Node 7 to Node 9:");
              int d9 = input.nextInt();
              n7.addDestination(n9, d9);
            } else if (add == 10) {
              System.out.println();
              System.out.println("Enter Distance From Node 7 to Node 10:");
              int d10 = input.nextInt();
              n7.addDestination(n10, d10);
            } else { 
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }
          } else if (sn == 8) {
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 1) {
              System.out.println();
              System.out.println("Enter Distance From Node 8 to Node 1:");
              int d1 = input.nextInt();
              n8.addDestination(n1, d1);
            } else if (add == 2) {
              System.out.println();
              System.out.println("Enter Distance From Node 8 to Node 2:");
              int d2 = input.nextInt();
              n8.addDestination(n2, d2);
            } else if (add == 3) {
              System.out.println();
              System.out.println("Enter Distance From Node 8 to Node 3:");
              int d3 = input.nextInt();
              n8.addDestination(n3, d3);
            } else if (add == 4) {
              System.out.println();
              System.out.println("Enter Distance From Node 8 to Node 4:");
              int d4 = input.nextInt();
              n8.addDestination(n4, d4);
            } else if (add == 5) {
              System.out.println();
              System.out.println("Enter Distance From Node 8 to Node 5:");
              int d5 = input.nextInt();
              n8.addDestination(n5, d5);
            } else if (add == 6) {
              System.out.println();
              System.out.println("Enter Distance From Node 8 to Node 6:");
              int d6 = input.nextInt();
              n8.addDestination(n6, d6);
            } else if (add == 7) {
              System.out.println();
              System.out.println("Enter Distance From Node 8 to Node 8:");
              int d7 = input.nextInt();
              n8.addDestination(n7, d7);
            } else if (add == 9) {
              System.out.println();
              System.out.println("Enter Distance From Node 8 to Node 9:");
              int d9 = input.nextInt();
              n8.addDestination(n9, d9);
            } else if (add == 10) {
              System.out.println();
              System.out.println("Enter Distance From Node 8 to Node 10:");
              int d10 = input.nextInt();
              n8.addDestination(n10, d10);
            } else { 
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }
          } else if (sn == 9) {
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 1) {
              System.out.println();
              System.out.println("Enter Distance From Node 9 to Node 1:");
              int d1 = input.nextInt();
              n9.addDestination(n1, d1);
            } else if (add == 2) {
              System.out.println();
              System.out.println("Enter Distance From Node 9 to Node 2:");
              int d2 = input.nextInt();
              n9.addDestination(n2, d2);
            } else if (add == 3) {
              System.out.println();
              System.out.println("Enter Distance From Node 9 to Node 3:");
              int d3 = input.nextInt();
              n9.addDestination(n3, d3);
            } else if (add == 4) {
              System.out.println();
              System.out.println("Enter Distance From Node 9 to Node 4:");
              int d4 = input.nextInt();
              n9.addDestination(n4, d4);
            } else if (add == 5) {
              System.out.println();
              System.out.println("Enter Distance From Node 9 to Node 5:");
              int d5 = input.nextInt();
              n9.addDestination(n5, d5);
            } else if (add == 6) {
              System.out.println();
              System.out.println("Enter Distance From Node 9 to Node 6:");
              int d6 = input.nextInt();
              n9.addDestination(n6, d6);
            } else if (add == 7) {
              System.out.println();
              System.out.println("Enter Distance From Node 9 to Node 7:");
              int d7 = input.nextInt();
              n9.addDestination(n7, d7);
            } else if (add == 8) {
              System.out.println();
              System.out.println("Enter Distance From Node 9 to Node 8:");
              int d8 = input.nextInt();
              n9.addDestination(n8, d8);
            } else if (add == 10) {
              System.out.println();
              System.out.println("Enter Distance From Node 9 to Node 10:");
              int d10 = input.nextInt();
              n9.addDestination(n10, d10);
            } else { 
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }
          } else if (sn == 10) {
            System.out.println();
            System.out.println("Select A 'TO' Node To Add Destination To Node: " + sn);
            int add = input.nextInt();

            if (add == 1) {
              System.out.println();
              System.out.println("Enter Distance From Node 10 to Node 1:");
              int d1 = input.nextInt();
              n10.addDestination(n1, d1);
            } else if (add == 2) {
              System.out.println();
              System.out.println("Enter Distance From Node 10 to Node 2:");
              int d2 = input.nextInt();
              n10.addDestination(n2, d2);
            } else if (add == 3) {
              System.out.println();
              System.out.println("Enter Distance From Node 10 to Node 3:");
              int d3 = input.nextInt();
              n10.addDestination(n3, d3);
            } else if (add == 4) {
              System.out.println();
              System.out.println("Enter Distance From Node 10 to Node 4:");
              int d4 = input.nextInt();
              n10.addDestination(n4, d4);
            } else if (add == 5) {
              System.out.println();
              System.out.println("Enter Distance From Node 10 to Node 5:");
              int d5 = input.nextInt();
              n10.addDestination(n5, d5);
            } else if (add == 6) {
              System.out.println();
              System.out.println("Enter Distance From Node 10 to Node 6:");
              int d6 = input.nextInt();
              n10.addDestination(n6, d6);
            } else if (add == 7) {
              System.out.println();
              System.out.println("Enter Distance From Node 10 to Node 7:");
              int d7 = input.nextInt();
              n10.addDestination(n7, d7);
            } else if (add == 8) {
              System.out.println();
              System.out.println("Enter Distance From Node 10 to Node 8:");
              int d8 = input.nextInt();
              n10.addDestination(n8, d8);
            } else if (add == 9) {
              System.out.println();
              System.out.println("Enter Distance From Node 10 to Node 9:");
              int d9 = input.nextInt();
              n10.addDestination(n9, d9);
            } else { 
              System.out.println("Invaid Input");
              System.out.println("Please Select A Valid Node!");
              continue;
            }
          }

          select: while (true) {
            select();
            int userRequest = input.nextInt();
            if (userRequest == 0) {
              System.exit(0);
            } else if (userRequest == 1) {
              continue main;
            } else if (userRequest == 2) {
              System.out.println("Amount of Nodes You Enter: ");
              int nodeAmount = input.nextInt();
              System.out.println("Please Enter Source Node: ");
              System.out.println("Node 1 = " + node1);
              System.out.println("Node 2 = " + node2);
              System.out.println("Node 3 = " + node3);
              System.out.println("Node 4 = " + node4);
              System.out.println("Node 5 = " + node5);
              System.out.println("Node 6 = " + node6);
              System.out.println("Node 7 = " + node7);
              System.out.println("Node 8 = " + node8);
              System.out.println("Node 9 = " + node9);
              System.out.println("Node 10 = " + node10);

              if ((nodeAmount == 5) || (nodeAmount == 6) || (nodeAmount == 7) || (nodeAmount == 8) || (nodeAmount == 9)
                  || (nodeAmount == 10)) {
                switch (nodeAmount) {
                  case 5:
                    Dijkstra.calculateShortestPathFromSource(n1);
                    Dijkstra.printPaths(Arrays.asList(n1, n2, n3, n4, n5));
                    continue select;
                  case 6:
                    Dijkstra.calculateShortestPathFromSource(n1);
                    Dijkstra.printPaths(Arrays.asList(n1, n2, n3, n4, n5, n6));
                    continue select;
                  case 7:
                    Dijkstra.calculateShortestPathFromSource(n1);
                    Dijkstra.printPaths(Arrays.asList(n1, n2, n3, n4, n5, n6, n7));
                    continue select;
                  case 8:
                    Dijkstra.calculateShortestPathFromSource(n1);
                    Dijkstra.printPaths(Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8));
                    continue select;
                  case 9:
                    Dijkstra.calculateShortestPathFromSource(n1);
                    Dijkstra.printPaths(Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8, n9));
                    continue select;
                  case 10:
                    Dijkstra.calculateShortestPathFromSource(n1);
                    Dijkstra.printPaths(Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10));
                    continue select;
                }
              } else { // if user input an invalid code it will prompt user invalid input and will loop again
                System.out.println("Invaid Input!");
                System.out.println("Please Select A Valid Source Node!");
                continue;
              }

            } else { // if user input an invalid it will prompt user invalid input and will loop again
              System.out.println("Invaid Input!");
              continue select;
            }
          }
        } else { // if user input an invalid it will prompt user invalid input and will loop again
          System.out.println("Invaid Input!");
          System.out.println("Please Select A Valid Node!");
          continue;
        }
      }
    } catch (Exception e) {
      System.out.println("Something went wrong.");
    } finally {
      System.out.println("The program is terminated.");
    }
  }
}
