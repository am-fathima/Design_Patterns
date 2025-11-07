package designPatterns;

/**
 * Prototype Pattern Demo
 * Creates new objects by cloning existing ones (prototypes)
 * Demonstrates deep copying to avoid shared references
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Document class implementing Cloneable for prototype pattern
 * Shows deep copying to ensure cloned objects are independent
 */
class Document implements Cloneable {
    private String title;
    private String content;
    private List<String> tags;
    private int version;
    
    // Constructor
    public Document(String title, String content) {
        this.title = title;
        this.content = content;
        this.tags = new ArrayList<>();
        this.version = 1;
    }
    
    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public List<String> getTags() { return new ArrayList<>(tags); } // Return copy to maintain encapsulation
    public void addTag(String tag) { this.tags.add(tag); }
    
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
    
    /**
     * Override clone method to provide deep copy functionality
     * @return Deep clone of the Document object
     */
    @Override
    public Document clone() {
        try {
            // First, call super.clone() to get the shallow copy
            Document cloned = (Document) super.clone();
            
            // Then perform deep copy for mutable fields
            cloned.tags = new ArrayList<>(this.tags); // Create new ArrayList with same elements
            
            // Note: String objects are immutable, so we don't need to clone them
            // For mutable objects, we would need to clone them too
            
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen - we implement Cloneable
        }
    }
    
    @Override
    public String toString() {
        return "Document [Title='" + title + "', Content='" + content + 
               "', Tags=" + tags + ", Version=" + version + "]";
    }
    
    /**
     * Additional method to demonstrate complex object cloning
     * Creates a template document for specific purposes
     */
    public Document createTemplate() {
        Document template = this.clone();
        template.setTitle("[TEMPLATE] " + template.getTitle());
        template.setContent("This is a template document. Replace this content.");
        template.setVersion(1);
        return template;
    }
}

/**
 * Document Registry that stores commonly used prototypes
 * Demonstrates practical use of Prototype pattern with a registry
 */
class DocumentRegistry {
    private java.util.Map<String, Document> prototypes = new java.util.HashMap<>();
    
    public void addPrototype(String key, Document document) {
        prototypes.put(key, document);
    }
    
    public Document getPrototype(String key) {
        Document prototype = prototypes.get(key);
        return (prototype != null) ? prototype.clone() : null;
    }
}

/**
 * Demo class for Prototype Pattern
 * Shows deep copying and independence of cloned objects
 */
public class PrototypePatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Prototype Pattern Demo ===");
        
        // Create original document
        System.out.println("\n--- Creating Original Document ---");
        Document original = new Document("Project Report", "This is the original content for the project report.");
        original.addTag("urgent");
        original.addTag("confidential");
        original.addTag("project");
        original.setVersion(2);
        
        System.out.println("Original: " + original);
        System.out.println("Original Hash Code: " + System.identityHashCode(original));
        System.out.println("Original Tags Hash Code: " + System.identityHashCode(original.getTags()));
        
        // Clone the document
        System.out.println("\n--- Cloning Document ---");
        Document cloned = original.clone();
        System.out.println("Cloned: " + cloned);
        System.out.println("Cloned Hash Code: " + System.identityHashCode(cloned));
        System.out.println("Cloned Tags Hash Code: " + System.identityHashCode(cloned.getTags()));
        
        // Modify the cloned document
        System.out.println("\n--- Modifying Cloned Document ---");
        cloned.setTitle("Project Report - Copy");
        cloned.setContent("This is modified content in the cloned document.");
        cloned.addTag("copy");
        cloned.setVersion(3);
        
        System.out.println("After modifications:");
        System.out.println("Original: " + original);
        System.out.println("Cloned: " + cloned);
        
        // Demonstrate deep copy with tags
        System.out.println("\n--- Demonstrating Deep Copy with Tags ---");
        System.out.println("Original tags: " + original.getTags());
        System.out.println("Cloned tags: " + cloned.getTags());
        System.out.println("Are tags the same object? " + 
            (System.identityHashCode(original.getTags()) == System.identityHashCode(cloned.getTags())));
        
        // Test template creation
        System.out.println("\n--- Creating Template from Original ---");
        Document template = original.createTemplate();
        System.out.println("Template: " + template);
        
        // Demonstrate with Document Registry
        System.out.println("\n--- Using Document Registry ---");
        DocumentRegistry registry = new DocumentRegistry();
        
        // Add prototypes to registry
        Document meetingMinutes = new Document("Meeting Minutes", "Attendees: ...");
        meetingMinutes.addTag("meeting");
        meetingMinutes.addTag("minutes");
        
        Document projectProposal = new Document("Project Proposal", "Executive Summary: ...");
        projectProposal.addTag("proposal");
        projectProposal.addTag("project");
        
        registry.addPrototype("meeting", meetingMinutes);
        registry.addPrototype("proposal", projectProposal);
        
        // Create new documents from prototypes
        Document newMeeting = registry.getPrototype("meeting");
        newMeeting.setTitle("Weekly Team Meeting - " + java.time.LocalDate.now());
        
        Document newProposal = registry.getPrototype("proposal");
        newProposal.setTitle("Q4 Marketing Proposal");
        
        System.out.println("New Meeting: " + newMeeting);
        System.out.println("New Proposal: " + newProposal);
        
        // Show that registry prototypes remain unchanged
        System.out.println("\n--- Registry Prototypes (Unchanged) ---");
        System.out.println("Meeting Prototype: " + meetingMinutes);
        System.out.println("Proposal Prototype: " + projectProposal);
        
        // Demonstrate the pattern benefits
        System.out.println("\n=== Prototype Pattern Benefits ===");
        System.out.println("1. Avoids expensive object creation from scratch");
        System.out.println("2. Independent copies - changes don't affect original");
        System.out.println("3. Deep copying prevents shared mutable state issues");
        System.out.println("4. Useful for object initialization with default states");
        System.out.println("5. Registry pattern combines well with prototype");
    }
}
