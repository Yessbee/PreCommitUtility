public class TestExactLogic {
    public static void main(String[] args) {
        String branch = "PCU-001-further-improvement-from-develop";
        
        System.out.println("Testing exact logic from GitUtils.extractJiraId method:");
        System.out.println("========================================================");
        System.out.println("Branch name: " + branch);
        
        String result = extractJiraId(branch);
        System.out.println("Extracted Jira ID: " + result);
        System.out.println("Result is null: " + (result == null));
    }
    
    // Exact copy of the method from GitUtils
    public static String extractJiraId(String branch) {
        if (branch == null) return null;
        String[] parts = branch.split("-");
        if (parts.length >= 2 && parts[0].matches("[A-Z]+") && parts[1].matches("\\d+")) {
            return parts[0] + "-" + parts[1];
        }
        return null;
    }
}