package dev.sunnysingh.tinderAI.conversations;

import dev.sunnysingh.tinderAI.profile.Profile;

import java.util.List;

public record Conversation(
        String id,
        String profileId,
        List<ChatMessage> messages


) {
}
