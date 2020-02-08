package edu.kit.palladio.rcp;

import edu.kit.palladio.rcp.api.ILoadMe;

public interface IComponentInformation {
	ILoadMe getComponent();
	String getId();
}
