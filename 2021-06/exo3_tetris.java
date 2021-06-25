/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;

public class IsoContest {
public static void main( String[] argv ) throws Exception {
        String[] lines = new String[21];
        lines[20] = "##########";
        
		String  line = "";
		Scanner sc = new Scanner(System.in);
		
		int l = 0;
		int lastPos = -1;
		int lastLine = -1;
		int nbValidLines = 0;
		
		while(sc.hasNextLine()) {
			lines[l++] = sc.nextLine();
		}
		
		for (l = 0 ; l < lines.length ; l++) {
		    line = lines[l];
			int nbBLocks = (int) line.chars().filter(ch -> ch == '#').count();
			if (nbBLocks == 9) {
			    int newPos = line.indexOf(".");
			    if (lastPos >= -1 && lastPos == newPos) {
			        nbValidLines++;
			        if (nbValidLines == 4 && lines[l+1].charAt(lastPos) == '#') {
                        boolean stillOk = true;
                        for (int i = l - 4 ; i >= 0 && stillOk ; i--) {
                            stillOk = lines[i].charAt(lastPos) == '.';
                        }
                        if (stillOk) {
                            System.out.println("BOOM " + (lastPos + 1));
                            return;
                        }
                    }
			    } else {
			        nbValidLines = 1;
			    }
			    lastPos = newPos;
			} else {
			    nbValidLines = 0;
			}
		}
        
        System.out.println("NOPE");
		
	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
	}
}