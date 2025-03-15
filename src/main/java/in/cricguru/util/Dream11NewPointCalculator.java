package in.cricguru.util;

import in.cricguru.dto.StatsDto;

public class Dream11NewPointCalculator {

    public static int calculatePoints(StatsDto statsDto, String role) {
        int points = 4;

        // Batting Points
        points += calculateBattingPoints(statsDto, role);

        // Bowling Points
        points += calculateBowlingPoints(statsDto);

        // Fielding Points
        points += calculateFieldingPoints(statsDto);

        return points;
    }

    private static int calculateBattingPoints(StatsDto statsDto, String role) {
        int points = 0;
        points += null != statsDto.getRunsScored() ? statsDto.getRunsScored() : 0;
        points += null != statsDto.getFours() ? statsDto.getFours() * 4 : 0;
        points += null != statsDto.getSixes() ? statsDto.getSixes() * 6 : 0;

        if (null != statsDto.getRunsScored() && statsDto.getRunsScored() >= 25) {
            if (statsDto.getRunsScored() < 50) {
                points += 4;
            } else if (statsDto.getRunsScored() < 75) {
                points += 8;
            } else if (statsDto.getRunsScored() < 100) {
                points += 12;
            } else {
                points += 16;
            }
        }

        if (!"Bowler".equalsIgnoreCase(role)) {
            if (null != statsDto.getRunsScored() && statsDto.getRunsScored() == 0) {
                points -= 2;
            } else if (null != statsDto.getRunsScored() && statsDto.getRunsScored() > 0 && null != statsDto.getBallFaced() && statsDto.getBallFaced() >= 10) {
                if (statsDto.getStrikeRate() > 170) points += 6;
                else if (statsDto.getStrikeRate() > 150) points += 4;
                else if (statsDto.getStrikeRate() >= 130) points += 2;
                else if (statsDto.getStrikeRate() >= 60 && statsDto.getStrikeRate() <= 70) points -= 2;
                else if (statsDto.getStrikeRate() >= 50 && statsDto.getStrikeRate() < 60) points -= 4;
                else if (statsDto.getStrikeRate() < 50) points -= 6;
            }


        }
        return points;
    }

    private static int calculateBowlingPoints(StatsDto statsDto) {
        int points = 0;
        points += null != statsDto.getTotalWickets() ? statsDto.getTotalWickets() * 25 : 0;
        if (null != statsDto.getBowledLbw() && statsDto.getBowledLbw() > 0) {
            points += statsDto.getBowledLbw() * 8;
        }

        if (null != statsDto.getTotalWickets() && statsDto.getTotalWickets() == 3) {
            points += 4;
        }
        if (null != statsDto.getTotalWickets() && statsDto.getTotalWickets() == 4) {
            points += 8;
        }
        if (null != statsDto.getTotalWickets() && statsDto.getTotalWickets() >= 5) {
            points += 16;
        }

        points += null != statsDto.getMaiden() ? statsDto.getMaiden() * 12 : 0;
        points += null != statsDto.getDots() ? statsDto.getDots() : 0;

        if (null != statsDto.getOvers() && statsDto.getOvers() >= 2) {
            if (statsDto.getEconomyRate() <= 5) points += 6;
            else if (statsDto.getEconomyRate() >= 5.01 && statsDto.getEconomyRate() <= 6) points += 4;
            else if (statsDto.getEconomyRate() >= 6.01 && statsDto.getEconomyRate() <= 7) points += 2;
            else if (statsDto.getEconomyRate() >= 10.01 && statsDto.getEconomyRate() <= 11) points -= 2;
            else if (statsDto.getEconomyRate() >= 11.01 && statsDto.getEconomyRate() <= 12) points -= 4;
            else if (statsDto.getEconomyRate() > 12.01) points -= 6;
        }

        if (null != statsDto.getOvers() && statsDto.getOvers() >= 2) {
            if (statsDto.getEconomyRate() < 5) points += 6;
            else if (statsDto.getEconomyRate() >= 5 && statsDto.getEconomyRate() < 6) points += 4;
            else if (statsDto.getEconomyRate() >= 6 && statsDto.getEconomyRate() <= 7) points += 2;
            else if (statsDto.getEconomyRate() >= 10 && statsDto.getEconomyRate() <= 11) points -= 2;
            else if (statsDto.getEconomyRate() > 11 && statsDto.getEconomyRate() <= 12) points -= 4;
            else if (statsDto.getEconomyRate() > 12) points -= 6;
        }
        return points;
    }

    private static int calculateFieldingPoints(StatsDto statsDto) {
        int points = 0;
        points += null != statsDto.getCatchTaken() ? statsDto.getCatchTaken() * 8 : 0;
        if (null != statsDto.getCatchTaken() && statsDto.getCatchTaken() >= 3) points += 4;
        points += null != statsDto.getStumping() ? statsDto.getStumping() * 12 : 0;
        points += null != statsDto.getDirectRunout() ? statsDto.getDirectRunout() * 12 : 0;
        points += null != statsDto.getInDirectRunout() ? statsDto.getInDirectRunout() * 6 : 0;
        return points;
    }
}
