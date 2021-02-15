/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 50 "../../../../../SMSS_model.ump"
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
  private List<MessageExchange> messageExchanges;
  private SMSS sMSS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Message(String aName, SMSS aSMSS)
  {
    super();
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    messageExchanges = new ArrayList<MessageExchange>();
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create message due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  /* Code from template association_GetMany */
  public MessageExchange getMessageExchange(int index)
  {
    MessageExchange aMessageExchange = messageExchanges.get(index);
    return aMessageExchange;
  }

  public List<MessageExchange> getMessageExchanges()
  {
    List<MessageExchange> newMessageExchanges = Collections.unmodifiableList(messageExchanges);
    return newMessageExchanges;
  }

  public int numberOfMessageExchanges()
  {
    int number = messageExchanges.size();
    return number;
  }

  public boolean hasMessageExchanges()
  {
    boolean has = messageExchanges.size() > 0;
    return has;
  }

  public int indexOfMessageExchange(MessageExchange aMessageExchange)
  {
    int index = messageExchanges.indexOf(aMessageExchange);
    return index;
  }
  /* Code from template association_GetOne */
  public SMSS getSMSS()
  {
    return sMSS;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMessageExchanges()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MessageExchange addMessageExchange(ReceiverObject aReceiver)
  {
    return new MessageExchange(aReceiver, this);
  }

  public boolean addMessageExchange(MessageExchange aMessageExchange)
  {
    boolean wasAdded = false;
    if (messageExchanges.contains(aMessageExchange)) { return false; }
    Message existingMessage = aMessageExchange.getMessage();
    boolean isNewMessage = existingMessage != null && !this.equals(existingMessage);
    if (isNewMessage)
    {
      aMessageExchange.setMessage(this);
    }
    else
    {
      messageExchanges.add(aMessageExchange);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMessageExchange(MessageExchange aMessageExchange)
  {
    boolean wasRemoved = false;
    //Unable to remove aMessageExchange, as it must always have a message
    if (!this.equals(aMessageExchange.getMessage()))
    {
      messageExchanges.remove(aMessageExchange);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMessageExchangeAt(MessageExchange aMessageExchange, int index)
  {  
    boolean wasAdded = false;
    if(addMessageExchange(aMessageExchange))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessageExchanges()) { index = numberOfMessageExchanges() - 1; }
      messageExchanges.remove(aMessageExchange);
      messageExchanges.add(index, aMessageExchange);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMessageExchangeAt(MessageExchange aMessageExchange, int index)
  {
    boolean wasAdded = false;
    if(messageExchanges.contains(aMessageExchange))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessageExchanges()) { index = numberOfMessageExchanges() - 1; }
      messageExchanges.remove(aMessageExchange);
      messageExchanges.add(index, aMessageExchange);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMessageExchangeAt(aMessageExchange, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSMSS(SMSS aSMSS)
  {
    boolean wasSet = false;
    if (aSMSS == null)
    {
      return wasSet;
    }

    SMSS existingSMSS = sMSS;
    sMSS = aSMSS;
    if (existingSMSS != null && !existingSMSS.equals(aSMSS))
    {
      existingSMSS.removeMessage(this);
    }
    sMSS.addMessage(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    messagesByName.remove(getName());
    for(int i=messageExchanges.size(); i > 0; i--)
    {
      MessageExchange aMessageExchange = messageExchanges.get(i - 1);
      aMessageExchange.delete();
    }
    SMSS placeholderSMSS = sMSS;
    this.sMSS = null;
    if(placeholderSMSS != null)
    {
      placeholderSMSS.removeMessage(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null");
  }
}