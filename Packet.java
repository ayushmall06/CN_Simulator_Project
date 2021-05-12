public class Packet {

    String destinationMac ;
    String sourceMac;
    String message;
    public Packet(String destinationMac,String sourceMac, String message)
    {
        this.destinationMac = destinationMac;
        this.sourceMac = sourceMac;
        this.message = message;
    }
    String getDestinationMac()
    {
        return destinationMac;
    }
    String getSourceMac()
    {
        return sourceMac;
    }
    String getMessage()
    {
        return message;
    }
}
