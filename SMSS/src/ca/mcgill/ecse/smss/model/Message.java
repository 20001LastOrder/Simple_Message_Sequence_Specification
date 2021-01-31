/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 44 "../../../../../SMSS_model.ump"
public class Message extends Block
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Message> messagesByName = new HashMap<String, Message>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Message Attributes
  private String name;

  //Message Associations
  private Fragment beforeFragment;
  private Fragment afterFragment;
  private Sender sender;
  private Receiver receiver;
  private Operand operand;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Message(Operation aOperation, String aName, Sender aSender, Receiver aReceiver, Operand aOperand)
  {
    super(aOperation);
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddSender = setSender(aSender);
    if (!didAddSender)
    {
      throw new RuntimeException("Unable to create sentMessage due to sender. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddReceiver = setReceiver(aReceiver);
    if (!didAddReceiver)
    {
      throw new RuntimeException("Unable to create receivedMessage due to receiver. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddOperand = setOperand(aOperand);
    if (!didAddOperand)
    {
      throw new RuntimeException("Unable to create message due to operand. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      messagesByName.remove(anOldName);
    }
    messagesByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Message getWithName(String aName)
  {
    return messagesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }
  /* Code from template association_GetOne */
  public Fragment getBeforeFragment()
  {
    return beforeFragment;
  }

  public boolean hasBeforeFragment()
  {
    boolean has = beforeFragment != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Fragment getAfterFragment()
  {
    return afterFragment;
  }

  public boolean hasAfterFragment()
  {
    boolean has = afterFragment != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Sender getSender()
  {
    return sender;
  }
  /* Code from template association_GetOne */
  public Receiver getReceiver()
  {
    return receiver;
  }
  /* Code from template association_GetOne */
  public Operand getOperand()
  {
    return operand;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBeforeFragment(Fragment aNewBeforeFragment)
  {
    boolean wasSet = false;
    beforeFragment = aNewBeforeFragment;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setAfterFragment(Fragment aNewAfterFragment)
  {
    boolean wasSet = false;
    afterFragment = aNewAfterFragment;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSender(Sender aSender)
  {
    boolean wasSet = false;
    if (aSender == null)
    {
      return wasSet;
    }

    Sender existingSender = sender;
    sender = aSender;
    if (existingSender != null && !existingSender.equals(aSender))
    {
      existingSender.removeSentMessage(this);
    }
    sender.addSentMessage(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setReceiver(Receiver aReceiver)
  {
    boolean wasSet = false;
    if (aReceiver == null)
    {
      return wasSet;
    }

    Receiver existingReceiver = receiver;
    receiver = aReceiver;
    if (existingReceiver != null && !existingReceiver.equals(aReceiver))
    {
      existingReceiver.removeReceivedMessage(this);
    }
    receiver.addReceivedMessage(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setOperand(Operand aOperand)
  {
    boolean wasSet = false;
    //Must provide operand to message
    if (aOperand == null)
    {
      return wasSet;
    }

    if (operand != null && operand.numberOfMessages() <= Operand.minimumNumberOfMessages())
    {
      return wasSet;
    }

    Operand existingOperand = operand;
    operand = aOperand;
    if (existingOperand != null && !existingOperand.equals(aOperand))
    {
      boolean didRemove = existingOperand.removeMessage(this);
      if (!didRemove)
      {
        operand = existingOperand;
        return wasSet;
      }
    }
    operand.addMessage(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    messagesByName.remove(getName());
    beforeFragment = null;
    afterFragment = null;
    Sender placeholderSender = sender;
    this.sender = null;
    if(placeholderSender != null)
    {
      placeholderSender.removeSentMessage(this);
    }
    Receiver placeholderReceiver = receiver;
    this.receiver = null;
    if(placeholderReceiver != null)
    {
      placeholderReceiver.removeReceivedMessage(this);
    }
    Operand placeholderOperand = operand;
    this.operand = null;
    if(placeholderOperand != null)
    {
      placeholderOperand.removeMessage(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "beforeFragment = "+(getBeforeFragment()!=null?Integer.toHexString(System.identityHashCode(getBeforeFragment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "afterFragment = "+(getAfterFragment()!=null?Integer.toHexString(System.identityHashCode(getAfterFragment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "sender = "+(getSender()!=null?Integer.toHexString(System.identityHashCode(getSender())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "receiver = "+(getReceiver()!=null?Integer.toHexString(System.identityHashCode(getReceiver())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "operand = "+(getOperand()!=null?Integer.toHexString(System.identityHashCode(getOperand())):"null");
  }
}