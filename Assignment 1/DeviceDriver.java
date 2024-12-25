//______________________________________
//Assignment 1
//© Your Name
//Written by: Sotirios Damas 40317602
//_____________________________________

import java.util.Scanner;

public class DeviceDriver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Smart Device Management System!");

        // Prompt a user for maximum number of SmartDevices (maxDevices) that the system can manage.
        System.out.print("Please enter the maximum number of SmartDevices that the system can manage: ");
        int maxDevices = sc.nextInt();
        sc.nextLine(); 

        // Create an empty array, DeviceDatabase, with a capacity of maxDevices
        SmartDevice[] DeviceDatabase = new SmartDevice[maxDevices];
        int deviceCount = 0; // Number of devices currently in the database

        final String PASSWORD = "device2024";

        int totalFailedAttempts = 0;

        while (true) {
            // Display the main menu
            int choice = 0;
            do {
                System.out.println("\nWhat do you want to do?");
                System.out.println("1. Add devices (password required).");
                System.out.println("2. Update device (password required).");
                System.out.println("3. Find devices by type.");
                System.out.println("4. Find affordable devices.");
                System.out.println("5. Quit");
                System.out.print("Please enter your choice > ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); 
                    if (choice < 1 || choice > 5) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    sc.nextLine(); // consume invalid input
                }
            } while (choice < 1 || choice > 5);

            switch (choice) {
                case 1:
                    // Option 1: Add devices
                    if (!authenticate(sc, PASSWORD, 3)) {
                        totalFailedAttempts += 3;
                        if (totalFailedAttempts >= 12) {
                            System.out.println("Program detected suspicious activities and will terminate immediately!");
                            sc.close();
                            System.exit(0);
                        } else {
                            break; // go back to main menu
                        }
                    } else {
                        // Password correct
                        totalFailedAttempts = 0; // reset total failed attempts
                        System.out.print("How many SmartDevices do you want to enter? ");
                        int numDevicesToAdd = sc.nextInt();
                        sc.nextLine(); 

                        int availableSpace = maxDevices - deviceCount;
                        // Check the available space
                        if (numDevicesToAdd > availableSpace) {
                            System.out.println("You can only add up to " + availableSpace + " devices.");
                            numDevicesToAdd = availableSpace;
                        }
                        //add device 
                        for (int i = 0; i < numDevicesToAdd; i++) {
                            System.out.println("Entering information for device #" + (deviceCount + 1));
                            //call method for adding new device
                            SmartDevice newDevice = readDevice(sc);
                            DeviceDatabase[deviceCount] = newDevice;
                            deviceCount++;
                        }
                    }
                    break;

                case 2:
                    // Option 2: Update device
                    if (!authenticate(sc, PASSWORD, 3)) {
                        // After 3 failed attempts, main menu is re-displayed
                        break;
                    } else {
                        // Password correct
                        if (deviceCount == 0) {
                            System.out.println("No devices in the database to update.");
                            break;
                        }

                        boolean foundDevice = false;
                        while (!foundDevice) {
                            System.out.print("Enter the device ID of the SmartDevice you wish to update: ");
                            long deviceIDToUpdate = sc.nextLong();
                            sc.nextLine(); 
                            
                            //call method to return the device index or -1 if not found
                            int deviceIndex = findDeviceByID(DeviceDatabase, deviceCount, deviceIDToUpdate);

                            if (deviceIndex == -1) {
                                System.out.print("No device found with the specified ID. Do you want to try again? (yes/no): ");
                                String response = sc.nextLine();
                                if (response.equalsIgnoreCase("no")) {
                                    foundDevice = true; // exit loop
                                }
                            } else {
                                // Device found
                                foundDevice = true;
                                SmartDevice deviceToUpdate = DeviceDatabase[deviceIndex];

                                // Display current information
                                System.out.println("SmartDevice: # " + deviceIndex);
                                System.out.println("ID: " + deviceToUpdate.getDeviceID());
                                System.out.println("Device Name: " + deviceToUpdate.getDeviceName());
                                System.out.println("Device Type: " + deviceToUpdate.getDeviceType());
                                System.out.println("OS Version: " + deviceToUpdate.getOsVersion());
                                System.out.println("Battery Life: " + deviceToUpdate.getBatteryLife());
                                System.out.println("Price: " + deviceToUpdate.getPrice());
                                System.out.println("Availability: " + deviceToUpdate.getIsInStock());

                                // Update attributes
                                boolean doneUpdating = false;
                                while (!doneUpdating) {
                                    int updateChoice = 0;
                                    do {
                                        System.out.println("\nWhat information would you like to change?");
                                        System.out.println("1. Device Name");
                                        System.out.println("2. Device Type");
                                        System.out.println("3. OS Version");
                                        System.out.println("4. Battery Life");
                                        System.out.println("5. Price");
                                        System.out.println("6. Availability");
                                        System.out.println("7. Quit");
                                        System.out.print("Enter your choice > ");
                                        if (sc.hasNextInt()) {
                                            updateChoice = sc.nextInt();
                                            sc.nextLine(); 
                                            if (updateChoice < 1 || updateChoice > 7) {
                                                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                                            }
                                        } else {
                                            System.out.println("Invalid input. Please enter a number between 1 and 7.");
                                            sc.nextLine(); // consume invalid input
                                        }
                                    } while (updateChoice < 1 || updateChoice > 7);

                                    switch (updateChoice) {
                                        case 1:
                                            System.out.print("Enter new Device Name: ");
                                            String newDeviceName = sc.nextLine();
                                            deviceToUpdate.setDeviceName(newDeviceName);
                                            break;
                                        case 2:
                                            System.out.print("Enter new Device Type: ");
                                            String newDeviceType = sc.nextLine();
                                            deviceToUpdate.setDeviceType(newDeviceType);
                                            break;
                                        case 3:
                                            System.out.print("Enter new OS Version: ");
                                            String newOsVersion = sc.nextLine();
                                            deviceToUpdate.setOsVersion(newOsVersion);
                                            break;
                                        case 4:
                                            System.out.print("Enter new Battery Life: ");
                                            float newBatteryLife = sc.nextFloat();
                                            sc.nextLine(); 
                                            deviceToUpdate.setBatteryLife(newBatteryLife);
                                            break;
                                        case 5:
                                            System.out.print("Enter new Price: ");
                                            float newPrice = sc.nextFloat();
                                            sc.nextLine(); 
                                            deviceToUpdate.setPrice(newPrice);
                                            break;
                                        case 6:
                                            System.out.print("Enter new Availability (true/false): ");
                                            boolean newIsInStock = sc.nextBoolean();
                                            sc.nextLine(); 
                                            deviceToUpdate.setIsInStock(newIsInStock);
                                            break;
                                        case 7:
                                            doneUpdating = true;
                                            break;
                                    }

                                    if (!doneUpdating) {
                                        // Display updated information
                                        System.out.println("\nUpdated SmartDevice information:");
                                        System.out.println(deviceToUpdate.toString());
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 3:
                    // Option 3: Find devices by type
                    if (deviceCount == 0) {
                        System.out.println("No devices in the database.");
                    } else {
                        System.out.print("Enter the desired device type: ");
                        String desiredType = sc.nextLine();
                        //call method to display the available devices 
                        findSmartDevicesByType(DeviceDatabase, deviceCount, desiredType);
                    }
                    break;

                case 4:
                    // Option 4: Find affordable devices
                    if (deviceCount == 0) {
                        System.out.println("No devices in the database.");
                    } else {
                        System.out.print("Enter the maximum price: ");
                        float maxPrice = sc.nextFloat();
                        sc.nextLine(); 
                        //call method to display the available devices 
                        findAffordableDevices(DeviceDatabase, deviceCount, maxPrice);
                    }
                    break;

                case 5:
                    // Option 5: Quit
                    System.out.println("Closing of the Smart Device Management System.");
                    sc.close();
                    System.exit(0);
                    break;
            }
        }
    }

    // Authenticate method
    private static boolean authenticate(Scanner sc, String password, int maxAttempts) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            System.out.print("Enter password: ");
            String enteredPassword = sc.nextLine();
            if (enteredPassword.equals(password)) {
                return true;
            } else {
                System.out.println("Incorrect password.");
                attempts++;
            }
        }
        return false;
    }

    // Read device information from user
    private static SmartDevice readDevice(Scanner sc) {
        System.out.print("Enter Device ID (long): ");
        long deviceID = sc.nextLong();
        sc.nextLine(); 

        System.out.print("Enter Device Name: ");
        String deviceName = sc.nextLine();

        System.out.print("Enter Device Type: ");
        String deviceType = sc.nextLine();

        System.out.print("Enter OS Version: ");
        String osVersion = sc.nextLine();

        System.out.print("Enter Battery Life (float): ");
        float batteryLife = sc.nextFloat();
        sc.nextLine(); 

        System.out.print("Enter Price (float): ");
        float price = sc.nextFloat();
        sc.nextLine(); 

        System.out.print("Enter Availability (true/false): ");
        boolean isInStock = sc.nextBoolean();
        sc.nextLine(); 

        return new SmartDevice(deviceID, deviceName, deviceType, osVersion, batteryLife, price, isInStock);
    }

    // Find device by deviceID
    private static int findDeviceByID(SmartDevice[] deviceDatabase, int deviceCount, long deviceID) {
        for (int i = 0; i < deviceCount; i++) {
            if (deviceDatabase[i].getDeviceID() == deviceID) {
                return i;
            }
        }
        return -1;
    }

    // Find devices by type
    private static void findSmartDevicesByType(SmartDevice[] deviceDatabase, int deviceCount, String deviceType) {
        boolean found = false;
        for (int i = 0; i < deviceCount; i++) {
            if (deviceDatabase[i].getDeviceType().equalsIgnoreCase(deviceType)) {
                System.out.println("\nDevice #" + (i + 1));
                System.out.println(deviceDatabase[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No devices found with type: " + deviceType);
        }
    }

    // Find affordable devices
    private static void findAffordableDevices(SmartDevice[] deviceDatabase, int deviceCount, float maxPrice) {
        boolean found = false;
        for (int i = 0; i < deviceCount; i++) {
            if (deviceDatabase[i].getPrice() <= maxPrice) {
                System.out.println("\nDevice #" + (i + 1));
                System.out.println(deviceDatabase[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No devices found with price less than or equal to: " + maxPrice);
        }
    }
}
