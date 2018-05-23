package nc.vo.hzyb.pub;

import nc.bs.logging.Logger;

/**
 * 日志操作类
 */
public class Log {
  private static Log instance = new Log();

  private Log() {
  }

  public static Log getInstance() {
    return Log.instance;
  }

  public void error(Throwable ex) {
    Logger.error(ex.getMessage(), ex);
  }

  public void info(String message) {
    Logger.error( message );
  }

  public void info(Object obj) {
    Logger.error(obj);
  }

  public void debug(String message) {
    Logger.debug(message);
  }
}
