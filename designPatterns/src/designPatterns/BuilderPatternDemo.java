package designPatterns;

/**
 * Builder Pattern Demo
 * Creates complex Computer objects step by step using method chaining
 */
class Computer {
    // Required parameters
    private final String cpu;
    private final int ram;
    
    // Optional parameters
    private final int storage;
    private final String graphicsCard;
    
    // Private constructor - only Builder can create Computer instances
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
    }
    
    // Getters
    public String getCpu() { return cpu; }
    public int getRam() { return ram; }
    public int getStorage() { return storage; }
    public String getGraphicsCard() { return graphicsCard; }
    
    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + "GB, Storage=" + storage + 
               "GB, Graphics Card=" + graphicsCard + "]";
    }
    
    /**
     * Static nested Builder class
     * Implements the Builder pattern with method chaining
     */
    public static class Builder {
        // Required parameters
        private final String cpu;
        private final int ram;
        
        // Optional parameters - initialized to default values
        private int storage = 256;
        private String graphicsCard = "Integrated";
        
        /**
         * Builder constructor with required parameters
         * @param cpu CPU type (required)
         * @param ram RAM in GB (required)
         */
        public Builder(String cpu, int ram) {
            this.cpu = cpu;
            this.ram = ram;
        }
        
        /**
         * Set storage capacity
         * @param storage Storage in GB
         * @return Builder instance for method chaining
         */
        public Builder setStorage(int storage) {
            this.storage = storage;
            return this;
        }
        
        /**
         * Set graphics card
         * @param graphicsCard Graphics card model
         * @return Builder instance for method chaining
         */
        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }
        
        /**
         * Build method that creates the final Computer object
         * @return Computer instance with configured properties
         */
        public Computer build() {
            return new Computer(this);
        }
    }
}

/**
 * Demo class for Builder Pattern
 * Shows how to create different computer configurations using method chaining
 */
public class BuilderPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo ===");
        
        // Build a Gaming PC with method chaining
        System.out.println("\n--- Building Gaming PC ---");
        Computer gamingPC = new Computer.Builder("Intel i9", 32)
            .setStorage(2000)
            .setGraphicsCard("NVIDIA RTX 4080")
            .build();
        System.out.println(gamingPC);
        
        // Build an Office PC with different configuration
        System.out.println("\n--- Building Office PC ---");
        Computer officePC = new Computer.Builder("Intel i5", 16)
            .setStorage(512)
            .setGraphicsCard("Integrated Intel Graphics")
            .build();
        System.out.println(officePC);
        
        // Build a Budget PC using only required parameters (defaults for optional)
        System.out.println("\n--- Building Budget PC (Using Defaults) ---");
        Computer budgetPC = new Computer.Builder("AMD Ryzen 3", 8).build();
        System.out.println(budgetPC);
        
        // Build a Developer Workstation
        System.out.println("\n--- Building Developer Workstation ---");
        Computer devWorkstation = new Computer.Builder("AMD Ryzen 9", 64)
            .setStorage(4000)
            .setGraphicsCard("NVIDIA RTX 4090")
            .build();
        System.out.println(devWorkstation);
        
        // Demonstrate method chaining flexibility
        System.out.println("\n--- Demonstrating Method Chaining ---");
        Computer customPC = new Computer.Builder("Intel i7", 24)
            .setStorage(1000)  // Chain methods
            .setGraphicsCard("AMD Radeon RX 7800 XT")  // More chaining
            .build();  // Final build call
        System.out.println(customPC);
        
        // Show the benefits of Builder pattern
        System.out.println("\n=== Builder Pattern Benefits ===");
        System.out.println("1. Method chaining for readable code");
        System.out.println("2. No complex constructor with many parameters");
        System.out.println("3. Immutable objects after construction");
        System.out.println("4. Flexible - can set only needed parameters");
        System.out.println("5. Clear separation between required and optional parameters");
        
        // Compare with traditional approach
        System.out.println("\n=== Without Builder Pattern (Traditional) ===");
        System.out.println("Would require:");
        System.out.println("new Computer(\"i9\", 32, 1000, \"RTX 4080\")");
        System.out.println("OR multiple constructors for different combinations");
        System.out.println("OR setters that break immutability");
    }
}