package edu.kit.palladio.rcp;

import edu.kit.palladio.rcpapi.ILoadMe;

public interface IComponentInformation {
	ILoadMe getComponent();
	String getId();
}
