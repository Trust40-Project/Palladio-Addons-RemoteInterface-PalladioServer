package edu.kit.palladio.proto.filemanagement;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *message Directory{
 *string name = 1;
 *repeated DirectoryChild children = 2;
 *}
 *message DirectoryChild{
 *oneof child_oneof {
 *Directory name = 1;
 *File sub_message = 2;
 *}
 *}
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.30.2)",
    comments = "Source: filemanagement.proto")
public final class RemoteFileUploadGrpc {

  private RemoteFileUploadGrpc() {}

  public static final String SERVICE_NAME = "filemanagement.RemoteFileUpload";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.File,
      edu.kit.palladio.proto.filemanagement.CreateFileResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = edu.kit.palladio.proto.filemanagement.File.class,
      responseType = edu.kit.palladio.proto.filemanagement.CreateFileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.File,
      edu.kit.palladio.proto.filemanagement.CreateFileResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.File, edu.kit.palladio.proto.filemanagement.CreateFileResponse> getCreateMethod;
    if ((getCreateMethod = RemoteFileUploadGrpc.getCreateMethod) == null) {
      synchronized (RemoteFileUploadGrpc.class) {
        if ((getCreateMethod = RemoteFileUploadGrpc.getCreateMethod) == null) {
          RemoteFileUploadGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<edu.kit.palladio.proto.filemanagement.File, edu.kit.palladio.proto.filemanagement.CreateFileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.File.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.CreateFileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteFileUploadMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.File,
      edu.kit.palladio.proto.filemanagement.DeleteFileResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = edu.kit.palladio.proto.filemanagement.File.class,
      responseType = edu.kit.palladio.proto.filemanagement.DeleteFileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.File,
      edu.kit.palladio.proto.filemanagement.DeleteFileResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.File, edu.kit.palladio.proto.filemanagement.DeleteFileResponse> getDeleteMethod;
    if ((getDeleteMethod = RemoteFileUploadGrpc.getDeleteMethod) == null) {
      synchronized (RemoteFileUploadGrpc.class) {
        if ((getDeleteMethod = RemoteFileUploadGrpc.getDeleteMethod) == null) {
          RemoteFileUploadGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<edu.kit.palladio.proto.filemanagement.File, edu.kit.palladio.proto.filemanagement.DeleteFileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.File.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.DeleteFileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteFileUploadMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.FileChunck,
      edu.kit.palladio.proto.filemanagement.WriteResponse> getWriteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "write",
      requestType = edu.kit.palladio.proto.filemanagement.FileChunck.class,
      responseType = edu.kit.palladio.proto.filemanagement.WriteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.FileChunck,
      edu.kit.palladio.proto.filemanagement.WriteResponse> getWriteMethod() {
    io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.FileChunck, edu.kit.palladio.proto.filemanagement.WriteResponse> getWriteMethod;
    if ((getWriteMethod = RemoteFileUploadGrpc.getWriteMethod) == null) {
      synchronized (RemoteFileUploadGrpc.class) {
        if ((getWriteMethod = RemoteFileUploadGrpc.getWriteMethod) == null) {
          RemoteFileUploadGrpc.getWriteMethod = getWriteMethod =
              io.grpc.MethodDescriptor.<edu.kit.palladio.proto.filemanagement.FileChunck, edu.kit.palladio.proto.filemanagement.WriteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "write"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.FileChunck.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.WriteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteFileUploadMethodDescriptorSupplier("write"))
              .build();
        }
      }
    }
    return getWriteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.FileChunck,
      edu.kit.palladio.proto.filemanagement.WriteResponse> getAppendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "append",
      requestType = edu.kit.palladio.proto.filemanagement.FileChunck.class,
      responseType = edu.kit.palladio.proto.filemanagement.WriteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.FileChunck,
      edu.kit.palladio.proto.filemanagement.WriteResponse> getAppendMethod() {
    io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.FileChunck, edu.kit.palladio.proto.filemanagement.WriteResponse> getAppendMethod;
    if ((getAppendMethod = RemoteFileUploadGrpc.getAppendMethod) == null) {
      synchronized (RemoteFileUploadGrpc.class) {
        if ((getAppendMethod = RemoteFileUploadGrpc.getAppendMethod) == null) {
          RemoteFileUploadGrpc.getAppendMethod = getAppendMethod =
              io.grpc.MethodDescriptor.<edu.kit.palladio.proto.filemanagement.FileChunck, edu.kit.palladio.proto.filemanagement.WriteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "append"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.FileChunck.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.WriteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteFileUploadMethodDescriptorSupplier("append"))
              .build();
        }
      }
    }
    return getAppendMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.File,
      edu.kit.palladio.proto.filemanagement.FileChunck> getReadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "read",
      requestType = edu.kit.palladio.proto.filemanagement.File.class,
      responseType = edu.kit.palladio.proto.filemanagement.FileChunck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.File,
      edu.kit.palladio.proto.filemanagement.FileChunck> getReadMethod() {
    io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.File, edu.kit.palladio.proto.filemanagement.FileChunck> getReadMethod;
    if ((getReadMethod = RemoteFileUploadGrpc.getReadMethod) == null) {
      synchronized (RemoteFileUploadGrpc.class) {
        if ((getReadMethod = RemoteFileUploadGrpc.getReadMethod) == null) {
          RemoteFileUploadGrpc.getReadMethod = getReadMethod =
              io.grpc.MethodDescriptor.<edu.kit.palladio.proto.filemanagement.File, edu.kit.palladio.proto.filemanagement.FileChunck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "read"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.File.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.FileChunck.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteFileUploadMethodDescriptorSupplier("read"))
              .build();
        }
      }
    }
    return getReadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.FileTreeRequest,
      edu.kit.palladio.proto.filemanagement.File> getListFilesAndDirectoriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listFilesAndDirectories",
      requestType = edu.kit.palladio.proto.filemanagement.FileTreeRequest.class,
      responseType = edu.kit.palladio.proto.filemanagement.File.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.FileTreeRequest,
      edu.kit.palladio.proto.filemanagement.File> getListFilesAndDirectoriesMethod() {
    io.grpc.MethodDescriptor<edu.kit.palladio.proto.filemanagement.FileTreeRequest, edu.kit.palladio.proto.filemanagement.File> getListFilesAndDirectoriesMethod;
    if ((getListFilesAndDirectoriesMethod = RemoteFileUploadGrpc.getListFilesAndDirectoriesMethod) == null) {
      synchronized (RemoteFileUploadGrpc.class) {
        if ((getListFilesAndDirectoriesMethod = RemoteFileUploadGrpc.getListFilesAndDirectoriesMethod) == null) {
          RemoteFileUploadGrpc.getListFilesAndDirectoriesMethod = getListFilesAndDirectoriesMethod =
              io.grpc.MethodDescriptor.<edu.kit.palladio.proto.filemanagement.FileTreeRequest, edu.kit.palladio.proto.filemanagement.File>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listFilesAndDirectories"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.FileTreeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.kit.palladio.proto.filemanagement.File.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteFileUploadMethodDescriptorSupplier("listFilesAndDirectories"))
              .build();
        }
      }
    }
    return getListFilesAndDirectoriesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RemoteFileUploadStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteFileUploadStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteFileUploadStub>() {
        @java.lang.Override
        public RemoteFileUploadStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteFileUploadStub(channel, callOptions);
        }
      };
    return RemoteFileUploadStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RemoteFileUploadBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteFileUploadBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteFileUploadBlockingStub>() {
        @java.lang.Override
        public RemoteFileUploadBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteFileUploadBlockingStub(channel, callOptions);
        }
      };
    return RemoteFileUploadBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RemoteFileUploadFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteFileUploadFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteFileUploadFutureStub>() {
        @java.lang.Override
        public RemoteFileUploadFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteFileUploadFutureStub(channel, callOptions);
        }
      };
    return RemoteFileUploadFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *message Directory{
   *string name = 1;
   *repeated DirectoryChild children = 2;
   *}
   *message DirectoryChild{
   *oneof child_oneof {
   *Directory name = 1;
   *File sub_message = 2;
   *}
   *}
   * </pre>
   */
  public static abstract class RemoteFileUploadImplBase implements io.grpc.BindableService {

    /**
     */
    public void create(edu.kit.palladio.proto.filemanagement.File request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.CreateFileResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    public void delete(edu.kit.palladio.proto.filemanagement.File request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.DeleteFileResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.FileChunck> write(
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.WriteResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getWriteMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.FileChunck> append(
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.WriteResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getAppendMethod(), responseObserver);
    }

    /**
     */
    public void read(edu.kit.palladio.proto.filemanagement.File request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.FileChunck> responseObserver) {
      asyncUnimplementedUnaryCall(getReadMethod(), responseObserver);
    }

    /**
     */
    public void listFilesAndDirectories(edu.kit.palladio.proto.filemanagement.FileTreeRequest request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.File> responseObserver) {
      asyncUnimplementedUnaryCall(getListFilesAndDirectoriesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.kit.palladio.proto.filemanagement.File,
                edu.kit.palladio.proto.filemanagement.CreateFileResponse>(
                  this, METHODID_CREATE)))
          .addMethod(
            getDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.kit.palladio.proto.filemanagement.File,
                edu.kit.palladio.proto.filemanagement.DeleteFileResponse>(
                  this, METHODID_DELETE)))
          .addMethod(
            getWriteMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                edu.kit.palladio.proto.filemanagement.FileChunck,
                edu.kit.palladio.proto.filemanagement.WriteResponse>(
                  this, METHODID_WRITE)))
          .addMethod(
            getAppendMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                edu.kit.palladio.proto.filemanagement.FileChunck,
                edu.kit.palladio.proto.filemanagement.WriteResponse>(
                  this, METHODID_APPEND)))
          .addMethod(
            getReadMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                edu.kit.palladio.proto.filemanagement.File,
                edu.kit.palladio.proto.filemanagement.FileChunck>(
                  this, METHODID_READ)))
          .addMethod(
            getListFilesAndDirectoriesMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                edu.kit.palladio.proto.filemanagement.FileTreeRequest,
                edu.kit.palladio.proto.filemanagement.File>(
                  this, METHODID_LIST_FILES_AND_DIRECTORIES)))
          .build();
    }
  }

