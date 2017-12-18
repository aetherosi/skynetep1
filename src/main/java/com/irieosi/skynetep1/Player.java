package com.irieosi.skynetep1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int nbNodes = in.nextInt(); // the total number of nodes in the level, including the gateways
        int nbLinks = in.nextInt(); // the number of remainingLinks
        int nbGateways = in.nextInt(); // the number of exit gateways


        List<Link> remainingLinks = createLinks(in, nbLinks);

        List<Integer> gateways = createGateways(in, nbGateways);

        List<Node> nodes = createNodes(nbNodes, gateways);

        // game loop
        while (true) {
            int agentPosition = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

        }
    }

    public static List<Integer> createGateways(Scanner in, int nbGateways) {
        List<Integer> gateways = new ArrayList<>();
        for (int i = 0; i < nbGateways; i++) {
            int gateway = in.nextInt(); // the index of a gateway node
            gateways.add(gateway);
        }
        return gateways;
    }

    public static List<Link> createLinks(Scanner in, int nbLinks) {

        List<Link> links = new ArrayList<>();
        for (int i = 0; i < nbLinks; i++) {
            int originNodeId = in.nextInt(); // N1 and N2 defines a link between these nodes
            int destinationNodeId = in.nextInt();
            Link link = new Link(originNodeId, destinationNodeId);

            links.add(link);
        }
        return links;
    }

    public static List<Node> createNodes(int nbNodes, List<Integer> gateways) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nbNodes ; i++) {
            Node node = new Node(0);
            if (gateways.contains(i)) {
                node.setGateway(true);
            }
            nodes.add(node);
        }

        return nodes;
    }


    public static List<Link> determineDeadEnds(List<Link> links, List<Integer> gateways) {
        return links.stream()
                .filter(link -> gateways.contains(link.getOrigin()) || gateways.contains(link.getDestination()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Link> determineImmediateDeadEnds(List<Link> links, int agentPosition) {
        return links.stream()
                .filter(link -> (link.getOrigin() == agentPosition) || (link.getDestination() == agentPosition))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Link> cutLink(Link linkToCut, List<Link> links) {
        links.remove(linkToCut);
        return links;
    }

    public static Link determineLinkToCut(List<Link> deadEnds, List<Link> immediateDeadEnds) {
        if (immediateDeadEnds.size() > 0) {
            return immediateDeadEnds.get(0);
        } else {
            return deadEnds.get(0);
        }
    }
}
