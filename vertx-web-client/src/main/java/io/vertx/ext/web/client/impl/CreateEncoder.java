package io.vertx.ext.web.client.impl;

import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.vertx.ext.web.client.impl.encoders.AbstractMultipartFormEncoder;
import io.vertx.ext.web.client.impl.encoders.BufferMultipartPostRequestEncoder;

public class CreateEncoder {

  public static AbstractMultipartFormEncoder from(DefaultFullHttpRequest request,
                                                  boolean multipart, HttpPostRequestEncoder.EncoderMode encoderMode) throws HttpPostRequestEncoder.ErrorDataEncoderException {
        return new BufferMultipartPostRequestEncoder
          (new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE), request, multipart, encoderMode);
  }
}
