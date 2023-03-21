package org.example.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Objects;

@JsonAutoDetect
public class Vote {
    @JsonSerialize(as = String.class)
    @JsonDeserialize(as = String.class)
    private String Name;
    @JsonSerialize(as = String.class)
    @JsonDeserialize(as = String.class)
    private String Description;
    @JsonSerialize(contentAs = String.class)
    @JsonDeserialize(contentAs = String.class)
    private ArrayList<String> AnswerOptions;
    @JsonSerialize(contentAs = Integer.class)
    @JsonDeserialize(contentAs = Integer.class)
    private ArrayList<Integer> AnswerOptionsSelectedNumber;
    @JsonSerialize(contentAs = UserVote.class)
    @JsonDeserialize(contentAs = UserVote.class)
    private final ArrayList<UserVote> UserVotes = new ArrayList<>();
    @JsonSerialize(as = String.class)
    @JsonDeserialize(as = String.class)
    private String Creator;

    public Vote() {}
    public Vote(String name, String description, ArrayList<String> answerOptions, String creator) {
        Name = name;
        Description = description;
        AnswerOptions = answerOptions;
        Creator = creator;
        AnswerOptionsSelectedNumber = new ArrayList<>();
        for (int i = 0; i < answerOptions.size(); i++) AnswerOptionsSelectedNumber.add(0);
    }

    @JsonIgnore
    public String getName() {
        return Name;
    }
    @JsonIgnore
    public String getDescription() {
        return Description;
    }
    @JsonIgnore
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public ArrayList<String> getAnswerOptions() {
        return new ArrayList<>(AnswerOptions);
    }

    public void voteByUser(int answerOption, String userName) {
        if (answerOption >= AnswerOptions.size())
            throw new RuntimeException("Выбранный вами вариант ответа не существует");
        for (var userVote: UserVotes) {
            if (!Objects.equals(userVote.getUserName(), userName)) continue;
            for (int i = 0; i < AnswerOptions.size(); i++) {
                if (Objects.equals(userVote.getChosenOption(), AnswerOptions.get(i)))
                    AnswerOptionsSelectedNumber.set(i, AnswerOptionsSelectedNumber.get(i) - 1);
            }
            userVote.setChosenOption(AnswerOptions.get(answerOption));
        }
        UserVotes.add(new UserVote(userName, AnswerOptions.get(answerOption)));
        AnswerOptionsSelectedNumber.set(answerOption, AnswerOptionsSelectedNumber.get(answerOption) + 1);
    }
    @JsonIgnore
    public String getVoteResults() {
        var strBuilder = new StringBuilder();
        ArrayList<String> answerOptions = getAnswerOptions();
        for (int i = 0; i < answerOptions.size(); i++) {
            var opt = answerOptions.get(i);
            strBuilder.append(opt).append(" - ").append(AnswerOptionsSelectedNumber.get(i)).append("\n");
        }
        return strBuilder.toString();
    }
}
