import java.util.*;

public class OptimizedTemplate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        
        // Key change: Track count of PCs for each version
        Map<Integer, Integer> versionCount = new HashMap<>();
        
        // Initially: PC i has version i, so each version 1 to n appears once
        for(int i = 1; i <= n; i++) {
            versionCount.put(i, 1);
        }
        
        while(q-- > 0) {
            int threshold = sc.nextInt();  // Xi
            int upgradeTo = sc.nextInt();  // Yi
            
            // Step 1: Count how many PCs need upgrading
            int upgradeCount = 0;
            
            // TODO: Loop through versions from 1 to threshold
            // For each version ≤ threshold:
            //   - Add versionCount.get(version) to upgradeCount
            //   - Set versionCount.put(version, 0) to remove them
            
            // Step 2: Add all upgraded PCs to the new version
            // TODO: versionCount.put(upgradeTo, versionCount.get(upgradeTo) + upgradeCount)
            
            System.out.println(upgradeCount);
        }
    }
}

/*
HINTS FOR YOU TO COMPLETE:

1. Replace the TODO comments with actual code
2. Handle the case where upgradeTo might not exist in map yet
3. Use getOrDefault() to avoid null pointer exceptions

The algorithm:
- For each query, iterate through versions 1 to threshold (not 1 to n!)
- Count how many PCs have those versions
- Move all those PCs to the upgradeTo version
- This reduces complexity from O(n×q) to O(threshold×q)
*/