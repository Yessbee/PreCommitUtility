import java.util.regex.Pattern;

public class DebugJiraIdExtraction {
    
    public static void main(String[] args) {
        // Test with the user's actual branch name
        String branchName = "PCU-001-further-improvement-from-develop";
        
        System.out.println("Debugging Jira ID extraction for branch: " + branchName);
        System.out.println("==================================================================");
        
        // Step 1: Check if branch is null
        if (branchName == null) {
            System.out.println("Branch name is null");
            return;
        }
        
        // Step 2: Split by hyphens
        String[] parts = branchName.split("-");
        System.out.println("Split parts (" + parts.length + " total):");
        for (int i = 0; i < parts.length; i++) {
            System.out.println("  [" + i + "] \"" + parts[i] + "\"");
        }
        
        // Step 3: Check conditions
        System.out.println("\nChecking conditions:");
        boolean lengthCheck = parts.length >= 2;
        System.out.println("  parts.length >= 2: " + lengthCheck + " (" + parts.length + " >= 2)");
        
        if (lengthCheck) {
            boolean firstPartCheck = parts[0].matches("[A-Z]+");
            System.out.println("  parts[0].matches(\"[A-Z]+\") (\"" + parts[0] + "\"): " + firstPartCheck);
            
            boolean secondPartCheck = parts[1].matches("\\d+");
            System.out.println("  parts[1].matches(\"\\\\d+\") (\"" + parts[1] + "\"): " + secondPartCheck);
            
            if (firstPartCheck && secondPartCheck) {
                String extracted = parts[0] + "-" + parts[1];
                System.out.println("  Would extract Jira ID: " + extracted);
            } else {
                System.out.println("  Conditions not met - would return null");
            }
        } else {
            System.out.println("  Not enough parts - would return null");
        }
        
        // Additional test: What if we only look for the first hyphen?
        System.out.println("\nAlternative approach - only consider first hyphen:");
        int firstHyphenIndex = branchName.indexOf('-');
        if (firstHyphenIndex > 0 && firstHyphenIndex < branchName.length() - 1) {
            String beforeHyphen = branchName.substring(0, firstHyphenIndex);
            String afterHyphen = branchName.substring(firstHyphenIndex + 1);
            
            System.out.println("  Before first hyphen: \"" + beforeHyphen + "\"");
            System.out.println("  After first hyphen: \"" + afterHyphen + "\"");
            
            boolean beforeCheck = beforeHyphen.matches("[A-Z]+");
            System.out.println("  Before matches \"[A-Z]+\": " + beforeCheck);
            
            // Extract digits at the beginning of the part after hyphen
            String digitsAtStart = extractLeadingDigits(afterHyphen);
            boolean afterCheck = digitsAtStart != null && !digitsAtStart.isEmpty();
            System.out.println("  Leading digits in after part: \"" + digitsAtStart + "\"");
            System.out.println("  After has leading digits: " + afterCheck);
            
            if (beforeCheck && afterCheck) {
                String extracted = beforeHyphen + "-" + digitsAtStart;
                System.out.println("  Would extract Jira ID: " + extracted);
            }
        }
    }
    
    private static String extractLeadingDigits(String str) {
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                digits.append(c);
            } else {
                break;
            }
        }
        return digits.length() > 0 ? digits.toString() : null;
    }
}