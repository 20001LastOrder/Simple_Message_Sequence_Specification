/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 87 "../../../../../SMSS_model.ump"
public class Operand
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Operand Attributes
  private String condition;

  //Operand Associations
  private List<Block> blocks;
  private Fragment fragment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Operand(String aCondition, Fragment aFragment)
  {
    condition = aCondition;
    blocks = new ArrayList<Block>();
    boolean didAddFragment = setFragment(aFragment);
    if (!didAddFragment)
    {
      throw new RuntimeException("Unable to create operand due to fragment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCondition(String aCondition)
  {
    boolean wasSet = false;
    condition = aCondition;
    wasSet = true;
    return wasSet;
  }

  public String getCondition()
  {
    return condition;
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
  public Fragment getFragment()
  {
    return fragment;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    Operand existingOperand = aBlock.getOperand();
    if (existingOperand == null)
    {
      aBlock.setOperand(this);
    }
    else if (!this.equals(existingOperand))
    {
      existingOperand.removeBlock(aBlock);
      addBlock(aBlock);
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
    if (blocks.contains(aBlock))
    {
      blocks.remove(aBlock);
      aBlock.setOperand(null);
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
  /* Code from template association_SetOneToMany */
  public boolean setFragment(Fragment aFragment)
  {
    boolean wasSet = false;
    if (aFragment == null)
    {
      return wasSet;
    }

    Fragment existingFragment = fragment;
    fragment = aFragment;
    if (existingFragment != null && !existingFragment.equals(aFragment))
    {
      existingFragment.removeOperand(this);
    }
    fragment.addOperand(this);
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
    
    Fragment placeholderFragment = fragment;
    this.fragment = null;
    if(placeholderFragment != null)
    {
      placeholderFragment.removeOperand(this);
    }
  }

  // line 92 "../../../../../SMSS_model.ump"
   public String toString(){
    StringBuilder sb = new StringBuilder();
	  sb.append('[').append(condition).append(']');
	  for (Block block : blocks) {
		  sb.append("\n").append(block.toString().replaceAll("(?m)^", "\t"));
	  }
	  
	  return sb.toString();
  }

}