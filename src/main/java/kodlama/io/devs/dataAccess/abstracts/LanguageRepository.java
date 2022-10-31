package kodlama.io.devs.dataAccess.abstracts;

import kodlama.io.devs.entities.Language;

import java.util.List;

public interface LanguageRepository {
    List<Language> getAllLanguages();

    Language getSingleLanguage(int id);

    Language createNewLanguage(String name);

    Language updateLanguage(Language language);

    String deleteLanguage(int id);
}
