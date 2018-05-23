package nc.vo.hzjy.pub;

import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.er.exception.SystemRuntimeException;

public class ExceptionUtils {
	private static ExceptionUtils instance = new ExceptionUtils();

	private ExceptionUtils() {
	}

	public static ExceptionUtils getInstance() {
		return ExceptionUtils.instance;
	}

	/**
	 * 在EJB边界对所有的异常进行BusinessException包装
	 * 
	 * @param ex
	 *            Exception
	 * @throws BusinessException
	 */
	public void marsh(Exception ex) throws BusinessException {
		Throwable cause = this.unmarsh(ex);
		Log.getInstance().error(cause);

		if (cause instanceof BusinessException) {
			throw (BusinessException) cause;
		} else if (cause instanceof BusinessRuntimeException) {
			throw new BusinessException(cause);
		} else if (cause instanceof TransferException) {
			throw new BusinessException(cause);
		} else {
			TransferException tex = new TransferException(cause);
			throw new BusinessException(tex);
		}
	}

	public Throwable unmarsh(Throwable ex) {
		Throwable cause = ex.getCause();
		if (cause != null) {
			cause = this.unmarsh(cause);
		} else {
			cause = ex;
		}
		return cause;
	}

	public void wrappBusinessException(String message) {
		CustDevBusinessException ex = new CustDevBusinessException(message);
		throw new SystemRuntimeException(ex);
	}

	public void wrappBusinessException(String message, String location) {
		CustDevBusinessException ex = new CustDevBusinessException(message,
				location);
		throw new SystemRuntimeException(ex);
	}

	public void wrappBusinessException(String message, Throwable ex) {
		CustDevBusinessException exception = new CustDevBusinessException(
				message, ex);
		throw new SystemRuntimeException(exception);
	}

	public void wrappBusinessException(String message, String location,
			Throwable ex) {
		CustDevBusinessException exception = new CustDevBusinessException(
				message, location, ex);
		throw new SystemRuntimeException(exception);
	}

	public void wrappException(Exception ex) {
		throw new SystemRuntimeException(ex);
	}

	public void unSupported() {
		String message = "不支持此种业务，请检查";
		CustDevBusinessException ex = new CustDevBusinessException(message);
		throw new SystemRuntimeException(ex);
	}

	public void notImplement() {
		String message = "还没有实现此功能";
		CustDevBusinessException ex = new CustDevBusinessException(message);
		throw new SystemRuntimeException(ex);
	}

}
