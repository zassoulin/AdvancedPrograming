package com.advancedprograming.gameclient.model;

public interface DictionaryServerCommunicator {
    String runClientQueryRequest(String query);
    String runClientChallengeRequest(String query);
}
