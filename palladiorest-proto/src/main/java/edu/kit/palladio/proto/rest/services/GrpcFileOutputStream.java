package edu.kit.palladio.proto.rest.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import com.google.protobuf.ByteString;

import edu.kit.palladio.proto.filemanagement.FileChunck;
import io.grpc.stub.StreamObserver;

public class GrpcFileOutputStream extends OutputStream {

    private final StreamObserver<FileChunck> requestObserver;
    private final String path;
    public GrpcFileOutputStream(final StreamObserver<FileChunck> requestObserver, final String path){
        this.requestObserver = requestObserver;
        this.path = path;
    }

    @Override
    public void write(int b) throws IOException {
        final byte lower = (byte)(b & 0xFF);
         
        requestObserver.onNext(FileChunck.newBuilder().setPath(path).setContent(ByteString.copyFrom(new byte[]{lower})).build());
    }

    @Override
    public void write(byte[] b, int off, int length){
        if(b == null){
            throw new NullPointerException();
        }
        if(off < 0|| length < 0 || off + length > b.length){
            throw new IndexOutOfBoundsException();
        }
       final byte[] bytesInRange = Arrays.copyOfRange(b, off, off + length);
       final ByteString content = ByteString.copyFrom(bytesInRange);
       requestObserver.onNext(FileChunck.newBuilder().setPath(path).setContent(content).build());
    }


    @Override
    public void close(){
         // Mark the end of requests
         requestObserver.onCompleted();
    }


    
}