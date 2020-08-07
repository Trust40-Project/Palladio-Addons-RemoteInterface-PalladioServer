package edu.kit.palladio.proto.filemanagement;

import java.util.Collection;
import edu.kit.palladio.proto.filemanagement.FileChunck;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.protobuf.ByteString;

import edu.kit.palladio.proto.filemanagement.RemoteFileUploadGrpc.RemoteFileUploadImplBase;
import edu.kit.palladio.remote.filemanagement.AppendOutputStream;
import edu.kit.palladio.remote.filemanagement.EclipseFileInputStream;
import edu.kit.palladio.remote.filemanagement.EclipseFileOutputStream;
import edu.kit.palladio.remote.filemanagement.FileManager;
import edu.kit.palladio.remote.filemanagement.IFileManager;
import io.grpc.stub.StreamObserver;


public class RemoteFileUploadImpl extends RemoteFileUploadImplBase {
	
	private static final int BUFFERSIZE = 4096;
	private IFileManager fileManager;
	
	public RemoteFileUploadImpl(){
		fileManager = new FileManager();
	}
    

    /**
     */
    public io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.FileChunck> append(
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.WriteResponse> responseObserver) {
    	return new StreamObserver<FileChunck>() {
    		String currentPath = null;
    		AppendOutputStream outStream = null;
    		
    	    @Override
    	    public void onNext(FileChunck fileChunck) {
    	    	final String pathFromRequest = fileChunck.getPath();
    	    	
    	    	if(!pathFromRequest.equals(currentPath)) {
    	    		outStream = new EclipseFileOutputStream(pathFromRequest, true);
    	    		currentPath = pathFromRequest;
    	    	
    	    	} 
    	    	ByteString content = fileChunck.getContent();
    	    	if(!content.isEmpty()) {
    	    		try {
						outStream.append(content.toByteArray());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	    	}	
    	    }

    	    @Override
    	    public void onError(Throwable t) {
    	    	System.out.print("Encountered error in append " + t.getMessage());
    	    }

    	    @Override
    	    public void onCompleted() {
    	      responseObserver.onNext(WriteResponse.newBuilder().build());
    	      responseObserver.onCompleted();
    	    }
    	  };
    }

    /**
     */
    public void read(edu.kit.palladio.proto.filemanagement.File request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.FileChunck> responseObserver) {
    	final String path = request.getPath();
    	try(final InputStream streamOfFile = new EclipseFileInputStream(path)){
    		final byte[] dataBuffer = new byte[BUFFERSIZE];
    		int bytesRead = streamOfFile.read(dataBuffer);
    		while(bytesRead != -1) {
    			ByteString bytesOfFile = ByteString.copyFrom(dataBuffer, 0, bytesRead);
    			FileChunck chunck = FileChunck.newBuilder().setPath(path).setContent(bytesOfFile).build();
    			responseObserver.onNext(chunck);
    			bytesRead = streamOfFile.read(dataBuffer);
    		}
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	responseObserver.onCompleted();
    	
    }

    /**
     */
    public void listFilesAndDirectories(edu.kit.palladio.proto.filemanagement.FileTreeRequest request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.File> responseObserver) {
    	assert(request.getStartingDir().getIsDirectory());
    	final String path = request.getStartingDir().getPath();
    	Collection<edu.kit.palladio.remote.filemanagement.File> filesAndDirectories = this.fileManager.listFilesAndDirectories(path);
    	for(edu.kit.palladio.remote.filemanagement.File file: filesAndDirectories) {
    		File protoFile = File.newBuilder().setPath(file.getPath()).setIsDirectory(file.isDirectory()).build();
    		responseObserver.onNext(protoFile);
    	}
    	responseObserver.onCompleted();
    	
    }
    
    /**
     */
    @Override
    public void create(edu.kit.palladio.proto.filemanagement.File request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.CreateFileResponse> responseObserver) {
    	final String path = request.getPath();
    	if(request.getIsDirectory()) {
    		fileManager.createDirectory(path);
    	} else {
    		fileManager.createFile(path);
    	}
	
    	responseObserver.onNext(CreateFileResponse.getDefaultInstance());
		responseObserver.onCompleted();
		
    }

    /**
     */
    @Override
    public void delete(edu.kit.palladio.proto.filemanagement.File request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.DeleteFileResponse> responseObserver) {
      fileManager.delete(request.getPath());
      responseObserver.onNext(DeleteFileResponse.getDefaultInstance());
		responseObserver.onCompleted();
    }
    
   
	
    
   

    /**
     */
    public io.grpc.stub.StreamObserver<FileChunck> write(
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.WriteResponse> responseObserver) {
    	return new StreamObserver<FileChunck>() {
    		String currentPath = null;
    		OutputStream outStream = null;
    		
    	    @Override
    	    public void onNext(FileChunck fileChunck) {
    	    	final String pathFromRequest = fileChunck.getPath();
    	    	
    	    	if(!pathFromRequest.equals(currentPath)) {
    	    		if(outStream != null) {
    	    			try {
							outStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    	    		}
    	    		outStream = new EclipseFileOutputStream(pathFromRequest);
    	    		currentPath = pathFromRequest;
    	    	
    	    	} 
    	    	ByteString content = fileChunck.getContent();
    	    	if(!content.isEmpty()) {
    	    		try {
						outStream.write(content.toByteArray());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	    	}	
    	    }

    	    @Override
    	    public void onError(Throwable t) {
    	    	System.out.print("Encountered error in write " + t.getMessage());
    	    }

    	    @Override
    	    public void onCompleted() {
    	      responseObserver.onNext(WriteResponse.newBuilder().build());
    	      responseObserver.onCompleted();
    	    }
    	  };
    }

}
