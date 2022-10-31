package kodlama.io.devs.business.abstracts;

import kodlama.io.devs.entities.Language;

import java.util.List;

public interface LanguageService {
    List<Language> getAllLanguages();

    Language getSingleLanguage(int id);

    Language createNewLanguage(String name) throws Exception;

    Language updateLanguage(Language language) throws Exception;

    String deleteLanguage(int id);
}
