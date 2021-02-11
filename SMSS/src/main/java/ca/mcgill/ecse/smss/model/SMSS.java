/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 3 "../../../../../SMSS_model.ump"
public class SMSS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SMSS Attributes
  private String methodName;
  private String senderClass;
  private String senderName;

  //SMSS Associations
  private List<ReceiverClass> receiverClasses;
  private List<Message> messages;
  private List<Block> blocks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SMSS()
  {
    methodName = "";
    senderClass = "";
    senderName = "";
    receiverClasses = new ArrayList<ReceiverClass>();
    messages = new ArrayList<Message>();
    blocks = new ArrayList<Block>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMethodName(String aMethodName)
  {
    boolean wasSet = false;
    methodName = aMethodName;
    wasSet = true;
    return wasSet;
  }

  public boolean setSenderClass(String aSenderClass)
  {
    boolean wasSet = false;
    senderClass = aSenderClass;
    wasSet = true;
    return wasSet;
  }

  public boolean setSenderName(String aSenderName)
  {
    boolean wasSet = false;
    senderName = aSenderName;
    wasSet = true;
    return wasSet;
  }

  public String getMethodName()
  {
    return methodName;
  }

  public String getSenderClass()
  {
    return senderClass;
  }

  public String getSenderName()
  {
    return senderName;
  }
  /* Code from template association_GetMany */
  public ReceiverClass getReceiverClass(int index)
  {
    ReceiverClass aReceiverClass = receiverClasses.get(index);
    return aReceiverClass;
  }

  public List<ReceiverClass> getReceiverClasses()
  {
    List<ReceiverClass> newReceiverClasses = Collections.unmodifiableList(receiverClasses);
    return newReceiverClasses;
  }

  public int numberOfReceiverClasses()
  {
    int number = receiverClasses.size();
    return number;
  }

  public boolean hasReceiverClasses()
  {
    boolean has = receiverClasses.size() > 0;
    return has;
  }

  public int indexOfReceiverClass(ReceiverClass aReceiverClass)
  {
    int index = receiverClasses.indexOf(aReceiverClass);
    return index;
  }
  /* Code from template association_GetMany */
  public Message getMessage(int index)
  {
    Message aMessage = messages.get(index);
    return aMessage;
  }

  public List<Message> getMessages()
  {
    List<Message> newMessages = Collections.unmodifiableList(messages);
    return newMessages;
  }

  public int numberOfMessages()
  {
    int number = messages.size();
    return number;
  }

  public boolean hasMessages()
  {
    boolean has = messages.size() > 0;
    return has;
  }

  public int indexOfMessage(Message aMessage)
  {
    int index = messages.indexOf(aMessage);
    return index;
  }
  /* Code from template association_GetMany */
  public Block getBlock(int index)
  {
    Block aBlock = blocks.get(index);
    return aBlock;
  }

  public List<Block> getBlocks()
  {
    List<Block> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(Block aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReceiverClasses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ReceiverClass addReceiverClass(String aName)
  {
    return new ReceiverClass(aName, this);
  }

  public boolean addReceiverClass(ReceiverClass aReceiverClass)
  {
    boolean wasAdded = false;
    if (receiverClasses.contains(aReceiverClass)) { return false; }
    SMSS existingSMSS = aReceiverClass.getSMSS();
    boolean isNewSMSS = existingSMSS != null && !this.equals(existingSMSS);
    if (isNewSMSS)
    {
      aReceiverClass.setSMSS(this);
    }
    else
    {
      receiverClasses.add(aReceiverClass);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReceiverClass(ReceiverClass aReceiverClass)
  {
    boolean wasRemoved = false;
    //Unable to remove aReceiverClass, as it must always have a sMSS
    if (!this.equals(aReceiverClass.getSMSS()))
    {
      receiverClasses.remove(aReceiverClass);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReceiverClassAt(ReceiverClass aReceiverClass, int index)
  {  
    boolean wasAdded = false;
    if(addReceiverClass(aReceiverClass))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReceiverClasses()) { index = numberOfReceiverClasses() - 1; }
      receiverClasses.remove(aReceiverClass);
      receiverClasses.add(index, aReceiverClass);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReceiverClassAt(ReceiverClass aReceiverClass, int index)
  {
    boolean wasAdded = false;
    if(receiverClasses.contains(aReceiverClass))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReceiverClasses()) { index = numberOfReceiverClasses() - 1; }
      receiverClasses.remove(aReceiverClass);
      receiverClasses.add(index, aReceiverClass);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReceiverClassAt(aReceiverClass, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMessages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Message addMessage(String aName)
  {
    return new Message(aName, this);
  }

  public boolean addMessage(Message aMessage)
  {
    boolean wasAdded = false;
    if (messages.contains(aMessage)) { return false; }
    SMSS existingSMSS = aMessage.getSMSS();
    boolean isNewSMSS = existingSMSS != null && !this.equals(existingSMSS);
    if (isNewSMSS)
    {
      aMessage.setSMSS(this);
    }
    else
    {
      messages.add(aMessage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMessage(Message aMessage)
  {
    boolean wasRemoved = false;
    //Unable to remove aMessage, as it must always have a sMSS
    if (!this.equals(aMessage.getSMSS()))
    {
      messages.remove(aMessage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMessageAt(Message aMessage, int index)
  {  
    boolean wasAdded = false;
    if(addMessage(aMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessages()) { index = numberOfMessages() - 1; }
      messages.remove(aMessage);
      messages.add(index, aMessage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMessageAt(Message aMessage, int index)
  {
    boolean wasAdded = false;
    if(messages.contains(aMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessages()) { index = numberOfMessages() - 1; }
      messages.remove(aMessage);
      messages.add(index, aMessage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMessageAt(aMessage, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    SMSS existingSMSS = aBlock.getSMSS();
    if (existingSMSS == null)
    {
      aBlock.setSMSS(this);
    }
    else if (!this.equals(existingSMSS))
    {
      existingSMSS.removeBlock(aBlock);
      addBlock(aBlock);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(Block aBlock)
  {
    boolean wasRemoved = false;
    if (blocks.contains(aBlock))
    {
      blocks.remove(aBlock);
      aBlock.setSMSS(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(Block aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(Block aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (receiverClasses.size() > 0)
    {
      ReceiverClass aReceiverClass = receiverClasses.get(receiverClasses.size() - 1);
      aReceiverClass.delete();
      receiverClasses.remove(aReceiverClass);
    }
    
    while (messages.size() > 0)
    {
      Message aMessage = messages.get(messages.size() - 1);
      aMessage.delete();
      messages.remove(aMessage);
    }
    
    while (blocks.size() > 0)
    {
      Block aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "methodName" + ":" + getMethodName()+ "," +
            "senderClass" + ":" + getSenderClass()+ "," +
            "senderName" + ":" + getSenderName()+ "]";
  }
}