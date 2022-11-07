package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.LanguageService;
import kodlama.io.devs.business.requests.languageRequests.LanguageRequest;
import kodlama.io.devs.business.responses.frameworkResponses.FrameworkResponseWithoutLanguage;
import kodlama.io.devs.business.responses.languageResponses.LanguageResponse;
import kodlama.io.devs.business.utils.Utility;
import kodlama.io.devs.dataAccess.abstracts.LanguageRepository;

import kodlama.io.devs.entities.Framework;
import kodlama.io.devs.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LanguageManager implements LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageManager(
            LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }


    @Override
    public List<LanguageResponse> getAllLanguages() {
        List<Language> languages = languageRepository.findAll();
        List<LanguageResponse> languageResponses = new ArrayList<>();

        for (Language language : languages) {
            LanguageResponse response = new LanguageResponse();
            List<Framework> frameworks = language.getFrameworks();

            List<FrameworkResponseWithoutLanguage> mappedFrameworks =
                    Utility.mapFrameworkEntityToFrameworkResponseWithoutLanguage(frameworks);

            response.setId(language.getId());
            response.setName(language.getName());
            response.setFrameworks(mappedFrameworks);
            languageResponses.add(response);
        }

        return languageResponses;
    }

    @Override
    public LanguageResponse getSingleLanguage(int id) throws Exception {
        Optional<Language> language = languageRepository.findById(id);
        LanguageResponse singleLanguageResponse = new LanguageResponse();
        if (language.isPresent()) {
            singleLanguageResponse.setId(language.get().getId());
            singleLanguageResponse.setName(language.get().getName());
            List<Framework> frameworks = language.get().getFrameworks();

            List<FrameworkResponseWithoutLanguage> mappedFrameworks =
                    Utility.mapFrameworkEntityToFrameworkResponseWithoutLanguage(frameworks);

            singleLanguageResponse.setFrameworks(mappedFrameworks);
            return singleLanguageResponse;
        } else {
            throw new Exception("Bu alanda kayıtlı dil yok.");
        }
    }

    @Override
    public String createNewLanguage(LanguageRequest request) throws Exception {
        Language language = new Language();

        Utility.checkLanguageNameIsBlank(request.getName());

        language.setName(request.getName().toLowerCase());

        try {
            languageRepository.save(language);
            return "Yeni dil kaydedildi.";
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new Exception("Bu isimde bir dil mevcut");
        }
    }

    @Override
    public String updateLanguage(LanguageRequest createUpdateLanguageRequest, int id) throws Exception {
        Optional<Language> language = languageRepository.findById(id);
        if (language.isPresent()) {
            language.get().setName(createUpdateLanguageRequest.getName());

            try {
                languageRepository.save(language.get());
            } catch (DataIntegrityViolationException exception) {
                throw new Exception("Bu isimde bir dil mevcut!");
            }

            return "Güncelleme işlemi başarı ile yapıldı!";
        }
        throw new Exception("Bu alanda bir dil mevcut değil.");
    }

    @Override
    public String deleteLanguage(int id) throws Exception {
        try {
            languageRepository.deleteById(id);
            return "Dil başarıyle silindi.";
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Bu alanda kayıtlı bir dil yok.");
        }
    }
}
