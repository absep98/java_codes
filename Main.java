import java.util.*;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int q = sc.nextInt();
    Map<Integer, Integer> versionCount = new HashMap<>();
    for (int i = 1; i <= n; i++) {
        versionCount.put(i, 1);
    }
    while(q-- > 0){
      int currentversionTillpthPC = sc.nextInt();
      int upgradeTo = sc.nextInt();
      int count = 0;
      
      // Only check versions that actually have PCs
      List<Integer> versionsToUpdate = new ArrayList<>();
      for(Map.Entry<Integer, Integer> entry : versionCount.entrySet()) {
          int version = entry.getKey();
          int pcCount = entry.getValue();
          
          if(version <= currentversionTillpthPC && version < upgradeTo && pcCount > 0) {
              count += pcCount;
              versionsToUpdate.add(version);
          }
      }
      
      // Remove PCs from old versions
      for(int version : versionsToUpdate) {
          versionCount.put(version, 0);
      }
      
      // Add all upgraded PCs to new version
      versionCount.put(upgradeTo, versionCount.getOrDefault(upgradeTo, 0) + count);
      System.out.println(count);
    }
  }
}