// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: example.proto

package sr.middleware.gen;

public final class ExampleProto {
  private ExampleProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_example_MessageWithOptionalField_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_example_MessageWithOptionalField_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_example_MessageWithoutOptionalField_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_example_MessageWithoutOptionalField_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_example_MessageReturned_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_example_MessageReturned_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rexample.proto\022\007example\"D\n\030MessageWithO" +
      "ptionalField\022\021\n\004arg1\030\001 \001(\005H\000\210\001\001\022\014\n\004arg2\030" +
      "\002 \001(\005B\007\n\005_arg1\"9\n\033MessageWithoutOptional" +
      "Field\022\014\n\004arg1\030\001 \001(\005\022\014\n\004arg2\030\002 \001(\005\"!\n\017Mes" +
      "sageReturned\022\016\n\006return\030\001 \001(\0052\301\001\n\007Example" +
      "\022W\n\026MethodWithOptionalArgs\022!.example.Mes" +
      "sageWithOptionalField\032\030.example.MessageR" +
      "eturned\"\000\022]\n\031MethodWithoutOptionalArgs\022$" +
      ".example.MessageWithoutOptionalField\032\030.e" +
      "xample.MessageReturned\"\000B2\n\021sr.middlewar" +
      "e.genB\014ExampleProtoP\001\252\002\014ExampleProtob\006pr" +
      "oto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_example_MessageWithOptionalField_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_example_MessageWithOptionalField_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_example_MessageWithOptionalField_descriptor,
        new java.lang.String[] { "Arg1", "Arg2", "Arg1", });
    internal_static_example_MessageWithoutOptionalField_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_example_MessageWithoutOptionalField_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_example_MessageWithoutOptionalField_descriptor,
        new java.lang.String[] { "Arg1", "Arg2", });
    internal_static_example_MessageReturned_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_example_MessageReturned_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_example_MessageReturned_descriptor,
        new java.lang.String[] { "Return", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
