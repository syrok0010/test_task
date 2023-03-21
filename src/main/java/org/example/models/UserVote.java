package org.example.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect
public class UserVote {
    @JsonSerialize(as = String.class)
    @JsonDeserialize(as = String.class)
    private String UserName;
    @JsonDeserialize(as = String.class)
    @JsonSerialize(as = String.class)
    private String ChosenOption;

    public UserVote() { }

    public UserVote(String userName, String chosenOption) {
        UserName = userName;
        ChosenOption = chosenOption;
    }

    public void setChosenOption(String chosenOption) {
        ChosenOption = chosenOption;
    }

    @JsonIgnore
    public String getUserName() {
        return UserName;
    }

    @JsonIgnore
    public String getChosenOption() {
        return ChosenOption;
    }
}
