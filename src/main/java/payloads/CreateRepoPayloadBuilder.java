package payloads;
import POJO.CreateRepoRequest;

public class CreateRepoPayloadBuilder {

    public static CreateRepoRequest createRepoPayload(String name) {

        return CreateRepoRequest.builder()
                .name(name)
                .description("Repository created via RestAssured automation")
                .isPrivate(false)
                .hasIssues(true)
                .hasProjects(true)
                .hasWiki(true)
                .autoInit(true)
                .gitignoreTemplate("Java")
                .licenseTemplate("mit")
                .build();
    }

    // Overloaded method (custom values)
    public static CreateRepoRequest createRepoPayload(String name, boolean isPrivate, boolean hasWiki) {
        return CreateRepoRequest.builder()
                .name(name)
                .isPrivate(isPrivate)
                .hasWiki(hasWiki)
                .autoInit(true)
                .build();
    }

    public static CreateRepoRequest createRepoPayload(String name,String desc, boolean isPrivate) {
        return CreateRepoRequest.builder()
                .name(name)
                .description(desc)
                .isPrivate(isPrivate)
                .build();
    }


}
