package nc.vo.hzsb.pub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * MapListά����һ��key��һ���б��ӳ���ϵ��ÿ�η���һ����ֵ��ʱ�� �Ὣֵ���ڸü���Ӧ���б��У�����б��� ���򴴽��б�
 * 
 * @param <K>
 *            ��������
 * @param <V>
 *            ֵ������
 */
public class MapList<K, V> implements Serializable {
	private static final long serialVersionUID = -4970977770408734801L;

	/**
	 * ���key���б�ӳ���ϵ�����ݼ���
	 */
	private Map<K, List<V>> map = new HashMap<K, List<V>>();

	/**
	 * �Ƿ������ǰ��
	 * 
	 * @param key
	 *            ��
	 * @return ��ǰMapList�����˼�ʱ������true
	 */
	public boolean containsKey(K key) {
		return this.map.containsKey(key);
	}

	/**
	 * ���ݼ���ȡ�б�
	 * 
	 * @param key
	 *            ��
	 * @return ����Ӧ���б�
	 */
	public List<V> get(K key) {
		return this.map.get(key);
	}

	/**
	 * ���ݼ��Ƴ�ֵ
	 * 
	 * @param key
	 *            ��
	 * @return ����Ӧ���б�
	 */
	public List<V> remove(K key) {
		return this.map.remove(key);
	}

	/**
	 * ��ȡ���ļ���
	 * 
	 * @return ���ļ���
	 */
	public Set<K> keySet() {
		return this.map.keySet();
	}

	/**
	 * ��ȡ��ǰMapList����ͼ���������ٷ�������洢��Ԫ��
	 * 
	 * @return ��ǰMapList����ͼ
	 */
	public Set<Entry<K, List<V>>> entrySet() {
		return this.map.entrySet();
	}

	/**
	 * ����һ����ֵ�ԡ���ǰ��������ʱ�����Զ������б����򣬽�ֵ���뵽��Ӧ���б���
	 * 
	 * @param key
	 *            ��
	 * @param value
	 *            ֵ
	 */
	public void put(K key, V value) {
		List<V> l = this.map.get(key);
		if (l == null) {
			l = new ArrayList<V>();
			this.map.put(key, l);
		}
		l.add(value);
	}

	/**
	 * ��һ��ֵ�б�ȫ�����뵽��ֵ����
	 * 
	 * @param key
	 *            ��
	 * @param valueList
	 *            ֵ���б�
	 */
	public void putAll(K key, List<V> valueList) {
		List<V> l = this.map.get(key);
		if (l == null) {
			l = new ArrayList<V>();
			this.map.put(key, l);
		}
		l.addAll(valueList);
	}

	/**
	 * �õ���������
	 * 
	 * @return ��ǰMapList�Ĵ�С
	 */
	public int size() {
		return this.map.size();
	}

	/**
	 * ת��Ϊԭʼ��Map����
	 * 
	 * @return JDK�������ݽṹ��ʽ
	 */
	public Map<K, List<V>> toMap() {
		return this.map;
	}
}
