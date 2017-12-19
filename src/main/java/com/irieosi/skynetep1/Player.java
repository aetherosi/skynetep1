package com.irieosi.skynetep1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int nbNodes = in.nextInt();
        int nbLinks = in.nextInt();
        int nbGateways = in.nextInt();

        List<Link> remainingLinks = createLinks(in, nbLinks);
        List<Integer> gateways = createGateways(in, nbGateways);

        while (true) {
            int agentPosition = in.nextInt();

            List<Link> neighbours = getNeighbours(remainingLinks, agentPosition);
            List<Link> gatewayLinks = getGateways(neighbours, gateways);

            Link link = determineLinkToSever(neighbours, gatewayLinks);

            remainingLinks = severLink(link, remainingLinks);
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
            int node1 = in.nextInt();
            int node2 = in.nextInt();
            Link link = new Link(node1, node2);

            links.add(link);
        }
        return links;
    }

    public static Link determineLinkToSever(List<Link> neighbours, List<Link> gatewayLinks) {
        if (gatewayLinks.size() > 0) {
            return gatewayLinks.get(0);
        } else {
            return neighbours.get(0);
        }
    }

    public static List<Link> getGateways(List<Link> links, List<Integer> gateways) {
        return links.stream()
                .filter(link -> gateways.contains(link.getNode1()) || gateways.contains(link.getNode2()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Link> getNeighbours(List<Link> links, int agentPosition) {
        return links.stream()
                .filter(link -> (link.getNode1() == agentPosition) || (link.getNode2() == agentPosition))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Link> severLink(Link linkToSever, List<Link> links) {
        links.remove(linkToSever);
        return links;
    }

}
