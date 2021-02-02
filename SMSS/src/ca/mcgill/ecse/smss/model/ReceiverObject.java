/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 19 "../../../../../SMSS_model.ump"
public class ReceiverObject
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, ReceiverObject> receiverobjectsByName = new HashMap<String, ReceiverObject>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ReceiverObject Attributes
  private String name;

  //ReceiverObject Associations
  private List<MessageExchange> messageExchanges;
  private ReceiverClass receiverClass;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReceiverObject(String aName, ReceiverClass aReceiverClass)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    messageExchanges = new ArrayList<MessageExchange>();
    boolean didAddReceiverClass = setReceiverClass(aReceiverClass);
    if (!didAddReceiverClass)
    {
      throw new RuntimeException("Unable to create object due to receiverClass. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
      receiverobjectsByName.remove(anOldName);
    }
    receiverobjectsByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static ReceiverObject getWithName(String aName)
  {
    return receiverobjectsByName.get(aName);
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
  public ReceiverClass getReceiverClass()
  {
    return receiverClass;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMessageExchanges()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MessageExchange addMessageExchange(Message aMessage)
  {
    return new MessageExchange(this, aMessage);
  }

  public boolean addMessageExchange(MessageExchange aMessageExchange)
  {
    boolean wasAdded = false;
    if (messageExchanges.contains(aMessageExchange)) { return false; }
    ReceiverObject existingReceiver = aMessageExchange.getReceiver();
    boolean isNewReceiver = existingReceiver != null && !this.equals(existingReceiver);
    if (isNewReceiver)
    {
      aMessageExchange.setReceiver(this);
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
    //Unable to remove aMessageExchange, as it must always have a receiver
    if (!this.equals(aMessageExchange.getReceiver()))
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
  public boolean setReceiverClass(ReceiverClass aReceiverClass)
  {
    boolean wasSet = false;
    if (aReceiverClass == null)
    {
      return wasSet;
    }

    ReceiverClass existingReceiverClass = receiverClass;
    receiverClass = aReceiverClass;
    if (existingReceiverClass != null && !existingReceiverClass.equals(aReceiverClass))
    {
      existingReceiverClass.removeObject(this);
    }
    receiverClass.addObject(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    receiverobjectsByName.remove(getName());
    for(int i=messageExchanges.size(); i > 0; i--)
    {
      MessageExchange aMessageExchange = messageExchanges.get(i - 1);
      aMessageExchange.delete();
    }
    ReceiverClass placeholderReceiverClass = receiverClass;
    this.receiverClass = null;
    if(placeholderReceiverClass != null)
    {
      placeholderReceiverClass.removeObject(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "receiverClass = "+(getReceiverClass()!=null?Integer.toHexString(System.identityHashCode(getReceiverClass())):"null");
  }
}