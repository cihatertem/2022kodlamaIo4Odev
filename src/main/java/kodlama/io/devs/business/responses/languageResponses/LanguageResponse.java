package kodlama.io.devs.business.responses.languageResponses;

import kodlama.io.devs.business.responses.frameworkResponses.FrameworkResponseWithoutLanguage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageResponse {
    private int id;
    private String name;
    private List<FrameworkResponseWithoutLanguage> frameworks;
}
