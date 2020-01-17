

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.kit.palladio.rcp.IHelloWorldFromEclipse;
import edu.kit.palladio.rmi.filemanagment.Directory;
import edu.kit.palladio.rmi.filemanagment.File;
import edu.kit.palladio.rmi.filemanagment.IFileNode;
import edu.kit.palladio.rmi.filemanagment.IRemoteFileUpload;
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

            // create some files inside the new test project.
            //Charset charset = StandardCharsets.UTF_8;
            IFileNode projectRoot = new Directory(testProject.getProjectId());
            projectRoot.addChild((IFileNode)new Directory("empty"));
            projectRoot.addChild((IFileNode)new File("helloworld", "Hello world, this is the first file inside the root of the test project.", "UTF-8"));
            Directory notEmpty = new Directory("notempty");
            notEmpty.addChild((IFileNode)new File("somefile", "This is content in a file encoded in UTF-8", "UTF-8"));
            notEmpty.addChild((IFileNode)new File("anotherfile", "This is content in a file encoded in UTF-8", "UTF-8"));
            projectRoot.addChild((IFileNode)notEmpty);

            IRemoteFileUpload remoteFileUpload = (IRemoteFileUpload) registry.lookup(IRemoteFileUpload.class.getName());
            System.out.println("create file successfully? " + remoteFileUpload.createFiles(projectRoot));

            /*
            projectManager.deleteProject(testProject);
            for (IProject project : projectManager.getProjects()) {
                System.out.println(project.getProjectId());
            }*/
            
            IHelloWorldFromEclipse helloWorld = (IHelloWorldFromEclipse) registry.lookup(IHelloWorldFromEclipse.class.getName());
            String result = helloWorld.hello("Test from client without spring boot");
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}