  /**
   * <pre>
   *message Directory{
   *string name = 1;
   *repeated DirectoryChild children = 2;
   *}
   *message DirectoryChild{
   *oneof child_oneof {
   *Directory name = 1;
   *File sub_message = 2;
   *}
   *}
   * </pre>
   */
  public static final class RemoteFileUploadStub extends io.grpc.stub.AbstractAsyncStub<RemoteFileUploadStub> {
    private RemoteFileUploadStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteFileUploadStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteFileUploadStub(channel, callOptions);
    }

    /**
     */
    public void create(edu.kit.palladio.proto.filemanagement.File request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.CreateFileResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(edu.kit.palladio.proto.filemanagement.File request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.DeleteFileResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.FileChunck> write(
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.WriteResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getWriteMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.FileChunck> append(
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.WriteResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getAppendMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void read(edu.kit.palladio.proto.filemanagement.File request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.FileChunck> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listFilesAndDirectories(edu.kit.palladio.proto.filemanagement.FileTreeRequest request,
        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.File> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getListFilesAndDirectoriesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *message Directory{
   *string name = 1;
   *repeated DirectoryChild children = 2;
   *}
   *message DirectoryChild{
   *oneof child_oneof {
   *Directory name = 1;
   *File sub_message = 2;
   *}
   *}
   * </pre>
   */
  public static final class RemoteFileUploadBlockingStub extends io.grpc.stub.AbstractBlockingStub<RemoteFileUploadBlockingStub> {
    private RemoteFileUploadBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteFileUploadBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteFileUploadBlockingStub(channel, callOptions);
    }

    /**
     */
    public edu.kit.palladio.proto.filemanagement.CreateFileResponse create(edu.kit.palladio.proto.filemanagement.File request) {
      return blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.kit.palladio.proto.filemanagement.DeleteFileResponse delete(edu.kit.palladio.proto.filemanagement.File request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<edu.kit.palladio.proto.filemanagement.FileChunck> read(
        edu.kit.palladio.proto.filemanagement.File request) {
      return blockingServerStreamingCall(
          getChannel(), getReadMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<edu.kit.palladio.proto.filemanagement.File> listFilesAndDirectories(
        edu.kit.palladio.proto.filemanagement.FileTreeRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getListFilesAndDirectoriesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *message Directory{
   *string name = 1;
   *repeated DirectoryChild children = 2;
   *}
   *message DirectoryChild{
   *oneof child_oneof {
   *Directory name = 1;
   *File sub_message = 2;
   *}
   *}
   * </pre>
   */
  public static final class RemoteFileUploadFutureStub extends io.grpc.stub.AbstractFutureStub<RemoteFileUploadFutureStub> {
    private RemoteFileUploadFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteFileUploadFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteFileUploadFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.kit.palladio.proto.filemanagement.CreateFileResponse> create(
        edu.kit.palladio.proto.filemanagement.File request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.kit.palladio.proto.filemanagement.DeleteFileResponse> delete(
        edu.kit.palladio.proto.filemanagement.File request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_DELETE = 1;
  private static final int METHODID_READ = 2;
  private static final int METHODID_LIST_FILES_AND_DIRECTORIES = 3;
  private static final int METHODID_WRITE = 4;
  private static final int METHODID_APPEND = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RemoteFileUploadImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RemoteFileUploadImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((edu.kit.palladio.proto.filemanagement.File) request,
              (io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.CreateFileResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((edu.kit.palladio.proto.filemanagement.File) request,
              (io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.DeleteFileResponse>) responseObserver);
          break;
        case METHODID_READ:
          serviceImpl.read((edu.kit.palladio.proto.filemanagement.File) request,
              (io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.FileChunck>) responseObserver);
          break;
        case METHODID_LIST_FILES_AND_DIRECTORIES:
          serviceImpl.listFilesAndDirectories((edu.kit.palladio.proto.filemanagement.FileTreeRequest) request,
              (io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.File>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_WRITE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.write(
              (io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.WriteResponse>) responseObserver);
        case METHODID_APPEND:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.append(
              (io.grpc.stub.StreamObserver<edu.kit.palladio.proto.filemanagement.WriteResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RemoteFileUploadBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RemoteFileUploadBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return edu.kit.palladio.proto.filemanagement.FileManagementProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RemoteFileUpload");
    }
  }

  private static final class RemoteFileUploadFileDescriptorSupplier
      extends RemoteFileUploadBaseDescriptorSupplier {
    RemoteFileUploadFileDescriptorSupplier() {}
  }

  private static final class RemoteFileUploadMethodDescriptorSupplier
      extends RemoteFileUploadBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RemoteFileUploadMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RemoteFileUploadGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RemoteFileUploadFileDescriptorSupplier())
              .addMethod(getCreateMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getWriteMethod())
              .addMethod(getAppendMethod())
              .addMethod(getReadMethod())
              .addMethod(getListFilesAndDirectoriesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
