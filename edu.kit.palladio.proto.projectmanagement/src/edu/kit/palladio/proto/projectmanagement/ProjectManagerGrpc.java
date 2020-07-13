package edu.kit.palladio.proto.projectmanagement;

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

import edu.kit.palladio.proto.projectmanagement.ProjectManagerGrpc.ProjectManagerImplBase;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.30.2)", comments = "Source: projectmanagement.proto")
public final class ProjectManagerGrpc {

	private ProjectManagerGrpc() {
	}

	public static final String SERVICE_NAME = "projectmanagement.ProjectManager";

	// Static method descriptors that strictly reflect the proto.
	private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.CreateProjectResponse> getCreateProjectMethod;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
			+ "createProject", requestType = edu.kit.palladio.proto.projectmanagement.Project.class, responseType = edu.kit.palladio.proto.projectmanagement.CreateProjectResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
	public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.CreateProjectResponse> getCreateProjectMethod() {
		io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.CreateProjectResponse> getCreateProjectMethod;
		if ((getCreateProjectMethod = ProjectManagerGrpc.getCreateProjectMethod) == null) {
			synchronized (ProjectManagerGrpc.class) {
				if ((getCreateProjectMethod = ProjectManagerGrpc.getCreateProjectMethod) == null) {
					ProjectManagerGrpc.getCreateProjectMethod = getCreateProjectMethod = io.grpc.MethodDescriptor.<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.CreateProjectResponse>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.UNARY)
							.setFullMethodName(generateFullMethodName(SERVICE_NAME, "createProject"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(edu.kit.palladio.proto.projectmanagement.Project.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(edu.kit.palladio.proto.projectmanagement.CreateProjectResponse
											.getDefaultInstance()))
							.setSchemaDescriptor(new ProjectManagerMethodDescriptorSupplier("createProject")).build();
				}
			}
		}
		return getCreateProjectMethod;
	}

	private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse> getDeleteProjectMethod;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
			+ "deleteProject", requestType = edu.kit.palladio.proto.projectmanagement.Project.class, responseType = edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
	public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse> getDeleteProjectMethod() {
		io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse> getDeleteProjectMethod;
		if ((getDeleteProjectMethod = ProjectManagerGrpc.getDeleteProjectMethod) == null) {
			synchronized (ProjectManagerGrpc.class) {
				if ((getDeleteProjectMethod = ProjectManagerGrpc.getDeleteProjectMethod) == null) {
					ProjectManagerGrpc.getDeleteProjectMethod = getDeleteProjectMethod = io.grpc.MethodDescriptor.<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.UNARY)
							.setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteProject"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(edu.kit.palladio.proto.projectmanagement.Project.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse
											.getDefaultInstance()))
							.setSchemaDescriptor(new ProjectManagerMethodDescriptorSupplier("deleteProject")).build();
				}
			}
		}
		return getDeleteProjectMethod;
	}

	private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.Project> getGetProjectMethod;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
			+ "getProject", requestType = edu.kit.palladio.proto.projectmanagement.Project.class, responseType = edu.kit.palladio.proto.projectmanagement.Project.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
	public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.Project> getGetProjectMethod() {
		io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.Project> getGetProjectMethod;
		if ((getGetProjectMethod = ProjectManagerGrpc.getGetProjectMethod) == null) {
			synchronized (ProjectManagerGrpc.class) {
				if ((getGetProjectMethod = ProjectManagerGrpc.getGetProjectMethod) == null) {
					ProjectManagerGrpc.getGetProjectMethod = getGetProjectMethod = io.grpc.MethodDescriptor.<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.Project>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.UNARY)
							.setFullMethodName(generateFullMethodName(SERVICE_NAME, "getProject"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(edu.kit.palladio.proto.projectmanagement.Project.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(edu.kit.palladio.proto.projectmanagement.Project.getDefaultInstance()))
							.setSchemaDescriptor(new ProjectManagerMethodDescriptorSupplier("getProject")).build();
				}
			}
		}
		return getGetProjectMethod;
	}

	private static volatile io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.GetProjectRequest, edu.kit.palladio.proto.projectmanagement.Projects> getGetProjectsMethod;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
			+ "getProjects", requestType = edu.kit.palladio.proto.projectmanagement.GetProjectRequest.class, responseType = edu.kit.palladio.proto.projectmanagement.Projects.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
	public static io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.GetProjectRequest, edu.kit.palladio.proto.projectmanagement.Projects> getGetProjectsMethod() {
		io.grpc.MethodDescriptor<edu.kit.palladio.proto.projectmanagement.GetProjectRequest, edu.kit.palladio.proto.projectmanagement.Projects> getGetProjectsMethod;
		if ((getGetProjectsMethod = ProjectManagerGrpc.getGetProjectsMethod) == null) {
			synchronized (ProjectManagerGrpc.class) {
				if ((getGetProjectsMethod = ProjectManagerGrpc.getGetProjectsMethod) == null) {
					ProjectManagerGrpc.getGetProjectsMethod = getGetProjectsMethod = io.grpc.MethodDescriptor.<edu.kit.palladio.proto.projectmanagement.GetProjectRequest, edu.kit.palladio.proto.projectmanagement.Projects>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.UNARY)
							.setFullMethodName(generateFullMethodName(SERVICE_NAME, "getProjects"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
									edu.kit.palladio.proto.projectmanagement.GetProjectRequest.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(edu.kit.palladio.proto.projectmanagement.Projects.getDefaultInstance()))
							.setSchemaDescriptor(new ProjectManagerMethodDescriptorSupplier("getProjects")).build();
				}
			}
		}
		return getGetProjectsMethod;
	}

	/**
	 * Creates a new async stub that supports all call types for the service
	 */
	public static ProjectManagerStub newStub(io.grpc.Channel channel) {
		io.grpc.stub.AbstractStub.StubFactory<ProjectManagerStub> factory = new io.grpc.stub.AbstractStub.StubFactory<ProjectManagerStub>() {
			@java.lang.Override
			public ProjectManagerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
				return new ProjectManagerStub(channel, callOptions);
			}
		};
		return ProjectManagerStub.newStub(factory, channel);
	}

	/**
	 * Creates a new blocking-style stub that supports unary and streaming output
	 * calls on the service
	 */
	public static ProjectManagerBlockingStub newBlockingStub(io.grpc.Channel channel) {
		io.grpc.stub.AbstractStub.StubFactory<ProjectManagerBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<ProjectManagerBlockingStub>() {
			@java.lang.Override
			public ProjectManagerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
				return new ProjectManagerBlockingStub(channel, callOptions);
			}
		};
		return ProjectManagerBlockingStub.newStub(factory, channel);
	}

	/**
	 * Creates a new ListenableFuture-style stub that supports unary calls on the
	 * service
	 */
	public static ProjectManagerFutureStub newFutureStub(io.grpc.Channel channel) {
		io.grpc.stub.AbstractStub.StubFactory<ProjectManagerFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<ProjectManagerFutureStub>() {
			@java.lang.Override
			public ProjectManagerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
				return new ProjectManagerFutureStub(channel, callOptions);
			}
		};
		return ProjectManagerFutureStub.newStub(factory, channel);
	}

	/**
	 */
	public static abstract class ProjectManagerImplBase implements io.grpc.BindableService {

		/**
		 */
		public void createProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.CreateProjectResponse> responseObserver) {
			asyncUnimplementedUnaryCall(getCreateProjectMethod(), responseObserver);
		}

		/**
		 */
		public void deleteProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse> responseObserver) {
			asyncUnimplementedUnaryCall(getDeleteProjectMethod(), responseObserver);
		}

		/**
		 */
		public void getProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Project> responseObserver) {
			asyncUnimplementedUnaryCall(getGetProjectMethod(), responseObserver);
		}

		/**
		 */
		public void getProjects(edu.kit.palladio.proto.projectmanagement.GetProjectRequest request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Projects> responseObserver) {
			asyncUnimplementedUnaryCall(getGetProjectsMethod(), responseObserver);
		}

		@java.lang.Override
		public final io.grpc.ServerServiceDefinition bindService() {
			return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
					.addMethod(getCreateProjectMethod(), asyncUnaryCall(
							new MethodHandlers<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.CreateProjectResponse>(
									this, METHODID_CREATE_PROJECT)))
					.addMethod(getDeleteProjectMethod(), asyncUnaryCall(
							new MethodHandlers<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse>(
									this, METHODID_DELETE_PROJECT)))
					.addMethod(getGetProjectMethod(), asyncUnaryCall(
							new MethodHandlers<edu.kit.palladio.proto.projectmanagement.Project, edu.kit.palladio.proto.projectmanagement.Project>(
									this, METHODID_GET_PROJECT)))
					.addMethod(getGetProjectsMethod(), asyncUnaryCall(
							new MethodHandlers<edu.kit.palladio.proto.projectmanagement.GetProjectRequest, edu.kit.palladio.proto.projectmanagement.Projects>(
									this, METHODID_GET_PROJECTS)))
					.build();
		}
	}

	/**
	 */
	public static final class ProjectManagerStub extends io.grpc.stub.AbstractAsyncStub<ProjectManagerStub> {
		private ProjectManagerStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected ProjectManagerStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new ProjectManagerStub(channel, callOptions);
		}

		/**
		 */
		public void createProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.CreateProjectResponse> responseObserver) {
			asyncUnaryCall(getChannel().newCall(getCreateProjectMethod(), getCallOptions()), request, responseObserver);
		}

		/**
		 */
		public void deleteProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse> responseObserver) {
			asyncUnaryCall(getChannel().newCall(getDeleteProjectMethod(), getCallOptions()), request, responseObserver);
		}

		/**
		 */
		public void getProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Project> responseObserver) {
			asyncUnaryCall(getChannel().newCall(getGetProjectMethod(), getCallOptions()), request, responseObserver);
		}

		/**
		 */
		public void getProjects(edu.kit.palladio.proto.projectmanagement.GetProjectRequest request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Projects> responseObserver) {
			asyncUnaryCall(getChannel().newCall(getGetProjectsMethod(), getCallOptions()), request, responseObserver);
		}
	}

	/**
	 */
	public static final class ProjectManagerBlockingStub
			extends io.grpc.stub.AbstractBlockingStub<ProjectManagerBlockingStub> {
		private ProjectManagerBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected ProjectManagerBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new ProjectManagerBlockingStub(channel, callOptions);
		}

		/**
		 */
		public edu.kit.palladio.proto.projectmanagement.CreateProjectResponse createProject(
				edu.kit.palladio.proto.projectmanagement.Project request) {
			return blockingUnaryCall(getChannel(), getCreateProjectMethod(), getCallOptions(), request);
		}

		/**
		 */
		public edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse deleteProject(
				edu.kit.palladio.proto.projectmanagement.Project request) {
			return blockingUnaryCall(getChannel(), getDeleteProjectMethod(), getCallOptions(), request);
		}

		/**
		 */
		public edu.kit.palladio.proto.projectmanagement.Project getProject(
				edu.kit.palladio.proto.projectmanagement.Project request) {
			return blockingUnaryCall(getChannel(), getGetProjectMethod(), getCallOptions(), request);
		}

		/**
		 */
		public edu.kit.palladio.proto.projectmanagement.Projects getProjects(
				edu.kit.palladio.proto.projectmanagement.GetProjectRequest request) {
			return blockingUnaryCall(getChannel(), getGetProjectsMethod(), getCallOptions(), request);
		}
	}

	/**
	 */
	public static final class ProjectManagerFutureStub
			extends io.grpc.stub.AbstractFutureStub<ProjectManagerFutureStub> {
		private ProjectManagerFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected ProjectManagerFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new ProjectManagerFutureStub(channel, callOptions);
		}

		/**
		 */
		public com.google.common.util.concurrent.ListenableFuture<edu.kit.palladio.proto.projectmanagement.CreateProjectResponse> createProject(
				edu.kit.palladio.proto.projectmanagement.Project request) {
			return futureUnaryCall(getChannel().newCall(getCreateProjectMethod(), getCallOptions()), request);
		}

		/**
		 */
		public com.google.common.util.concurrent.ListenableFuture<edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse> deleteProject(
				edu.kit.palladio.proto.projectmanagement.Project request) {
			return futureUnaryCall(getChannel().newCall(getDeleteProjectMethod(), getCallOptions()), request);
		}

		/**
		 */
		public com.google.common.util.concurrent.ListenableFuture<edu.kit.palladio.proto.projectmanagement.Project> getProject(
				edu.kit.palladio.proto.projectmanagement.Project request) {
			return futureUnaryCall(getChannel().newCall(getGetProjectMethod(), getCallOptions()), request);
		}

		/**
		 */
		public com.google.common.util.concurrent.ListenableFuture<edu.kit.palladio.proto.projectmanagement.Projects> getProjects(
				edu.kit.palladio.proto.projectmanagement.GetProjectRequest request) {
			return futureUnaryCall(getChannel().newCall(getGetProjectsMethod(), getCallOptions()), request);
		}
	}

	private static final int METHODID_CREATE_PROJECT = 0;
	private static final int METHODID_DELETE_PROJECT = 1;
	private static final int METHODID_GET_PROJECT = 2;
	private static final int METHODID_GET_PROJECTS = 3;

	private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
			io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
			io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
			io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
		private final ProjectManagerImplBase serviceImpl;
		private final int methodId;

		MethodHandlers(ProjectManagerImplBase serviceImpl, int methodId) {
			this.serviceImpl = serviceImpl;
			this.methodId = methodId;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("unchecked")
		public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
			switch (methodId) {
			case METHODID_CREATE_PROJECT:
				serviceImpl.createProject((edu.kit.palladio.proto.projectmanagement.Project) request,
						(io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.CreateProjectResponse>) responseObserver);
				break;
			case METHODID_DELETE_PROJECT:
				serviceImpl.deleteProject((edu.kit.palladio.proto.projectmanagement.Project) request,
						(io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse>) responseObserver);
				break;
			case METHODID_GET_PROJECT:
				serviceImpl.getProject((edu.kit.palladio.proto.projectmanagement.Project) request,
						(io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Project>) responseObserver);
				break;
			case METHODID_GET_PROJECTS:
				serviceImpl.getProjects((edu.kit.palladio.proto.projectmanagement.GetProjectRequest) request,
						(io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Projects>) responseObserver);
				break;
			default:
				throw new AssertionError();
			}
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("unchecked")
		public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
			switch (methodId) {
			default:
				throw new AssertionError();
			}
		}
	}

	private static abstract class ProjectManagerBaseDescriptorSupplier
			implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
		ProjectManagerBaseDescriptorSupplier() {
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
			return edu.kit.palladio.proto.projectmanagement.ProjectManagementProtos.getDescriptor();
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
			return getFileDescriptor().findServiceByName("ProjectManager");
		}
	}

	private static final class ProjectManagerFileDescriptorSupplier extends ProjectManagerBaseDescriptorSupplier {
		ProjectManagerFileDescriptorSupplier() {
		}
	}

	private static final class ProjectManagerMethodDescriptorSupplier extends ProjectManagerBaseDescriptorSupplier
			implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
		private final String methodName;

		ProjectManagerMethodDescriptorSupplier(String methodName) {
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
			synchronized (ProjectManagerGrpc.class) {
				result = serviceDescriptor;
				if (result == null) {
					serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
							.setSchemaDescriptor(new ProjectManagerFileDescriptorSupplier())
							.addMethod(getCreateProjectMethod()).addMethod(getDeleteProjectMethod())
							.addMethod(getGetProjectMethod()).addMethod(getGetProjectsMethod()).build();
				}
			}
		}
		return result;
	}

	
}
