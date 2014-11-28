 /*
 * @author Essenam Kakpo
 * April 28, 2013
 */


package graphicalphonedirectory;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;



/**
 *
 * @author Kheops
 */
/*
Author: Essenam Kakpo
Date: March 8, 2013
*/ 
/** the PhoneDirectory class to manage contacts information, like add, delete
 * update, and search
*/


public class PhoneDirectory {
  private static final String DATA_FILE = "records.dat";
  private PhoneRecord[] records;
  
  private int numRecords;
  
  
  
  
  
  
  
  
  
  
  
  

 // constructor to initialize instance variables
  public PhoneDirectory(int size) 
  {
      this.records = new PhoneRecord[size];
      this.numRecords = 0;
      readRecords();
      
  }

    /**
     * @return the records
     */
    public PhoneRecord[] getRecords() {
        return records;
    }

    /**
     * @return the numRecords
     */
    public int getNumRecords() {
        return numRecords;
    }
    
    
    public String Publish()
    {
        
      
        if (records[0] != null)
            
        {
            String st = "Stored Contacts: \n";
            for (PhoneRecord r : records)
    	{
            st += r.getName() + " " + r.getNumber() +"\n"; 
    	}
      return st;
      
    }
        else return "No Contact stored\n";
    }
    
    public String searchRecords(String inputName)
    {
        
        //find all records with record name contains this inputName string, and print all of them out on the screen
        //use the contains method in String, learn by yourself how to use it 
    	for (PhoneRecord r : this.records)
    	{
    		if (r != null)
    		{
    			if (r.getName().contains(inputName) || r.getName().contains(inputName.toLowerCase()) || r.getName().contains(inputName.toUpperCase()))
    			{
    				return(r.getName() + " " + r.getNumber());
    			}
    		}
    	}
        
        return "Contact not found. Verify case and spelling! \n";
    }
    
    public void deleteRecord(String contactName)
    {
        // find a record with name exactly the same with contactName, then delete this record.
        // remember available spaces can only be at the end of the arrays, 
        //when deleting an element not at the end of the array, you have to move this availble space to the end of the array.
    	
    	
        boolean somethingToPrint = true;
    	loop : for(int index = 0; index <= this.records.length-1;index++) // minus 2 because we don't care if the open space is at the end of the array
    	{
    		if (this.records[index].getName().equals(contactName) || this.records[index].getName().toLowerCase().equals(contactName) || this.records[index].getName().toUpperCase().equals(contactName))
    		{
    			somethingToPrint = false;
    			for(int i = index;i < this.records.length-1;i++)
    			{
    				//should we replace the continue statement with a break????
    				if (this.records[i+1] == null) continue;
    				else this.records[i] = this.records[i+1];
    			}
    			
    			for(int i = this.records.length-1; ;i--)
    			{
    				if (this.records[i] == null) continue;
    				else this.records[i] = null; break;
    			}
    			
    			this.numRecords--;
    			break loop;
    		}
    	}
    	
    	if(somethingToPrint)
    	{
    		System.out.println("No match in phone record, try again.");
    	}
        
    }
    
    public String updateRecord(String contactName, String phoneNumber)
    {
        boolean found = false;
        for(int index = 0; index <= this.records.length-1;index++) // minus 2 because we don't care if the open space is at the end of the array
    	{
    		if (this.records[index].getName().equals(contactName))
    		{
    			this.records[index].setNumber(phoneNumber);
                        found = true;
    		}
    	}
        if (!found) return "Contact not found. Verify case and spelling! \n";
        else return "1 Contact successfully updated! \n";
        
    }
    
    public void addRecord(PhoneRecord record)
    {
        
        // add this record to the array
    	
    	if (this.numRecords < this.records.length) //add record to last cell if directory not full
    	{
    		records[records.length-1] = record;
    		numRecords++;
    	}
    	
    	else //Create array one cell larger, make copy from original and add new record
    	{
    		PhoneRecord[] records = new PhoneRecord[this.records.length+1];
    		System.arraycopy(this.records, 0, records, 0, this.records.length);
    		this.records = records;
    		this.records[this.records.length-1] = record;
    		this.numRecords++;
    	}
        
    }
    
    private void readRecords() {
    try {
      FileInputStream fileIn = new FileInputStream(DATA_FILE);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      records = (PhoneRecord[]) in.readObject();
      in.close();
    } catch (Exception e) {
      JOptionPane.showMessageDialog( null, " Data File does not exist or cannot be read\n" + 
              "A new one will be created.\n", "Warning",JOptionPane.PLAIN_MESSAGE );
      records = new PhoneRecord[1];
    }
    }
    
    public void saveRecords() {
    try {
      FileOutputStream fileOut = new FileOutputStream(DATA_FILE);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(records);
      out.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog( null, " Error writing to Data File.\n", "Error",JOptionPane.ERROR_MESSAGE );
    }
  }
    
    
    
    
  }
    
    
    
    
    
