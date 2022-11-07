package kodlama.io.devs.business.responses.frameworkResponses;


import kodlama.io.devs.business.responses.languageResponses.LanguageResponseWithoutFrameworks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrameworkResponse {
    private int id;
    private String name;
    private LanguageResponseWithoutFrameworks language;
}
