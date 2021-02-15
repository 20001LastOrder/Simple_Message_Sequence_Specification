/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 35 "../../../../../SMSS_model.ump"
public abstract class Block
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
    // line 44 "../../../../../SMSS_model.ump"
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
  /* Code from template association_SetOptionalOneToMany */
  public boolean setOperand(Operand aOperand)
  {
    boolean wasSet = false;
    // line 38 "../../../../../SMSS_model.ump"
    if (sMSS != null) {
      		throw new RuntimeException("The block can only belong to either smss or operand");
      	}
    // END OF UMPLE BEFORE INJECTION
    Operand existingOperand = operand;
    operand = aOperand;
    if (existingOperand != null && !existingOperand.equals(aOperand))
    {
      existingOperand.removeBlock(this);
    }
    if (aOperand != null)
    {
      aOperand.addBlock(this);
    }
    wasSet = true;
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
      Operand placeholderOperand = operand;
      this.operand = null;
      placeholderOperand.removeBlock(this);
    }
  }

}