package payloads;

import POJO.CreateRepoRequest;

public class PatchRepoPayloadBuilder {

    public static CreateRepoRequest updateNameAndDescription(String Name,String description) {
        return CreateRepoRequest.builder()
                .name(Name)
                .description(description)
                .build();
    }

    public static CreateRepoRequest updateNameDescriptionVisibility(String Name,String description,boolean visibility) {
        return CreateRepoRequest.builder()
                .name(Name)
                .description(description)
                .isPrivate(visibility)
                .build();
    }

    public static CreateRepoRequest updateName(String name) {
        return CreateRepoRequest.builder()
                .name(name)
                .build();
    }

    public static CreateRepoRequest updateDescription(String description) {
        return CreateRepoRequest.builder()
                .description(description)
                .build();
    }

    public static CreateRepoRequest updateVisibility(boolean isPrivate) {
        return CreateRepoRequest.builder()
                .isPrivate(isPrivate)
                .build();
    }

    public static CreateRepoRequest updateWiki(boolean hasWiki) {
        return CreateRepoRequest.builder()
                .hasWiki(hasWiki)
                .build();
    }
}
