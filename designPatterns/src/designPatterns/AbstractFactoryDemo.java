package designPatterns;

/**
 * Abstract Factory Pattern Demo
 * Creates families of related objects (UI themes) without specifying concrete classes
 */
// Abstract Product A: Button interface
interface Button {
    void paint();
    void click();
}

// Abstract Product B: Checkbox interface
interface Checkbox {
    void check();
    void render();
}

// Concrete Products for Light Theme
class LightButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering Light Theme Button: White background, Blue text, Light shadow");
    }
    
    @Override
    public void click() {
        System.out.println("Light Button clicked: Bright highlight effect");
    }
}

class LightCheckbox implements Checkbox {
    @Override
    public void check() {
        System.out.println("Light Checkbox checked: Blue checkmark on white background");
    }
    
    @Override
    public void render() {
        System.out.println("Rendering Light Checkbox: White box with gray border");
    }
}

// Concrete Products for Dark Theme
class DarkButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering Dark Theme Button: Dark background, White text, Glow effect");
    }
    
    @Override
    public void click() {
        System.out.println("Dark Button clicked: Glowing pulse effect");
    }
}

class DarkCheckbox implements Checkbox {
    @Override
    public void check() {
        System.out.println("Dark Checkbox checked: Green checkmark on dark background");
    }
    
    @Override
    public void render() {
        System.out.println("Rendering Dark Checkbox: Dark gray box with light border");
    }
}

// Concrete Products for High Contrast Theme (Extension)
class HighContrastButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering High Contrast Button: Black background, Yellow text, Thick border");
    }
    
    @Override
    public void click() {
        System.out.println("High Contrast Button clicked: Inverted colors effect");
    }
}

class HighContrastCheckbox implements Checkbox {
    @Override
    public void check() {
        System.out.println("High Contrast Checkbox checked: Yellow checkmark on black background");
    }
    
    @Override
    public void render() {
        System.out.println("Rendering High Contrast Checkbox: Black box with yellow border");
    }
}

// Abstract Factory interface
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
    String getThemeName();
}

// Concrete Factory for Light Theme
class LightThemeFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
    
    @Override
    public String getThemeName() {
        return "Light Theme";
    }
}

// Concrete Factory for Dark Theme
class DarkThemeFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
    
    @Override
    public String getThemeName() {
        return "Dark Theme";
    }
}

// Concrete Factory for High Contrast Theme (Extension)
class HighContrastFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new HighContrastButton();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new HighContrastCheckbox();
    }
    
    @Override
    public String getThemeName() {
        return "High Contrast Theme";
    }
}

// Client class that uses the abstract factory
class Application {
    private Button button;
    private Checkbox checkbox;
    private GUIFactory factory;
    
    public Application(GUIFactory factory) {
        this.factory = factory;
        this.createUI();
    }
    
    private void createUI() {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }
    
    public void renderUI() {
        System.out.println("\n=== " + factory.getThemeName() + " ===");
        System.out.println("--- Rendering UI Components ---");
        button.paint();
        checkbox.render();
    }
    
    public void simulateUserInteraction() {
        System.out.println("\n--- User Interaction Simulation ---");
        button.click();
        checkbox.check();
    }
    
    public void showThemeInfo() {
        System.out.println("Theme: " + factory.getThemeName());
        System.out.println("Components created by: " + factory.getClass().getSimpleName());
    }
}

// Factory provider - decides which factory to use based on input
class GUIFactoryProvider {
    public static GUIFactory getFactory(String themeType) {
        if (themeType == null) {
            return null;
        }
        
        switch (themeType.toLowerCase()) {
            case "light":
                return new LightThemeFactory();
            case "dark":
                return new DarkThemeFactory();
            case "highcontrast":
            case "high-contrast":
            case "high_contrast":
                return new HighContrastFactory();
            default:
                throw new IllegalArgumentException("Unknown theme type: " + themeType);
        }
    }
}

/**
 * Demo class for Abstract Factory Pattern
 * Shows how to create families of related UI components
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        System.out.println("=== Abstract Factory Pattern Demo ===");
        System.out.println("Creating UI components for different themes\n");
        
        // Demo 1: Light Theme
        System.out.println("🎨 DEMO 1: LIGHT THEME");
        GUIFactory lightFactory = GUIFactoryProvider.getFactory("light");
        Application lightApp = new Application(lightFactory);
        lightApp.renderUI();
        lightApp.simulateUserInteraction();
        
        // Demo 2: Dark Theme
        System.out.println("\n🎨 DEMO 2: DARK THEME");
        GUIFactory darkFactory = GUIFactoryProvider.getFactory("dark");
        Application darkApp = new Application(darkFactory);
        darkApp.renderUI();
        darkApp.simulateUserInteraction();
        
        // Demo 3: High Contrast Theme
        System.out.println("\n🎨 DEMO 3: HIGH CONTRAST THEME");
        GUIFactory highContrastFactory = GUIFactoryProvider.getFactory("highcontrast");
        Application highContrastApp = new Application(highContrastFactory);
        highContrastApp.renderUI();
        highContrastApp.simulateUserInteraction();
        
        // Demo 4: Dynamic theme switching simulation
        System.out.println("\n🔄 DEMO 4: DYNAMIC THEME SWITCHING");
        String[] themes = {"light", "dark", "highcontrast"};
        
        for (String theme : themes) {
            System.out.println("\n--- Switching to " + theme.toUpperCase() + " theme ---");
            GUIFactory factory = GUIFactoryProvider.getFactory(theme);
            Application app = new Application(factory);
            app.showThemeInfo();
            app.renderUI();
        }
        
        // Demo 5: Show factory relationships
        System.out.println("\n🏗️ DEMO 5: FACTORY RELATIONSHIPS");
        demonstrateFactoryRelationships();
        
        // Benefits of Abstract Factory Pattern
        System.out.println("\n=== Abstract Factory Pattern Benefits ===");
        System.out.println("✅ 1. Ensures compatibility between related objects");
        System.out.println("✅ 2. Easy to add new product families (themes)");
        System.out.println("✅ 3. Client code is independent of concrete classes");
        System.out.println("✅ 4. Promotes consistency among products");
        System.out.println("✅ 5. Easy to switch between families at runtime");
    }
    
    private static void demonstrateFactoryRelationships() {
        System.out.println("\nFactory - Product Relationships:");
        
        GUIFactory[] factories = {
            new LightThemeFactory(),
            new DarkThemeFactory(),
            new HighContrastFactory()
        };
        
        for (GUIFactory factory : factories) {
            System.out.println("\n" + factory.getThemeName() + ":");
            System.out.println("  - Button: " + factory.createButton().getClass().getSimpleName());
            System.out.println("  - Checkbox: " + factory.createCheckbox().getClass().getSimpleName());
            System.out.println("  - Factory: " + factory.getClass().getSimpleName());
        }
        
        System.out.println("\nEach factory creates a compatible family of products!");
        System.out.println("LightThemeFactory → LightButton + LightCheckbox");
        System.out.println("DarkThemeFactory → DarkButton + DarkCheckbox");
        System.out.println("HighContrastFactory → HighContrastButton + HighContrastCheckbox");
    }
}