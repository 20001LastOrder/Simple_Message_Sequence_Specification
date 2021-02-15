/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 25 "../../../../../SMSS_model.ump"
public class MessageExchange extends Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MessageExchange Associations
  private ReceiverObject receiver;
  private Message message;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MessageExchange(ReceiverObject aReceiver, Message aMessage)
  {
    super();
    boolean didAddReceiver = setReceiver(aReceiver);
    if (!didAddReceiver)
    {
      throw new RuntimeException("Unable to create messageExchange due to receiver. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddMessage = setMessage(aMessage);
    if (!didAddMessage)
    {
      throw new RuntimeException("Unable to create messageExchange due to message. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public ReceiverObject getReceiver()
  {
    return receiver;
  }
  /* Code from template association_GetOne */
  public Message getMessage()
  {
    return message;
  }
  /* Code from template association_SetOneToMany */
  public boolean setReceiver(ReceiverObject aReceiver)
  {
    boolean wasSet = false;
    if (aReceiver == null)
    {
      return wasSet;
    }

    ReceiverObject existingReceiver = receiver;
    receiver = aReceiver;
    if (existingReceiver != null && !existingReceiver.equals(aReceiver))
    {
      existingReceiver.removeMessageExchange(this);
    }
    receiver.addMessageExchange(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMessage(Message aMessage)
  {
    boolean wasSet = false;
    if (aMessage == null)
    {
      return wasSet;
    }

    Message existingMessage = message;
    message = aMessage;
    if (existingMessage != null && !existingMessage.equals(aMessage))
    {
      existingMessage.removeMessageExchange(this);
    }
    message.addMessageExchange(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ReceiverObject placeholderReceiver = receiver;
    this.receiver = null;
    if(placeholderReceiver != null)
    {
      placeholderReceiver.removeMessageExchange(this);
    }
    Message placeholderMessage = message;
    this.message = null;
    if(placeholderMessage != null)
    {
      placeholderMessage.removeMessageExchange(this);
    }
    super.delete();
  }

}