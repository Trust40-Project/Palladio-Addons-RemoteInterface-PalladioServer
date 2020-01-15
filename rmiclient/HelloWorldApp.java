import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.kit.palladio.rcp.IHelloWorldFromEclipse;
import edu.kit.palladio.rmi.projectmanagment.IProject;
import edu.kit.palladio.rmi.projectmanagment.IProjectManager;

public class HelloWorldApp{

    public static void main(String args[]){
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 10099);
            for (String string : registry.list()) {
                System.out.println(string);
            }

            IProjectManager projectManager = (IProjectManager) registry.lookup(IProjectManager.class.getName());
            IProject testProject = projectManager.createProject("testproject");
            for (IProject project : projectManager.getProjects()) {
                System.out.println(project.getProjectId());
            }
            
            projectManager.deleteProject(testProject);
            for (IProject project : projectManager.getProjects()) {
                System.out.println(project.getProjectId());
            }
            
            IHelloWorldFromEclipse helloWorld = (IHelloWorldFromEclipse) registry.lookup(IHelloWorldFromEclipse.class.getName());
            String result = helloWorld.hello("Test from client without spring boot");
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}