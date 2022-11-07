package kodlama.io.devs.business.abstracts;

import kodlama.io.devs.business.requests.languageRequests.LanguageRequest;
import kodlama.io.devs.business.responses.languageResponses.LanguageResponse;

import java.util.List;


public interface LanguageService {
    List<LanguageResponse> getAllLanguages();

    LanguageResponse getSingleLanguage(int id) throws Exception;

    String createNewLanguage(LanguageRequest languageRequest) throws Exception;

    String updateLanguage(LanguageRequest createUpdateLanguageRequest, int id) throws Exception;

    String deleteLanguage(int id) throws Exception;
}
