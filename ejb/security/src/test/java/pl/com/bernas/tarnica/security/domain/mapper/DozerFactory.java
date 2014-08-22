package pl.com.bernas.tarnica.security.domain.mapper;

public class DozerFactory {
	private static volatile Dozer instance = null;

	public static Dozer prepareDoozerForTests() {
		if (instance == null) {
			synchronized (DozerFactory.class) {
				if (instance == null) {
					instance = new Dozer();
					instance.init();
				}
			}
		}

		return instance;
	}
}
