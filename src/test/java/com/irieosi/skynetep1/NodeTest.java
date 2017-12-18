package com.irieosi.skynetep1;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class NodeTest {

    @Test
    public void should_assign_an_id_to_a_node() {
        Node node = new Node(0);
        Assertions.assertThat(node.getId()).isEqualTo(0);
    }

    @Test
    public void should_turn_a_node_into_gateway_node() {
        Node node = new Node(0);
        node.setGateway(true);
    }

}