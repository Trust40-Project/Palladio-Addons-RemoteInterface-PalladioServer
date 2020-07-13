package edu.kit.palladio.proto.projectmanagement;


public class ProjectManagerService extends ProjectManagerGrpc.ProjectManagerImplBase{
		public void createProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.CreateProjectResponse> responseObserver) {
			System.out.println("create project");
			CreateProjectResponse reply = CreateProjectResponse.newBuilder().build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}

		/**
		 */
		public void deleteProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse> responseObserver) {
			System.out.println("create project");
			DeleteProjectResponse reply = DeleteProjectResponse.newBuilder().build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}

		/**
		 */
		public void getProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Project> responseObserver) {
			System.out.println("create project");
			Project reply = Project.newBuilder().setProjectId("test").build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}

		/**
		 */
		public void getProjects(edu.kit.palladio.proto.projectmanagement.GetProjectRequest request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Projects> responseObserver) {
			System.out.println("create project");
			Projects reply = Projects.newBuilder().addProjects(Project.newBuilder().setProjectId("test")).build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
	}