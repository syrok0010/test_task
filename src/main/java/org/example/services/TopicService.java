package org.example.services;

import org.example.models.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TopicService {
    public static TopicService Singleton = new TopicService();
    private ArrayList<Topic> Topics = new ArrayList<>();
    public void add(Topic topic) {
        var existingTopic = Topics.stream().filter(x -> Objects.equals(x.getName(), topic.getName())).findAny().orElse(null);
        if (existingTopic != null) throw new RuntimeException("Топик уже существует");
        Topics.add(topic);
    }
    public void addVote(String topicName, String name, String description, ArrayList<String> answerOptions, String creator) {
        var topic = getTopic(topicName);
        topic.createVote(name, description, answerOptions, creator);
    }

    public void deleteVote(String topicName, String voteName, String username) {
        var topic = getTopic(topicName);
        topic.deleteVote(voteName, username);
    }

    public String listAllVotesInTopic(String topicName) {
        var topic = getTopic(topicName);
        var strBuilder = new StringBuilder();
        strBuilder.append(topicName);
        strBuilder.append("(votes in topic=");
        strBuilder.append(topic.getVoteCount());
        strBuilder.append(")\n");
        for (var vote: topic.getVoteNameList()) strBuilder.append(vote).append("\n");
        return strBuilder.toString();
    }

    public String getVoteView(String topicName, String voteName) {
        var vote = getTopic(topicName).getVote(voteName);
        if (vote == null) return "Голосование не существует";
        return vote.getDescription() + "\n" + vote.getVoteResults();
    }

    private Topic getTopic(String topicName) {
        var topic = Topics.stream().filter(x -> Objects.equals(x.getName(), topicName)).findAny().orElse(null);
        if (topic == null) throw new RuntimeException("Топик не существует");
        return topic;
    }

    public String listAllVoteOptions(String topicName, String voteName) {
        var vote = getTopic(topicName).getVote(voteName);
        var str = new StringBuilder();
        var options = vote.getAnswerOptions();
        for (int i = 0; i < options.size(); i++)
            str.append(i).append(" - ").append(options.get(i)).append("\n");
        return str.toString();
    }

    public String vote(String topicName, String voteName, Integer choice, String username) {
        try {
            getTopic(topicName)
                    .getVote(voteName)
                    .voteByUser(choice, username);
            return "Успешно проголосовано";
        }
        catch (RuntimeException e)
        {
            return e.getMessage();
        }
    }

    public Object getAllTopics() {
        return this.Topics;
    }
    public void loadTopics(Topic[] topics) {
        Topics = new ArrayList<>(List.of(topics));
    }
}
