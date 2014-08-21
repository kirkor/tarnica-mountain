package pl.beriko.ioz.web.i18n;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "language")
@SessionScoped
public class LanguageBean implements Serializable {
	private static final long serialVersionUID = 1707550833539794602L;

	private Locale current;

	private static final List<Locale> LOCALES = new ArrayList<Locale>();

	private static final Locale PL = new Locale("pl", "PL");
	private static final Locale EN = Locale.ENGLISH;

	private static final Map<Locale, Map<String, Locale>> COUNTRIES = new LinkedHashMap<Locale, Map<String, Locale>>();

	private static final Map<String, Locale> PL_COUNTRIES = new LinkedHashMap<String, Locale>();
	private static final Map<String, Locale> EN_COUNTRIES = new LinkedHashMap<String, Locale>();

	static {
		LOCALES.add(PL);
		LOCALES.add(EN);

		// TODO: change to messages

		PL_COUNTRIES.put("Angielski", EN);
		PL_COUNTRIES.put("Polski", PL);

		EN_COUNTRIES.put("English", EN);
		EN_COUNTRIES.put("Polish", PL);

		COUNTRIES.put(PL, PL_COUNTRIES);
		COUNTRIES.put(EN, EN_COUNTRIES);
	}

	public LanguageBean() {
		String country = FacesContext.getCurrentInstance().getViewRoot().getLocale().getCountry();
		String language = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();

		current = new Locale(language, country);

		if (!LOCALES.contains(current)) {
			current = EN;
		}
	}

	public Map<String, Locale> getCountriesInMap() {
		return COUNTRIES.get(current);
	}

	public String getLocaleCode() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			System.out.println(request.getAuthType());
			System.out.println(request.getUserPrincipal());
			System.out.println(request.isUserInRole("ADMIN"));
		}
		
		
		
		return current.getLanguage() + "_" + current.getCountry();
	}

	public void setLocaleCode(String localeCode) {
		String localeCodes[] = localeCode.split("_");

		if (localeCodes.length > 1 && localeCodes[1] != null) {
			this.current = new Locale(localeCodes[0], localeCodes[1]);
		} else {
			this.current = new Locale(localeCodes[0]);
		}
	}

	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		if (e.getNewValue() != null) {

			String newLocaleValue[] = e.getNewValue().toString().split("_");
			Locale tmp = null;

			if (newLocaleValue.length > 1 && newLocaleValue[1] != null) {
				tmp = new Locale(newLocaleValue[0], newLocaleValue[1]);
			} else {
				tmp = new Locale(newLocaleValue[0]);
			}

			if (LOCALES.contains(tmp)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale(tmp);
			} else {
				FacesContext.getCurrentInstance().getViewRoot().setLocale(EN);
			}
		}
	}
}
