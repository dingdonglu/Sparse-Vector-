
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class DoublyLinkedList<AnyType> implements List<AnyType>
{
  private static class Node<AnyType>
  {
    private AnyType data;
    private Node<AnyType> prev;
    private Node<AnyType> next;

    public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) //Node class
    {
      setData(d);
      setPrev(p);
      setNext(n);
    }

    public AnyType getData() { return data; }

    public void setData(AnyType d) { data = d; }

    public Node<AnyType> getPrev() { return prev; }

    public void setPrev(Node<AnyType> p) { prev = p; }

    public Node<AnyType> getNext() { return next; }

    public void setNext(Node<AnyType> n) { next = n; }
  }

  private int theSize ;
  private int modCount;
  private Node<AnyType> header;
  private Node<AnyType> trailer;

  public DoublyLinkedList()				//DoublyLinkedList
  {
    header = new Node<AnyType>(null, null, null);
    trailer = new Node<AnyType>(null, null, null);
    modCount = 0;
    clear();
  }

  
  public void clear()
  {
    header.setNext(trailer);
    trailer.setPrev(header);
    theSize = 0;
  }

  public int size()
  {
    return theSize;
  }

  public boolean isEmpty()
  {
    return (size() == 0);
  }

  public AnyType get(int index)
  {
	  
    Node<AnyType> indexNode = getNode(index);		//get node at index
    return indexNode.getData();						//and return the data of it
    
  }

  public AnyType set(int index, AnyType newValue)
  {
	  
    Node<AnyType> indexNode = getNode(index);		//get node at index
    AnyType oldValue = indexNode.getData();			//store old value

    indexNode.setData(newValue);					//set it to new value
    return oldValue;								//and return the old value 	
    	
  }

  public boolean add(AnyType newValue)				//adding to back 
  {
    add(size(), newValue);					
    return true;
  }

  public void add(int index, AnyType newValue)		
  {
    addBefore(getNode(index, 0, size()), newValue);	
  }

  public AnyType remove(int index)					//remove the node at the index using getNode
  {
    return remove(getNode(index));					//and return the value of it.  
  }

  public Iterator<AnyType> iterator()
  {
    return new LinkedListIterator();    
  }

  private Node<AnyType> getNode(int index)
  {
    return (getNode(index, 0, size()-1));			
  }

  private Node<AnyType> getNode(int index, int lower, int upper)
  {
    Node<AnyType> currNode;
    //Creates a node called currNode
    if (index < lower || index > upper)	
      throw new IndexOutOfBoundsException();
    //Determines if it's within bounds
    int n = size();
    if (index < n/2)
    {
      currNode = header.getNext();		//if the index is within front half, use header to get the node
      for (int i = 0; i < index; i++) currNode = currNode.getNext();
    }
    else								//if the index is within the back half, use trailer to get the node
    {
      currNode = trailer;
      for (int i = n; i > index; i--) currNode = currNode.getPrev();
    }

    return currNode;
  }

  
 
  private void addBefore(Node<AnyType> nextNode, AnyType newValue)
  {
	  
    Node<AnyType> prevNode = nextNode.getPrev();	//find the previous node
    Node<AnyType> newNode = new Node<>(newValue, prevNode, nextNode);	//create the new node containing newValue
    
    prevNode.setNext(newNode);			//set the previous node's next to newNode
    nextNode.setPrev(newNode);			//and nextNode's previous to newNode, which will put the new Node right in the middle
    modCount++;							//increment modification count
    theSize++;							//increment size
  }

  private AnyType remove(Node<AnyType> currNode)
  {
    Node<AnyType> prevNode = currNode.getPrev();	//get the previous node
    Node<AnyType> nextNode = currNode.getNext();	//get the next node

    prevNode.setNext(nextNode);						//set the previous node's next to nextNode
    nextNode.setPrev(prevNode);						//set nextNode's previous to prevNode
    theSize--;										//and we basically unlink currNode
    modCount++;

    return currNode.getData();
  }

 
  private class LinkedListIterator implements Iterator<AnyType>
  {
    private Node<AnyType> current;
    private int expectedModCount;
    private boolean okToRemove;

    LinkedListIterator()		//LinkedListIterator
    {
      current = header.getNext();
      expectedModCount = modCount;
      okToRemove = false;
    }

    public boolean hasNext()
    {
      return (current != trailer);
    }

    public AnyType next()
    {
      if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
      if (!hasNext())
        throw new NoSuchElementException();

      AnyType nextValue = current.getData();
      current = current.getNext();
      okToRemove = true;
      return nextValue;
    }

    public void remove()
    {
      if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
      if (!okToRemove)
        throw new IllegalStateException();

      DoublyLinkedList.this.remove(current.getPrev());
      expectedModCount++;
      okToRemove = false;
    }
  }
}
