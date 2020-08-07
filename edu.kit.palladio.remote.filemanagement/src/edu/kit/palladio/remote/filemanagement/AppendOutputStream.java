package edu.kit.palladio.remote.filemanagement;

import java.io.IOException;

public interface AppendOutputStream {
	void append(int b) throws IOException;
	void append(byte[] b) throws IOException;
	void write(byte[] b, int off,int len)throws IOException;

}
