// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: projectmanagement.proto

package edu.kit.palladio.proto.projectmanagement;

public final class ProjectManagementProtos {
  private ProjectManagementProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_projectmanagement_Project_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_projectmanagement_Project_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_projectmanagement_CreateProjectResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_projectmanagement_CreateProjectResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_projectmanagement_DeleteProjectResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_projectmanagement_DeleteProjectResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_projectmanagement_GetProjectsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_projectmanagement_GetProjectsRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027projectmanagement.proto\022\021projectmanage" +
      "ment\"\034\n\007Project\022\021\n\tprojectId\030\001 \001(\t\"\027\n\025Cr" +
      "eateProjectResponse\"\027\n\025DeleteProjectResp" +
      "onse\"\024\n\022GetProjectsRequest2\340\002\n\016ProjectMa" +
      "nager\022W\n\rcreateProject\022\032.projectmanageme" +
      "nt.Project\032(.projectmanagement.CreatePro" +
      "jectResponse\"\000\022W\n\rdeleteProject\022\032.projec" +
      "tmanagement.Project\032(.projectmanagement." +
      "DeleteProjectResponse\"\000\022F\n\ngetProject\022\032." +
      "projectmanagement.Project\032\032.projectmanag" +
      "ement.Project\"\000\022T\n\013getProjects\022%.project" +
      "management.GetProjectsRequest\032\032.projectm" +
      "anagement.Project\"\0000\001BE\n(edu.kit.palladi" +
      "o.proto.projectmanagementB\027ProjectManage" +
      "mentProtosP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_projectmanagement_Project_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_projectmanagement_Project_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_projectmanagement_Project_descriptor,
        new java.lang.String[] { "ProjectId", });
    internal_static_projectmanagement_CreateProjectResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_projectmanagement_CreateProjectResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_projectmanagement_CreateProjectResponse_descriptor,
        new java.lang.String[] { });
    internal_static_projectmanagement_DeleteProjectResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_projectmanagement_DeleteProjectResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_projectmanagement_DeleteProjectResponse_descriptor,
        new java.lang.String[] { });
    internal_static_projectmanagement_GetProjectsRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_projectmanagement_GetProjectsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_projectmanagement_GetProjectsRequest_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}