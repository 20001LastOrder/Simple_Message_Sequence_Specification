/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 17 "../../../../../SMSS_model.ump"
public class Instance
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instance Attributes
  private int id;
  private String name;

  //Instance Associations
  private Type type;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Instance(int aId, String aName, Type aType)
  {
    id = aId;
    name = aName;
    boolean didAddType = setType(aType);
    if (!didAddType)
    {
      throw new RuntimeException("Unable to create object due to type. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public Type getType()
  {
    return type;
  }
  /* Code from template association_SetOneToMany */
  public boolean setType(Type aType)
  {
    boolean wasSet = false;
    if (aType == null)
    {
      return wasSet;
    }

    Type existingType = type;
    type = aType;
    if (existingType != null && !existingType.equals(aType))
    {
      existingType.removeObject(this);
    }
    type.addObject(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Type placeholderType = type;
    this.type = null;
    if(placeholderType != null)
    {
      placeholderType.removeObject(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type = "+(getType()!=null?Integer.toHexString(System.identityHashCode(getType())):"null");
  }
}