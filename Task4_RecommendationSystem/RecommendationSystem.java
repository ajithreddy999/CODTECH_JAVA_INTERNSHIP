import java.util.*;

public class RecommendationSystem {

    private static final Map<String, List<String>> userPreferences = new HashMap<>();

   static {
    userPreferences.put("Ajith", Arrays.asList("Ala Vaikunthapurramuloo", "Pushpa", "Arya", "S/O Satyamurthy"));
    userPreferences.put("Divya", Arrays.asList("Hi Nanna", "Geetha Govindam", "Premam", "Majili"));
    userPreferences.put("Nikhil", Arrays.asList("Karthikeya", "Spy", "Ekkadiki Pothavu Chinnavada", "18 Pages"));
    userPreferences.put("Ravi", Arrays.asList("RRR", "Baahubali", "Magadheera", "Eega"));
}


    public static List<String> recommendMovies(String user) {
        List<String> currentUserLikes = userPreferences.get(user);
        if (currentUserLikes == null) {
            System.out.println("User not found!");
            return Collections.emptyList();
        }

        Map<String, Integer> similarityScores = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : userPreferences.entrySet()) {
            String otherUser = entry.getKey();
            if (otherUser.equals(user)) continue;

            List<String> otherLikes = entry.getValue();
            int common = 0;

            for (String movie : otherLikes) {
                if (currentUserLikes.contains(movie)) {
                    common++;
                }
            }
            similarityScores.put(otherUser, common);
        }

        String bestMatch = similarityScores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (bestMatch == null) return Collections.emptyList();

        List<String> recommendations = new ArrayList<>();
        for (String movie : userPreferences.get(bestMatch)) {
            if (!currentUserLikes.contains(movie)) {
                recommendations.add(movie);
            }
        }

        return recommendations;
    }

    public static void main(String[] args) {
        String user = "Ajith";
        System.out.println("User: " + user);
        System.out.println("Liked Movies: " + userPreferences.get(user));

        List<String> recommendations = recommendMovies(user);
        System.out.println("\nRecommended Movies: " + recommendations);
    }
}
