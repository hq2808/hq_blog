package com.hq2808.blog.base.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * The Class XSSRequestWrapper.
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * Instantiates a new XSS request wrapper.
	 *
	 * @param request the request
	 */
	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	  /* (non-Javadoc)
  	 * @see javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
  	 */
  	@Override
	public String[] getParameterValues(String parameter) {
	    String[] values = super.getParameterValues(parameter);
	    if (values == null) {
	        return new String[]{};
	    }	
	    int count = values.length;
	    String[] encodedValues = new String[count];
	    for (int i = 0; i < count; i++) {
	        encodedValues[i] = this.stripXSS(values[i]);
	    }
	    return encodedValues;
	}

	  /* (non-Javadoc)
  	 * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
  	 */
  	@Override
  	public String getParameter(String parameter) {
	    String value = super.getParameter(parameter);
	    return this.stripXSS(value);
  	}

	  /* (non-Javadoc)
  	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.String)
  	 */
  	@Override
  	public String getHeader(String name) {
	    String value = super.getHeader(name);
	    return this.stripXSS(value);
  	}

	/**
	 * Strip XSS.
	 *
	 * @param value the value
	 * @return the string
	 */
  	private String stripXSS(String value) {
	    return StringEscapeUtils.escapeHtml3(value);
	}
	  
}
