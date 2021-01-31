/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 35 "../../../../../SMSS_model.ump"
public class Receiver extends Instance
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Receiver Associations
  private List<Message> receivedMessages;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Receiver(int aId, String aName, Type aType)
  {
    super(aId, aName, aType);
    receivedMessages = new ArrayList<Message>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Message getReceivedMessage(int index)
  {
    Message aReceivedMessage = receivedMessages.get(index);
    return aReceivedMessage;
  }

  public List<Message> getReceivedMessages()
  {
    List<Message> newReceivedMessages = Collections.unmodifiableList(receivedMessages);
    return newReceivedMessages;
  }

  public int numberOfReceivedMessages()
  {
    int number = receivedMessages.size();
    return number;
  }

  public boolean hasReceivedMessages()
  {
    boolean has = receivedMessages.size() > 0;
    return has;
  }

  public int indexOfReceivedMessage(Message aReceivedMessage)
  {
    int index = receivedMessages.indexOf(aReceivedMessage);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReceivedMessages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Message addReceivedMessage(Operation aOperation, String aName, Sender aSender, Operand aOperand)
  {
    return new Message(aOperation, aName, aSender, this, aOperand);
  }

  public boolean addReceivedMessage(Message aReceivedMessage)
  {
    boolean wasAdded = false;
    if (receivedMessages.contains(aReceivedMessage)) { return false; }
    Receiver existingReceiver = aReceivedMessage.getReceiver();
    boolean isNewReceiver = existingReceiver != null && !this.equals(existingReceiver);
    if (isNewReceiver)
    {
      aReceivedMessage.setReceiver(this);
    }
    else
    {
      receivedMessages.add(aReceivedMessage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReceivedMessage(Message aReceivedMessage)
  {
    boolean wasRemoved = false;
    //Unable to remove aReceivedMessage, as it must always have a receiver
    if (!this.equals(aReceivedMessage.getReceiver()))
    {
      receivedMessages.remove(aReceivedMessage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReceivedMessageAt(Message aReceivedMessage, int index)
  {  
    boolean wasAdded = false;
    if(addReceivedMessage(aReceivedMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReceivedMessages()) { index = numberOfReceivedMessages() - 1; }
      receivedMessages.remove(aReceivedMessage);
      receivedMessages.add(index, aReceivedMessage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReceivedMessageAt(Message aReceivedMessage, int index)
  {
    boolean wasAdded = false;
    if(receivedMessages.contains(aReceivedMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReceivedMessages()) { index = numberOfReceivedMessages() - 1; }
      receivedMessages.remove(aReceivedMessage);
      receivedMessages.add(index, aReceivedMessage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReceivedMessageAt(aReceivedMessage, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=receivedMessages.size(); i > 0; i--)
    {
      Message aReceivedMessage = receivedMessages.get(i - 1);
      aReceivedMessage.delete();
    }
    super.delete();
  }

}