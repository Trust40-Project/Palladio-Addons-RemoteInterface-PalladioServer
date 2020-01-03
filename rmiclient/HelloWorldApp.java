import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloWorldApp{

    public static void main(String args[]){
        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "HelloEclipse";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            for (String string : registry.list()) {
                System.out.println(string);
            }
            
            IHelloWorldFromEclipse helloWorld = (IHelloWorldFromEclipse) registry.lookup(name);
            String result = helloWorld.hello("Test from client without spring boot");
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}