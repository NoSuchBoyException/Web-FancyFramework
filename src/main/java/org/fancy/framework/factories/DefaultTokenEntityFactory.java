package org.fancy.framework.factories;

import javax.servlet.http.HttpServletRequest;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.constants.FieldConsts.AuthFields;
import org.fancy.framework.entities.DefaultTokenEntity;
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
	 * @return DefaultTokenEntity if parameters is valid, otherwise
	 *         CheckedException
	 */
	public static Object getEntity(HttpServletRequest request, Object[] params) {
		try {
			// Get token from HTTP header, query string or cookie.
			String token = extractHelper.getValueFromRequest(request,
					AuthFields.TOKEN);

			return new DefaultTokenEntity(token);
		} catch (ValueNotFoundException e) {
			return new CheckedException(ErrorConsts.EC_MISSING_TOKEN,
					ErrorConsts.MSG_MISSING_TOKEN);
		}
	}
	
}
