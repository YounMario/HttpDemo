package com.younchen.utils.http;

import java.io.IOException;

import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

public class CustomRequestBody extends RequestBody {

	protected RequestBody delegate;
	protected UpLoadListener listener;

	protected CountingSink countingSink;

	public CustomRequestBody(RequestBody delegate, UpLoadListener listener) {
		this.delegate = delegate;
		this.listener = listener;
	}

	@Override
	public MediaType contentType() {
		return delegate.contentType();
	}

	@Override
	public long contentLength() throws IOException {
		return delegate.contentLength();
	}

	@Override
	public void writeTo(BufferedSink sink) throws IOException {
		BufferedSink bufferedSink;

		countingSink = new CountingSink(sink);
		bufferedSink = Okio.buffer(countingSink);
		delegate.writeTo(bufferedSink);
		bufferedSink.flush();
	}

	protected final class CountingSink extends ForwardingSink {

		private long bytesWritten = 0;

		public CountingSink(Sink delegate) {
			super(delegate);
		}

		@Override
		public void write(Buffer source, long byteCount) throws IOException {
			super.write(source, byteCount);
			bytesWritten += byteCount;
			int prograss = (int) (bytesWritten*100 / contentLength());
			if (listener != null)
				listener.onPrograss(prograss);
		}
	}

	/**
	 * 
	 * @author 上传监听事件
	 *
	 */
	public static interface UpLoadListener {
		public void onPrograss(int prograss);
	}

}
