import java.util.ArrayList;
import java.util.List;

public class Switch {

    private String switchName;

    private static int count = 0;

    private int ID;

    List<Interface> interfaces = new ArrayList<Interface>(4);

    List<String> mac_Address_Key = new ArrayList<String>(20);

    List<String> mac_Address_Value = new ArrayList<String>(20);



    public Switch(String switchName)
    {
        this.switchName = switchName;
        this.ID = count++;
        for(int i = 0; i< 4; i++)
        {try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            interfaces.add(new Interface());
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }


    }
    
    

    void learnDevices()
    {
        for(int i =0 ; i < interfaces.size(); i++)
        {
            if(interfaces.get(i).active == Link.CONNECTED)
            {
                interfaces.get(i).sendData(new Packet("ff:ff:ff:ff:ff:ff", interfaces.get(i).getMacAddress(), "ICMP"));
            }
        }
    }
    

    boolean addConnectionLink(Link link)
    {
        for(int i = 0; i < 4; i++)
        {
            if(interfaces.get(i).active == Link.NOT_CONNECTED)
            {
                try{
                interfaces.get(i).addConnection(link);
                interfaces.get(i).active = Link.CONNECTED;
                return true;
                }
                catch(CannotAddConnectionLinkException err)
                {
                    System.out.println(err);
                }
                
            }
        }
        return false;
    }

    public Interface getInterface(String mac_Address) throws LinkNotConnectedException
    {
        for(int i =0; i < interfaces.size();i++)
        {
            if(interfaces.get(i).getMacAddress().equals(mac_Address))
            {
                return interfaces.get(i);
            }
        } throw new LinkNotConnectedException("Link Not Connected");
    }

    public void senseLink()
    {
        for(int i = 0; i < 4; i++)
        {
            if(interfaces.get(i).active == Link.CONNECTED)
            {
                if(interfaces.get(i).getReceivedPacket() != null)
                {
                  String key =  interfaces.get(i).getReceivedPacket().getDestinationMac();
                  String value;

                  if(interfaces.get(i).getReceivedPacket().getDestinationMac() == interfaces.get(i).getMacAddress())
                  {
                      mac_Address_Key.add(interfaces.get(i).getReceivedPacket().getSourceMac());
                      mac_Address_Value.add(interfaces.get(i).getMacAddress());
                  }
                  else
                  {
                    for(int j = 0; j < mac_Address_Key.size(); j++)
                    {
                        if(key.equals(mac_Address_Key.get(j)))
                        {
                            value = mac_Address_Value.get(j);
                           try{
                                getInterface(value).sendData(interfaces.get(i).getReceivedPacket());
                           }catch(LinkNotConnectedException err)
                           {
                               System.out.println(err);
                           }
                        }
                        else
                        {
                            for(int k =0; k < interfaces.size(); k++)
                            {
                                if(k != j)
                                {
                                    if(interfaces.get(k).active == Link.CONNECTED)
                                    {
                                        interfaces.get(k).sendData(interfaces.get(i).getReceivedPacket());
                                    }
                                }

                            }
                        }
                    }
                  }
                  

                }
                
            }
        }

    }






    class Interface
    {
        private int active = Link.NOT_CONNECTED;
        private String macAddress;
        private Link connectedLink = null;
        private Packet receivedPacket = null;
        public Interface()
        {
            this.macAddress = DeviceManager.getMAC_ADDRESS();
        }

        public int getInterfaceStatus()
        {
            return this.active;
        }
        public String getMacAddress()
        {
            return this.macAddress;
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
       
           if(this.active == Link.NOT_CONNECTED)
           {
               this.active = Link.CONNECTED;
               this.connectedLink = link;
               link.addSwitchConnection(this);
               System.out.println("Switch " + this.getMacAddress()+" is connected to Link "+ link.getID());
               return true;
           }
           else
            throw new CannotAddConnectionLinkException("Cannot add Connection Link");
      

       }

       public void sendData(Packet packet)
       {
           try{
           connectedLink.putDataonLink(packet);
           }catch(LinkBusyException err)
           {
               System.out.println(err);
           }
       }
    }









    
}
