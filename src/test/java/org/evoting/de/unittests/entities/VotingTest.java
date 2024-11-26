package org.evoting.de.unittests.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.evoting.de.voting.domain.model.vote.Vote;

import java.util.Date;

public class VotingTest {

    @Test
    public void testVotingCreation() {
        Vote vote = new Vote("1", "topic1", true, new Date(), new Date());

        assertEquals("1", vote.getId());
        assertEquals("topic1", vote.getTopicId());
        assertTrue(vote.isVotingOptions());
    }

    @Test
    public void testVotingSettersAndGetters() {
        Vote vote = new Vote("1", "topic1", true, new Date(), new Date());

        vote.setTopicId("topic2");
        assertEquals("topic2", vote.getTopicId());

        vote.setVotingOptions(false);
        assertFalse(vote.isVotingOptions());
    }
}