/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 https://www.isograd-testingservices.com/FR/solutions-challenges-de-code
 * ***/
package com.isograd.exercise;
import java.util.*;

public class IsoContest {
public static void main( String[] argv ) throws Exception {
        int num = 0;
        int readIndex = 0;
		String  line = null;
		Scanner sc = new Scanner(System.in);
		
		// Read inputs
		while(sc.hasNextLine()) {
		    if (num == 0) {
		        num = Integer.parseInt(sc.nextLine());
		        readIndex = num / 2;
		    } else {
			    line = sc.nextLine();
		    }
		}
		// Just an exception check
		if (line == null || num != line.length()) {
		    throw new IllegalArgumentException("Wrong line length according to number");
		}
		
		// Characters counts map, full and half
		Map<Character, Integer> totalCount = new HashMap<>();
		Map<Character, Integer> halfCount = new HashMap<>();

        // Count total letters in the full line and count the first half to initialize the half count map
		char letter;
		int count;
		for (int i = 0 ; i < line.length() ; i++) {
		    letter = line.charAt(i);
		    count = totalCount.computeIfAbsent(letter, l -> 0) + 1;
		    totalCount.put(letter, count);
            if (i < readIndex) {
                halfCount.put(letter, count);
            }
		}
		
		int validCombinaison = 0, readStart = 0, newCount;
		boolean isValid;
		char oldLetter, newLetter;
		
		// Then, read the second half of the line, letter per letter
		while (readIndex < line.length()) {
            isValid = true;
            // Check the current state of the half count map, if it contains exactly half the total map, it's a valid combination
            for (final Map.Entry<Character, Integer> entry : halfCount.entrySet()) {
                if (!entry.getValue().equals(totalCount.get(entry.getKey()) / 2)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                validCombinaison++;
            }
            
            // Select the old letter to remove from the half count map and next letter to count
            oldLetter = line.charAt(readStart++);
            newLetter = line.charAt(readIndex++); 
            
            // Update counts in the half map, by removing the first old letter and adding the new next letter
            halfCount.put(oldLetter, halfCount.get(oldLetter) - 1);
            newCount = halfCount.computeIfAbsent(newLetter, l -> 0);
            halfCount.put(newLetter,newCount + 1);
		}
		
	    System.out.println(validCombinaison*2);
	}
}