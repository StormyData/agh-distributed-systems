// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: smarthome.proto

package sr.grpc.gen;

/**
 * Protobuf type {@code smarthome.GenericSensorControlerDescArg}
 */
public final class GenericSensorControlerDescArg extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:smarthome.GenericSensorControlerDescArg)
    GenericSensorControlerDescArgOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GenericSensorControlerDescArg.newBuilder() to construct.
  private GenericSensorControlerDescArg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GenericSensorControlerDescArg() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GenericSensorControlerDescArg();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sr.grpc.gen.SmartHomeProto.internal_static_smarthome_GenericSensorControlerDescArg_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sr.grpc.gen.SmartHomeProto.internal_static_smarthome_GenericSensorControlerDescArg_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sr.grpc.gen.GenericSensorControlerDescArg.class, sr.grpc.gen.GenericSensorControlerDescArg.Builder.class);
  }

  public static final int SENSORID_FIELD_NUMBER = 1;
  private int sensorId_ = 0;
  /**
   * <code>int32 sensorId = 1;</code>
   * @return The sensorId.
   */
  @java.lang.Override
  public int getSensorId() {
    return sensorId_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (sensorId_ != 0) {
      output.writeInt32(1, sensorId_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (sensorId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, sensorId_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof sr.grpc.gen.GenericSensorControlerDescArg)) {
      return super.equals(obj);
    }
    sr.grpc.gen.GenericSensorControlerDescArg other = (sr.grpc.gen.GenericSensorControlerDescArg) obj;

    if (getSensorId()
        != other.getSensorId()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SENSORID_FIELD_NUMBER;
    hash = (53 * hash) + getSensorId();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.GenericSensorControlerDescArg parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(sr.grpc.gen.GenericSensorControlerDescArg prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code smarthome.GenericSensorControlerDescArg}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:smarthome.GenericSensorControlerDescArg)
      sr.grpc.gen.GenericSensorControlerDescArgOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sr.grpc.gen.SmartHomeProto.internal_static_smarthome_GenericSensorControlerDescArg_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sr.grpc.gen.SmartHomeProto.internal_static_smarthome_GenericSensorControlerDescArg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sr.grpc.gen.GenericSensorControlerDescArg.class, sr.grpc.gen.GenericSensorControlerDescArg.Builder.class);
    }

    // Construct using sr.grpc.gen.GenericSensorControlerDescArg.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      sensorId_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sr.grpc.gen.SmartHomeProto.internal_static_smarthome_GenericSensorControlerDescArg_descriptor;
    }

    @java.lang.Override
    public sr.grpc.gen.GenericSensorControlerDescArg getDefaultInstanceForType() {
      return sr.grpc.gen.GenericSensorControlerDescArg.getDefaultInstance();
    }

    @java.lang.Override
    public sr.grpc.gen.GenericSensorControlerDescArg build() {
      sr.grpc.gen.GenericSensorControlerDescArg result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sr.grpc.gen.GenericSensorControlerDescArg buildPartial() {
      sr.grpc.gen.GenericSensorControlerDescArg result = new sr.grpc.gen.GenericSensorControlerDescArg(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(sr.grpc.gen.GenericSensorControlerDescArg result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.sensorId_ = sensorId_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof sr.grpc.gen.GenericSensorControlerDescArg) {
        return mergeFrom((sr.grpc.gen.GenericSensorControlerDescArg)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sr.grpc.gen.GenericSensorControlerDescArg other) {
      if (other == sr.grpc.gen.GenericSensorControlerDescArg.getDefaultInstance()) return this;
      if (other.getSensorId() != 0) {
        setSensorId(other.getSensorId());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              sensorId_ = input.readInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int sensorId_ ;
    /**
     * <code>int32 sensorId = 1;</code>
     * @return The sensorId.
     */
    @java.lang.Override
    public int getSensorId() {
      return sensorId_;
    }
    /**
     * <code>int32 sensorId = 1;</code>
     * @param value The sensorId to set.
     * @return This builder for chaining.
     */
    public Builder setSensorId(int value) {
      
      sensorId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int32 sensorId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSensorId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      sensorId_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:smarthome.GenericSensorControlerDescArg)
  }

  // @@protoc_insertion_point(class_scope:smarthome.GenericSensorControlerDescArg)
  private static final sr.grpc.gen.GenericSensorControlerDescArg DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sr.grpc.gen.GenericSensorControlerDescArg();
  }

  public static sr.grpc.gen.GenericSensorControlerDescArg getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GenericSensorControlerDescArg>
      PARSER = new com.google.protobuf.AbstractParser<GenericSensorControlerDescArg>() {
    @java.lang.Override
    public GenericSensorControlerDescArg parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<GenericSensorControlerDescArg> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GenericSensorControlerDescArg> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sr.grpc.gen.GenericSensorControlerDescArg getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
