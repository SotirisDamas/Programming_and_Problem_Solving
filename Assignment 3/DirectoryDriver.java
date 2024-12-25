package Assignment3;
/**
 * Name and ID: Sotirios Damas, 40317602
 * Assignment #: 3
 * Due Date: 11/30/2024
 */
public class DirectoryDriver {
	
	/**
     * The main method
     */
    public static void main(String[] args) {
    	// 1. Create the root directory
        Directory root = new Directory("root", false);
        
        //2. Add Subdirectories and Files:
        root.addFile("readme.txt");
        
        Directory documents = root.addDirectory("documents");
        Directory work = documents.addDirectory("work");
        work.addFile("report.docx");
        work.addFile("presentation.pptx");
        
        Directory personal = documents.addDirectory("personal");
        personal.addFile("resume.pdf");
      
        Directory music = root.addDirectory("music");
        Directory rock = music.addDirectory("rock");
        Directory album1 = rock.addDirectory("album1");
        album1.addFile("song1.mp3");
        album1.addFile("song2.mp3");
        Directory pop = music.addDirectory("pop");
        Directory album2 = pop.addDirectory("album2");
        album2.addFile("song3.mp3");
        Directory downloads = root.addDirectory("downloads");
        Directory software = downloads.addDirectory("software");
        software.addFile("program1.exe");
        software.addFile("program2.exe");
        Directory images = downloads.addDirectory("images");
        images.addFile("image1.jpg");
        images.addFile("image2.png");
        Directory videos = root.addDirectory("videos");
        Directory comp6481 = root.addDirectory("comp6481");
        comp6481.addFile("CourseOutline.pdf");
        
        
        // 3. Print Directory Structure:
        root.printDirectoryStructure();

        // 4. Calculate Directory Size:
        System.out.println("\nTotal size of root directory: " + root.getSize());
        System.out.println("Total size of documents directory: " + documents.getSize());

        // 5. Search for Files:
        String file = "resume.pdf";
        if (root.findFile(file) != null) {
            System.out.println("\nFile " + file + " found at: " + root.findFile(file));
        } else {
            System.out.println("\nFile " + file + " not found.");
        }

        // Attempt to search for a file that does not exist
        file = "null.txt";
        if (root.findFile(file) != null) {
            System.out.println("File " + file + " found at: " + root.findFile(file));
        } else {
            System.out.println("File " + file + " not found.");
        }

    }
}

