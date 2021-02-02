/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 30 "../../../../../SMSS_model.ump"
public class Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Associations
  private SMSS sMSS;
  private Operand operand;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block()
  {}

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public SMSS getSMSS()
  {
    return sMSS;
  }

  public boolean hasSMSS()
  {
    boolean has = sMSS != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Operand getOperand()
  {
    return operand;
  }

  public boolean hasOperand()
  {
    boolean has = operand != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setSMSS(SMSS aSMSS)
  {
    boolean wasSet = false;
    // line 38 "../../../../../SMSS_model.ump"
    if (operand != null) {
      		throw new RuntimeException("The block can only belong to either smss or operand");
      	}
    // END OF UMPLE BEFORE INJECTION
    SMSS existingSMSS = sMSS;
    sMSS = aSMSS;
    if (existingSMSS != null && !existingSMSS.equals(aSMSS))
    {
      existingSMSS.removeBlock(this);
    }
    if (aSMSS != null)
    {
      aSMSS.addBlock(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMandatoryMany */
  public boolean setOperand(Operand aOperand)
  {
    //
    // This source of this source generation is association_SetOptionalOneToMandatoryMany.jet
    // This set file assumes the generation of a maximumNumberOfXXX method does not exist because 
    // it's not required (No upper bound)
    //   
    boolean wasSet = false;
    Operand existingOperand = operand;

    if (existingOperand == null)
    {
      if (aOperand != null)
      {
        if (aOperand.addBlock(this))
        {
          existingOperand = aOperand;
          wasSet = true;
        }
      }
    } 
    else if (existingOperand != null)
    {
      if (aOperand == null)
      {
        if (existingOperand.minimumNumberOfBlock() < existingOperand.numberOfBlock())
        {
          existingOperand.removeBlock(this);
          existingOperand = aOperand;  // aOperand == null
          wasSet = true;
        }
      } 
      else
      {
        if (existingOperand.minimumNumberOfBlock() < existingOperand.numberOfBlock())
        {
          existingOperand.removeBlock(this);
          aOperand.addBlock(this);
          existingOperand = aOperand;
          wasSet = true;
        }
      }
    }
    if (wasSet)
    {
      operand = existingOperand;
    }
    return wasSet;
  }
  
  public void delete()
  {
    if (sMSS != null)
    {
      SMSS placeholderSMSS = sMSS;
      this.sMSS = null;
      placeholderSMSS.removeBlock(this);
    }
    if (operand != null)
    {
      if (operand.numberOfBlock() <= 1)
      {
        operand.delete();
      }
      else
      {
        Operand placeholderOperand = operand;
        this.operand = null;
        placeholderOperand.removeBlock(this);
      }
    }
  }

}