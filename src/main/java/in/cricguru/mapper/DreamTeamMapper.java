package in.cricguru.mapper;

import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.MatchBetweenResponse;
import in.cricguru.response.PlayerPerformanceResponse;
import in.cricguru.response.PlayerPerformanceResponse.MatchPerformance;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

@Component
public class DreamTeamMapper {



    public List<DreamTeamResponse> mapToDreamTeamResponse(List<Object[]> result) {
        List<DreamTeamResponse> dreamTeamResponses = new ArrayList<>();
        for (Object[] row : result) {
            DreamTeamResponse response = new DreamTeamResponse();
            response.setPlayerId((Integer) row[0]);
            response.setPlayerImgUrl((String) row[1]);
            response.setPlayerNickName((String) row[2]);
            response.setPlayerRole((String) row[3]);
            response.setTeam1((String) row[4]);
            response.setTeam2((String) row[5]);
            response.setDream11OldPoints((Integer) row[6]);
            response.setMy11CirclePoints((Integer) row[7]);
            response.setDream11NewPoints((Integer) row[8]);
            dreamTeamResponses.add(response);
        }
        return dreamTeamResponses;
    }


    public List<MatchBetweenResponse> mapToMatchBetweenResponse(List<Object[]> result) {
        List<MatchBetweenResponse> matchBetweenResponses = new ArrayList<>();
        for (Object[] row : result) {
            MatchBetweenResponse response = new MatchBetweenResponse();
            response.setTeam1Name((String) row[0]);
            response.setTeam2Name((String) row[1]);
            response.setMatchDate(row[2].toString());
            matchBetweenResponses.add(response);
        }
        return matchBetweenResponses;
    }

    public List<DreamTeamResponse> mapPerformanceDataToDreamTeam(List<PlayerPerformanceResponse> performanceData) {
        // First, group players by role
        Map<String, List<PlayerPerformanceResponse>> playersByRole = performanceData.stream()
                .filter(player -> player.getLastMatchPoints() != null)
                .collect(Collectors.groupingBy(PlayerPerformanceResponse::getRole));

        // Initialize result list
        List<DreamTeamResponse> dreamTeam = new ArrayList<>();

        // Required roles and their minimum counts
        Map<String, Integer> requiredRoles = new HashMap<>();
        requiredRoles.put("Wicket Keeper", 1);
        requiredRoles.put("Batter", 1);
        requiredRoles.put("All Rounder", 1);
        requiredRoles.put("Bowler", 1);

        // First, ensure minimum requirements are met
        for (Map.Entry<String, Integer> roleEntry : requiredRoles.entrySet()) {
            String role = roleEntry.getKey();
            int minRequired = roleEntry.getValue();

            List<PlayerPerformanceResponse> playersInRole = playersByRole.getOrDefault(role, new ArrayList<>());
            playersInRole.sort((p1, p2) -> p2.getLastMatchPoints().compareTo(p1.getLastMatchPoints()));

            // Add minimum required players for this role
            for (int i = 0; i < minRequired && i < playersInRole.size(); i++) {
                dreamTeam.add(createDreamTeamResponse(playersInRole.get(i)));
            }
        }

        // Then fill remaining slots with best performing players from any role
        List<PlayerPerformanceResponse> remainingPlayers = performanceData.stream()
                .filter(player -> player.getLastMatchPoints() != null)
                .filter(player -> !dreamTeam.stream()
                        .map(DreamTeamResponse::getPlayerId)
                        .collect(Collectors.toSet())
                        .contains(player.getPlayerId()))
                .sorted((p1, p2) -> p2.getLastMatchPoints().compareTo(p1.getLastMatchPoints()))
                .collect(Collectors.toList());

        // Fill remaining slots up to maximum of 11 players
        for (PlayerPerformanceResponse player : remainingPlayers) {
            if (dreamTeam.size() >= 11) break;

            // Ensure no more than 8 players from a single category
            long countInRole = dreamTeam.stream()
                    .filter(dreamPlayer -> dreamPlayer.getPlayerRole().equals(player.getRole()))
                    .count();

            if (countInRole < 8) {
                dreamTeam.add(createDreamTeamResponse(player));
            }
        }

        return dreamTeam;
    }

    private DreamTeamResponse createDreamTeamResponse(PlayerPerformanceResponse player) {
        DreamTeamResponse response = new DreamTeamResponse();
        response.setPlayerId(player.getPlayerId());
        response.setPlayerImgUrl(player.getPlayerImageUrl());
        response.setPlayerNickName(player.getPlayerName());
        response.setPlayerRole(player.getRole());
        
        // Get team logos from the last match
        if (!player.getRecentMatches().isEmpty()) {
            MatchPerformance lastMatch = player.getRecentMatches().get(0);
            response.setTeam1(lastMatch.getTeam1Logo());
            response.setTeam2(lastMatch.getTeam2Logo());
        }
        
        // Set points
        response.setDream11OldPoints(player.getLastMatchPoints());
        response.setMy11CirclePoints(player.getLastMatchPoints());
        response.setDream11NewPoints(player.getLastMatchPoints());
        
        return response;
    }
}
