package com.zhyea.argo.config.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.chobit.commons.utils.IoKit;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.util.WebUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 缓存request body
 *
 * @author robin
 * @since 2025/5/12 22:30
 */
public class ContentCachingRequestWrapper extends HttpServletRequestWrapper {


	private final FastByteArrayOutputStream cachedContent;
	private BufferedReader reader;

	/**
	 * Create a new ContentCachingRequestWrapper for the given servlet request.
	 *
	 * @param request the original servlet request
	 */
	public ContentCachingRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		int contentLength = request.getContentLength();
		this.cachedContent = (contentLength > 0) ? new FastByteArrayOutputStream(contentLength) : new FastByteArrayOutputStream();
		this.writeToCache(request);
	}


	private void writeToCache(HttpServletRequest request) throws IOException {
		if (HttpMethod.GET.matches(request.getMethod())) {
			this.writeRequestParametersToCachedContent();
		} else if (!HttpMethod.OPTIONS.matches(request.getMethod())) {
			IoKit.copy(request.getInputStream(), this.cachedContent);
		}
	}


	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new ContentCachingInputStream(new ByteArrayInputStream(this.cachedContent.toByteArray()));
	}

	@Override
	public String getCharacterEncoding() {
		String enc = super.getCharacterEncoding();
		return (enc != null ? enc : WebUtils.DEFAULT_CHARACTER_ENCODING);
	}

	@Override
	public BufferedReader getReader() throws IOException {
		if (this.reader == null) {
			this.reader = new BufferedReader(new InputStreamReader(getInputStream(), getCharacterEncoding()));
		}
		return this.reader;
	}

	@Override
	public String getParameter(String name) {
		if (this.cachedContent.size() == 0 && isFormPost()) {
			writeRequestParametersToCachedContent();
		}
		return super.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		if (this.cachedContent.size() == 0 && isFormPost()) {
			writeRequestParametersToCachedContent();
		}
		return super.getParameterMap();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		if (this.cachedContent.size() == 0 && isFormPost()) {
			writeRequestParametersToCachedContent();
		}
		return super.getParameterNames();
	}

	@Override
	public String[] getParameterValues(String name) {
		if (this.cachedContent.size() == 0 && isFormPost()) {
			writeRequestParametersToCachedContent();
		}
		return super.getParameterValues(name);
	}


	private boolean isFormPost() {
		String contentType = getContentType();
		return (contentType != null && contentType.contains(MediaType.APPLICATION_FORM_URLENCODED_VALUE) &&
				HttpMethod.POST.matches(getMethod()));
	}

	private void writeRequestParametersToCachedContent() {
		try {
			if (this.cachedContent.size() == 0) {
				return;
			}
			String requestEncoding = getCharacterEncoding();
			Map<String, String[]> form = super.getParameterMap();
			for (Iterator<String> nameIterator = form.keySet().iterator(); nameIterator.hasNext(); ) {
				String name = nameIterator.next();
				List<String> values = Arrays.asList(form.get(name));
				for (Iterator<String> valueIterator = values.iterator(); valueIterator.hasNext(); ) {
					String value = valueIterator.next();
					this.cachedContent.write(URLEncoder.encode(name, requestEncoding).getBytes());
					if (value != null) {
						this.cachedContent.write('=');
						this.cachedContent.write(URLEncoder.encode(value, requestEncoding).getBytes());
						if (valueIterator.hasNext()) {
							this.cachedContent.write('&');
						}
					}
				}
				if (nameIterator.hasNext()) {
					this.cachedContent.write('&');
				}
			}
		} catch (IOException ex) {
			throw new IllegalStateException("Failed to write request parameters to cached content", ex);
		}
	}


	public byte[] getContentAsByteArray() {
		return this.cachedContent.toByteArray();
	}

	/**
	 * Return the cached request content as a String, using the configured
	 * {@link Charset}.
	 * <p><strong>Note:</strong> The String returned from this method
	 * reflects the amount of content that has been read at the time when it
	 * is called. If the application does not read the content, this method
	 * returns an empty String.
	 *
	 * @see #getContentAsByteArray()
	 * @since 6.1
	 */
	public String getContentAsString() {
		return this.cachedContent.toString(Charset.forName(getCharacterEncoding()));
	}


	private static class ContentCachingInputStream extends ServletInputStream {

		private final ByteArrayInputStream is;

		public ContentCachingInputStream(ByteArrayInputStream is) {
			this.is = is;
		}

		@Override
		public int read() throws IOException {
			return is.read();
		}

		@Override
		public boolean isFinished() {
			return is.available() == 0;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener listener) {

		}
	}
}
