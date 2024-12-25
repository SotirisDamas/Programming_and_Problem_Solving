package Assignment2;
/**
 * Name and ID: Sotirios Damas, 40317602
 * Assignment #: 2
 * Due Date: 11/16/2024
 */
import java.util.Arrays;
import java.util.Scanner;

/**
 * Participant.java
 */

/**
 * Class: Participant
 * Description: Represents a participant in the Diwali Diyas Collection Contest.
 */
public class Participant {
    private String participantID;    // Unique identifier for the participant
    private String participantName;  // Name of the participant
    private int[] diyaCollection;    // Array representing counts of each type of Diya
    //private boolean isTopThree;      // Indicates if the participant is in the top three

    /**
     * Parameterized Constructor
     * Initializes the Participant with the provided ID, name, and Diya collection.
     *
     * @param participantID    Unique identifier for the participant.
     * @param participantName  Name of the participant.
     * @param diyaCollection   Array containing counts of each type of Diya.
     */
    public Participant(String participantID, String participantName, int[] diyaCollection) {
        this.participantID = participantID;
        this.participantName = participantName;
        this.diyaCollection = diyaCollection.clone(); // Ensures a deep copy to prevent external modifications
        //this.isTopThree = false; // Default value
    }

    /**
     * Copy Constructor for Cloning
     * Creates a new Participant by copying attributes from an existing Participant,
     * except for the participantID, which is assigned a new unique value.
     *
     * @param other             The Participant object to copy.
     * @param newParticipantID  The new unique participantID to assign.
     */
    public Participant(Participant other, String newParticipantID) {
        this.participantID = newParticipantID;
        this.participantName = other.participantName;
        this.diyaCollection = other.diyaCollection.clone(); // Deep copy to prevent shared references
        //this.isTopThree = other.isTopThree; // Copy the top three status
    }
    
    
    /**
     * Clone Method
     * Prompts the user to enter a new participantID and creates a clone of the current Participant.
     *
     * @return A new Participant object cloned from the current Participant with a new participantID.
     */
    public Participant clone() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new participantID for clone: ");
        String newParticipantID = sc.nextLine().trim();
        System.out.print("Enter new participantName for clone: ");
        String newParticipantName = sc.nextLine().trim();
        return new Participant(newParticipantID, newParticipantName, this.diyaCollection.clone());
    }


    /**
     * Gets the participantID.
     *
     * @return The participant's unique identifier.
     */
    public String getParticipantID() {
        return participantID;
    }

    /**
     * Sets the participantID.
     *
     * @param participantID The new participant ID.
     */
    public void setParticipantID(String participantID) {
        this.participantID = participantID;
    }

    /**
     * Gets the participantName.
     *
     * @return The participant's name.
     */
    public String getParticipantName() {
        return participantName;
    }

    /**
     * Sets the participantName.
     *
     * @param participantName The new participant name.
     */
    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    /**
     * Gets the diyaCollection.
     *
     * @return A cloned array of the Diya collection to prevent privacy leaks.
     */
    public int[] getDiyaCollection() {
        return diyaCollection.clone(); // Prevents privacy leak by returning a copy
    }

    /**
     * Sets the diyaCollection.
     *
     * @param diyaCollection The new Diya collection array.
     */
    public void setDiyaCollection(int[] diyaCollection) {
        this.diyaCollection = diyaCollection.clone(); // Ensures a deep copy
    }

  

    /**
     * toString Method
     * Returns a string representation of the Participant object.
     *
     * @return A string containing participantID, participantName, diyaCollection details.
     */
    @Override
    public String toString() {
        return "ParticipantID: " + participantID +
               ", Name: " + participantName +
               ", DiyaCollection: " + Arrays.toString(diyaCollection);
    }

    /**
     * equals Method
     * Determines if this Participant is equal to another object.
     * Two Participants are equal if they have the same participantName and diyaCollection,
     * regardless of their participantID.
     *
     * @param obj The object to compare with.
     * @return True if equal based on participantName and diyaCollection; otherwise, false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        
        if (obj == null || !(obj instanceof Participant))
            return false; // Null or different class
        
        Participant other = (Participant) obj;
        
        // Compare participantName
        if (!this.participantName.equals(other.participantName))
            return false;
        
        // Compare diyaCollection arrays
        return Arrays.equals(this.diyaCollection, other.diyaCollection);
    }
}
