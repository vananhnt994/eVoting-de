package org.evoting.de.topicmanagement.domain.model.vote;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class VotingResultTest {
    @Test
    public void testVotingResultCreation() {
        VotingResult result = new VotingResult("1", "approved", "60% yes, 40% no", "topic1", "The vote was successful.");

        assertEquals("1", result.getId());
        assertEquals("approved", result.getResult());
        assertEquals("60% yes, 40% no", result.getResultDistribution());
    }
}