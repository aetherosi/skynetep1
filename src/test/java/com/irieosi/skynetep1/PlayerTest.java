package com.irieosi.skynetep1;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PlayerTest {


    @Test
    public void should_create_three_nodes_and_no_gateway() {
        List<Integer> gatewayIds = new ArrayList<>();
        int nbNodes = 3;

        Assertions.assertThat(Player.createNodes(nbNodes, gatewayIds)).hasSize(nbNodes);
    }

    @Test
    public void should_create_a_gateway_on_node_of_id_2() {
        String input = "2\n";
        int nbGateways = 1;

        Scanner in = new Scanner(input);

        List<Integer> gateways = Player.createGateways(in, nbGateways);

        Assertions.assertThat(gateways).hasSize(nbGateways);
        Assertions.assertThat(gateways.get(0)).isEqualTo(2);
    }

    @Test
    public void should_create_three_nodes_with_one_as_gateway() {
        List<Integer> gateways = new ArrayList<>();
        int nbNodes = 3;

        gateways.add(2);

        List<Node> nodes = Player.createNodes(nbNodes, gateways);
        Assertions.assertThat(nodes.get(2).isGateway()).isTrue();
    }

    @Test
    public void should_create_a_link_between_node_of_id_1_and_node_of_id_2() {
        String input = "1\n"+ "2\n";
        int nbLinks = 1;

        Scanner in = new Scanner(input);

        List<Link> links = Player.createLinks(in, nbLinks);
        Link link = links.get(0);

        Assertions.assertThat(link.getOrigin()).isEqualTo(1);
        Assertions.assertThat(link.getDestination()).isEqualTo(2);
    }

    @Test
    public void should_return_all_links_leading_to_a_gateway() {
        List<Integer> gateways = Arrays.asList(3);
        List<Link> links = new ArrayList<>();
        links.add(new Link(1, 2));
        links.add(new Link(2, 3));



        List<Link> deadEnds = Player.determineDeadEnds(links, gateways);

        Assertions.assertThat(deadEnds).hasSize(1);
    }

    @Test
    public void should_return_all_links_directly_leading_to_a_gateway_according_to_the_agent_position() {
        List<Link> deadEnds = new ArrayList<>();
        int agentPosition = 5;
        deadEnds.add(new Link(0, 2));
        deadEnds.add(new Link(3, 4));
        deadEnds.add(new Link(4, 5));

        List<Link> immediateDeadEnds = Player.determineImmediateDeadEnds(deadEnds, agentPosition);

        Assertions.assertThat(immediateDeadEnds).hasSize(1);
        Assertions.assertThat(immediateDeadEnds.get(0).getOrigin()).isEqualTo(4);
        Assertions.assertThat(immediateDeadEnds.get(0).getDestination()).isEqualTo(5);
    }

    @Test
    public void should_determine_which_link_to_cut() {
        List<Link> deadEnds = new ArrayList<>();
        deadEnds.add(new Link(2, 5));
        deadEnds.add(new Link(1, 3));

        List<Link> immediateDeadEnds = new ArrayList<>();
        Link linkToCut = new Link(0, 2);
        immediateDeadEnds.add(linkToCut);


        Assertions.assertThat(Player.determineLinkToCut(deadEnds, immediateDeadEnds)).isEqualTo(linkToCut);

    }

    @Test
    public void should_severe_a_link() {
        List<Link> links = new ArrayList<>();
        Link linkToCut = new Link(0, 2);
        links.add(linkToCut);
        links.add(new Link(3, 4));
        links.add(new Link(4, 5));

        Assertions.assertThat(Player.cutLink(linkToCut, links)).doesNotContain(linkToCut);
    }


}