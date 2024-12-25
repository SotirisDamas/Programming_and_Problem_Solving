package Assignment2;
/**
 * Name and ID: Sotirios Damas, 40317602
 * Assignment #: 2
 * Due Date: 11/16/2024
 */
import java.util.*;

/**
 * CompetitionResults.java
 */

/**
 * Class: CompetitionResults
 * Description: Processes the results of the Diwali Diyas Collection Contest.
 * Implements the Lightable interface and performs operations as per assignment instructions.
 */
public class CompetitionResults implements Lightable {
    private Participant currentParticipant;
    private Set<String> topThreeIDs;

    /**
     * Constructor
     *
     * @param currentParticipant The Participant associated with this CompetitionResults instance.
     * @param topThreeIDs        The set containing participantIDs of the top three participants.
     */
    public CompetitionResults(Participant currentParticipant, Set<String> topThreeIDs) {
        this.currentParticipant = currentParticipant;
        this.topThreeIDs = topThreeIDs;
    }

    /**
     * isInTheTopThree Method (from Lightable interface)
     * Determines if the given Participant is in the same group as the current participant.
     *
     * @param P The Participant to check.
     * @return True if both are in the same group (both in top three or both not); otherwise, false.
     */
    @Override
    public boolean isInTheTopThree(Participant P) {
        if (P == null) {
            return false;
        }
        boolean currentInTopThree = topThreeIDs.contains(currentParticipant.getParticipantID());
        boolean pInTopThree = topThreeIDs.contains(P.getParticipantID());
        return currentInTopThree == pInTopThree;
    }

