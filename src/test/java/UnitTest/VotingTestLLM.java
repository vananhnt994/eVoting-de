package UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.evoting.de.entites.Vote;

import java.time.LocalDateTime;
import java.util.Date;

public class VotingTestLLM {

    private static final String TEST_VOTE_ID = "1";
    private static final String TEST_TOPIC_ID = "topic1";
    private static final String UPDATED_TOPIC_ID = "topic2";

    private Vote vote;

    // Setup method to avoid repeating code in every test
    @BeforeEach
    public void setUp() {
        vote = new Vote(TEST_VOTE_ID, TEST_TOPIC_ID, true, Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()));
    }

    @Test
    public void testVotingCreation() {
        // Assert the creation of the vote object
        assertNotNull(vote, "Vote object should not be null");
        assertEquals(TEST_VOTE_ID, vote.getId(), "Vote ID should match");
        assertEquals(TEST_TOPIC_ID, vote.getTopicId(), "Topic ID should match");
        assertTrue(vote.isVotingOptions(), "Voting options should be true on creation");
    }

    @Test
    public void testVotingSettersAndGetters() {
        // Test the setters and getters of the vote object
        vote.setTopicId(UPDATED_TOPIC_ID);
        assertEquals(UPDATED_TOPIC_ID, vote.getTopicId(), "Topic ID should be updated");

        vote.setVotingOptions(false);
        assertFalse(vote.isVotingOptions(), "Voting options should be set to false");
    }

    @Test
    public void testVotingWithNullValues() {
        // Test edge cases with null values
        Vote voteWithNullTopic = new Vote(null, null, false, new Date(), new Date());
        assertNull(voteWithNullTopic.getId(), "Vote ID should be null");
        assertNull(voteWithNullTopic.getTopicId(), "Topic ID should be null");
        assertFalse(voteWithNullTopic.isVotingOptions(), "Voting options should be false");
    }

    @Test
    public void testVotingWithEmptyStrings() {
        // Test case where empty strings are used
        Vote voteWithEmptyStrings = new Vote("", "", true, new Date(), new Date());
        assertEquals("", voteWithEmptyStrings.getId(), "Vote ID should be an empty string");
        assertEquals("", voteWithEmptyStrings.getTopicId(), "Topic ID should be an empty string");
        assertTrue(voteWithEmptyStrings.isVotingOptions(), "Voting options should be true if set to true");
    }
}