package in.cricguru.mapper;

import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.MatchBetweenResponse;
import in.cricguru.response.PlayerPerformanceResponse;
import in.cricguru.response.PlayerPerformanceResponse.MatchPerformance;

import in.cricguru.response.PlayerSelectionResponse;
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

    public List<PlayerSelectionResponse> mapToPlayerSelectionResponse(List<Object[]> dbResults) {
        List<PlayerSelectionResponse> captains = new ArrayList<>();
        List<PlayerSelectionResponse> viceCaptains = new ArrayList<>();
        List<PlayerSelectionResponse> otherPlayers = new ArrayList<>();
        
        // Get the first element which contains all players
        if (dbResults == null || dbResults.isEmpty()) {
            return new ArrayList<>();
        }
        
        Object[] playersList = dbResults.get(0);
        
        // Iterate over each player in the list
        for (Object playerData : playersList) {
            try {
                Object[] result;
                if (playerData instanceof Object[]) {
                    result = (Object[]) playerData;
                } else {
                    continue;
                }
                
                PlayerSelectionResponse response = new PlayerSelectionResponse();
                
                // Basic player info
                response.setPlayerId(safelyConvertToInteger(result[0]));
                response.setPlayerNickName(safelyConvertToString(result[1]));
                response.setPlayerImgUrl(safelyConvertToString(result[2]));
                response.setPlayerRole(safelyConvertToString(result[3]));
                
                // Captain/Vice-Captain status
                Boolean isCaptain = safelyConvertToBoolean(result[4]);
                Boolean isViceCaptain = safelyConvertToBoolean(result[5]);
                Boolean isPlaying15 = safelyConvertToBoolean(result[6]);
                
                response.setIsCaptain(isCaptain);
                response.setIsViceCaptain(isViceCaptain);
                response.setPlaying15(isPlaying15);
                
                // Add to appropriate list
                if (Boolean.TRUE.equals(isCaptain)) {
                    captains.add(response);
                } else if (Boolean.TRUE.equals(isViceCaptain)) {
                    viceCaptains.add(response);
                } else {
                    otherPlayers.add(response);
                }
                
            } catch (Exception e) {
                System.err.println("Error mapping player: " + e.getMessage() + 
                                 ". Data: " + java.util.Arrays.toString((Object[]) playerData));
            }
        }
        
        // Combine all lists, with captains first, then vice-captains, then others
        List<PlayerSelectionResponse> allPlayers = new ArrayList<>();
        allPlayers.addAll(captains);
        allPlayers.addAll(viceCaptains);
        allPlayers.addAll(otherPlayers);
        
        return allPlayers;
    }

    private Integer safelyConvertToInteger(Object value) {
        if (value == null) return null;
        if (value instanceof Integer) return (Integer) value;
        if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private String safelyConvertToString(Object value) {
        return value != null ? value.toString() : null;
    }

    private Boolean safelyConvertToBoolean(Object value) {
        if (value == null) return false;
        if (value instanceof Boolean) return (Boolean) value;
        if (value.toString().equalsIgnoreCase("true")) return true;
        if (value.toString().equalsIgnoreCase("1")) return true;
        return false;
    }
}
