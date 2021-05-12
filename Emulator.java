public class Emulator {

   public static void main(String[] args) {
   /* Host host1 = new Host("A");
    Link l1 = new Link("L1");
    try
    {
        Thread.sleep(3000);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
    Host host2 = new Host("B");
    try{
        host1.addConnection(l1);
        host2.addConnection(l1);
        
        
    }catch(CannotAddConnectionLinkException err)
    {
        System.out.println(err);
    }

    Thread t1 = new Thread(){
        @Override
        public void run() {
            try{
            host1.sendData(l1, host2.getMacAddress(), "Hi I am host 1");
            }catch(LinkNotConnectedException err)
            {
                System.out.println(err);
            }
            while(true){
               
                host1.senseLink();
            }
            
        };
    };
    Thread t2 = new Thread(){
        @Override
        public void run() {
           try{
            host2.sendData(l1, host1.getMacAddress(), "Hi I am host 2");
            }catch(LinkNotConnectedException err)
            {
                System.out.println(err);
            }
            while(true){
                
                host2.senseLink();
            }
            
        };
    };
    t2.start();
    t1.start();
    */


    //******************************************************************************* */

    /*
    Host h1 = new Host("A");
    try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
    Host h2 = new Host("B");
    try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
    Host h3 = new Host("C");
    try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
    Host h4 = new Host("D");
    Link l1 = new Link("l1");
    Link l2 = new Link("l2");
    Link l3 = new Link("l3");
    Link l4 = new Link("l4");
    Link l5 = new Link("l5");

    Hub hub1 = new Hub("h1");
    Hub hub2 = new Hub("h2");

    try{
    h1.addConnection(l1);
    h2.addConnection(l2);
    hub1.addConnection(l1);
    hub1.addConnection(l2);
    hub1.addConnection(l3);
    hub2.addConnection(l3);
    hub2.addConnection(l4);
    hub2.addConnection(l5);
    h3.addConnection(l4);
    h4.addConnection(l5);
    }catch(CannotAddConnectionLinkException err)
    {
        System.out.println(err);
    }

    try{
    h1.sendData(l1, h3.getMacAddress(), "Hi I am host 1");
    }catch(LinkNotConnectedException err)
    {
        System.out.println(err);
    }
    hub1.senseLink();
    h2.senseLink();
    hub2.senseLink();
    h3.senseLink();
    h4.senseLink();
    */



    Host h1 = new Host("A");
    try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
    Host h2 = new Host("B");
    try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
    Host h3 = new Host("C");
    try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
    Host h4 = new Host("D");

    Switch s1 = new Switch("S1");
    Hub hub1 = new Hub("h1");
    Hub hub2 = new Hub("h2");

    Link l1 = new Link("l1");
    Link l2 = new Link("l2");
    Link l3 = new Link("l3");
    Link l4 = new Link("l4");
    Link l5 = new Link("l5");
    Link l6 = new Link("l6");

    try{
        h1.addConnection(l1);
        h2.addConnection(l2);
        hub1.addConnection(l1);
        hub1.addConnection(l2);
        hub1.addConnection(l3);
        s1.interfaces.get(0).addConnection(l3);
        s1.interfaces.get(1).addConnection(l4);
        hub2.addConnection(l4);
        hub2.addConnection(l5);
        hub2.addConnection(l6);
        h3.addConnection(l5);
        h4.addConnection(l6);
        }catch(CannotAddConnectionLinkException err)
        {
            System.out.println(err);
        }

      /*  s1.learnDevices();
        hub1.senseLink();
        h1.senseLink();
        hub1.senseLink();
        s1.senseLink();
        h2.senseLink();
        hub1.senseLink();
        s1.senseLink();
*/
        try{
        h1.sendData(l1, h4.getMacAddress(), "Hi I am host 1");
        }catch(LinkNotConnectedException err)
        {
            System.out.println(err);
        }
        hub1.senseLink();
        s1.senseLink();
        hub2.senseLink();
        h4.senseLink();

        for(int i = 0 ; i < s1.mac_Address_Key.size(); i++)
        {
            System.out.println(s1.mac_Address_Key.get(i)+ "   "+s1.mac_Address_Value.get(i));
        }
        




    

   }
   


    
}
