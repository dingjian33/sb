package nc.vo.hzyb.pub;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import nc.vo.pub.BusinessException;

/**
 * �ͻ���������ҵ���쳣��
 * ֮��������stackTrace���������Ƿ�ֹ���쳣������ǰ̨��ʱ������ȱ����Ӧ���쳣��ջ
 * �е�class������class not found�Ĵ��󡣶�����ϵͳ�ܹ���ǰ̨��ӡ��ص��쳣��ջ��
 * ����ϵͳ�Ŀ�ά�����кܴ����ߡ���ΪĳЩ��Ŀ�����ʱ���Ǹ�����������ϴ������ġ�
 * �ڼ�Ⱥ������£���̨��־�ڶ࣬Ҫǰ��ʵʩ�ҵ�׼ȷ����־�ļ�����������Ŀǰ��������
 * Ҳ�Ƕ����ǵ�һ����������ս��
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
