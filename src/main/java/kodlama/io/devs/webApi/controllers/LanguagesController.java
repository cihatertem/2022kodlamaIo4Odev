package kodlama.io.devs.webApi.controllers;

import kodlama.io.devs.business.abstracts.LanguageService;
import kodlama.io.devs.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
    private LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("")
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @GetMapping("/{id}")
    public Language getSingleLanguage(@PathVariable int id) {
        return languageService.getSingleLanguage(id);
    }

    @PostMapping("")
    public Language createNewLanguage(@RequestBody Language language) throws Exception {
        return languageService.createNewLanguage(language.getName());
    }

    @PutMapping("/{id}")
    public Language updateLanguage(@PathVariable int id, @RequestBody Language language) throws Exception {
        language.setId(id);
        return languageService.updateLanguage(language);
    }

    @DeleteMapping("/{id}")
    public String deleteLanguage(@PathVariable int id) {
        return languageService.deleteLanguage(id);
    }

}
