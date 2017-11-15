package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import calculator.GridState;

public class StatePersistence {
	
	public void saveState(File f, GridState grid) throws IOException {
		
		
		
		FileOutputStream filetoWrite = new FileOutputStream(f.getName());
		ObjectOutputStream output = new ObjectOutputStream(filetoWrite);
		output.writeObject(grid);
		output.close();
		
	}
	
	public GridState loadState(File f) throws ClassNotFoundException, IOException {
		
		FileInputStream filetoRead = new FileInputStream(f);
		ObjectInputStream input = new ObjectInputStream(filetoRead);
		GridState tempState = (GridState) input.readObject();
		input.close();
		 
		return tempState;
		
	}

}
