package vertex;

import helper.HashEncoderHelper;

import java.io.StringWriter;

public class Server extends NetworkPather
{

    public Server(String label, int[]ip)
    {
        super(label, ip);
    }

    public Server(NetworkPather np)
    {
        super(np.label, np.getIPAddress());
    }

    public int[] getIPAddress()
    {
        return super.getIPAddress();
    }

    @Override
    void fillVertexInfo(String[] args)
    {
        //pass
    }

    @Override
    public String toString()
    {
        StringWriter swt = new StringWriter();
        swt.write("Server point: Name=   "+super.label+"\twith IP address=");
        int []ip = super.getIPAddress();
        swt.write(String.valueOf(ip[0]));
        swt.write(".");
        swt.write(String.valueOf(ip[1]));
        swt.write(".");
        swt.write(String.valueOf(ip[2]));
        swt.write(".");
        swt.write(String.valueOf(ip[3]));
        swt.write(".");
        return swt.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Server)
        {
            Server tmp = (Server)obj;
            int[] ip = tmp.getIPAddress();
            int[] ipo = super.getIPAddress();
            boolean judge = ip[0] == ipo[0] && ip[1] == ipo[1] && ip[2] == ipo[2] && ip[3] == ipo[3];
            return tmp.getLabel().equals(super.label) && judge;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return (new HashEncoderHelper()).hash(super.label+super.getIPAddress()[0]+super.getIPAddress()[1]+super.getIPAddress()[2]+super.getIPAddress()[3]+"Server");
    }
}
