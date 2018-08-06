package nc.vo.hzyb.pub;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import nc.vo.pub.BusinessException;

/**
 * 客户化开发的业务异常。
 * 之所以增加stackTrace变量，就是防止在异常传播到前台的时候，由于缺少相应的异常堆栈
 * 中的class，导致class not found的错误。而我们系统能够在前台打印相关的异常堆栈，
 * 对于系统的可维护性有很大的提高。因为某些项目出错的时候，是根本不会给你上传环境的。
 * 在集群的情况下，后台日志众多，要前方实施找到准确的日志文件传回来，就目前看来，这
 * 也是对他们的一个严厉的挑战。
 */
public class CustDevBusinessException extends BusinessException {
  private static final long serialVersionUID = -5506475841265255052L;

  private String location = null;

  private String stackTrace = null;

  public CustDevBusinessException(
      String message) {
    super(message);
  }

  public CustDevBusinessException(
      String message, String location) {
    super(message);
    this.location = location;
  }

  public CustDevBusinessException(
      String message, Throwable ex) {
    super(message);
    this.stackTrace = this.getStackTrace(ex);
  }

  public CustDevBusinessException(
      String message, String location, Throwable ex) {
    super(message);
    this.location = location;
    this.stackTrace = this.getStackTrace(ex);
  }

  public String getLocation() {
    return this.location;
  }

  private String getStackTrace(Throwable ex) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw, true);
    ex.printStackTrace(pw);
    return sw.getBuffer().toString();
  }

  @Override
  public void printStackTrace() {
    if (this.stackTrace != null) {
      this.printStackTrace(System.err);
    }
    else {
      super.printStackTrace();
    }
  }

  @Override
  public void printStackTrace(PrintWriter s) {
    if (this.stackTrace != null) {
      synchronized (s) {
        s.print(this.stackTrace);
      }
    }
    else {
      super.printStackTrace(s);
    }
  }

  @Override
  public void printStackTrace(PrintStream s) {
    if (this.stackTrace != null) {
      synchronized (s) {
        s.print(this.stackTrace);
      }
    }
    else {
      super.printStackTrace(s);
    }
  }

}
