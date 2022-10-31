package kodlama.io.devs.dataAccess.concretes;

import kodlama.io.devs.dataAccess.abstracts.LanguageRepository;
import kodlama.io.devs.entities.Language;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryLanguageRepository implements LanguageRepository {
    public List<Language> languages;

    public InMemoryLanguageRepository() {
        languages = new ArrayList<>();
        languages.add(new Language(1, "C#"));
        languages.add(new Language(2, "Java"));
        languages.add(new Language(3, "Python"));
    }

    @Override
    public List<Language> getAllLanguages() {
        return languages;
    }

    @Override
    public Language getSingleLanguage(int id) {
        return languages.stream().filter(item -> item.getId() == id)
                .toList()
                .get(0);
    }

    @Override
    public Language createNewLanguage(String name) {
        int id = languages.get(languages.size() - 1).getId() + 1;
        languages.add(new Language(id, name));
        return languages.stream()
                .filter(item -> item.getId() == id)
                .toList()
                .get(0);
    }

    @Override
    public Language updateLanguage(Language language) {
        Language oldLang = languages.stream()
                .filter(item -> item.getId() == language.getId())
                .toList()
                .get(0);
        oldLang.setName(language.getName());
        return oldLang;
    }

    @Override
    public String deleteLanguage(int id) {
        languages = languages.stream().filter(item -> item.getId() != id).collect(Collectors.toList());
        return "Dil silindi.";
    }
}
