package com.ajsherrell.wizardjokes;

import com.ajsherrell.manualjokes.JokeSmith;

public class JokeWizard {
    public String tellAWizardJoke() {
        JokeSmith myJokeSmith = new JokeSmith();
        return "A Wizard says " + myJokeSmith.tellAHandCraftedJoke();
    }
}
