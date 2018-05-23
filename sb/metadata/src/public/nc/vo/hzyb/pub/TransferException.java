package nc.vo.hzyb.pub;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import nc.vo.pub.BusinessException;

/**
 * 
 * ����̨���쳣��ջ���ݵ�ǰ̨������������ɺ�̨���쳣ֱ�Ӵ��ݵ�ǰ̨������ǰ̨
 * û����ص��쳣�࣬�����·����л�ʧ��
 * 
 */
public class TransferException extends BusinessException {
  private static final long serialVersionUID = -3036090600059961398L;
  
  private String stackTrace = null;

  public TransferException(
      Throwable exception) {
    super( exception.getMessage() );
    this.stackTrace = this.getStackTrace(exception);
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

  public String getInnerStackTrace(){
    return this.stackTrace;
  }
}
