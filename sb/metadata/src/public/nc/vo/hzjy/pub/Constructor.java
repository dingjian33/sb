package nc.vo.hzjy.pub;

import java.lang.reflect.Array;

/**
 * VO������
 */
public class Constructor {
	private Constructor() {
		// ȱʡ���췽��
	}

	/**
	 * ����һ�����͵�ʵ������ǰ�������Ĭ�Ϲ��췽��
	 * 
	 * @param <T>
	 *            Ҫ������������
	 * @param voclass
	 *            Ҫ����ʵ����Class
	 * @return ������ɵ�ʵ��
	 */
	public static <T> T construct(Class<T> voclass) {
		T instance = null;
		try {
			instance = voclass.newInstance();
		} catch (InstantiationException ex) {
			ExceptionUtils.getInstance().wrappException(ex);
		} catch (IllegalAccessException ex) {
			ExceptionUtils.getInstance().wrappException(ex);
		}
		return instance;
	}

	/**
	 * ����һ�����͵�����ʵ����ͬʱ�����е�ֵ�Ѿ���ʼ��
	 * 
	 * @param <T>
	 *            Ҫ�������������
	 * @param voclass
	 *            �����Class
	 * @param size
	 *            ����ĳ���
	 * @return Ԫ���Ѿ���ʼ��������
	 */
	public static <T> T[] construct(Class<T> voclass, int size) {
		T[] instances = Constructor.declareArray(voclass, size);
		for (int i = 0; i < size; i++) {
			instances[i] = Constructor.construct(voclass);
		}
		return instances;
	}

	/**
	 * ����һ�����͵����顣�����е�Ԫ��û�г�ʼ��
	 * 
	 * @param <T>
	 *            Ҫ�������������
	 * @param voclass
	 *            �����Class
	 * @param size
	 *            Ҫ��������鳤��
	 * @return Ԫ��û�г�ʼ��������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] declareArray(Class<T> voclass, int size) {
		T[] instances = (T[]) Array.newInstance(voclass, size);
		return instances;
	}

}
