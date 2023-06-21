import React from 'react';
import {
  Dimensions,
  StyleSheet,
  Text
} from 'react-native';
import { Col, Grid } from 'react-native-easy-grid';
import Section from './Section';


const ScoreTable = (props: { response: string; }) => {

  // The response should be a json in this format:
  //{
  //  "players": ["Avi", "Shimon", "Gaydamat"],
  //  "scores": [42, 69, 4206969]
  //}
  const { response } = props

  let section = "Retarded Score Table"
  let players = ["Rave"]
  let scores = [0]

  try {
    const data = JSON.parse(response);

    const tmp_section = "Score Table"
    const tmp_players = data.players
    const tmp_scores = data.scores

    section = tmp_section
    players = tmp_players
    scores = tmp_scores
  } catch (error) {
    console.error(error)
  }

  return (
    <Section title={section}>
      <Grid style={styles.grid}>
        <Col style={styles.column}>
          <Text style={styles.textHeader}>Players</Text>
          {players.map((player: string, index: number) => (
            <Text style={styles.text} key={index}>{player}</Text>
          ))}
        </Col>
        <Col style={styles.column}>
          <Text style={styles.textHeader}>Scores</Text>
          {scores.map((score: number, index: number) => (
            <Text style={styles.text} key={index}>{score}</Text>
          ))}
        </Col>
      </Grid>
    </Section>
  );
};

const styles = StyleSheet.create({
  grid: {
    flex: 1,
    width: Dimensions.get('window').width,
  },
  column: {
    flex: 1,
  },
  textHeader: {
    flex: 1,
    fontWeight: 'bold',
    marginTop: 8,
    fontSize: 18,
  },
  text: {
    flex: 1,
    marginTop: 8,
    fontSize: 18,
    fontWeight: '200',
  },
});

export default ScoreTable;
