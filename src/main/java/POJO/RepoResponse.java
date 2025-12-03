package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepoResponse {
        private int id;
        @JsonProperty("node_id")
        private String nodeId;
        private String name;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("private")
        private boolean isPrivate;
        @JsonProperty("permissions")
        private Permissions permissions;
        @JsonProperty("owner")
        private Owner owner;
        private String description;
        private boolean fork;
        private int size;
        @JsonProperty("stargazers_count")
        private int stargazersCount;
        @JsonProperty("watchers_count")
        private int watchersCount;
        private String language;
        @JsonProperty("has_issues")
        private boolean hasIssues;
        @JsonProperty("has_projects")
        private boolean hasProjects;
        @JsonProperty("has_downloads")
        private boolean hasDownloads;
        @JsonProperty("has_wiki")
        private boolean hasWiki;
        @JsonProperty("has_pages")
        private boolean hasPages;
        @JsonProperty("has_discussions")
        private boolean hasDiscussions;
        @JsonProperty("forks_count")
        private int forksCount;
        private String mirrorUrl;
        private boolean archived;
        private boolean disabled;
        private int openIssuesCount;
        //private License license;
        @JsonProperty("allow_forking")
        private boolean allowForking;
        @JsonProperty("is_template")
        private boolean isTemplate;
        @JsonProperty("web_commit_signoff_required")
        private boolean webCommitSignoffRequired;
        // private List<String> topics;
        private String visibility;
        private int forks;
        private int openIssues;
        private int watchers;
        @JsonProperty("default_branch")
        private String defaultBranch;
        @JsonProperty("allow_squash_merge")
        private boolean allowSquashMerge;

        @JsonProperty("allow_merge_commit")
        private boolean allowMergeCommit;

        @JsonProperty("allow_rebase_merge")
        private boolean allowRebaseMerge;

        @JsonProperty("allow_auto_merge")
        private boolean allowAutoMerge;

        @JsonProperty("delete_branch_on_merge")
        private boolean deleteBranchOnMerge;

        @JsonProperty("allow_update_branch")
        private boolean allowUpdateBranch;

        @JsonProperty("use_squash_pr_title_as_default")
        private boolean useSquashPrTitleAsDefault;

        @JsonProperty("squash_merge_commit_message")
        private String squashMergeCommitMessage;

        @JsonProperty("squash_merge_commit_title")
        private String squashMergeCommitTitle;

        @JsonProperty("merge_commit_message")
        private String mergeCommitMessage;

        @JsonProperty("merge_commit_title")
        private String mergeCommitTitle;

        @JsonProperty("network_count")
        private int networkCount;

        @JsonProperty("subscribers_count")
        private int subscribersCount;


}
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Owner {
    private String login;
    private Integer id;
    @JsonProperty("node_id")
    private String nodeId;
    private String type;
    @JsonProperty("user_view_type")
    private String userViewType;
    @JsonProperty("site_admin")
    private boolean siteAdmin;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("gravatar_id")
    private String gravatarId;
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Permissions {
    private boolean admin;
    private boolean maintain;
    private boolean push;
    private boolean triage;
    private boolean pull;
}
