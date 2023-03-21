package org.example.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@JsonAutoDetect
public class Topic {
    @JsonSerialize(as = String.class)
    @JsonDeserialize(as = String.class)
    private String Name;
    @JsonSerialize(as = String.class)
    @JsonDeserialize(as = String.class)
    private String Creator;

    @JsonSerialize(contentAs = Vote.class)
    @JsonDeserialize(contentAs = Vote.class)
    private ArrayList<Vote> Votes;

    public Topic() {}
    public Topic(String name, String creator) {
        Creator = creator;
        Name = name;
        Votes = new ArrayList<>();
    }
    @JsonIgnore
    public String getName() {
        return Name;
    }
    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    public void createVote(String name, String description, ArrayList<String> answerOptions, String creator) {
        for (var vote: Votes)
            if (Objects.equals(vote.getName(), name))
                throw new RuntimeException("Голосование с указанным именем уже существует");
        Votes.add(new Vote(name, description, answerOptions, creator));
    }
    public void deleteVote(String name, String currentUserName) {
        for (var vote: Votes) {
            if (!Objects.equals(vote.getName(), name)) continue;
            if (!Objects.equals(currentUserName, vote.getCreator()))
                throw new RuntimeException("Нет привилегий для удаления голосования");
            Votes.remove(vote);
            return;
        }
    }

    public void vote(String voteName, int answerOption, String userName) {
        for (var vote: Votes) {
            if (!Objects.equals(vote.getName(), voteName)) continue;
            vote.voteByUser(answerOption, userName);
        }
    }
    @JsonIgnore
    public int getVoteCount() {
        return Votes.size();
    }
    @JsonIgnore
    public List<String> getVoteNameList() {
        return Votes.stream().map(Vote::getName).collect(Collectors.toList());
    }

    public Vote getVote(String voteName) {
        return Votes.stream().filter(x -> x.getName().equals(voteName)).findAny().orElse(null);
    }
}
