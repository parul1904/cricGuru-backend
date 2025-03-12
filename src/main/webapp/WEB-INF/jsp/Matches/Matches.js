import React from "react";
import { useNavigate } from "react-router-dom";
import matchData from "./Matches.json";
import "./Matches.css";

const MatchSchedule = () => {
  const navigate = useNavigate();

  const handleMatchCentre = () => {
    navigate("/matchcentre")
  };

  return (
    <div className="match-container">
      {matchData.map((match, index) => (
        <div key={match.id} className="match-card">
          <div className="match-header">
            <div>
              <span className="match-number">MATCH {index + 1}</span>
              <span className="match-date">{match.date}</span>
            </div>

            <div className="match-info">
              <span>
                🕒 {match.time} | 📍 {match.stadium}
              </span>
            </div>
          </div>

          <div className="teams-container">
            <div className="team">
              <img
                src={require(`../../assets/teamLogo/${match.team1.logo}`)}
                alt={match.team1.name}
              />
              <span>{match.team1.name}</span>
            </div>
            <span className="vs-text">VS</span>
            <div className="team">
              <img
                src={require(`../../assets/teamLogo/${match.team2.logo}`)}
                alt={match.team2.name}
              />
              <span>{match.team2.name}</span>
            </div>
          </div>

          <div className="matchesFooter">
            {match.result ? (
              <div className="matchResult">
                <p>{match.result}</p>
                <div className="impactPlayer">
                  <img src="" alt="impactPlayer" />
                  <p>Impact Player</p>
                </div>
                <div className="matchPlayer">
                  <img src="" alt="playerOftheMatch" />
                  <p>Player of the Match</p>
                </div>
              </div>
            ) : (
              <div
                className="match-centre-btn"
                onClick={handleMatchCentre}
              >
                Match Centre
              </div>
            )}
          </div>
        </div>
      ))}
    </div>
  );
};

export default MatchSchedule;
