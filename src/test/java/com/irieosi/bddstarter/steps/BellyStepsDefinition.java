package com.irieosi.bddstarter.steps;

import com.irieosi.bddstarter.Belly;
import cucumber.api.java8.En;

import static org.assertj.core.api.Assertions.*;

public class BellyStepsDefinition implements En {

    public Belly belly;

    public BellyStepsDefinition() {
        Before(()-> belly = new Belly());

        Given("^I have (\\d+) cukes in my belly$", (Integer cukes) -> {
            belly.eat(cukes);

        });

        When("^I wait (\\d+) hour$", (Integer digestionTime) -> {
            // Write code here that turns the phrase above into concrete actions
           belly.digest(digestionTime);
        });

        Then("^my belly should growl$", () -> {
            assertThat(belly.growl()).isEqualTo("GRRRRR");
        });

        After(()->{

        });
    }
}