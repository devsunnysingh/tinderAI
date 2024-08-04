package dev.sunnysingh.tinderAI.conversations;

import java.time.LocalDateTime;

public record ChatMessage(
        String messageText,
        String authorId,
        LocalDateTime createdAt
) {
}
