//______________________________________
//Assignment 1
//© Your Name
//Written by: Sotirios Damas 40317602
//_____________________________________

public class SmartDevice {
    private long deviceID;
    private String deviceName;
    private String deviceType;
    private String osVersion;
    private float batteryLife;
    private float price;
    private boolean isInStock;
    private static int numberOfSmartDevices = 0;

    // Default constructor
    public SmartDevice() {
        this(0L, "Unknown", "Unknown", "Unknown", 0.0f, 0.0f, false);
    }

    // Parameterized constructor
    public SmartDevice(long deviceID, String deviceName, String deviceType, String osVersion,
            float batteryLife, float price, boolean isInStock) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.osVersion = osVersion;
        this.batteryLife = batteryLife;
        this.price = price;
        this.isInStock = isInStock;
        numberOfSmartDevices++;
    }

    // Getters and Setters
    public long getDeviceID() {
        return deviceID;
    }

    // No setter for deviceID since it cannot be changed after creation

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public float getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(float batteryLife) {
        this.batteryLife = batteryLife;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(boolean isInStock) {
        this.isInStock = isInStock;
    }

    public static int getNumberOfSmartDevices() {
        // Return the number of devices created prior to this call
        return numberOfSmartDevices;
    }

    // Override equals() method
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof SmartDevice))
            return false;
        SmartDevice other = (SmartDevice) obj;
        return this.deviceID == other.deviceID && this.isInStock == other.isInStock;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "Device ID: " + deviceID + "\n" +
                "Device Name: " + deviceName + "\n" +
                "Device Type: " + deviceType + "\n" +
                "OS Version: " + osVersion + "\n" +
                "Battery Life: " + batteryLife + "\n" +
                "Price: " + price + "\n" +
                "Availability: " + isInStock;
    }
}
