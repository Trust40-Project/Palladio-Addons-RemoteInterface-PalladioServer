// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: filemanagement.proto

package edu.kit.palladio.proto.filemanagement;

public final class FileManagementProtos {
  private FileManagementProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_filemanagement_File_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_filemanagement_File_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_filemanagement_FileChunck_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_filemanagement_FileChunck_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_filemanagement_FileTreeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_filemanagement_FileTreeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_filemanagement_CreateFileResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_filemanagement_CreateFileResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_filemanagement_WriteResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_filemanagement_WriteResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_filemanagement_DeleteFileResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_filemanagement_DeleteFileResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024filemanagement.proto\022\016filemanagement\")" +
      "\n\004File\022\014\n\004path\030\001 \001(\t\022\023\n\013isDirectory\030\002 \001(" +
      "\010\"+\n\nFileChunck\022\014\n\004path\030\001 \001(\t\022\017\n\007content" +
      "\030\002 \001(\014\"<\n\017FileTreeRequest\022)\n\013startingDir" +
      "\030\001 \001(\0132\024.filemanagement.File\"\024\n\022CreateFi" +
      "leResponse\"\017\n\rWriteResponse\"\024\n\022DeleteFil" +
      "eResponse2\303\003\n\020RemoteFileUpload\022D\n\006create" +
      "\022\024.filemanagement.File\032\".filemanagement." +
      "CreateFileResponse\"\000\022D\n\006delete\022\024.fileman" +
      "agement.File\032\".filemanagement.DeleteFile" +
      "Response\"\000\022F\n\005write\022\032.filemanagement.Fil" +
      "eChunck\032\035.filemanagement.WriteResponse\"\000" +
      "(\001\022G\n\006append\022\032.filemanagement.FileChunck" +
      "\032\035.filemanagement.WriteResponse\"\000(\001\022<\n\004r" +
      "ead\022\024.filemanagement.File\032\032.filemanageme" +
      "nt.FileChunck\"\0000\001\022T\n\027listFilesAndDirecto" +
      "ries\022\037.filemanagement.FileTreeRequest\032\024." +
      "filemanagement.File\"\0000\001B?\n%edu.kit.palla" +
      "dio.proto.filemanagementB\024FileManagement" +
      "ProtosP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_filemanagement_File_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_filemanagement_File_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_filemanagement_File_descriptor,
        new java.lang.String[] { "Path", "IsDirectory", });
    internal_static_filemanagement_FileChunck_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_filemanagement_FileChunck_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_filemanagement_FileChunck_descriptor,
        new java.lang.String[] { "Path", "Content", });
    internal_static_filemanagement_FileTreeRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_filemanagement_FileTreeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_filemanagement_FileTreeRequest_descriptor,
        new java.lang.String[] { "StartingDir", });
    internal_static_filemanagement_CreateFileResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_filemanagement_CreateFileResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_filemanagement_CreateFileResponse_descriptor,
        new java.lang.String[] { });
    internal_static_filemanagement_WriteResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_filemanagement_WriteResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_filemanagement_WriteResponse_descriptor,
        new java.lang.String[] { });
    internal_static_filemanagement_DeleteFileResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_filemanagement_DeleteFileResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_filemanagement_DeleteFileResponse_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
