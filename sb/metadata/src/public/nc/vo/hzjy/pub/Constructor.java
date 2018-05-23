package nc.vo.hzjy.pub;

import java.lang.reflect.Array;

/**
 * VO构造器
 */
public class Constructor {
	private Constructor() {
		// 缺省构造方法
	}

	/**
	 * 构造一个类型的实例。当前类必须有默认构造方法
	 * 
	 * @param <T>
	 *            要构造对象的类型
	 * @param voclass
	 *            要构造实例的Class
	 * @return 构造完成的实例
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
	 * 构造一个类型的数组实例，同时数组中的值已经初始化
	 * 
	 * @param <T>
	 *            要构造数组的类型
	 * @param voclass
	 *            数组的Class
	 * @param size
	 *            数组的长度
	 * @return 元素已经初始化的数组
	 */
	public static <T> T[] construct(Class<T> voclass, int size) {
		T[] instances = Constructor.declareArray(voclass, size);
		for (int i = 0; i < size; i++) {
			instances[i] = Constructor.construct(voclass);
		}
		return instances;
	}

	/**
	 * 构造一个类型的数组。数组中的元素没有初始化
	 * 
	 * @param <T>
	 *            要构造数组的类型
	 * @param voclass
	 *            数组的Class
	 * @param size
	 *            要构造的数组长度
	 * @return 元素没有初始化的数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] declareArray(Class<T> voclass, int size) {
		T[] instances = (T[]) Array.newInstance(voclass, size);
		return instances;
	}

}
