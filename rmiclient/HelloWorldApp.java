import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.kit.palladio.rcp.IHelloWorldFromEclipse;

public class HelloWorldApp{

    public static void main(String args[]){
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 10099);
            for (String string : registry.list()) {
                System.out.println(string);
            }
            
            IHelloWorldFromEclipse helloWorld = (IHelloWorldFromEclipse) registry.lookup(IHelloWorldFromEclipse.class.getName());
            String result = helloWorld.hello("Test from client without spring boot");
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}