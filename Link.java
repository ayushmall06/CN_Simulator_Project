import java.util.*;

public class Link {
    
    public static final int CONNECTED = 1;
    public static final int NOT_CONNECTED = 0;


    static int count = 0;

    //Link ID
    private int ID;

    //Link Name
    private String linkName;

    //Buffer packet
    private Packet bufferPacket = null;

    //List of host's connected to this Link
    List<Host> hostList = new ArrayList<Host>();

    //List of hub's connected to this Link
    List<Hub> hubList = new ArrayList<Hub>();

    //List of switch's connected to this Link
    List<Switch.Interface> switchList = new ArrayList<Switch.Interface>();


    //*******************************************Constructors***************************8 */
    public Link(String linkName)
    {
        this.linkName = linkName;
        this.ID = count++;
    }

    //************************************************************************************ */

    public String getLinkName()
    {
        return this.linkName;
    }
    public int getID()
    {
        return this.ID;
    }


    //add Host Connection
    public void addHostConnection(Host host)
    {
        hostList.add(host);
    }

     //add Hub Connection
     public void addHubConnection(Hub hub)
     {
         hubList.add(hub);
     }

     public void addSwitchConnection(Switch.Interface switchInterface)
     {
         switchList.add(switchInterface);
     }

     //get Packet On Link
     public Packet getPacket()
     {
         return this.bufferPacket;
     }


     public boolean putDataonLink(Packet packet) throws LinkBusyException
     {
         if(this.bufferPacket == null)
         {
             this.bufferPacket = packet;
             for(int i = 0; i < hostList.size(); i++)
             {
                 hostList.get(i).setReceivedPacket(packet);
             }
             for(int i = 0; i < hubList.size(); i++)
             {
                 hubList.get(i).setReceivedPacket(packet);
             }
             for(int i = 0; i < switchList.size(); i++)
             {
                 switchList.get(i).setReceivedPacket(packet);
             }
             this.bufferPacket = null;

             return true;


         }
         else
         {
            for(int i = 0; i < hostList.size(); i++)
            {
                hostList.get(i).setReceivedPacket(null);
            }
            for(int i = 0; i < hubList.size(); i++)
            {
                hubList.get(i).setReceivedPacket(null);
            }
            this.bufferPacket = null;
            throw new LinkBusyException("Collision occured at "+ this.getLinkName());

         }
     }

}



class LinkBusyException extends Exception
{
    public LinkBusyException(String message)
    {
        super(message);
    }
}
