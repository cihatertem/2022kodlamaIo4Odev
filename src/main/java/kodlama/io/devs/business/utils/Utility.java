package kodlama.io.devs.business.utils;

import kodlama.io.devs.business.requests.frameworkRequests.FrameworkRequest;
import kodlama.io.devs.business.responses.frameworkResponses.FrameworkResponseWithoutLanguage;
import kodlama.io.devs.entities.Framework;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static void checkLanguageNameIsBlank(String name) throws Exception {
        if (name == null || name.isBlank()) {
            throw new Exception("İsim alanı boş olamaz.");
        }
    }

    public static void checkLanguageIdIsNull(FrameworkRequest request) throws Exception {
        if (request.getLanguageId() == null) throw new Exception("Dil alanı boş olamaz!");

    }

    public static List<FrameworkResponseWithoutLanguage> mapFrameworkEntityToFrameworkResponseWithoutLanguage(List<Framework> frameworks) {
        List<FrameworkResponseWithoutLanguage> frameworkResponses = new ArrayList<>();

        for (Framework framework : frameworks) {
            FrameworkResponseWithoutLanguage frameworkResponse = new FrameworkResponseWithoutLanguage();
            frameworkResponse.setId(framework.getId());
            frameworkResponse.setName(framework.getName());
            frameworkResponses.add(frameworkResponse);
        }
        return frameworkResponses;
    }
}
