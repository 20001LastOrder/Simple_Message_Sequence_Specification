/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 70 "../../../../../SMSS_model.ump"
public class Condition
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Condition Attributes
  private String description;

  //Condition Associations
  private Operand operand;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Condition(String aDescription, Operand aOperand)
  {
    description = aDescription;
    boolean didAddOperand = setOperand(aOperand);
    if (!didAddOperand)
    {
      throw new RuntimeException("Unable to create condition due to operand. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public String getDescription()
  {
    return description;
  }
  /* Code from template association_GetOne */
  public Operand getOperand()
  {
    return operand;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setOperand(Operand aNewOperand)
  {
    boolean wasSet = false;
    if (aNewOperand == null)
    {
      //Unable to setOperand to null, as condition must always be associated to a operand
      return wasSet;
    }
    
    Condition existingCondition = aNewOperand.getCondition();
    if (existingCondition != null && !equals(existingCondition))
    {
      //Unable to setOperand, the current operand already has a condition, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Operand anOldOperand = operand;
    operand = aNewOperand;
    operand.setCondition(this);

    if (anOldOperand != null)
    {
      anOldOperand.setCondition(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Operand existingOperand = operand;
    operand = null;
    if (existingOperand != null)
    {
      existingOperand.setCondition(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "operand = "+(getOperand()!=null?Integer.toHexString(System.identityHashCode(getOperand())):"null");
  }
}