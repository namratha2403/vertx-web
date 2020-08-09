package io.vertx.ext.web.client.impl.encoders;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import java.nio.charset.Charset;

public abstract class AbstractMultipartFormEncoder extends HttpPostRequestEncoder {

  public AbstractMultipartFormEncoder(HttpDataFactory factory, HttpRequest request, boolean multipart, Charset charset, EncoderMode encoderMode) throws ErrorDataEncoderException {
    super(factory, request, multipart, charset, encoderMode);
  }

  public abstract void addBodyBinaryUpload(String name,
                                           String filename,
                                           String contentType,
                                           boolean isText,
                                           Object buffer) throws ErrorDataEncoderException;
}
