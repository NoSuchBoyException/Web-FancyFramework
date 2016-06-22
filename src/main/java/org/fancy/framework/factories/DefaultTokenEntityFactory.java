package org.fancy.framework.factories;

import javax.servlet.http.HttpServletRequest;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.constants.FieldConsts.AuthFields;
import org.fancy.framework.entities.impl.DefaultTokenEntity;
import org.fancy.framework.exceptions.CheckedException;
import org.fancy.framework.exceptions.ValueNotFoundException;
import org.fancy.framework.helpers.ExtractHelper;

public class DefaultTokenEntityFactory {

	private static ExtractHelper extractHelper = ExtractHelper.getInstance();

	/**
	 * The static factory method of token entity bean
	 * 
	 * @param request
	 *            HttpServletRequest from client
	 * @param params
	 *            the parameters for get token entity
	 * @return DefaultTokenEntity if parameters is valid
	 * @throws CheckedException if parameters is invalid
	 */
	public static Object getEntity(HttpServletRequest request)
			throws CheckedException {
		try {
			// Get token from HTTP header, query string or cookie.
			String token = extractHelper.getValueFromRequest(request,
					AuthFields.TOKEN);

			return new DefaultTokenEntity(token);
		} catch (ValueNotFoundException e) {
			throw new CheckedException(ErrorConsts.EC_BAD_REQUEST,
					ErrorConsts.MSG_BAD_REQUEST);
		}
	}
	
}