    /**
     * Main method
     * Performs the following:
     * - Creates ParticipantList objects
     * - Inputs participant information and initializes a ParticipantList
     * - Ensures no duplicate records are added
     * - Inputs request information and processes participants
     * - Prompts the user to search for participantIDs
     * - Tests constructors and methods
     */
    public static void main(String[] args) {
       
        // (a) Create at least two empty lists of ParticipantList class
        ParticipantList list1 = new ParticipantList();
        ParticipantList list2 = new ParticipantList(); // For testing copy constructor later

        Scanner sc = new Scanner(System.in);

        // (b) Input participant information to initialize one of the ParticipantList objects
        System.out.println("Enter participant information (Format: ParticipantName Diya1 Diya2 Diya3 Diya4 Diya5).");
        System.out.println("Enter 'END' to finish entering participants.");

        while (true) {
            System.out.print("Enter Participant Data: ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            String[] tokens = line.split("\\s+");
            if (tokens.length != 6) { // ParticipantName, Diya1-5
                System.out.println("Invalid input format. Please enter exactly 6 values.");
                continue;
            }
            String participantName = tokens[0];
            int[] diyaCounts = new int[5];
            try {
                for (int i = 0; i < 5; i++) {
                    diyaCounts[i] = Integer.parseInt(tokens[i + 1]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please ensure Diya counts are integers.");
                continue;
            }

            // Use participantName as participantID (assuming uniqueness)
            String participantID = participantName;

            Participant p = new Participant(participantID, participantName, diyaCounts);
            if (!list1.contains(participantID)) {
                list1.addToStart(p);
                System.out.println("Participant added successfully.");
            } else {
                System.out.println("Duplicate participant detected. Participant not added.");
            }
        }

        // Copy list1 into list2 using the copy constructor
        list2 = new ParticipantList(list1);

        // (c) Input request information and create an ArrayList
        System.out.println("\nEnter participant names for result requests (one per line).");
        System.out.println("Enter 'END' to finish entering requests.");
        ArrayList<String> requestList = new ArrayList<>();
        while (true) {
            System.out.print("Enter Request Participant Name: ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            if (!line.isEmpty()) {
                requestList.add(line);
            }
        }

        // Process each participant to determine the top three
        // For this, we need to calculate decorative values and material costs

        // Decorative values and material costs for each Diya type
        int[] decorativeValues = {8, 7, 9, 6, 7}; 
        double[] materialCosts = {3.00, 2.50, 4.00, 2.00, 3.50};

        // Calculate total decorative value and material cost for each participant
        ArrayList<ParticipantScore> participantScores = new ArrayList<>();
        ArrayList<Participant> participants = list1.getParticipants();

        for (Participant p : participants) {
            int[] counts = p.getDiyaCollection();
            int totalDecorativeValue = 0;
            double totalMaterialCost = 0.0;
            for (int i = 0; i < counts.length; i++) {
                totalDecorativeValue += counts[i] * decorativeValues[i];
                totalMaterialCost += counts[i] * materialCosts[i];
            }
            participantScores.add(new ParticipantScore(p, totalDecorativeValue, totalMaterialCost));
        }

        // Sort participants based on total decorative value (descending) and total material cost (ascending)
        Collections.sort(participantScores, new Comparator<ParticipantScore>() {
            @Override
            public int compare(ParticipantScore ps1, ParticipantScore ps2) {
                if (ps2.totalDecorativeValue != ps1.totalDecorativeValue) {
                    return Integer.compare(ps2.totalDecorativeValue, ps1.totalDecorativeValue);
                } else {
                    return Double.compare(ps1.totalMaterialCost, ps2.totalMaterialCost);
                }
            }
        });

        // Identify top three participants
        Set<String> topThreeIDs = new HashSet<>();
        for (int i = 0; i < 3 && i < participantScores.size(); i++) {
            Participant p = participantScores.get(i).participant;
            topThreeIDs.add(p.getParticipantID());
        }

        // (c) Process each request and print the outcome
        System.out.println("\nContest Results:");
        for (String participantName : requestList) {
            Participant p = null;
            for (Participant participant : participants) {
                if (participant.getParticipantName().equalsIgnoreCase(participantName)) {
                    p = participant;
                    break;
                }
            }
            if (p == null) {
                System.out.println("Participant " + participantName + " is not a participant in the competition.");
                continue;
            }

            // Determine ranking
            int rank = -1;
            for (int i = 0; i < participantScores.size(); i++) {
                if (participantScores.get(i).participant.getParticipantID().equals(p.getParticipantID())) {
                    rank = i + 1;
                    break;
                }
            }

            // Safeguard in case rank was not found 
            if (rank == -1) {
                System.out.println("Participant " + p.getParticipantName() + " not found in rankings.");
                continue;
            }

            ParticipantScore ps = participantScores.get(rank - 1);
            String output = "";

            // Check if there's any participant with the same decorative value but lower material cost
            boolean hasLowerMaterialCost = false;
            for (ParticipantScore s : participantScores) {
                if (s.totalDecorativeValue == ps.totalDecorativeValue && s.totalMaterialCost < ps.totalMaterialCost) {
                    hasLowerMaterialCost = true;
                    break;
                }
            }

            if (rank == 1) {
                if (hasLowerMaterialCost) {
                    output = p.getParticipantName() + " wins the Luminary Legend award with a collection having highest decorative value and a lower material cost.";
                } else {
                    output = p.getParticipantName() + " wins the Luminary Legend award with a collection having highest decorative value.";
                }
            } else if (rank == 2) {
                if (hasLowerMaterialCost) {
                    output = p.getParticipantName() + " wins the Artisan’s Heart award with a collection having second highest decorative value and a lower material cost.";
                } else {
                    output = p.getParticipantName() + " wins the Artisan’s Heart award with a collection having second highest decorative value.";
                }
            } else if (rank == 3) {
                if (hasLowerMaterialCost) {
                    output = p.getParticipantName() + " wins the Candlelight Dreamer award with a collection having third highest decorative value and a lower material cost.";
                } else {
                    output = p.getParticipantName() + " wins the Candlelight Dreamer award with a collection having third highest decorative value.";
                }
            } else {
                // Not in top three
                ParticipantScore thirdPs = participantScores.get(2); // Assuming at least 3 participants
                if (ps.totalDecorativeValue < thirdPs.totalDecorativeValue) {
                    output = p.getParticipantName() + " is not in top three owing to collection with lower decorative value.";
                } else if (ps.totalDecorativeValue == thirdPs.totalDecorativeValue && ps.totalMaterialCost > thirdPs.totalMaterialCost) {
                    output = p.getParticipantName() + " is not in top three owing to collection with higher material cost.";
                } else {
                    output = p.getParticipantName() + " is not in top three.";
                }
            }
            System.out.println(output);
        }

        // (d) Participant ID search
        System.out.println("\nParticipant ID Search:");
        System.out.println("Enter participantIDs to search. Enter 'END' to finish.");
        while (true) {
            System.out.print("Enter ParticipantID to Search: ");
            String participantID = sc.nextLine().trim();
            if (participantID.equalsIgnoreCase("END")) {
                break;
            }
            if (participantID.isEmpty()) {
                System.out.println("ParticipantID cannot be empty.");
                continue;
            }
            Participant foundParticipant = list1.find(participantID);
            if (foundParticipant != null) {
                System.out.println("Participant found: " + foundParticipant);
            } else {
                System.out.println("Participant not found.");
            }
        }

        // (e) Testing constructors and methods
        System.out.println("\nTesting Constructors and Methods:");
        testConstructorsAndMethods(list1, sc);

        // (f) Testing isInTheTopThree method
        // Create test participants
        Participant testParticipant1 = new Participant("TestParticipant1", "TestParticipant1", new int[]{1, 2, 3, 4, 5});
        Participant testParticipant2 = new Participant(testParticipant1, "TestParticipant2");
        testParticipant2.setParticipantName("TestParticipant2"); // Update name
        Participant testParticipant3 = testParticipant1.clone(); // Clone method (user inputs new ID and name)

        // Add test participants to list1
        list1.addToStart(testParticipant1);
        list1.addToStart(testParticipant2);
        list1.addToStart(testParticipant3);

        // Create a new ParticipantList copy
        ParticipantList list3 = new ParticipantList(list1);

        // Define topThreeIDs again based on updated list1
        ArrayList<Participant> updatedParticipants = list1.getParticipants();
        ArrayList<ParticipantScore> updatedParticipantScores = new ArrayList<>();
        for (Participant p : updatedParticipants) {
            int[] counts = p.getDiyaCollection();
            int totalDecorativeValue = 0;
            double totalMaterialCost = 0.0;
            for (int i = 0; i < counts.length; i++) {
                totalDecorativeValue += counts[i] * decorativeValues[i];
                totalMaterialCost += counts[i] * materialCosts[i];
            }
            updatedParticipantScores.add(new ParticipantScore(p, totalDecorativeValue, totalMaterialCost));
        }

        Collections.sort(updatedParticipantScores, new Comparator<ParticipantScore>() {
            @Override
            public int compare(ParticipantScore ps1, ParticipantScore ps2) {
                if (ps2.totalDecorativeValue != ps1.totalDecorativeValue) {
                    return Integer.compare(ps2.totalDecorativeValue, ps1.totalDecorativeValue);
                } else {
                    return Double.compare(ps1.totalMaterialCost, ps2.totalMaterialCost);
                }
            }
        });

        Set<String> updatedTopThreeIDs = new HashSet<>();
        for (int i = 0; i < 3 && i < updatedParticipantScores.size(); i++) {
            Participant p = updatedParticipantScores.get(i).participant;
            updatedTopThreeIDs.add(p.getParticipantID());
        }

        // Create CompetitionResults instance with testParticipant1
        CompetitionResults compResultsTest = new CompetitionResults(testParticipant1, updatedTopThreeIDs);

        // Compare testParticipant1 with testParticipant2
        boolean isSameGroup = compResultsTest.isInTheTopThree(testParticipant2);
        if (isSameGroup) {
            System.out.println(testParticipant1.getParticipantName() + " is in the same group as " + testParticipant2.getParticipantName());
        } else {
            System.out.println(testParticipant1.getParticipantName() + " is not in the same group as " + testParticipant2.getParticipantName());
        }

        // Compare testParticipant1 with testParticipant3
        boolean isSameGroup2 = compResultsTest.isInTheTopThree(testParticipant3);
        if (isSameGroup2) {
            System.out.println(testParticipant1.getParticipantName() + " is in the same group as " + testParticipant3.getParticipantName());
        } else {
            System.out.println(testParticipant1.getParticipantName() + " is not in the same group as " + testParticipant3.getParticipantName());
        }

        
        sc.close();

        // Display a closing message
        System.out.println("\nThank you for using the Diwali Diyas Collection");
    }

    /**
     * Helper method to test constructors and methods of Participant and ParticipantList.
     *
     * @param list1   The ParticipantList to operate on.
     * @param sc The Scanner object for user input (if needed).
     */
    private static void testConstructorsAndMethods(ParticipantList list1, Scanner sc) {
        // Create test participants
        Participant testParticipant1 = new Participant("TestParticipant1", "TestParticipant1", new int[]{1, 2, 3, 4, 5});
        Participant testParticipant2 = new Participant(testParticipant1, "TestParticipant2"); // Copy constructor
        Participant testParticipant3 = testParticipant1.clone(); // Clone method (assumes user inputs "TestParticipant3")

        // Test addToStart
        list1.addToStart(testParticipant1);
        System.out.println("After addToStart(testParticipant1): " + list1.getParticipants());

        // Test insertAtIndex
        try {
            list1.insertAtIndex(testParticipant2, 1);
            System.out.println("After insertAtIndex(testParticipant2, 1): " + list1.getParticipants());
        } catch (NoSuchElementException e) {
            System.out.println("Exception during insertAtIndex: " + e.getMessage());
        }

        // Test replaceAtIndex
        list1.replaceAtIndex(testParticipant3, 0);
        System.out.println("After replaceAtIndex(testParticipant3, 0): " + list1.getParticipants());

        // Test deleteFromIndex
        try {
            list1.deleteFromIndex(1);
            System.out.println("After deleteFromIndex(1): " + list1.getParticipants());
        } catch (NoSuchElementException e) {
            System.out.println("Exception during deleteFromIndex: " + e.getMessage());
        }

        // Test deleteFromStart
        list1.deleteFromStart();
        System.out.println("After deleteFromStart(): " + list1.getParticipants());

        // Additional Edge Case Tests
        // Attempt to delete from an invalid index
        try {
            list1.deleteFromIndex(10); // Should throw exception
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception during invalid deleteFromIndex: " + e.getMessage());
        }

        // Attempt to insert at an invalid index
        try {
            list1.insertAtIndex(testParticipant2, -1); // Should throw exception
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception during invalid insertAtIndex: " + e.getMessage());
        }

        // Attempt to replace at an invalid index
        list1.replaceAtIndex(testParticipant3, 5); // Should do nothing
        System.out.println("After attempting invalid replaceAtIndex(5): " + list1.getParticipants());
    }

    /**
     * Helper class to store Participant scores
     */
    private static class ParticipantScore {
        Participant participant;
        int totalDecorativeValue;
        double totalMaterialCost;

        public ParticipantScore(Participant participant, int totalDecorativeValue, double totalMaterialCost) {
            this.participant = participant;
            this.totalDecorativeValue = totalDecorativeValue;
            this.totalMaterialCost = totalMaterialCost;
        }
    }
}
