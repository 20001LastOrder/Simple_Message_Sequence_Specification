/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 65 "../../../../../SMSS_model.ump"
public class Operand
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Operand Associations
  private List<Message> messages;
  private Condition condition;
  private Fragment fragment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Operand(Fragment aFragment)
  {
    messages = new ArrayList<Message>();
    boolean didAddFragment = setFragment(aFragment);
    if (!didAddFragment)
    {
      throw new RuntimeException("Unable to create operand due to fragment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_GetOne */
  public Condition getCondition()
  {
    return condition;
  }

  public boolean hasCondition()
  {
    boolean has = condition != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Fragment getFragment()
  {
    return fragment;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfMessagesValid()
  {
    boolean isValid = numberOfMessages() >= minimumNumberOfMessages();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMessages()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Message addMessage(Operation aOperation, String aName, Sender aSender, Receiver aReceiver)
  {
    Message aNewMessage = new Message(aOperation, aName, aSender, aReceiver, this);
    return aNewMessage;
  }

  public boolean addMessage(Message aMessage)
  {
    boolean wasAdded = false;
    if (messages.contains(aMessage)) { return false; }
    Operand existingOperand = aMessage.getOperand();
    boolean isNewOperand = existingOperand != null && !this.equals(existingOperand);

    if (isNewOperand && existingOperand.numberOfMessages() <= minimumNumberOfMessages())
    {
      return wasAdded;
    }
    if (isNewOperand)
    {
      aMessage.setOperand(this);
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
    //Unable to remove aMessage, as it must always have a operand
    if (this.equals(aMessage.getOperand()))
    {
      return wasRemoved;
    }

    //operand already at minimum (1)
    if (numberOfMessages() <= minimumNumberOfMessages())
    {
      return wasRemoved;
    }

    messages.remove(aMessage);
    wasRemoved = true;
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setCondition(Condition aNewCondition)
  {
    boolean wasSet = false;
    if (condition != null && !condition.equals(aNewCondition) && equals(condition.getOperand()))
    {
      //Unable to setCondition, as existing condition would become an orphan
      return wasSet;
    }

    condition = aNewCondition;
    Operand anOldOperand = aNewCondition != null ? aNewCondition.getOperand() : null;

    if (!this.equals(anOldOperand))
    {
      if (anOldOperand != null)
      {
        anOldOperand.condition = null;
      }
      if (condition != null)
      {
        condition.setOperand(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setFragment(Fragment aFragment)
  {
    boolean wasSet = false;
    //Must provide fragment to operand
    if (aFragment == null)
    {
      return wasSet;
    }

    if (fragment != null && fragment.numberOfOperands() <= Fragment.minimumNumberOfOperands())
    {
      return wasSet;
    }

    Fragment existingFragment = fragment;
    fragment = aFragment;
    if (existingFragment != null && !existingFragment.equals(aFragment))
    {
      boolean didRemove = existingFragment.removeOperand(this);
      if (!didRemove)
      {
        fragment = existingFragment;
        return wasSet;
      }
    }
    fragment.addOperand(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=messages.size(); i > 0; i--)
    {
      Message aMessage = messages.get(i - 1);
      aMessage.delete();
    }
    Condition existingCondition = condition;
    condition = null;
    if (existingCondition != null)
    {
      existingCondition.delete();
      existingCondition.setOperand(null);
    }
    Fragment placeholderFragment = fragment;
    this.fragment = null;
    if(placeholderFragment != null)
    {
      placeholderFragment.removeOperand(this);
    }
  }

}