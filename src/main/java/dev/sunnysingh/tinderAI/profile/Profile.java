package dev.sunnysingh.tinderAI.profile;

public record Profile(
        String id,
        String firstName,
        String lastName,
        int age,
        String ethnicity,
        Gender gender,
        String bio,
        String imgUrl,
        String myersGregPersonalityType
) {
}
