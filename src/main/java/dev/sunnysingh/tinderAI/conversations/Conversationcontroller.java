package dev.sunnysingh.tinderAI.conversations;

import dev.sunnysingh.tinderAI.profile.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class Conversationcontroller {
    private ConversationRepository conversationRepository;
    private ProfileRepository profileRepository;


    public Conversationcontroller(ConversationRepository conversationRepository, ProfileRepository profileRepository){
        this.conversationRepository=conversationRepository;
        this.profileRepository=profileRepository;
    }
    @GetMapping("/conversations/{conversationId}")
    public Conversation getConversationById(@PathVariable String conversationId){
        Conversation conversation= conversationRepository.findById(conversationId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Unable to find the conversation with ID-->"+conversationId));

        return conversation;
    }



    @PostMapping("/conversations")
    public Conversation createNewConversation(@RequestBody CreateConversationRequest request ){
        profileRepository.findById(request.profileId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Conversation conversation=new Conversation(
                UUID.randomUUID().toString(),
                request.profileId(),
                new ArrayList<>()
        );

        conversationRepository.save(conversation);
        return conversation;
    }
    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId, @RequestBody ChatMessage chatMessage){
        Conversation conversation= conversationRepository.findById(conversationId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Unable to find the conversation with ID-->"+conversationId));
        profileRepository.findById(chatMessage.authorId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"UNable to find Profile ID:"+chatMessage.authorId()));
//        todo: Need to validatye that the author of a message happens to be only the one associated with the message user
        ChatMessage chatMessageWithTime=new ChatMessage(
                chatMessage.messageText(),
                chatMessage.authorId(),
                LocalDateTime.now()
        );
        conversation.messages().add(chatMessageWithTime);
        conversationRepository.save(conversation);
        return conversation;

    }
    public record CreateConversationRequest(
            String profileId
    ){}
}
