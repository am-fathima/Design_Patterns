package designPatterns;

/**
 * Factory Method Pattern Demo
 * Creates shapes without exposing instantiation logic to the client
 */
// Shape interface
interface Shape {
    void draw();
}

// Concrete Shape implementations
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle ○");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle ▭");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Square □");
    }
}

// Factory class that creates Shape objects
class ShapeFactory {
    /**
     * Factory method that creates Shape objects based on input
     * @param shapeType Type of shape to create
     * @return Shape object
     */
    public Shape createShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        
        // Using equalsIgnoreCase for case-insensitive comparison
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        
        return null; // Return null if shape type is not recognized
    }
}

// Demo class to test the Factory Method Pattern
public class FactoryDemo {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===");
        
        // Create the factory
        ShapeFactory shapeFactory = new ShapeFactory();
        
        // Create different shapes using the factory
        System.out.println("\n--- Creating Shapes using Factory ---");
        
        Shape shape1 = shapeFactory.createShape("CIRCLE");
        Shape shape2 = shapeFactory.createShape("RECTANGLE");
        Shape shape3 = shapeFactory.createShape("SQUARE");
        Shape shape4 = shapeFactory.createShape("circle"); // Testing case-insensitive
        
        // Draw all shapes
        System.out.println("\n--- Drawing Shapes ---");
        if (shape1 != null) shape1.draw();
        if (shape2 != null) shape2.draw();
        if (shape3 != null) shape3.draw();
        if (shape4 != null) shape4.draw();
        
        // Demonstrate invalid shape type
        System.out.println("\n--- Testing Invalid Shape Type ---");
        Shape invalidShape = shapeFactory.createShape("TRIANGLE");
        if (invalidShape == null) {
            System.out.println("Invalid shape type: TRIANGLE - Factory returned null");
        }
        
        // Show that client doesn't use 'new' directly for shapes
        System.out.println("\n--- Demonstrating Factory Benefits ---");
        System.out.println("Client code uses: shapeFactory.createShape(\"CIRCLE\")");
        System.out.println("Instead of: new Circle()");
        System.out.println("This encapsulates object creation logic!");
        
        // Additional demonstration with array
        System.out.println("\n--- Creating Multiple Shapes Dynamically ---");
        String[] requestedShapes = {"circle", "rectangle", "square", "rectangle", "circle"};
        
        for (int i = 0; i < requestedShapes.length; i++) {
            Shape shape = shapeFactory.createShape(requestedShapes[i]);
            System.out.print("Shape " + (i + 1) + ": ");
            if (shape != null) {
                shape.draw();
            } else {
                System.out.println("Could not create shape for: " + requestedShapes[i]);
            }
        }
    }
}
