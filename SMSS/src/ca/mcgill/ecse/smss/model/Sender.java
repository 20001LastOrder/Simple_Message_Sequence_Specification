/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 30 "../../../../../SMSS_model.ump"
public class Sender extends Instance
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Sender Associations
  private List<Message> sentMessages;
  private SMSS sMSS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Sender(int aId, String aName, Type aType, SMSS aSMSS)
  {
    super(aId, aName, aType);
    sentMessages = new ArrayList<Message>();
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create sender due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Message getSentMessage(int index)
  {
    Message aSentMessage = sentMessages.get(index);
    return aSentMessage;
  }

  public List<Message> getSentMessages()
  {
    List<Message> newSentMessages = Collections.unmodifiableList(sentMessages);
    return newSentMessages;
  }

  public int numberOfSentMessages()
  {
    int number = sentMessages.size();
    return number;
  }

  public boolean hasSentMessages()
  {
    boolean has = sentMessages.size() > 0;
    return has;
  }

  public int indexOfSentMessage(Message aSentMessage)
  {
    int index = sentMessages.indexOf(aSentMessage);
    return index;
  }
  /* Code from template association_GetOne */
  public SMSS getSMSS()
  {
    return sMSS;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSentMessages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Message addSentMessage(Operation aOperation, String aName, Receiver aReceiver, Operand aOperand)
  {
    return new Message(aOperation, aName, this, aReceiver, aOperand);
  }

  public boolean addSentMessage(Message aSentMessage)
  {
    boolean wasAdded = false;
    if (sentMessages.contains(aSentMessage)) { return false; }
    Sender existingSender = aSentMessage.getSender();
    boolean isNewSender = existingSender != null && !this.equals(existingSender);
    if (isNewSender)
    {
      aSentMessage.setSender(this);
    }
    else
    {
      sentMessages.add(aSentMessage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSentMessage(Message aSentMessage)
  {
    boolean wasRemoved = false;
    //Unable to remove aSentMessage, as it must always have a sender
    if (!this.equals(aSentMessage.getSender()))
    {
      sentMessages.remove(aSentMessage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSentMessageAt(Message aSentMessage, int index)
  {  
    boolean wasAdded = false;
    if(addSentMessage(aSentMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSentMessages()) { index = numberOfSentMessages() - 1; }
      sentMessages.remove(aSentMessage);
      sentMessages.add(index, aSentMessage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSentMessageAt(Message aSentMessage, int index)
  {
    boolean wasAdded = false;
    if(sentMessages.contains(aSentMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSentMessages()) { index = numberOfSentMessages() - 1; }
      sentMessages.remove(aSentMessage);
      sentMessages.add(index, aSentMessage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSentMessageAt(aSentMessage, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setSMSS(SMSS aNewSMSS)
  {
    boolean wasSet = false;
    if (aNewSMSS == null)
    {
      //Unable to setSMSS to null, as sender must always be associated to a sMSS
      return wasSet;
    }
    
    Sender existingSender = aNewSMSS.getSender();
    if (existingSender != null && !equals(existingSender))
    {
      //Unable to setSMSS, the current sMSS already has a sender, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    SMSS anOldSMSS = sMSS;
    sMSS = aNewSMSS;
    sMSS.setSender(this);

    if (anOldSMSS != null)
    {
      anOldSMSS.setSender(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=sentMessages.size(); i > 0; i--)
    {
      Message aSentMessage = sentMessages.get(i - 1);
      aSentMessage.delete();
    }
    SMSS existingSMSS = sMSS;
    sMSS = null;
    if (existingSMSS != null)
    {
      existingSMSS.setSender(null);
    }
    super.delete();
  }

}