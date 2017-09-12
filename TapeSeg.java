public class TapeSeg<E>
{
  private E value;
  private TapeSeg<E> next;
  private TapeSeg<E> prev;
  
  public TapeSeg(E value, TapeSeg<E> next, TapeSeg<E> prev)
  {
    this.value = value;
    this.next = next;
    this.prev = prev;
  }
  
  public E getValue()
  {
    return value;
  }
  
  public TapeSeg<E> getNext()
  {
    return next;
  }
  
  public TapeSeg<E> getPrev()
  {
    return prev;
  }
   
  public void setValue(E value)
  {
    this.value = value;
  }
  
  public void setNext(TapeSeg<E> next)
  {
    this.next = next;
  }
  
  public void setPrev(TapeSeg<E> prev)
  {
    this.prev = prev;
  }
}
