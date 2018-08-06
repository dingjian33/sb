package nc.vo.hzyb.pub;

import java.util.List;


/**
 * ���������ת�����ߡ�ע�⣬����������Ǹ��������ʹ�õġ���Ϊ������ܲ���֪��
 * ������������͡�ҵ�������Ͳ�Ҫʹ�ô��࣬��Ϊҵ����һ��֪�����������������
 * ʲô��������ܴ�����ʹ�õ�ʱ��һ��Ҫ���Ǵ�����Ϊ0������
 * <p>
 * <b>���������ĳ��Ȳ���Ϊ0</b>
 * 
 * @param <E> Ҫת�����������
 */
public class ListToArrayTool<E> {
  /**
   * Ҫ������ת��Ϊ����Ķ����class
   */
  private Class<E> clazz;

  /**
   * ���������ת�����ߵĹ��캯��
   */
  public ListToArrayTool() {
    // Ĭ�Ϲ��췽��
  }

  /**
   * ���������ת�����ߵĹ��캯��
   * 
   * @param clazz Ҫת�������class
   */
  public ListToArrayTool(Class<E> clazz) {
    this.clazz = clazz;
  }

  /**
   * ������ת��Ϊ����
   * 
   * @param list ����
   * @return ת���������
   */
  @SuppressWarnings({
    "unchecked", "cast"
  })
  public E[] convertToArray(List<E> list) {
    int size = list.size();
    if (size == 0) {
      // list������Ϊ0��Ȼ�����޷�֪��ȷ�е����ͣ�ֻ�ܲ�֧��
      if (this.clazz == null) {
        ExceptionUtils.getInstance().unSupported();
      }
      else {
        E[] instances = (E[]) Constructor.construct(this.clazz, 0);
        return instances;
      }
    }
    Class<?> objClass = (Class<?>) list.get(0).getClass();
    E[] instances = (E[]) Constructor.construct(objClass, size);
    instances = list.toArray(instances);
    return instances;
  }
}
