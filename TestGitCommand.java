import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestGitCommand {
    public static void main(String[] args) {
        System.out.println("Testing the Git command used by the tool:");
        System.out.println("===========================================");
        
        String branch = runSingleLine("git rev-parse --abbrev-ref HEAD");
        System.out.println("Git command result: \"" + branch + "\"");
        System.out.println("Branch is null: " + (branch == null));
        if (branch != null) {
            System.out.println("Branch length: " + branch.length());
            System.out.println("Branch chars: ");
            for (int i = 0; i < branch.length(); i++) {
                char c = branch.charAt(i);
                System.out.println("  [" + i + "] '" + c + "' (code: " + (int)c + ")");
            }
        }
    }
    
    private static String runSingleLine(String cmd) {
        try {
            System.out.println("Executing command: " + cmd);
            Process p = new ProcessBuilder(shell(), shellFlag(), cmd).redirectErrorStream(true).start();
            try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                return r.readLine();
            }
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    private static String shell() {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("OS detected: " + os);
        return os.contains("win") ? "cmd.exe" : "bash";
    }
    
    private static String shellFlag() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win") ? "/c" : "-c";
    }
}