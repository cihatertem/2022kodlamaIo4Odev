package kodlama.io.devs.webApi.controllers;

import kodlama.io.devs.business.abstracts.LanguageService;
import kodlama.io.devs.business.requests.languageRequests.LanguageRequest;
import kodlama.io.devs.business.responses.languageResponses.LanguageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
    private final LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("")
    public List<LanguageResponse> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @GetMapping("/{id}")
    public LanguageResponse getSingleLanguage(@PathVariable int id) throws Exception {
        return languageService.getSingleLanguage(id);
    }

    @PostMapping("")
    public String createNewLanguage(@RequestBody LanguageRequest createUpdateLanguageRequest) throws Exception {
        return languageService.createNewLanguage(createUpdateLanguageRequest);
    }

    @PutMapping("/{id}")
    public String updateLanguage(@RequestBody LanguageRequest createUpdateLanguageRequest, @PathVariable int id) throws Exception {
        return languageService.updateLanguage(createUpdateLanguageRequest, id);
    }

    @DeleteMapping("/{id}")
    public String deleteLanguage(@PathVariable int id) throws Exception {
        return languageService.deleteLanguage(id);
    }
}
