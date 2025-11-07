package designPatterns;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class implementing Singleton Pattern
 * Ensures only one instance exists throughout the application
 */
class Logger {
    
    // Private static instance - the single instance of the class
    private static Logger instance;
    
    // DateTimeFormatter for timestamp formatting
    private final DateTimeFormatter formatter;
    
    /**
     * Private constructor to prevent instantiation from outside
     * This is key to the Singleton pattern
     */
    private Logger() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Logger instance created!");
    }
    
    /**
     * Public static method to provide global access to the single instance
     * Uses lazy initialization - instance is created only when first needed
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    /**
     * Logs a message with timestamp
     * @param message The message to be logged
     */
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + timestamp + "] " + message);
    }
}

/**
 * Demonstration class for Singleton Pattern
 * Shows that multiple calls to getInstance() return the same Logger instance
 */
public class SingletonDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Demo ===");
        
        // Get first instance of Logger
        Logger logger1 = Logger.getInstance();
        logger1.log("First log message from logger1");
        
        // Get second instance - should be the same as logger1
        Logger logger2 = Logger.getInstance();
        logger2.log("Second log message from logger2");
        
        // Get third instance - should still be the same
        Logger logger3 = Logger.getInstance();
        logger3.log("Third log message from logger3");
        
        // Demonstrate that all references point to the same instance
        System.out.println("\n=== Instance Verification ===");
        System.out.println("logger1 == logger2: " + (logger1 == logger2));
        System.out.println("logger2 == logger3: " + (logger2 == logger3));
        System.out.println("logger1 == logger3: " + (logger1 == logger3));
        
        // Show hash codes to prove they're the same object
        System.out.println("\n=== Hash Code Verification ===");
        System.out.println("logger1 hashCode: " + System.identityHashCode(logger1));
        System.out.println("logger2 hashCode: " + System.identityHashCode(logger2));
        System.out.println("logger3 hashCode: " + System.identityHashCode(logger3));
        
        // Additional logging to show functionality
        System.out.println("\n=== Additional Logging ===");
        logger1.log("Application started");
        logger2.log("Processing data...");
        logger3.log("Application shutdown");
    }
}