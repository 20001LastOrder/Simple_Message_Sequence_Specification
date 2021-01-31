/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 40 "../../../../../SMSS_model.ump"
public class Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Associations
  private Operation operation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(Operation aOperation)
  {
    boolean didAddOperation = setOperation(aOperation);
    if (!didAddOperation)
    {
      throw new RuntimeException("Unable to create block due to operation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Operation getOperation()
  {
    return operation;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOperation(Operation aOperation)
  {
    boolean wasSet = false;
    if (aOperation == null)
    {
      return wasSet;
    }

    Operation existingOperation = operation;
    operation = aOperation;
    if (existingOperation != null && !existingOperation.equals(aOperation))
    {
      existingOperation.removeBlock(this);
    }
    operation.addBlock(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Operation placeholderOperation = operation;
    this.operation = null;
    if(placeholderOperation != null)
    {
      placeholderOperation.removeBlock(this);
    }
  }

}