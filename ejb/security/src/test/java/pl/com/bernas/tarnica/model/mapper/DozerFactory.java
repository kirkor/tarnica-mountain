package pl.com.bernas.tarnica.model.mapper;

import pl.com.bernas.tarnica.model.mapper.Dozer;

public class DozerFactory {
	private static volatile Dozer instance = null;

	private DozerFactory() {
	}

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
