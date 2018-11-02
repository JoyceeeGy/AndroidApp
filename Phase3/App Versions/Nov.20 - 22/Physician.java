package group0930.hospitaltriage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * A User with the authority of a nurse
 */

public class Physician extends User {

	public String[] getPrescription(String healthCardNum){
		try {
			FileReader fr = new FileReader(new File(AppConstant.prescriptions_path));
            BufferedReader br = new BufferedReader(fr);
            String[] prescription = new String[2];
            
			// Find the prescription of this Patient
            String line  = br.readLine();
			while (!(line.trim().equals(healthCardNum))){
				if (line == ""){
					br.close();
					return new String[]{};
				}line = br.readLine();
			} 
			line = br.readLine();
			prescription[0] = line.trim();
			line = br.readLine();
			prescription[1] = line.trim();
			br.close();
			return prescription;
		}catch (IOException e){
			e.printStackTrace();
			String[] emptyPrescription = new String[2];
			emptyPrescription[0] = "No prescription available."; 
			return emptyPrescription;
		}
			
	}
	public void updatePrescription(String healthCardNum, String medication, String instruction){
		try {
			File fr = new File(AppConstant.prescriptions_path);
			RandomAccessFile raf = new RandomAccessFile(fr, "rw");
			
			// Search for the startpoint of this Patient's prescription
			String line = raf.readLine();
			while (!line.equals(healthCardNum)){
				line = raf.readLine();
			}long marker = raf.getFilePointer(); 
			line = raf.readLine();
			line = raf.readLine(); //escape this Patient's previous prescription
			// copy rest of the file
			ArrayList<String> restFile = new ArrayList<String>();
			line = raf.readLine();
			while (line != null) {
				restFile.add(line);
				line = raf.readLine();
			}
			
			String toWrite;
			raf.seek(marker - 1);
			raf.writeUTF("\n");
			toWrite = medication + "\n";
			raf.write(toWrite.getBytes());
			toWrite = instruction + "\n";
			raf.write(toWrite.getBytes());
			int ind = 0;
			int size = restFile.size();
			while (ind < size) {
				if (ind == size - 1) {
					toWrite = restFile.get(ind);
				}else {
					toWrite = restFile.get(ind) + "\n";
				}
				raf.write(toWrite.getBytes());
				ind ++;
			}raf.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
