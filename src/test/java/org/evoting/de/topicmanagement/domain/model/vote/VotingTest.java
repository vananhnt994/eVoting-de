package org.evoting.de.topicmanagement.domain.model.vote;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.springframework.boot.test.context.SpringBootTest;

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