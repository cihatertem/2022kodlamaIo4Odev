package kodlama.io.devs.business.requests.frameworkRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrameworkRequest {
    private String name;
    private Integer languageId;
}
