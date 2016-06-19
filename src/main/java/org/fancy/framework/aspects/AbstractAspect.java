package org.fancy.framework.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Abstract aspect class declares all interface of aspects contains pointcut
 * and a serious of advice function
 * 
 * @author Dongfan Yang
 * @time 2016年3月3日
 */
public abstract class AbstractAspect {

	/**
	 * Advice which execute before target method
	 * 
	 * @param jp
	 *            the join point which can get target object info
	 * @return void
	 * @throws Exception
	 */
	protected void doBefore(JoinPoint jp) throws Exception {};

	/**
	 * Advice which execute around target method
	 * 
	 * @param pjp
	 *            the proceeding join point which can invoke target method
	 * @return the return value of target method after handled
	 * @throws throwable
	 *             the throwable may thrown in target method
	 */
	protected Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		return pjp;
	};

	/**
	 * Advice which execute after target method
	 * 
	 * @param jp
	 *            the join point which can get target object info
	 * @return void
	 * @throws Exception
	 */
	protected void doAfter(JoinPoint jp) throws Exception {};

	/**
	 * Advice which execute after target method returned
	 * 
	 * @param jp
	 *            the join point which can get target object info
	 * @param result
	 *            the result return by target method, the value of 'returning'
	 *            property in <aop:after-returning/> node if it exists must be
	 *            'result'
	 * @return void
	 * @throws Exception
	 */
	protected void doAfterReturning(JoinPoint jp, Object result)
			 throws Exception{};

	/**
	 * Advice which execute after target method thrown exception
	 * 
	 * @param jp
	 *            the join point which can get target object info
	 * @param ex
	 *            the exception or error thrown in target method, the value of
	 *            'throwing' property in <aop:after-throwing/> node if it exists
	 *            must be 'ex'
	 * @return void
	 * @throws Exception
	 */
	protected void doAfterThrowing(JoinPoint jp, Throwable ex)
			 throws Exception {};

}
