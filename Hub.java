import java.util.*;

public class Hub {

    //Counter ID
    private static int count = 0;

    //Contains name of the hub
    private String hubName;

    //Detemines the unique hub
    private int hubID;

    //Interfaces names
    int[] interfaces;

    //Interfaces connections names
    List<Link> connectionLink;

    private Packet receivedPacket = null;

//*****************************************Constructors********************************* */    
    public Hub(String hubName)
    {
        this.hubName = hubName;
        this.hubID = count++;
        interfaces = new int[]{0,0,0,0,0,0,0,0,0,0};
        connectionLink = new ArrayList<Link>();
    }
//************************************************************************************** */  

//***************************************Getters+++++++++++++++++++++++++++++++++++++++ */
   
    //Returns Hub Name
    public String getHubName()
    {
        return this.hubName;
    }

    //Returns Hub ID
    public int getHubID()
    {
        return this.hubID;
    }

    public void setReceivedPacket(Packet packet)
    {
        this.receivedPacket = packet;
    }

    public Packet getReceivedPacket()
    {
        return this.receivedPacket;
    }




    //Add Connection Link Function
   public boolean addConnection(Link link) throws CannotAddConnectionLinkException
   {
       for(int i = 0; i < interfaces.length; i++)
        {   if(interfaces[i] == Link.NOT_CONNECTED)
           {
               interfaces[i] = Link.CONNECTED;
               connectionLink.add(link);
               link.addHubConnection(this);
               System.out.println("Hub "+ this.hubName +" is connected to Link "+ link.getID());
               return true;
           }
           
        }
        
            throw new CannotAddConnectionLinkException("Cannot add Connection Link");

   }

   public void senseLink()
   {
       if(this.getReceivedPacket() != null)
       {
           for(int i = 0; i < interfaces.length; i++)
           {
               if(interfaces[i] == Link.CONNECTED)
               {
                   try{
                   connectionLink.get(i).putDataonLink(this.getReceivedPacket());
                   }catch(LinkBusyException err)
                   {
                       System.out.println(err);
                   }
               }
           }
       }
   }

}