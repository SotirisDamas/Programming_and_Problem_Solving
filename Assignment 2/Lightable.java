package Assignment2;
/**
 * Name and ID: Sotirios Damas, 40317602
 * Assignment #: 2
 * Due Date: 11/16/2024
 */
/**
 * Interface: Lightable
 * Description: Defines the contract for determining group membership based on top three.
 */
public interface Lightable {
    /**
     * Determines if the given Participant is in the same group as the current participant.
     *
     * @param P The Participant to compare with.
     * @return True if both are in the same group (both in top three or both not); otherwise, false.
     */
    boolean isInTheTopThree(Participant P);
}

