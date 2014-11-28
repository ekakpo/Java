/*
 * Author: Essenam Kakpo
 * 
 */
package graphicalphonedirectory;

import java.io.Serializable;

/**
 *
 * @author Kheops
 */
/*
Author: Essenam Kakpo
Date: April 28, 2013
*/

// Represents a record containing a name and a phone number
class PhoneRecord implements Serializable {
  private String name;
  private String number;

  // Constructor
  public PhoneRecord(String personName, String phoneNumber) {
    name = personName;                       
    number = phoneNumber;
  }

  // Returns the name stored in the record
  public String getName() {
    return name;
  }
 
  // Returns the phone number stored in the record
  public String getNumber() {
    return number;
  }
  
  public void setName(String name)
  {
	  this.name = name;
  }
  
  public void setNumber(String number)
  {
	  this.number = number;
  }
}

