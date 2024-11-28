package org.evoting.de.topicmanagement.domain.model.vote;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class VotingResultLLMTest {

    // Constants for expected values
    private static final String TEST_ID = "1";
    private static final String TEST_RESULT = "approved";
    private static final String TEST_DISTRIBUTION = "60% yes, 40% no";
    private static final String TEST_TOPIC = "topic1";
    private static final String TEST_DESCRIPTION = "The vote was successful.";

    private VotingResult result;

    // Setup method to avoid repeated code in each test
    @BeforeEach
    public void setUp() {
        result = new VotingResult(TEST_ID, TEST_RESULT, TEST_DISTRIBUTION, TEST_TOPIC, TEST_DESCRIPTION);
    }

    @Test
    public void testVotingResultCreation_ValidInputs() {
        // Validate that the VotingResult object is created correctly
        assertEquals(TEST_ID, result.getId(), "ID should match the expected value");
        assertEquals(TEST_RESULT, result.getResult(), "Result should match the expected value");
        assertEquals(TEST_DISTRIBUTION, result.getResultDistribution(), "Distribution should match the expected value");
    }

    @Test
    public void testVotingResultCreation_WithNullValues() {
        // Test the VotingResult object with null values
        VotingResult resultWithNulls = new VotingResult(null, null, null, null, null);

        assertNull(resultWithNulls.getId(), "ID should be null");
        assertNull(resultWithNulls.getResult(), "Result should be null");
        assertNull(resultWithNulls.getResultDistribution(), "Distribution should be null");

    }

    @Test
    public void testVotingResultCreation_WithEmptyStrings() {
        // Test the VotingResult object with empty strings
        VotingResult resultWithEmptyStrings = new VotingResult("", "", "", "", "");

        assertEquals("", resultWithEmptyStrings.getId(), "ID should be an empty string");
        assertEquals("", resultWithEmptyStrings.getResult(), "Result should be an empty string");
        assertEquals("", resultWithEmptyStrings.getResultDistribution(), "Distribution should be an empty string");

    }

    @Test
    public void testVotingResultCreation_InvalidResult() {
        // Test the VotingResult object with an invalid result
        VotingResult resultWithInvalidResult = new VotingResult("2", "invalid", "50% yes, 50% no", "topic2", "The vote failed.");

        assertEquals("2", resultWithInvalidResult.getId(), "ID should match");
        assertEquals("invalid", resultWithInvalidResult.getResult(), "Result should be 'invalid'");
        assertEquals("50% yes, 50% no", resultWithInvalidResult.getResultDistribution(), "Result distribution should be '50% yes, 50% no'");

    }
}
