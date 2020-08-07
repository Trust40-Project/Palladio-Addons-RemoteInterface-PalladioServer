package edu.kit.palladio.proto.rest.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kit.palladio.proto.filemanagement.File;
import edu.kit.palladio.proto.filemanagement.FileChunck;
import edu.kit.palladio.proto.filemanagement.RemoteFileUploadGrpc;
import edu.kit.palladio.proto.filemanagement.RemoteFileUploadGrpc.RemoteFileUploadBlockingStub;
import edu.kit.palladio.proto.rest.ChannelFactory;


public class GrpcFileInputStream extends InputStream {

    private final Iterator<FileChunck> fileContent;
    private InputStream currentChunk = null;
    

    public GrpcFileInputStream(final RemoteFileUploadBlockingStub blockingStub,final String path){
        final File fileToRead = File.newBuilder().setIsDirectory(false).setPath(path).build();
        fileContent = blockingStub.read(fileToRead);
    }
    @Override
    public int read() throws IOException {      
        while((currentChunk == null || currentChunk.available() == 0) && fileContent.hasNext()){
            if(currentChunk != null){
                currentChunk.close();
            }
            FileChunck currentFileChunck = fileContent.next();
            currentChunk = currentFileChunck.getContent().newInput();
        }
        if((currentChunk == null || currentChunk.available() == 0)){
            return -1;
        }
        return this.currentChunk.read();
    }

    @Override
    public void close() throws IOException {
        if(this.currentChunk != null){
            this.currentChunk.close();
        }
    }
    
}