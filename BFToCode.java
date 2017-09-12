import java.util.*;

public class BFToCode
{
  private TapeSeg<Integer> tape;
  private ArrayList<Integer> output;
  private boolean failed;
  
  public BFToCode()
  {
    failed = false;
    tape = new TapeSeg<Integer>(0, null, null);
    output = new ArrayList<Integer>();
  }
  
  public void translate(String s)
  {
    try
    {
      while (s.length() != 0)
      {
        String command = s.substring(0,1);
        s = s.substring(1);
        if (command.equals("+"))
        {
          tape.setValue(tape.getValue() + 1);
        }
        else if (command.equals("-"))
        {
          if (tape.getValue() > 0)
            tape.setValue(tape.getValue() - 1);
        }
        else if(command.equals("."))
        {
          //System.out.println(tape.get(pointer));
          output.add(tape.getValue());
        }
        else if(command.equals(","))
        {
          //prompt for input
        }
        else if (command.equals(">"))
        {
          if (tape.getNext() == null)
          {
            tape.setNext(new TapeSeg<Integer>(0, null, tape));
            tape = tape.getNext();
          }
          else
          {
            tape = tape.getNext();
          }
        }
        else if (command.equals("<"))
        {
          if (tape.getPrev() == null)
          {
            tape.setPrev(new TapeSeg<Integer>(0, tape, null));
            tape = tape.getPrev();
          }
          else
          {
            tape = tape.getPrev();
          }
        }
        else if (command.equals("["))
        {
          int step = 0;
          String toTranslate = "";
          while (!command.equals("]"))
          {
            command = s.substring(0,1);
            toTranslate += command;
            s = s.substring(1);
          }
          toTranslate = toTranslate.substring(0, toTranslate.length() - 1);
          while (tape.getValue() != 0 && !failed)
          {
            translate(toTranslate);
            if (step > 1000)
            {
              failed = true;
            }
            step++;
          }
        }
      }
    }
    //let it test its outputs even if invalid
    catch (Exception e)
    {}
  }
  
  public boolean failed()
  {
    return failed;
  }
  
  public ArrayList<Integer> getOutputs()
  {
    return output;
  }
}