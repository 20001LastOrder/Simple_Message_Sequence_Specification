/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 24 "../../../../../SMSS_model.ump"
public class Operation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Operation Attributes
  private String name;

  //Operation Associations
  private List<Block> blocks;
  private SMSS sMSS;
  private Type type;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Operation(String aName, SMSS aSMSS, Type aType)
  {
    name = aName;
    blocks = new ArrayList<Block>();
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create mainMethod due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddType = setType(aType);
    if (!didAddType)
    {
      throw new RuntimeException("Unable to create method due to type. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public Block getBlock(int index)
  {
    Block aBlock = blocks.get(index);
    return aBlock;
  }

  public List<Block> getBlocks()
  {
    List<Block> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(Block aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public SMSS getSMSS()
  {
    return sMSS;
  }
  /* Code from template association_GetOne */
  public Type getType()
  {
    return type;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addBlock()
  {
    return new Block(this);
  }

  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    Operation existingOperation = aBlock.getOperation();
    boolean isNewOperation = existingOperation != null && !this.equals(existingOperation);
    if (isNewOperation)
    {
      aBlock.setOperation(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(Block aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a operation
    if (!this.equals(aBlock.getOperation()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(Block aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(Block aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setSMSS(SMSS aNewSMSS)
  {
    boolean wasSet = false;
    if (aNewSMSS == null)
    {
      //Unable to setSMSS to null, as mainMethod must always be associated to a sMSS
      return wasSet;
    }
    
    Operation existingMainMethod = aNewSMSS.getMainMethod();
    if (existingMainMethod != null && !equals(existingMainMethod))
    {
      //Unable to setSMSS, the current sMSS already has a mainMethod, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    SMSS anOldSMSS = sMSS;
    sMSS = aNewSMSS;
    sMSS.setMainMethod(this);

    if (anOldSMSS != null)
    {
      anOldSMSS.setMainMethod(null);
    }
    wasSet = true;
    return wasSet;
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
      existingType.removeMethod(this);
    }
    type.addMethod(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (blocks.size() > 0)
    {
      Block aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    SMSS existingSMSS = sMSS;
    sMSS = null;
    if (existingSMSS != null)
    {
      existingSMSS.setMainMethod(null);
    }
    Type placeholderType = type;
    this.type = null;
    if(placeholderType != null)
    {
      placeholderType.removeMethod(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "type = "+(getType()!=null?Integer.toHexString(System.identityHashCode(getType())):"null");
  }
}