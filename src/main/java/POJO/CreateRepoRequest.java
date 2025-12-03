package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateRepoRequest {
    private String name;
    private String description;
    @JsonProperty("private")
    private boolean isPrivate;
    @JsonProperty("has_issues")
    private boolean hasIssues;     //if "FALSE" issue tab remove ho jayega
    @JsonProperty("has_projects")
    private boolean hasProjects;       //if "False" project tab remove ho jayega
    @JsonProperty("has_wiki")
    private boolean hasWiki;       //if "False" wiki tab remove ho jayega
    @JsonProperty("auto_init")
    private boolean autoInit;          //create repo with README.md file
    @JsonProperty("gitignore_template")
    private String gitignoreTemplate; // add .gitignore filr add hojegi per auto_init TRUE hona chaiye "java,python,node,android...etc"
   @JsonProperty("license_template")
    private String licenseTemplate;   //add LICENSE file with mit, apache-2.0, unlicense

}
