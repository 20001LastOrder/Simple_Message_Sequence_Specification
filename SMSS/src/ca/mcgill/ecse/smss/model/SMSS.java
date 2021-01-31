/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 3 "../../../../../SMSS_model.ump"
public class SMSS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SMSS Associations
  private List<Type> types;
  private Operation mainMethod;
  private Sender sender;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SMSS()
  {
    types = new ArrayList<Type>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Type getType(int index)
  {
    Type aType = types.get(index);
    return aType;
  }

  public List<Type> getTypes()
  {
    List<Type> newTypes = Collections.unmodifiableList(types);
    return newTypes;
  }

  public int numberOfTypes()
  {
    int number = types.size();
    return number;
  }

  public boolean hasTypes()
  {
    boolean has = types.size() > 0;
    return has;
  }

  public int indexOfType(Type aType)
  {
    int index = types.indexOf(aType);
    return index;
  }
  /* Code from template association_GetOne */
  public Operation getMainMethod()
  {
    return mainMethod;
  }

  public boolean hasMainMethod()
  {
    boolean has = mainMethod != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Sender getSender()
  {
    return sender;
  }

  public boolean hasSender()
  {
    boolean has = sender != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Type addType(String aName)
  {
    return new Type(aName, this);
  }

  public boolean addType(Type aType)
  {
    boolean wasAdded = false;
    if (types.contains(aType)) { return false; }
    SMSS existingSMSS = aType.getSMSS();
    boolean isNewSMSS = existingSMSS != null && !this.equals(existingSMSS);
    if (isNewSMSS)
    {
      aType.setSMSS(this);
    }
    else
    {
      types.add(aType);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeType(Type aType)
  {
    boolean wasRemoved = false;
    //Unable to remove aType, as it must always have a sMSS
    if (!this.equals(aType.getSMSS()))
    {
      types.remove(aType);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTypeAt(Type aType, int index)
  {  
    boolean wasAdded = false;
    if(addType(aType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTypes()) { index = numberOfTypes() - 1; }
      types.remove(aType);
      types.add(index, aType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTypeAt(Type aType, int index)
  {
    boolean wasAdded = false;
    if(types.contains(aType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTypes()) { index = numberOfTypes() - 1; }
      types.remove(aType);
      types.add(index, aType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTypeAt(aType, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setMainMethod(Operation aNewMainMethod)
  {
    boolean wasSet = false;
    if (mainMethod != null && !mainMethod.equals(aNewMainMethod) && equals(mainMethod.getSMSS()))
    {
      //Unable to setMainMethod, as existing mainMethod would become an orphan
      return wasSet;
    }

    mainMethod = aNewMainMethod;
    SMSS anOldSMSS = aNewMainMethod != null ? aNewMainMethod.getSMSS() : null;

    if (!this.equals(anOldSMSS))
    {
      if (anOldSMSS != null)
      {
        anOldSMSS.mainMethod = null;
      }
      if (mainMethod != null)
      {
        mainMethod.setSMSS(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setSender(Sender aNewSender)
  {
    boolean wasSet = false;
    if (sender != null && !sender.equals(aNewSender) && equals(sender.getSMSS()))
    {
      //Unable to setSender, as existing sender would become an orphan
      return wasSet;
    }

    sender = aNewSender;
    SMSS anOldSMSS = aNewSender != null ? aNewSender.getSMSS() : null;

    if (!this.equals(anOldSMSS))
    {
      if (anOldSMSS != null)
      {
        anOldSMSS.sender = null;
      }
      if (sender != null)
      {
        sender.setSMSS(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (types.size() > 0)
    {
      Type aType = types.get(types.size() - 1);
      aType.delete();
      types.remove(aType);
    }
    
    Operation existingMainMethod = mainMethod;
    mainMethod = null;
    if (existingMainMethod != null)
    {
      existingMainMethod.delete();
    }
    Sender existingSender = sender;
    sender = null;
    if (existingSender != null)
    {
      existingSender.delete();
    }
  }

}