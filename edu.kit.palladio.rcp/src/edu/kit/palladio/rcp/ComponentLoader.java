package edu.kit.palladio.rcp;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import edu.kit.palladio.rcpapi.ILoadMe;

@Component(immediate=true)
public class ComponentLoader implements IComponentLoader {

	private final List<ILoadMe> availableComponents = new LinkedList<ILoadMe>();
	
    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindComponent(ILoadMe component, Map<String, String> serviceProperties) {

        availableComponents.add(component);
    }



    public void unbindComponent(ILoadMe component) {
    	this.availableComponents.remove(component);

    }



    public void updatedComponent(ILoadMe component, Map<String, String> serviceProperties) {

        unbindComponent(component);

        bindComponent(component, serviceProperties);

    }



    public Collection<ILoadMe> getComponents() {

        return Collections.unmodifiableList(availableComponents);

    }
}
