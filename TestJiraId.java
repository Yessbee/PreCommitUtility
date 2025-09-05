public class TestJiraId {
    public static void main(String[] args) {
        String branch = "PCU-001-further-improvement-from-develop";
        System.out.println("Branch: " + branch);
        
        String[] parts = branch.split("-");
        System.out.println("Parts length: " + parts.length);
        
        for (int i = 0; i < parts.length; i++) {
            System.out.println("Part " + i + ": '" + parts[i] + "'");
        }
        
        System.out.println("Part 0 matches [A-Z]+: " + parts[0].matches("[A-Z]+"));
        System.out.println("Part 1 matches \\d+: " + parts[1].matches("\\d+"));
        
        if (parts.length >= 2 && parts[0].matches("[A-Z]+") && parts[1].matches("\\d+")) {
            System.out.println("Would extract: " + parts[0] + "-" + parts[1]);
        } else {
            System.out.println("Would NOT extract Jira ID");
        }
    }
}