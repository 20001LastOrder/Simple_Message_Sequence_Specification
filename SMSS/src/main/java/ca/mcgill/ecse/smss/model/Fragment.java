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

  //Fragment Associations
  private List<Operand> operands;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Fragment()
  {
    super();
    operands = new ArrayList<Operand>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfOperandsValid()
  {
    boolean isValid = numberOfOperands() >= minimumNumberOfOperands();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOperands()
  {
    return 2;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Operand addOperand(String aCondition, Block... allBlock)
  {
    Operand aNewOperand = new Operand(aCondition, this, allBlock);
    return aNewOperand;
  }

  public boolean addOperand(Operand aOperand)
  {
    boolean wasAdded = false;
    if (operands.contains(aOperand)) { return false; }
    Fragment existingFragment = aOperand.getFragment();
    boolean isNewFragment = existingFragment != null && !this.equals(existingFragment);

    if (isNewFragment && existingFragment.numberOfOperands() <= minimumNumberOfOperands())
    {
      return wasAdded;
    }
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
    if (this.equals(aOperand.getFragment()))
    {
      return wasRemoved;
    }

    //fragment already at minimum (2)
    if (numberOfOperands() <= minimumNumberOfOperands())
    {
      return wasRemoved;
    }

    operands.remove(aOperand);
    wasRemoved = true;
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

}