import java.net.NetworkInterface;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MacAddress {

    public static void main(String[] args) {



        getMacAddresses();
    }

    public static List<String> getMacAddresses() {
        List<String> macAddressesList = new ArrayList<String>();
        try {
            macAddressesList = AccessController.doPrivileged(new PrivilegedExceptionAction<List<String>>() {
                public List<String> run() throws Exception {
                    List<String> macAddressesList = new ArrayList<String>();
                    Enumeration<NetworkInterface> nwInterface = NetworkInterface.getNetworkInterfaces();
                    while(nwInterface.hasMoreElements()) {
                        NetworkInterface nis = nwInterface.nextElement();
                        if(nis == null) {
                            continue;
                        }
                        byte[] mac = nis.getHardwareAddress();
                        if(mac == null) {
                            continue;
                        }
                        StringBuffer sb = new StringBuffer();
                        for(int index = 0; index < mac.length; index++) {
                            sb.append(String.format("%02X%s", mac[index], (index < mac.length - 1) ? "-" : ""));
                        }
                        macAddressesList.add(sb.toString());
                    }
                    return macAddressesList;
                }
            });
        } catch(Exception ex) {
            throw new RuntimeException("Could not get MAC addresses", ex);
        }
        return macAddressesList;
    }
}
