package factory;

import java.util.ResourceBundle;

/*
 * ���������ڴ�������ʵ��
 */
public class BeanFactory {
	/**
	 * ���������ļ�
	 */
	private static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("instance");
	}

	/*
	 * ����ָ����KEY����ȡ�����ļ��Ļ�ȡ���ȫ·��
	 */
	public static <T> T getInstance(String key, Class<T> clazz) {
		String className = bundle.getString(key);

		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
