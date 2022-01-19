// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ChatRoomProtocol.proto

package optic_fusion1.common.protos;

public interface AuthenticationRequestPacketOrBuilder extends
    // @@protoc_insertion_point(interface_extends:AuthenticationRequestPacket)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string session_id = 1;</code>
   * @return Whether the sessionId field is set.
   */
  boolean hasSessionId();
  /**
   * <code>required string session_id = 1;</code>
   * @return The sessionId.
   */
  java.lang.String getSessionId();
  /**
   * <code>required string session_id = 1;</code>
   * @return The bytes for sessionId.
   */
  com.google.protobuf.ByteString
      getSessionIdBytes();

  /**
   * <code>required string username = 2;</code>
   * @return Whether the username field is set.
   */
  boolean hasUsername();
  /**
   * <code>required string username = 2;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <code>required string username = 2;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>required string password = 3;</code>
   * @return Whether the password field is set.
   */
  boolean hasPassword();
  /**
   * <code>required string password = 3;</code>
   * @return The password.
   */
  java.lang.String getPassword();
  /**
   * <code>required string password = 3;</code>
   * @return The bytes for password.
   */
  com.google.protobuf.ByteString
      getPasswordBytes();
}