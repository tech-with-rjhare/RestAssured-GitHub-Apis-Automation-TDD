package endpoints;

import config.ConfigManager;

public class Endpoints {

    public static final String GET_USER_REPOS = "/users/{username}/repos";
    public static final String GET_USER_PRIVATE_REPOS = "/user/repos";
    public static final String GET_TECH_WITH_RJHARE_REPOS = "/users/" + ConfigManager.getValue("owner_name2") +
            "/repos";
    public static final String GET_REPO_BY_NAME = "/repos/{owner}/{repo}";
    public static final String CREATE_REPO = "/user/repos";
    public static final String DELETE_REPO = "/repos/{owner}/{repo}";
    public static final String DELETE_REPO_BY_NAME = "/repos/"+ConfigManager.getValue("owner_name")+"/{repo}";
    public static final String UPDATE_REPO = "/repos/{owner}/{repo}";
}
