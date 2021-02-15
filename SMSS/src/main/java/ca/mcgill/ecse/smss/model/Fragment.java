/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 51 "../../../../../SMSS_model.ump"
public class Fragment extends Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Fragment Attributes
  private boolean isFinished;

  //Fragment Associations
  private List<Operand> operands;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Fragment(boolean aIsFinished)
  {
    super();
    isFinished = aIsFinished;
    operands = new ArrayList<Operand>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsFinished(boolean aIsFinished)
  {
    boolean wasSet = false;
    isFinished = aIsFinished;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsFinished()
  {
    return isFinished;
  }
  /* Code from template association_GetMany */
  public Operand getOperand(int index)
  {
    Operand aOperand = operands.get(index);
    return aOperand;
  }

  public List<Operand> getOperands()
  {
    List<Operand> newOperands = Collections.unmodifiableList(operands);
    return newOperands;
  }

  public int numberOfOperands()
  {
    int number = operands.size();
    return number;
  }

  public boolean hasOperands()
  {
    boolean has = operands.size() > 0;
    return has;
  }

  public int indexOfOperand(Operand aOperand)
  {
    int index = operands.indexOf(aOperand);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOperands()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Operand addOperand(String aCondition)
  {
    return new Operand(aCondition, this);
  }

  public boolean addOperand(Operand aOperand)
  {
    boolean wasAdded = false;
    if (operands.contains(aOperand)) { return false; }
    Fragment existingFragment = aOperand.getFragment();
    boolean isNewFragment = existingFragment != null && !this.equals(existingFragment);
    if (isNewFragment)
    {
      aOperand.setFragment(this);
    }
    else
    {
      operands.add(aOperand);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOperand(Operand aOperand)
  {
    boolean wasRemoved = false;
    //Unable to remove aOperand, as it must always have a fragment
    if (!this.equals(aOperand.getFragment()))
    {
      operands.remove(aOperand);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOperandAt(Operand aOperand, int index)
  {  
    boolean wasAdded = false;
    if(addOperand(aOperand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperands()) { index = numberOfOperands() - 1; }
      operands.remove(aOperand);
      operands.add(index, aOperand);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOperandAt(Operand aOperand, int index)
  {
    boolean wasAdded = false;
    if(operands.contains(aOperand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperands()) { index = numberOfOperands() - 1; }
      operands.remove(aOperand);
      operands.add(index, aOperand);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOperandAt(aOperand, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (operands.size() > 0)
    {
      Operand aOperand = operands.get(operands.size() - 1);
      aOperand.delete();
      operands.remove(aOperand);
    }
    
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "isFinished" + ":" + getIsFinished()+ "]";
  }
}