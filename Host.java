public class Host {

    

    //Stores the host's name
    private String hostName;

    //Store's the system Mac Address
    private String macAddress;

    //Interface status
    private int interfaces = Link.NOT_CONNECTED;       
    
    //Connected to link
    Link connectionLink = null;

    //Received Packet
    private Packet receivedPacket = null;

    //*****************************************Constructors********************************* */    
    public Host(String hostName)
    {
        this.hostName = hostName;
        this.macAddress = DeviceManager.getMAC_ADDRESS();
    }
   //***************************************************************************************/  
   
   //****************************************Getters************************************* */

   //return's macAdrress
   public String getMacAddress()
   {
       return this.macAddress;
   }

   //Returns host's name
   public String getHostName()
   {
       return this.hostName;
   }

   //***************************************************************************************/

   //Add Connection Link Function
   public boolean addConnection(Link link) throws CannotAddConnectionLinkException
   {
       
           if(interfaces == Link.NOT_CONNECTED)
           {
               interfaces = Link.CONNECTED;
               connectionLink = link;
               link.addHostConnection(this);
               System.out.println("Host "+ this.hostName+"  "+this.getMacAddress()+" is connected to Link "+ link.getID());
               return true;
           }
           else
            throw new CannotAddConnectionLinkException("Cannot add Connection Link");
      

   }
   public boolean linkStatus(Link link) 
   {
       if(interfaces == Link.CONNECTED)
       {
           if(connectionLink == link)
           {
               return true;
           }
           else return false;
           
       }
       return false;
       
   }




   public boolean sendData(Link link, String destinationMacAddress, String message) throws LinkNotConnectedException
   {
       if(linkStatus(link))
       {
           try
           {
               link.putDataonLink(new Packet(destinationMacAddress, this.getMacAddress(), message));
               if(this.receivedPacket != null && this.receivedPacket.getSourceMac().equals(this.getMacAddress()))
               {
                   this.receivedPacket = null;
               }
               
           }catch(LinkBusyException err)
           {
               System.out.println(err);
           }
           
        
        return true;
       }
       else throw new LinkNotConnectedException("Link: "+link.getLinkName() +" is not connected!");
   }

public void setReceivedPacket(Packet packet) {
    this.receivedPacket = packet;

}


public void senseLink()
{
    if(receivedPacket != null)
    {
       if(receivedPacket.getDestinationMac().equals(this.getMacAddress()))
       { 
           System.out.printf(" Received from %s %n Received By %s %n Received Message: %s",receivedPacket.getSourceMac(),receivedPacket.getDestinationMac(), receivedPacket.getMessage());
           if(!receivedPacket.getMessage().equals("ACK"))
          { try{
                sendData(this.connectionLink, receivedPacket.getSourceMac(), "ACK");
              }catch(LinkNotConnectedException err)
              {
                 System.out.println(err);
              }

            }

        receivedPacket = null;}

        else if(receivedPacket.getDestinationMac().equals("ff:ff:ff:ff:ff:ff"))
        {
            try{
                System.out.println("Host "+this.getHostName());
                sendData(this.connectionLink, receivedPacket.getSourceMac(), "ACK");
              }catch(LinkNotConnectedException err)
              {
                 System.out.println(err);
              }
        }

        
        else
        {
            System.out.println("HostName "+this.getHostName()+": Packet Rejected");
            this.receivedPacket = null;
        }



    }
}



}




class CannotAddConnectionLinkException extends Exception {

    public CannotAddConnectionLinkException(String message)
    {
        super(message);
    }
}

class LinkNotConnectedException extends Exception {

    public LinkNotConnectedException(String message)
    {
        super(message);

    }
}