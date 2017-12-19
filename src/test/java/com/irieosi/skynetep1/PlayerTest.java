package com.irieosi.skynetep1;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;

public class PlayerTest {

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
    public void should_create_a_link_between_node_of_id_1_and_node_of_id_2() {
        String input = "1\n"+ "2\n";
        int nbLinks = 1;

        Scanner in = new Scanner(input);

        List<Link> links = Player.createLinks(in, nbLinks);
        Link link = links.get(0);

        Assertions.assertThat(link.getNode1()).isEqualTo(1);
        Assertions.assertThat(link.getNode2()).isEqualTo(2);
    }

    @Test
    public void should_return_all_links_leading_to_a_gateway() {
        List<Integer> gateways = Collections.singletonList(3);
        List<Link> links = new ArrayList<>();
        links.add(new Link(1, 2));
        links.add(new Link(2, 3));

        List<Link> deadEnds = Player.getGateways(links, gateways);

        Assertions.assertThat(deadEnds).hasSize(1);
    }

    @Test
    public void should_return_all_neighbours__links_according_to_the_agent_position() {
        List<Link> links = new ArrayList<>();
        int agentPosition = 5;
        links.add(new Link(0, 2));
        links.add(new Link(3, 4));
        links.add(new Link(4, 5));

        List<Link> neighbours = Player.getNeighbours(links, agentPosition);

        Assertions.assertThat(neighbours).hasSize(1);
        Assertions.assertThat(neighbours.get(0).getNode1()).isEqualTo(4);
        Assertions.assertThat(neighbours.get(0).getNode2()).isEqualTo(5);

    }

    @Test
    public void should_determine_which_link_to_sever_when_there_is_a_direct_link_to_a_gateway() {
        List<Link> neighbours = new ArrayList<>();
        neighbours.add(new Link(2, 5));
        neighbours.add(new Link(1, 3));

        List<Link> linksToGateway = new ArrayList<>();
        Link linkToCut = new Link(0, 2);
        linksToGateway.add(linkToCut);


        Assertions.assertThat(Player.determineLinkToSever(neighbours, linksToGateway)).isEqualTo(linkToCut);

    }

    @Test
    public void should_sever_a_link() {
        List<Link> links = new ArrayList<>();
        Link linkToSever = new Link(0, 2);
        links.add(linkToSever);
        links.add(new Link(3, 4));
        links.add(new Link(4, 5));

        Assertions.assertThat(Player.severLink(linkToSever, links)).doesNotContain(linkToSever);
    }


}