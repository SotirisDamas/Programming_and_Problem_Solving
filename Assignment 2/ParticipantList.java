package Assignment2;
/**
 * Name and ID: Sotirios Damas, 40317602
 * Assignment #: 2
 * Due Date: 11/16/2024
 */
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * ParticipantList.java
 */

/**
 * CompetitionResults.java
 *
 * Name and ID: Sotirios Damas, 40317602
 * Assignment #: 2
 * Due Date: 11/16/2024
 */

/**
 * Class: ParticipantList
 * Description: Manages a linked list of Participant objects.
 */
public class ParticipantList {

    /**
     * Inner Class: ParticipantNode
     * Description: Represents a node in the linked list, containing a Participant object and a reference to the next ParticipantNode.
     */
    private class ParticipantNode {
        private Participant data;          // The Participant object stored in this node
        private ParticipantNode next;      // Reference to the next node in the list

        /**
         * Default Constructor
         * Initializes both attributes to null.
         */
        public ParticipantNode() {
            this.data = null;
            this.next = null;
        }

        /**
         * Parameterized Constructor
         * Initializes the node with a Participant object and a reference to the next node.
         *
         * @param data The Participant object to store in this node.
         * @param next The next ParticipantNode in the list.
         */
        public ParticipantNode(Participant data, ParticipantNode next) {
            this.data = data;
            this.next = next;
        }

        /**
         * Copy Constructor
         * Creates a deep copy of another ParticipantNode.
         *
         * @param other The ParticipantNode to copy.
         */
        public ParticipantNode(ParticipantNode other) {
            if (other != null) {
                Participant otherData = other.data;
                this.data = new Participant(
                    otherData.getParticipantID(),
                    otherData.getParticipantName(),
                    otherData.getDiyaCollection() // This method returns a cloned array
                );
                this.next = other.next;
            } else {
                this.data = null;
                this.next = null;
            }
        }

        /**
         * Clone Method
         * Creates and returns a deep copy of this ParticipantNode.
         *
         * @return A cloned ParticipantNode object.
         */
        public ParticipantNode clone() {
            return new ParticipantNode(this);
        }

        /**
         * Gets a deep copy of the Participant object stored in this node.
         *
         * @return A deep copy of the Participant object.
         */
        public Participant getData() {
            // Return a deep copy to prevent privacy leaks
            return new Participant(
                data.getParticipantID(),
                data.getParticipantName(),
                data.getDiyaCollection()
            );
        }

        /**
         * Gets the reference to the next ParticipantNode.
         *
         * @return The next ParticipantNode.
         */
        public ParticipantNode getNext() {
            return next;
        }


        /**
         * Sets the Participant object stored in this node.
         *
         * @param data The Participant object to set.
         */
        public void setData(Participant data) {
            this.data = data;
        }

        /**
         * Sets the reference to the next ParticipantNode.
         *
         * @param next The next ParticipantNode to set.
         */
        public void setNext(ParticipantNode next) {
            this.next = next;
        }
    }

    // Private attributes
    private ParticipantNode head;  // Points to the first node in the ParticipantList
    private int size;              // Indicates the current size of the list (number of nodes)

    /**
     * Default Constructor
     * Initializes an empty ParticipantList by setting head to null and size to 0.
     */
    public ParticipantList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Copy Constructor
     * Creates a deep copy of another ParticipantList.
     *
     * @param other The ParticipantList object to copy.
     */
    public ParticipantList(ParticipantList other) {
        if (other == null) {
            throw new NullPointerException("Cannot copy a null ParticipantList.");
        }

        if (other.head == null) {
            this.head = null;
            this.size = 0;
        } else {
            // Deep copy of the first node
            this.head = new ParticipantNode(other.head);
            ParticipantNode currentThis = this.head;
            ParticipantNode currentOther = other.head.getNext();

            // Iterate through the other list and copy each node
            while (currentOther != null) {
                ParticipantNode newNode = new ParticipantNode(currentOther);
                currentThis.setNext(newNode);
                currentThis = newNode;
                currentOther = currentOther.getNext();
            }

            this.size = other.size;
        }
    }

    /**
     * Adds a Participant to the start of the ParticipantList.
     *
     * @param p The Participant object to add.
     */
    public void addToStart(Participant p) {
        ParticipantNode newNode = new ParticipantNode(p, this.head);
        this.head = newNode;
        this.size++;
    }

