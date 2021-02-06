/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 64 "../../../../../SMSS_model.ump"
public class Operand
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Operand Attributes
  private String condition;

  //Operand Associations
  private List<Block> block;
  private Fragment fragment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Operand(String aCondition, Fragment aFragment, Block... allBlock)
  {
    condition = aCondition;
    block = new ArrayList<Block>();
    boolean didAddBlock = setBlock(allBlock);
    if (!didAddBlock)
    {
      throw new RuntimeException("Unable to create Operand, must have at least 1 block. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
    Block aBlock = block.get(index);
    return aBlock;
  }

  public List<Block> getBlock()
  {
    List<Block> newBlock = Collections.unmodifiableList(block);
    return newBlock;
  }

  public int numberOfBlock()
  {
    int number = block.size();
    return number;
  }

  public boolean hasBlock()
  {
    boolean has = block.size() > 0;
    return has;
  }

  public int indexOfBlock(Block aBlock)
  {
    int index = block.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public Fragment getFragment()
  {
    return fragment;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlock()
  {
    return 1;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (block.contains(aBlock)) { return false; }
    Operand existingOperand = aBlock.getOperand();
    if (existingOperand != null && existingOperand.numberOfBlock() <= minimumNumberOfBlock())
    {
      return wasAdded;
    }
    else if (existingOperand != null)
    {
      existingOperand.block.remove(aBlock);
    }
    block.add(aBlock);
    setOperand(aBlock,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(Block aBlock)
  {
    boolean wasRemoved = false;
    if (block.contains(aBlock) && numberOfBlock() > minimumNumberOfBlock())
    {
      block.remove(aBlock);
      setOperand(aBlock,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setBlock(Block... newBlock)
  {
    boolean wasSet = false;
    if (newBlock.length < minimumNumberOfBlock())
    {
      return wasSet;
    }

    ArrayList<Block> checkNewBlock = new ArrayList<Block>();
    HashMap<Operand,Integer> operandToNewBlock = new HashMap<Operand,Integer>();
    for (Block aBlock : newBlock)
    {
      if (checkNewBlock.contains(aBlock))
      {
        return wasSet;
      }
      else if (aBlock.getOperand() != null && !this.equals(aBlock.getOperand()))
      {
        Operand existingOperand = aBlock.getOperand();
        if (!operandToNewBlock.containsKey(existingOperand))
        {
          operandToNewBlock.put(existingOperand, new Integer(existingOperand.numberOfBlock()));
        }
        Integer currentCount = operandToNewBlock.get(existingOperand);
        int nextCount = currentCount - 1;
        if (nextCount < 1)
        {
          return wasSet;
        }
        operandToNewBlock.put(existingOperand, new Integer(nextCount));
      }
      checkNewBlock.add(aBlock);
    }

    block.removeAll(checkNewBlock);

    for (Block orphan : block)
    {
      setOperand(orphan, null);
    }
    block.clear();
    for (Block aBlock : newBlock)
    {
      if (aBlock.getOperand() != null)
      {
        aBlock.getOperand().block.remove(aBlock);
      }
      setOperand(aBlock, this);
      block.add(aBlock);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setOperand(Block aBlock, Operand aOperand)
  {
    try
    {
      java.lang.reflect.Field mentorField = aBlock.getClass().getDeclaredField("operand");
      mentorField.setAccessible(true);
      mentorField.set(aBlock, aOperand);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aOperand to aBlock", e);
    }
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(Block aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlock()) { index = numberOfBlock() - 1; }
      block.remove(aBlock);
      block.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(Block aBlock, int index)
  {
    boolean wasAdded = false;
    if(block.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlock()) { index = numberOfBlock() - 1; }
      block.remove(aBlock);
      block.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setFragment(Fragment aFragment)
  {
    boolean wasSet = false;
    //Must provide fragment to operand
    if (aFragment == null)
    {
      return wasSet;
    }

    if (fragment != null && fragment.numberOfOperands() <= Fragment.minimumNumberOfOperands())
    {
      return wasSet;
    }

    Fragment existingFragment = fragment;
    fragment = aFragment;
    if (existingFragment != null && !existingFragment.equals(aFragment))
    {
      boolean didRemove = existingFragment.removeOperand(this);
      if (!didRemove)
      {
        fragment = existingFragment;
        return wasSet;
      }
    }
    fragment.addOperand(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (block.size() > 0)
    {
      Block aBlock = block.get(block.size() - 1);
      aBlock.delete();
      block.remove(aBlock);
    }
    
    Fragment placeholderFragment = fragment;
    this.fragment = null;
    if(placeholderFragment != null)
    {
      placeholderFragment.removeOperand(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "condition" + ":" + getCondition()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fragment = "+(getFragment()!=null?Integer.toHexString(System.identityHashCode(getFragment())):"null");
  }
}