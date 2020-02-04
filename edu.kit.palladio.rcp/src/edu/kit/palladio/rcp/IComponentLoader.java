package edu.kit.palladio.rcp;

import java.util.Collection;

import edu.kit.palladio.rcpapi.ILoadMe;

public interface IComponentLoader {
	Collection<ILoadMe> getComponents();
}
