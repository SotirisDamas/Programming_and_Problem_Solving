package Assignment3;
/**
 * Name and ID: Sotirios Damas, 40317602
 * Assignment #: 3
 * Due Date: 11/30/2024
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Directory class to represent directories and files. Each directory can contain files or subdirectories.
 */
public class Directory {
    private String name;
    private boolean isFile;
    private List<Directory> children;

    /**
     * Constructs a new Directory.
     *
     * @param name    the name of the directory or file
     * @param isFile  true if this Directory represents a file, false if it represents a directory
     */
    public Directory(String name, boolean isFile) {
        this.name = name;
        this.isFile = isFile;
        if (!isFile) {
            children = new ArrayList<>();
        }
    }

    /**
     * Adds a file to the current directory.
     *
     * @param fileName the name of the file to add
     */
    public void addFile(String fileName) {
        if (this.isFile) {
            System.out.println("Cannot add a file to a file.");
            return;
        }
        Directory file = new Directory(fileName, true);
        this.children.add(file);
    }

    /**
     * Adds a subdirectory to the current directory.
     *
     * @param dirName the name of the subdirectory to add
     * @return the newly created Directory object representing the subdirectory, or null if addition failed
     */
    public Directory addDirectory(String dirName) {
        if (this.isFile) {
            System.out.println("Cannot add a directory to a file.");
            return null;
        }
        Directory dir = new Directory(dirName, false);
        this.children.add(dir);
        return dir;
    }

    /**
     * Prints the directory structure starting from the current directory.
     * The structure is printed with indentation to represent the hierarchy.
     */
    public void printDirectoryStructure() {
        printDirectoryStructure("");
    }

    /**
     * Recursively prints the directory structure with indentation.
     *
     * @param indent the current indentation string
     */
    private void printDirectoryStructure(String indent) {
        System.out.println(indent + name);
        if (!isFile && children != null) {
            for (Directory child : children) {
                child.printDirectoryStructure(indent + "  ");
            }
        }
    }

    /**
     * Returns the total number of files in the directory and its subdirectories.
     *
     * @return the total number of files
     */
    public int getSize() {
        if (isFile) {
            return 1;
        }
        int size = 0;
        if (children != null) {
            for (Directory child : children) {
                size += child.getSize();
            }
        }
        return size;
    }

    /**
     * Searches for a file by name and returns its full path if found.
     *
     * @param fileName the name of the file to search for
     * @param path     the current path during the search
     * @return the full path of the file if found, or null otherwise
     */
    public String findFile(String fileName, String path) {
        if (isFile) {
            if (name.equals(fileName)) {
                return path + "/" + name;
            } else {
                return null;
            }
        } else {
            String currentPath = path + "/" + name;
            for (Directory child : children) {
                String result = child.findFile(fileName, currentPath);
                if (result != null) {
                    return result;
                }
            }
            return null;
        }
    }

    /**
     * Searches for a file by name starting from the root directory and returns its full path if found.
     *
     * @param fileName the name of the file to search for
     * @return the full path of the file if found, or null otherwise
     */
    public String findFile(String fileName) {
        return findFile(fileName, "");
    }
}
