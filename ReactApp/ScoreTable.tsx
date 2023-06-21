import React from 'react';
import {
  StyleSheet,
  Text,
  View
} from 'react-native';
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
      <View style={styles.container}>
        <View style={styles.row}>
          <Text style={styles.columnHeader}>Players</Text>
          <Text style={styles.columnHeader}>Score</Text>
        </View>
        {players.map((player: string, index: number) => (
          <View style={styles.row} key={index}>
            <Text style={styles.column}>{player}</Text>
            <Text style={styles.column}>{scores[index]}</Text>
          </View>
        ))}
      </View>
    </Section>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignSelf: 'stretch',
    marginTop: 20,
    marginHorizontal: 10,
    borderWidth: 1,
    borderColor: 'black',
    borderRadius: 5,
    padding: 10,
    width: 300,
  },
  row: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 10,
    width: 300,
  },
  columnHeader: {
    flex: 1,
    fontWeight: 'bold',
    width: '50%',
  },
  column: {
    flex: 1,
    width: '50%',
  },
});

export default ScoreTable;
