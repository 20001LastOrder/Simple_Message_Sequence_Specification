/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 10 "../../../../../SMSS_model.ump"
public class Type
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Type> typesByName = new HashMap<String, Type>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Type Attributes
  private String name;

  //Type Associations
  private List<Instance> objects;
  private List<Operation> methods;
  private SMSS sMSS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Type(String aName, SMSS aSMSS)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    objects = new ArrayList<Instance>();
    methods = new ArrayList<Operation>();
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create type due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
      typesByName.remove(anOldName);
    }
    typesByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Type getWithName(String aName)
  {
    return typesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }
  /* Code from template association_GetMany */
  public Instance getObject(int index)
  {
    Instance aObject = objects.get(index);
    return aObject;
  }

  public List<Instance> getObjects()
  {
    List<Instance> newObjects = Collections.unmodifiableList(objects);
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

  public int indexOfObject(Instance aObject)
  {
    int index = objects.indexOf(aObject);
    return index;
  }
  /* Code from template association_GetMany */
  public Operation getMethod(int index)
  {
    Operation aMethod = methods.get(index);
    return aMethod;
  }

  public List<Operation> getMethods()
  {
    List<Operation> newMethods = Collections.unmodifiableList(methods);
    return newMethods;
  }

  public int numberOfMethods()
  {
    int number = methods.size();
    return number;
  }

  public boolean hasMethods()
  {
    boolean has = methods.size() > 0;
    return has;
  }

  public int indexOfMethod(Operation aMethod)
  {
    int index = methods.indexOf(aMethod);
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
  public Instance addObject(int aId, String aName)
  {
    return new Instance(aId, aName, this);
  }

  public boolean addObject(Instance aObject)
  {
    boolean wasAdded = false;
    if (objects.contains(aObject)) { return false; }
    Type existingType = aObject.getType();
    boolean isNewType = existingType != null && !this.equals(existingType);
    if (isNewType)
    {
      aObject.setType(this);
    }
    else
    {
      objects.add(aObject);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeObject(Instance aObject)
  {
    boolean wasRemoved = false;
    //Unable to remove aObject, as it must always have a type
    if (!this.equals(aObject.getType()))
    {
      objects.remove(aObject);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addObjectAt(Instance aObject, int index)
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

  public boolean addOrMoveObjectAt(Instance aObject, int index)
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMethods()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Operation addMethod(String aName, SMSS aSMSS)
  {
    return new Operation(aName, aSMSS, this);
  }

  public boolean addMethod(Operation aMethod)
  {
    boolean wasAdded = false;
    if (methods.contains(aMethod)) { return false; }
    Type existingType = aMethod.getType();
    boolean isNewType = existingType != null && !this.equals(existingType);
    if (isNewType)
    {
      aMethod.setType(this);
    }
    else
    {
      methods.add(aMethod);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMethod(Operation aMethod)
  {
    boolean wasRemoved = false;
    //Unable to remove aMethod, as it must always have a type
    if (!this.equals(aMethod.getType()))
    {
      methods.remove(aMethod);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMethodAt(Operation aMethod, int index)
  {  
    boolean wasAdded = false;
    if(addMethod(aMethod))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMethods()) { index = numberOfMethods() - 1; }
      methods.remove(aMethod);
      methods.add(index, aMethod);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMethodAt(Operation aMethod, int index)
  {
    boolean wasAdded = false;
    if(methods.contains(aMethod))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMethods()) { index = numberOfMethods() - 1; }
      methods.remove(aMethod);
      methods.add(index, aMethod);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMethodAt(aMethod, index);
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
      existingSMSS.removeType(this);
    }
    sMSS.addType(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    typesByName.remove(getName());
    while (objects.size() > 0)
    {
      Instance aObject = objects.get(objects.size() - 1);
      aObject.delete();
      objects.remove(aObject);
    }
    
    while (methods.size() > 0)
    {
      Operation aMethod = methods.get(methods.size() - 1);
      aMethod.delete();
      methods.remove(aMethod);
    }
    
    SMSS placeholderSMSS = sMSS;
    this.sMSS = null;
    if(placeholderSMSS != null)
    {
      placeholderSMSS.removeType(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null");
  }
}