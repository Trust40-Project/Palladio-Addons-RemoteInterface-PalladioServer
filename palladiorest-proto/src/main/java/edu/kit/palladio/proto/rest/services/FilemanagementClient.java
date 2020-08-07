package edu.kit.palladio.proto.rest.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.ByteString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kit.palladio.proto.filemanagement.CreateFileResponse;
import edu.kit.palladio.proto.filemanagement.DeleteFileResponse;
import edu.kit.palladio.proto.filemanagement.File;
import edu.kit.palladio.proto.filemanagement.FileChunck;
import edu.kit.palladio.proto.filemanagement.FileTreeRequest;
import edu.kit.palladio.proto.filemanagement.RemoteFileUploadGrpc;
import edu.kit.palladio.proto.filemanagement.RemoteFileUploadGrpc.RemoteFileUploadBlockingStub;
import edu.kit.palladio.proto.filemanagement.RemoteFileUploadGrpc.RemoteFileUploadStub;
import edu.kit.palladio.proto.filemanagement.WriteResponse;
import edu.kit.palladio.proto.rest.ChannelFactory;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

@Service
public class FilemanagementClient implements IFileService {

    private final RemoteFileUploadBlockingStub blockingStub;
    private final RemoteFileUploadStub asyncStub;

    /**
     * Construct client for accessing Projectmanagement server using the existing
     * channel.
     */
    @Autowired
    public FilemanagementClient(final ChannelFactory channelFactory) {
        // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's
        // responsibility to
        // shut it down.

        // Passing Channels to code makes code easier to test and makes it easier to
        // reuse Channels.
        blockingStub = RemoteFileUploadGrpc.newBlockingStub(channelFactory.getChannel());
        asyncStub = RemoteFileUploadGrpc.newStub(channelFactory.getChannel());
    }

    @Override
    public void createFile(final String path) {
        CreateFileResponse response = blockingStub
                .create(File.newBuilder().setIsDirectory(false).setPath(path).build());
    }

    @Override
    public void write(String path, MultipartFile file) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        final StreamObserver<WriteResponse> responseObserver = new StreamObserver<WriteResponse>() {

            @Override
            public void onNext(WriteResponse response) { // server is done
                System.out.println("write response");
            }

            @Override
            public void onError(Throwable t) { // server threw an exception
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() { // server finished
                finishLatch.countDown();
            }
        };

        final StreamObserver<FileChunck> requestObserver = asyncStub.write(responseObserver);

        try {
            try(final GrpcFileOutputStream outputStream = new GrpcFileOutputStream(requestObserver, path)){
                try(final InputStream fileStream = file.getInputStream()) {
                    fileStream.transferTo(outputStream);
                }
            }

           

        } catch (RuntimeException e) { // Cancel RPC requestObserver.onError(e);
            throw e;
        } // Mark the end of requests requestObserver.onCompleted();
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Receiving happens asynchronously
        try {
            finishLatch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void createDirectory(String path) {
        CreateFileResponse response = blockingStub.create(File.newBuilder().setIsDirectory(true).setPath(path).build());

    }

    @Override
    public void deleteFile(String path) {
        DeleteFileResponse response = blockingStub
            .delete(File.newBuilder().setIsDirectory(false).setPath(path).build());

    }

    @Override
    public void deleteDirectory(String path) {
        DeleteFileResponse response = blockingStub.delete(File.newBuilder().setIsDirectory(true).setPath(path).build());

    }

    @Override
    public Collection<edu.kit.palladio.remote.filemanagement.File> listFilesAndDirectories(String path) {
        final File file = File.newBuilder().setIsDirectory(true).setPath(path).build();
        final FileTreeRequest request = FileTreeRequest.newBuilder().setStartingDir(file).build();

        Iterator<File> files;
        try {
            files = blockingStub.listFilesAndDirectories(request);
        } catch (StatusRuntimeException ex) {
            System.out.println("RPC failed: " + ex.getStatus());
            return null;
        }

        List<edu.kit.palladio.remote.filemanagement.File> list = new ArrayList<>();
        files.forEachRemaining(fileFromResult -> {
            list.add(new edu.kit.palladio.remote.filemanagement.File(fileFromResult.getPath(),
                    fileFromResult.getIsDirectory()));
        });
        return list;
    }

    @Override
    public Resource read(String path) {
        final File fileToRead = File.newBuilder().setIsDirectory(false).setPath(path).build();
        Iterator<FileChunck> fileIterator = blockingStub.read(fileToRead);
        final List<ByteString> readBytes = new ArrayList<>();
        int totalSize = 0;
        while (fileIterator.hasNext()) {
            FileChunck currentChunck = fileIterator.next();
            ByteString currentContent = currentChunck.getContent();
            readBytes.add(currentContent);
            totalSize += currentContent.size();

        }

        final byte[] combined = new byte[totalSize];
        int offset = 0;
        for (ByteString currentByteString : readBytes) {
            currentByteString.copyTo(combined, offset);
        }
        return new ByteArrayResource(combined);

    }
}