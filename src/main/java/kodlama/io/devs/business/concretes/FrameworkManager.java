package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.FrameworkService;
import kodlama.io.devs.business.requests.frameworkRequests.FrameworkRequest;
import kodlama.io.devs.business.responses.frameworkResponses.FrameworkResponse;
import kodlama.io.devs.business.responses.languageResponses.LanguageResponseWithoutFrameworks;
import kodlama.io.devs.business.utils.Utility;
import kodlama.io.devs.dataAccess.abstracts.FrameworkRepository;
import kodlama.io.devs.dataAccess.abstracts.LanguageRepository;
import kodlama.io.devs.entities.Framework;
import kodlama.io.devs.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FrameworkManager implements FrameworkService {

    private final FrameworkRepository frameworkRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public FrameworkManager(
            FrameworkRepository frameworkRepository,
            LanguageRepository languageRepository
    ) {
        this.languageRepository = languageRepository;
        this.frameworkRepository = frameworkRepository;
    }

    @Override
    public String createNewFramework(FrameworkRequest request) throws Exception {
        Framework framework = new Framework();

        Utility.checkLanguageNameIsBlank(request.getName());

        framework.setName(request.getName().toLowerCase());

        Utility.checkLanguageIdIsNull(request);


        try {
            Optional<Language> language = languageRepository.findById(request.getLanguageId());
            if (language.isPresent()) {
                framework.setLanguage(language.get());
            } else {
                throw new Exception("Bu alanda kayıtlı bir dil yok.");
            }
        } catch (NoSuchElementException noSuchElementException) {
            throw new Exception("Böyle bir dil mevcut değil");
        }

        try {
            frameworkRepository.save(framework);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new Exception("Bu isimde bir framework mevcut.");
        }

        return "Framework başarıyla kayıt edildi.";
    }

    @Override
    public List<FrameworkResponse> getAllFrameworks() {
        List<Framework> frameworks = frameworkRepository.findAll();
        List<FrameworkResponse> frameworkResponses = new ArrayList<>();

        for (Framework framework : frameworks) {
            FrameworkResponse frameworkResponse = new FrameworkResponse();
            frameworkResponse.setId(framework.getId());
            frameworkResponse.setName(framework.getName());

            LanguageResponseWithoutFrameworks languageResponse = new LanguageResponseWithoutFrameworks();
            languageResponse.setId(framework.getLanguage().getId());
            languageResponse.setName(framework.getLanguage().getName());

            frameworkResponse.setLanguage(languageResponse);

            frameworkResponses.add(frameworkResponse);
        }


        return frameworkResponses;
    }

    @Override
    public FrameworkResponse getSingleFramework(int id) throws Exception {
        Optional<Framework> framework = frameworkRepository.findById(id);

        if (framework.isPresent()) {
            LanguageResponseWithoutFrameworks languageResponse = new LanguageResponseWithoutFrameworks();
            languageResponse.setId(framework.get().getLanguage().getId());
            languageResponse.setName(framework.get().getLanguage().getName());

            FrameworkResponse frameworkResponse = new FrameworkResponse();

            frameworkResponse.setId(framework.get().getId());
            frameworkResponse.setName(framework.get().getName());
            frameworkResponse.setLanguage(languageResponse);

            return frameworkResponse;
        }
        throw new Exception("Bu alanda kayıtlı bir framework yok.");
    }

    @Override
    public String updateFramework(FrameworkRequest request, int id) throws Exception {
        Optional<Framework> framework = frameworkRepository.findById(id);

        if (framework.isPresent()) {
            Utility.checkLanguageNameIsBlank(request.getName());

            framework.get().setName(request.getName().toLowerCase());

            Utility.checkLanguageIdIsNull(request);

            try {
                Optional<Language> language = languageRepository.findById(request.getLanguageId());
                if (language.isPresent()) {
                    framework.get().setLanguage(language.get());
                } else {
                    throw new Exception("Bu alanda kayıtlı bir dil yok.");
                }
            } catch (NoSuchElementException e) {
                throw new Exception("Bu alanda bir dil mevcut değil.");
            }

            try {
                frameworkRepository.save(framework.get());
            } catch (DataIntegrityViolationException e) {
                throw new Exception("Bu isimde kayıtlı bir framework mevcut.");
            }

            return "Framework başarıyla güncellendi.";
        }
        throw new Exception("Bu alanda kayıtlı bir framework yok.");
    }

    @Override
    public String deleteFramework(int id) throws Exception {
        try {
            frameworkRepository.deleteById(id);
            return "Framework başarı ile silindi.";
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Bu alanda kayıtlı bir dil yok.");
        }
    }
}
