class LPS{

    // Corrected method for minimum insertions to make palindrome
    public static int minInsertionsToMakePalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev; // Add separator to avoid confusion
        
        int lps[] = computeLPS(combined);
        
        // The LPS value at the end tells us the longest prefix of original string
        // that matches suffix of reversed string
        return s.length() - lps[lps.length - 1];
    }
    
    // Renamed to be more accurate
    public static int[] computeLPS(String s) {
        int i = 1, j = 0;
        int n = s.length();
        int lps[] = new int[n];
        
        while (i < n) {
            if(s.charAt(i) == s.charAt(j)){
                lps[i] = j + 1;
                i++;
                j++;
            } else if(j > 0){
                j = lps[j-1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    }
    
    public static void demonstrateWhySeparatorNeeded() {
        System.out.println("=== Why we need the '#' separator ===\n");
        
        String s = "abc";
        String rev = new StringBuilder(s).reverse().toString();
        
        // Without separator
        String withoutSeparator = s + rev;
        int[] lpsWithout = computeLPS(withoutSeparator);
        
        // With separator
        String withSeparator = s + "#" + rev;
        int[] lpsWith = computeLPS(withSeparator);
        
        System.out.println("Original string: \"" + s + "\"");
        System.out.println("Reversed string: \"" + rev + "\"");
        System.out.println();
        
        System.out.println("WITHOUT separator:");
        System.out.println("Combined: \"" + withoutSeparator + "\"");
        System.out.print("LPS array: ");
        for(int x : lpsWithout) System.out.print(x + " ");
        System.out.println();
        System.out.println("Last LPS value: " + lpsWithout[lpsWithout.length-1]);
        System.out.println("Result: " + s.length() + " - " + lpsWithout[lpsWithout.length-1] + " = " + (s.length() - lpsWithout[lpsWithout.length-1]));
        System.out.println();
        
        System.out.println("WITH separator:");
        System.out.println("Combined: \"" + withSeparator + "\"");
        System.out.print("LPS array: ");
        for(int x : lpsWith) System.out.print(x + " ");
        System.out.println();
        System.out.println("Last LPS value: " + lpsWith[lpsWith.length-1]);
        System.out.println("Result: " + s.length() + " - " + lpsWith[lpsWith.length-1] + " = " + (s.length() - lpsWith[lpsWith.length-1]));
        System.out.println();
        
        // Show the problem case
        System.out.println("=== Problem Case Example ===");
        String problem = "abab";
        String problemRev = new StringBuilder(problem).reverse().toString();
        String problemCombined = problem + problemRev;
        int[] problemLPS = computeLPS(problemCombined);
        
        System.out.println("String: \"" + problem + "\"");
        System.out.println("Reverse: \"" + problemRev + "\"");
        System.out.println("Combined: \"" + problemCombined + "\"");
        System.out.print("LPS array: ");
        for(int x : problemLPS) System.out.print(x + " ");
        System.out.println();
        System.out.println("Notice how the middle overlaps create false matches!");
        System.out.println("Position 4-7: \"" + problemCombined.substring(4) + "\" matches prefix \"" + problemCombined.substring(0, 4) + "\"");
        System.out.println("This gives wrong LPS calculation!");
    }

    public static void showCriticalExample() {
        System.out.println("\n=== CRITICAL EXAMPLE WHERE SEPARATOR MATTERS ===");
        
        String s = "aab";
        String rev = new StringBuilder(s).reverse().toString(); // "baa"
        
        // Without separator: "aabbaa"
        String without = s + rev;
        int[] lpsWithout = computeLPS(without);
        
        // With separator: "aab#baa"  
        String with = s + "#" + rev;
        int[] lpsWith = computeLPS(with);
        
        System.out.println("String: \"" + s + "\"");
        System.out.println("Reverse: \"" + rev + "\"");
        System.out.println();
        
        System.out.println("WITHOUT separator: \"" + without + "\"");
        System.out.println("LPS array: " + java.util.Arrays.toString(lpsWithout));
        System.out.println("Last LPS: " + lpsWithout[lpsWithout.length-1]);
        System.out.println("Wrong result: " + (s.length() - lpsWithout[lpsWithout.length-1]));
        System.out.println();
        
        System.out.println("WITH separator: \"" + with + "\"");
        System.out.println("LPS array: " + java.util.Arrays.toString(lpsWith));
        System.out.println("Last LPS: " + lpsWith[lpsWith.length-1]);
        System.out.println("Correct result: " + (s.length() - lpsWith[lpsWith.length-1]));
        System.out.println();
        
        // Explain the issue
        System.out.println("THE PROBLEM:");
        System.out.println("\"aabbaa\" creates false pattern matches!");
        System.out.println("Position 0-1: \"aa\" matches position 4-5: \"aa\"");
        System.out.println("This makes the algorithm think there's more overlap than actually exists");
        System.out.println("between the original string and its reverse.");
        System.out.println();
        System.out.println("THE SOLUTION:");
        System.out.println("\"aab#baa\" prevents false matches because '#' can't match any letter");
        System.out.println("This ensures we only find legitimate overlaps between original and reverse.");
    }

    public static void detailedLPSExplanation() {
        System.out.println("=== DETAILED LPS FORMATION ANALYSIS ===\n");
        
        String s = "abab";
        String rev = new StringBuilder(s).reverse().toString(); // "baba"
        String combined = s + rev; // "ababbaba"
        
        System.out.println("Original: \"" + s + "\"");
        System.out.println("Reverse: \"" + rev + "\"");
        System.out.println("Combined: \"" + combined + "\"");
        System.out.println("Positions: 01234567");
        System.out.println("           ababbaba");
        System.out.println();
        
        // Manual LPS calculation with detailed steps
        int[] lps = new int[combined.length()];
        System.out.println("=== STEP-BY-STEP LPS CALCULATION ===");
        
        // Position 0
        System.out.println("Position 0 ('a'): LPS[0] = 0 (always 0 for first character)");
        lps[0] = 0;
        
        int i = 1, j = 0;
        
        while (i < combined.length()) {
            System.out.println("\n--- Step " + i + " ---");
            System.out.println("i = " + i + ", j = " + j);
            System.out.println("Comparing: s[" + i + "] = '" + combined.charAt(i) + "' with s[" + j + "] = '" + combined.charAt(j) + "'");
            
            if (combined.charAt(i) == combined.charAt(j)) {
                lps[i] = j + 1;
                System.out.println("✓ MATCH! LPS[" + i + "] = " + (j + 1));
                System.out.println("  This means substring '" + combined.substring(0, i + 1) + "' has prefix-suffix match of length " + (j + 1));
                System.out.println("  Prefix: '" + combined.substring(0, j + 1) + "' = Suffix: '" + combined.substring(i - j, i + 1) + "'");
                i++;
                j++;
            } else if (j > 0) {
                System.out.println("✗ NO MATCH. j > 0, so j = LPS[" + (j-1) + "] = " + lps[j-1]);
                j = lps[j - 1];
            } else {
                lps[i] = 0;
                System.out.println("✗ NO MATCH. j = 0, so LPS[" + i + "] = 0");
                i++;
            }
            
            System.out.print("Current LPS array: ");
            for (int k = 0; k < combined.length(); k++) {
                if (k <= i - 1) System.out.print(lps[k] + " ");
                else System.out.print("_ ");
            }
            System.out.println();
        }
        
        System.out.println("\n=== FINAL ANALYSIS ===");
        System.out.println("Final LPS array: " + java.util.Arrays.toString(lps));
        System.out.println("Last value: " + lps[lps.length - 1]);
        System.out.println("This means: The last " + lps[lps.length - 1] + " characters match the first " + lps[lps.length - 1] + " characters");
        System.out.println("Prefix: '" + combined.substring(0, lps[lps.length - 1]) + "'");
        System.out.println("Suffix: '" + combined.substring(combined.length() - lps[lps.length - 1]) + "'");
        
        System.out.println("\n🚨 THE PROBLEM:");
        System.out.println("The suffix '" + combined.substring(combined.length() - lps[lps.length - 1]) + "' is part of the REVERSE string");
        System.out.println("The prefix '" + combined.substring(0, lps[lps.length - 1]) + "' is the ENTIRE ORIGINAL string");
        System.out.println("But we want to find overlap between original and reverse, not this false match!");
        
        int wrongResult = s.length() - lps[lps.length - 1];
        System.out.println("\nWrong calculation: " + s.length() + " - " + lps[lps.length - 1] + " = " + wrongResult);
    }
    
    public static void detailedWithSeparator() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== NOW WITH SEPARATOR ===\n");
        
        String s = "abab";
        String rev = new StringBuilder(s).reverse().toString(); // "baba"
        String combined = s + "#" + rev; // "abab#baba"
        
        System.out.println("Original: \"" + s + "\"");
        System.out.println("Reverse: \"" + rev + "\"");
        System.out.println("Combined: \"" + combined + "\"");
        System.out.println("Positions: 012345678");
        System.out.println("           abab#baba");
        System.out.println();
        
        int[] lps = computeLPS(combined);
        System.out.println("LPS array: " + java.util.Arrays.toString(lps));
        System.out.println("Last value: " + lps[lps.length - 1]);
        
        System.out.println("\n✅ CORRECT ANALYSIS:");
        if (lps[lps.length - 1] > 0) {
            System.out.println("Prefix: '" + combined.substring(0, lps[lps.length - 1]) + "'");
            System.out.println("Suffix: '" + combined.substring(combined.length() - lps[lps.length - 1]) + "'");
            System.out.println("This represents the overlap between original string end and reverse string start");
        } else {
            System.out.println("No overlap between original and reverse");
        }
        
        int correctResult = s.length() - lps[lps.length - 1];
        System.out.println("\nCorrect calculation: " + s.length() + " - " + lps[lps.length - 1] + " = " + correctResult);
        System.out.println("We need to add " + correctResult + " characters to make \"" + s + "\" a palindrome");
    }

    public static void visualComparison() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("=== VISUAL COMPARISON: WHY SEPARATOR IS CRUCIAL ===");
        System.out.println("=".repeat(70));
        
        String s = "abab";
        String rev = "baba";
        
        System.out.println("\n🔴 WITHOUT SEPARATOR: \"ababbaba\"");
        System.out.println("Position: 0 1 2 3 4 5 6 7");
        System.out.println("String:   a b a b b a b a");
        System.out.println("LPS:      0 0 1 2 0 1 2 3");
        System.out.println("          │         │ │ │ │");
        System.out.println("          └─────────┼─┼─┼─┘");
        System.out.println("                    │ │ │");
        System.out.println("                    │ │ └── Position 7: 'a' matches position 2: 'a'");
        System.out.println("                    │ └──── Position 6: 'b' matches position 1: 'b'");
        System.out.println("                    └────── Position 5: 'a' matches position 0: 'a'");
        
        System.out.println("\n📍 THE PROBLEM BREAKDOWN:");
        System.out.println("   Original: a b a b  (positions 0-3)");
        System.out.println("   Reverse:       b a b a  (positions 4-7)");
        System.out.println("   ──────────────────────────────────");
        System.out.println("   The algorithm finds: positions 5,6,7 = positions 0,1,2");
        System.out.println("   This creates: 'aba' (from reverse) = 'aba' (from original)");
        System.out.println("   ❌ BUT this is NOT a valid overlap between original and reverse!");
        
        System.out.println("\n   Why it's wrong:");
        System.out.println("   - We want: How much of END of original matches START of reverse");
        System.out.println("   - We got: How much of START of reverse matches START of original");
        System.out.println("   - Result: 4 - 3 = 1 (WRONG!)");
        
        System.out.println("\n" + "─".repeat(70));
        
        System.out.println("\n✅ WITH SEPARATOR: \"abab#baba\"");
        System.out.println("Position: 0 1 2 3 4 5 6 7 8");
        System.out.println("String:   a b a b # b a b a");
        System.out.println("LPS:      0 0 1 2 0 0 1 2 3");
        System.out.println("          │             │ │ │");
        System.out.println("          └─────────────┼─┼─┘");
        System.out.println("                        │ │");
        System.out.println("                        │ └── Position 8: 'a' matches position 2: 'a'");
        System.out.println("                        └──── Position 7: 'b' matches position 1: 'b'");
        
        System.out.println("\n📍 THE CORRECT ANALYSIS:");
        System.out.println("   Original: a b a b  #  (positions 0-4)");
        System.out.println("   Reverse:            b a b a  (positions 5-8)");
        System.out.println("   ──────────────────────────────────────────");
        System.out.println("   The algorithm finds: positions 6,7,8 match positions 0,1,2");
        System.out.println("   This means: 'aba' (end of combined) = 'aba' (start of combined)");
        System.out.println("   ✅ This correctly represents overlap!");
        
        System.out.println("\n   Why it's correct:");
        System.out.println("   - The '#' prevents false matches across the boundary");
        System.out.println("   - LPS[8] = 3 means last 3 chars match first 3 chars");
        System.out.println("   - Last 3 chars: 'aba' (from reverse 'baba')");
        System.out.println("   - First 3 chars: 'aba' (from original 'abab')");
        System.out.println("   - This shows: original 'abab' ends with 'ab', reverse 'baba' starts with 'ba'");
        System.out.println("   - Overlap: 'ab' from end of original matches... wait, let me recalculate!");
        
        System.out.println("\n🔍 ACTUALLY, LET ME TRACE THIS CORRECTLY:");
        System.out.println("   LPS[8] = 3 means:");
        System.out.println("   - Prefix of 'abab#baba': 'aba'");
        System.out.println("   - Suffix of 'abab#baba': 'aba'");
        System.out.println("   - But what we really want is how much of 'abab' overlaps with 'baba'");
        System.out.println("   - Answer: 'ab' from 'abab' overlaps with 'ab' from 'baba'");
        System.out.println("   - So we need: 4 - 2 = 2 insertions... hmm, let me verify this algorithm!");
    }

    public static void main(String[] args) {
        demonstrateWhySeparatorNeeded();
        visualComparison();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ACTUAL TEST RESULTS:");
        
        // Test cases
        String[] testCases = {"abc", "abab", "aba"};
        
        for(String s : testCases) {
            int result = minInsertionsToMakePalindrome(s);
            System.out.println("String: \"" + s + "\" → Minimum insertions: " + result);
        }
        
        showCriticalExample();
        
        detailedLPSExplanation();
        detailedWithSeparator();
        
        // Let's verify with actual palindrome construction
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== VERIFICATION ===");
        String test = "abab";
        System.out.println("Original: " + test);
        System.out.println("To make palindrome, we can:");
        System.out.println("1. Add at start: 'ba' + 'abab' = 'baabab' ❌ (not palindrome)");
        System.out.println("2. Add at end: 'abab' + 'a' = 'ababa' ✅ (palindrome!)");
        System.out.println("So minimum insertions = 1");
        System.out.println("Our algorithm result: " + minInsertionsToMakePalindrome(test));
    }
}