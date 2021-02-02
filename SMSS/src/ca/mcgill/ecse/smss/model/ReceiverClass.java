/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 13 "../../../../../SMSS_model.ump"
public class ReceiverClass
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, ReceiverClass> receiverclasssByName = new HashMap<String, ReceiverClass>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ReceiverClass Attributes
  private String name;

  //ReceiverClass Associations
  private List<ReceiverObject> objects;
  private SMSS sMSS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReceiverClass(String aName, SMSS aSMSS)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    objects = new ArrayList<ReceiverObject>();
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create receiverClass due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      receiverclasssByName.remove(anOldName);
    }
    receiverclasssByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static ReceiverClass getWithName(String aName)
  {
    return receiverclasssByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }
  /* Code from template association_GetMany */
  public ReceiverObject getObject(int index)
  {
    ReceiverObject aObject = objects.get(index);
    return aObject;
  }

  public List<ReceiverObject> getObjects()
  {
    List<ReceiverObject> newObjects = Collections.unmodifiableList(objects);
    return newObjects;
  }

  public int numberOfObjects()
  {
    int number = objects.size();
    return number;
  }

  public boolean hasObjects()
  {
    boolean has = objects.size() > 0;
    return has;
  }

  public int indexOfObject(ReceiverObject aObject)
  {
    int index = objects.indexOf(aObject);
    return index;
  }
  /* Code from template association_GetOne */
  public SMSS getSMSS()
  {
    return sMSS;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfObjects()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ReceiverObject addObject(String aName)
  {
    return new ReceiverObject(aName, this);
  }

  public boolean addObject(ReceiverObject aObject)
  {
    boolean wasAdded = false;
    if (objects.contains(aObject)) { return false; }
    ReceiverClass existingReceiverClass = aObject.getReceiverClass();
    boolean isNewReceiverClass = existingReceiverClass != null && !this.equals(existingReceiverClass);
    if (isNewReceiverClass)
    {
      aObject.setReceiverClass(this);
    }
    else
    {
      objects.add(aObject);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeObject(ReceiverObject aObject)
  {
    boolean wasRemoved = false;
    //Unable to remove aObject, as it must always have a receiverClass
    if (!this.equals(aObject.getReceiverClass()))
    {
      objects.remove(aObject);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addObjectAt(ReceiverObject aObject, int index)
  {  
    boolean wasAdded = false;
    if(addObject(aObject))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfObjects()) { index = numberOfObjects() - 1; }
      objects.remove(aObject);
      objects.add(index, aObject);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveObjectAt(ReceiverObject aObject, int index)
  {
    boolean wasAdded = false;
    if(objects.contains(aObject))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfObjects()) { index = numberOfObjects() - 1; }
      objects.remove(aObject);
      objects.add(index, aObject);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addObjectAt(aObject, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSMSS(SMSS aSMSS)
  {
    boolean wasSet = false;
    if (aSMSS == null)
    {
      return wasSet;
    }

    SMSS existingSMSS = sMSS;
    sMSS = aSMSS;
    if (existingSMSS != null && !existingSMSS.equals(aSMSS))
    {
      existingSMSS.removeReceiverClass(this);
    }
    sMSS.addReceiverClass(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    receiverclasssByName.remove(getName());
    while (objects.size() > 0)
    {
      ReceiverObject aObject = objects.get(objects.size() - 1);
      aObject.delete();
      objects.remove(aObject);
    }
    
    SMSS placeholderSMSS = sMSS;
    this.sMSS = null;
    if(placeholderSMSS != null)
    {
      placeholderSMSS.removeReceiverClass(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null");
  }
}