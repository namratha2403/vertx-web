package io.vertx.ext.web.client.impl.encoders;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.util.internal.StringUtil;
import java.io.IOException;

import static io.netty.util.internal.ObjectUtil.checkNotNull;

public class BufferMultipartPostRequestEncoder extends AbstractMultipartFormEncoder {


    private final HttpRequest request;
    private final HttpDataFactory factory;

    public BufferMultipartPostRequestEncoder(HttpDataFactory factory , HttpRequest request, boolean multipart,
                                             HttpPostRequestEncoder.EncoderMode encoderMode) throws ErrorDataEncoderException {
        super(factory, request, multipart, HttpConstants.DEFAULT_CHARSET, encoderMode);
        this.factory = factory;
        this.request = request;
    }

    public void addBodyBinaryUpload(String name,
                                    String filename ,
                                    String contentType,
                                    boolean isText,
                                    Object buffer) throws ErrorDataEncoderException {

        checkNotNull(name, "name");
        checkNotNull(buffer, "buffer cannot be empty");
        if (filename == null) {
            filename = StringUtil.EMPTY_STRING;
        }
        String scontentType = contentType;
        String contentTransferEncoding = null;
        if (contentType == null) {
            if (isText) {
                scontentType = "text/plain";
            } else {
                scontentType = "application/octet-stream";
            }
        }
        if (!isText) {
          contentTransferEncoding = "binary";
        }
        byte[] byteBuffer = (byte[])buffer;
        FileUpload fileUpload = factory.createFileUpload(request, name, filename, scontentType,
                contentTransferEncoding, null, byteBuffer.length);
        try {
            fileUpload.setContent(Unpooled.wrappedBuffer(byteBuffer));
        } catch (IOException e) {
            throw new ErrorDataEncoderException(e);
        }
        addBodyHttpData(fileUpload);
    }
}