    /**
     * Inserts a Participant at a specified index in the ParticipantList.
     *
     * @param p     The Participant object to insert.
     * @param index The position at which to insert the Participant.
     * @throws NoSuchElementException If the index is invalid (not between 0 and size-1).
     */
    public void insertAtIndex(Participant p, int index) {
        if (index < 0 || index >= size) { // Allow insertion at index == size (end of list)
            throw new NoSuchElementException("Invalid index. Must be between 0 and " + size + ".");
        }

        if (index == 0) {
            addToStart(p);
            return;
        }

        ParticipantNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        ParticipantNode newNode = new ParticipantNode(p, current.getNext());
        current.setNext(newNode);
        size++;
    }

    /**
     * Deletes a Participant from a specified index in the ParticipantList.
     *
     * @param index The position of the Participant to delete.
     * @throws NoSuchElementException If the index is invalid (not between 0 and size-1).
     */
    public void deleteFromIndex(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Invalid index. Must be between 0 and " + (size - 1) + ".");
        }

        if (index == 0) {
            deleteFromStart();
            return;
        }

        ParticipantNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        current.setNext(current.getNext().getNext());
        size--;
    }

    /**
     * Deletes the first Participant from the ParticipantList.
     * Handles all special cases, including an empty list.
     */
    public void deleteFromStart() {
        if (head == null) {
            // List is already empty; nothing to delete
            return;
        }

        head = head.getNext();
        size--;
    }

    /**
     * Replaces the Participant at a specified index with a new Participant.
     *
     * @param p     The new Participant object.
     * @param index The position of the Participant to replace.
     *              If the index is invalid, the method simply returns without making changes.
     */
    public void replaceAtIndex(Participant p, int index) {
        if (index < 0 || index >= size) {
            // Invalid index; do nothing
            return;
        }

        ParticipantNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        current.setData(p);
    }

    /**
     * Finds a Participant by their participantID.
     *
     * @param participantID The ID of the Participant to search for.
     * @return A deep copy of the Participant if found; otherwise, null.
     *         Also prints the number of iterations performed during the search.
     */
    public Participant find(String participantID) {
        ParticipantNode current = head;
        int iterations = 0;

        while (current != null) {
            iterations++;
            Participant currentParticipant = current.getData();

            if (currentParticipant.getParticipantID().equals(participantID)) {
                System.out.println("Number of iterations: " + iterations);
                // Use the parameterized constructor to create a deep copy
                return new Participant(
                    currentParticipant.getParticipantID(),
                    currentParticipant.getParticipantName(),
                    currentParticipant.getDiyaCollection() // Returns a cloned array
                );
            }
            current = current.getNext();
        }

        System.out.println("Number of iterations: " + iterations);
        return null; // Participant not found
    }


    /**
     * Checks if a Participant with the given participantID exists in the ParticipantList.
     *
     * @param participantID The ID of the Participant to check for.
     * @return True if a Participant with the given ID exists; otherwise, false.
     */
    public boolean contains(String participantID) {
        ParticipantNode current = head;

        while (current != null) {
            if (current.getData().getParticipantID().equals(participantID)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    /**
     * Compares this ParticipantList with another ParticipantList for equality.
     * Two ParticipantLists are considered equal if they contain similar Participants
     * in the same order, where Participants are equal based on their attributes
     * except for participantID.
     *
     * @param obj The ParticipantList to compare with.
     * @return True if both lists are equal; otherwise, false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; 
        }

        if (obj == null || !(obj instanceof ParticipantList)) {
            return false; 
        }

        ParticipantList other = (ParticipantList) obj;

        if (this.size != other.size) {
            return false; 
        }

        ParticipantNode currentThis = this.head;
        ParticipantNode currentOther = other.head;

        while (currentThis != null) {
            if (!currentThis.getData().equals(currentOther.getData())) {
                return false; // Mismatch in Participant data
            }
            currentThis = currentThis.getNext();
            currentOther = currentOther.getNext();
        }

        return true; // All Participants match
    }
    
    public ArrayList<Participant> getParticipants() {
        // Privacy Leak Note:
        // This method returns deep copies of the Participants to prevent privacy leaks.
        ArrayList<Participant> participants = new ArrayList<>();
        ParticipantNode current = head;

        while (current != null) {
            Participant currentParticipant = current.getData();
            Participant participantCopy = new Participant(
                currentParticipant.getParticipantID(),
                currentParticipant.getParticipantName(),
                currentParticipant.getDiyaCollection()
            );
            participants.add(participantCopy);
            current = current.getNext();
        }

        return participants;
    }

    /**
     * Gets the current size of the ParticipantList.
     *
     * @return The number of Participants in the list.
     */
    public int getSize() {
        return size;
    }
}
