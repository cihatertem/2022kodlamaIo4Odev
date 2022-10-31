package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.LanguageService;
import kodlama.io.devs.dataAccess.abstracts.LanguageRepository;
import kodlama.io.devs.entities.Language;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageManager implements LanguageService {
    private LanguageRepository languageRepository;

    public LanguageManager(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.getAllLanguages();
    }

    @Override
    public Language getSingleLanguage(int id) {
        return languageRepository.getSingleLanguage(id);
    }

    @Override
    public Language createNewLanguage(String name) throws Exception {
        checkEmptyLanguageName(name);
        checkDuplicationLanguageName(name);
        return languageRepository.createNewLanguage(name);
    }

    @Override
    public Language updateLanguage(Language language) throws Exception {
        checkEmptyLanguageName(language.getName());
        checkDuplicationLanguageName(language.getName());
        return languageRepository.updateLanguage(language);
    }

    @Override
    public String deleteLanguage(int id) {
        return languageRepository.deleteLanguage(id);
    }

    private void checkDuplicationLanguageName(String name) throws Exception {
        List<Language> languages = languageRepository.getAllLanguages();
        if (languages.stream().anyMatch(item -> item.getName().equalsIgnoreCase(name))) {
            throw new Exception("Bu isimde dil mevcut!");
        }
    }

    private void checkEmptyLanguageName(String name) throws Exception {
        if (name == null || name.isBlank()) throw new Exception("İsim alanı boş olamaz");
    }
}
